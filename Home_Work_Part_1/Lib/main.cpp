#include <iostream>
#include <vector>
#include "sort.h"

using namespace std;

#define sz 10



int main() {
	vector<int> v(sz);
	int m=100;
	for(int i = 0; i < sz; i++)
		v[i] = m--;
	BubbleSort(v);
	for(int i = 0; i < sz; i++)
		cout << v[i] << ' ';
	return 0;
}  
