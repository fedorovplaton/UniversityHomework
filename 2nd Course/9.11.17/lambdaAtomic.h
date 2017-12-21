#include <thread>
#include <mutex>

template <class T> class LambdaAtomic
{
	T data;
	mutable std::recursive_mutex mutex;

public:
	LambdaAtomic() = delete;
	LambdaAtomic(const T d) : data(d)
	{}
	LambdaAtomic(const LambdaAtomic <T> &other) : data(other.load())
	{}

	~LambdaAtomic() {};

	T load() const 
	{
		mutex.lock();
		T result = data;
		mutex.unlock();
		return result;
	}

	void store(const T d) 
	{
		mutex.lock();
		data = d;
		mutex.unlock();
	}

	T exchange(const T d) 
	{
		mutex.lock();
		T result = data;
		data = d;
		mutex.unlock();
		return result;
	}

	template <typename _return, typename... _Args> _return req(_return(T::*_function) (_Args...), _Args... __Args) 
	{
		mutex.lock();
		_return result = (data.*_function)(__Args...);
		mutex.unlock();
		return result;
	}

	template <typename func, typename... _Args> auto use(func _function, _Args... __Args) -> decltype(_function(data, __Args...)) 
	{
		mutex.lock();
		auto result = _function(data, __Args...);
		mutex.unlock();
		return result;
	}

	LambdaAtomic <T> & operator++ ()
	{
		mutex.lock();
		++data;
		mutex.unlock();
		return *this;
	}	

	LambdaAtomic & operator= (const LambdaAtomic <T> &other)
	{
		mutex.lock();
		data = other.load();
		mutex.unlock();
		return *this;
	}
};