#include<iostream>
#include<thread>
#include<mutex>
#include"header.h"

using namespace std;

void f(int &data) {
	data++;
	cout << data;
}

void f1(threadsafe_list<int> list) {
	list.for_each(f);
}

int main() {
	unsigned threads = 10;
	thread *arr = new thread[threads];
	threadsafe_list <int> list;
	for (unsigned i = 0; i < 5; i++) {
		list.push_front(i);
	}
	for (unsigned i = 0; i < threads; i++) {
		arr[i] = thread(f1,list);
	}
	for (unsigned i = 0; i < threads; i++) {
		arr[i].join();
	}

}
