#ifndef THREAD_SAFE_VECTOR_H_INCLUDED
#define THREAD_SAFE_VECTOR_H_INCLUDED

#include <iostream>
#include <thread>
#include <mutex>

template <typename T> class thread_safe_vector
{
private:
	T* begin;
	unsigned length;
	unsigned capacity;

	std::recursive_mutex mutex;
	
public:
	thread_safe_vector()
	{
		length = 0;
		capacity = 0;
		begin = nullptr;
	}
	thread_safe_vector(unsigned c)
	{
		length = 0;
		capacity = c;
		begin = new T[c];
	}
	~thread_safe_vector()
	{
		if (begin != nullptr) {
			delete[] begin;
		}
	}

	T* head()
	{
		return begin;
	}
	
	unsigned size()
	{
		return length;
	}
	unsigned cap()
	{
		return capacity;
	}

	T &operator[] (unsigned index)
	{
		mutex.lock();
		if (begin == nullptr) {
			mutex.unlock();
			std::cout << "There is no vector here";
		}
		if (index >= length && index < capacity) {
			length = index + 1;
		}
		mutex.unlock();
		return begin[index];
	}
	
	void push_back(T data)
	{
		mutex.lock();
		if (capacity <= length) {
			reserve(1);
		}
		begin[length] = data;
		length++;
		mutex.unlock();
	}
	void push_before(T data, unsigned index)
	{
		mutex.lock();
		if (index >= length) {
			mutex.unlock();
			std::cout<<"Out of range";
		}
		if (capacity <= length) {
			reserve(1);
		}
		for (unsigned i = length; i > index; i--) {
			begin[i] = begin[i - 1];
		}
		begin[index] = data;
		length++;
		mutex.unlock();
	}
	void push_front(T data)
	{
		push_before(data, 0); 
	}

	T pop(unsigned index)
	{
		mutex.lock();
		if (index >= length) {
			mutex.unlock();
			std::cout<<"Out of range";
		}
		T result = begin[index];
		for (unsigned i = index; i < length; i++) {
			begin[i] = begin[i + 1];
		}
		length--;
		mutex.unlock();
		return result;
	}
	T pop_back()
	{
		return pop(length - 1);
	}
	T pop_front()
	{
		return pop(0);
	}

	void clear()
	{
		mutex.lock();
		length = 0;
		mutex.unlock();
	}
	
	void resize(unsigned c)
	{
		mutex.lock();
		if (c == capacity) {
			mutex.unlock();
			return;
		}
		thread_safe_vector <T> other(c);
		for (unsigned i = 0; i < c && i < length; i++) {
			other[i] = begin[i];
		}
		swap(*this, other);
		mutex.unlock();
	}
	void reserve(unsigned _length)
	{
		mutex.lock();
		resize(capacity + _length);
		mutex.unlock();
	}
	
	thread_safe_vector(const thread_safe_vector <T> &other)
	{
		length = other.size;
		capacity = other.cap;
		begin = new T[capacity];
		for (unsigned i = 0; i < length; i++) {
			begin[i] = other.head[i];
		}
	}
	
	friend void swap(thread_safe_vector <T> &first, thread_safe_vector <T> &second)
	{
		first.mutex.lock();

		unsigned flength = first.size;
		unsigned fCap = first.cap;
		T* fBegin = first.head;

		second.mutex.lock();

		first.size = second.size;
		first.cap = second.cap;
		first.head = second.head;

		first.mutex.unlock();

		second.size = flength;
		second.cap = fCap;
		second.head = fBegin;

		second.mutex.unlock();
	}
};

#endif
