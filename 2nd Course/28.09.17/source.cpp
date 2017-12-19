#include<iostream>
#include<chrono>
#include<ctime>
#include"threadsafe_queue.h"

using namespace std;

void func(threadsafe_queue <int>& myQueue) {
	myQueue.push(*myQueue.try_pop());
}

int main() {
	threadsafe_queue<int> myQueue;
	thread *t = new thread[10];
	for (int i = 0; i < 10; i++) {
		myQueue.push(i);
	}
	for (int i = 0; i < 10; i++)
		t[i] = thread(func, myQueue);
	for (int i = 0; i < 10; i++) {
		t[i].join();
	}
	return EXIT_SUCCESS;
}