#include <iostream>
#include <vector>

using namespace std;

#define sz 10

extern void BubbleSort(vector<int>& v);

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
