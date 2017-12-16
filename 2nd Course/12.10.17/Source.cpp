#include <iostream>
#include <thread>
#include <vector>
#include <atomic>
#include "Header.h"

using namespace std;

void inc(Atomic<int>* ptr, unsigned s)
{
	for (unsigned i = 0; i < s; i++) {
		(*ptr)++;
	}
}

int main()
{
	unsigned threadsAmount = 100;
	Atomic <int> a(2);
	inc(&a, 1);

	thread* arr = new thread[threadsAmount];
	for (unsigned i = 0; i < threadsAmount; i++) {
		arr[i] = thread(inc, &a, 100);
		cout << a << endl;
	}
	for (unsigned i = 0; i < threadsAmount; i++) {
		arr[i].join();
	}
	cout << a.exchange(0) << endl;

	cout << a.load() << endl;
	delete[] arr;
	return 0;
}
