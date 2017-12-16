#include <thread>
#include <mutex>

using namespace std;

template <class T> class Atomic
{
	T data;
	mutex _mutex;

public:
	T load()
	{
		_mutex.lock();
		T result = data;
		_mutex.unlock();
		return result;
	}
	void store(T data)
	{
		_mutex.lock();
		data = data;
		_mutex.unlock();
	}
	T exchange(T data)
	{
		_mutex.lock();
		T result = data;
		data = data;
		_mutex.unlock();
		return result;
	}

	operator T()
	{
		_mutex.lock();
		T result = data;
		_mutex.unlock();
		return result;
	}

	Atomic<T> &operator++()
	{
		_mutex.lock();
		data++;
		_mutex.unlock();
		return *this;
	}
	Atomic<T> &operator++(int)
	{
		_mutex.lock();
		Atomic<T> result(++data);
		_mutex.unlock();
		return *this;
	}

	
	Atomic()
	{
		data = T();
	}
	Atomic(const T &d)
	{
		data = d;
	}
	Atomic(const Atomic <T> &other)
	{
		data = other.data;
	}
	Atomic(Atomic <T> &&other)
	{
		data = other.data;
	}

	

	Atomic &operator= (const Atomic <T> &other)
	{
		_mutex.lock();
		data = other.data;
		_mutex.unlock();
		return *this;
	}
	Atomic &operator= (Atomic <T> &&other)
	{
		_mutex.lock();
		data = other.data;
		_mutex.unlock();
		return *this;
	}

	~Atomic()
	{}
};
