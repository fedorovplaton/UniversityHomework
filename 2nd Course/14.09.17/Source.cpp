#include <iostream>

#include <thread>
#include <vector>
#include <algorithm>

#include <functional>

using namespace std;

void sortArr(vector <int> &arr)
{
	sort(arr.begin(), arr.end());
}

void print(vector < vector <int> > v)
{
	for (unsigned i = 0; i < v.size(); i++) {
		for (unsigned j = 0; j < v[i].size(); j++) {
			cout << v[i][j] << " ";
		}
		cout << endl;
	}
}

int main()
{
	int a = 0;

	vector < vector <int> > arr;

	for (int i = 0; i < 5; i++) {
		vector <int> temp;
		for (int j = 0; j < 5; j++) {
			cin >> a;
			temp.push_back(a); 
		}
		arr.push_back(temp); 
	}
	cout << endl;

	for (int i = 0; i < 5; i++) {
		thread t(sortArr, ref(arr[i]));
		t.join();
	}

	cout << endl;
	print(arr);

	return 0;
}
