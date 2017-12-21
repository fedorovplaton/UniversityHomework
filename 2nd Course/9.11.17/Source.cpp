#include <iostream>
#include <vector>
#include <atomic>
#include "lambdaAtomic.h"

using namespace std;

struct Incer
{
	int i;
	Incer & operator++ () { i++; return *this; }
	Incer (int _i = 0) : i(_i) {}
};

void _inc(LambdaAtomic <Incer> * ptr, unsigned s)
{
	for (unsigned i = 0; i < s; i++) {
		(*ptr).req(Incer::operator++);
	}
}

int main()
{
	LambdaAtomic <Incer> a(-1); 
	_inc(&a, 1);

	thread* t = new thread[10];
	for (unsigned i = 0; i < 10; i++) {
		t[i] = thread(_inc, &a, 10000);
	}
	for (unsigned i = 0; i < 10; i++) {
		t[i].join();
	}
	return 0;
}