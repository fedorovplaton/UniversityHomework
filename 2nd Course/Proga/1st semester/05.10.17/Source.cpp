#include <iostream>
#include <vector>
#include <thread>
#include <cstdlib>

using namespace std;

void Mergesort(vector <int> &arr, int maxThreads = 0) {
	if (arr.size() == 1) {
	} 
	else if (arr.size() == 2) {
		if (arr[1] < arr[0]) {
			int tmp = arr[1];
			arr[1] = arr[0];
			arr[0] = tmp;
		}
		else {

		}
	}
	else {
		unsigned length = arr.size() / 2;

		vector <int> LeftArr;
		vector <int> RightArr;
		unsigned LeftIndex = 0;
		unsigned RightIndex = 0;
		for (unsigned i = 0; i < arr.size(); i++) {
			if (LeftIndex < LeftArr.size() && (RightIndex >= RightArr.size() || LeftArr[LeftIndex] <= RightArr[RightIndex])) {
				arr[i] = LeftArr[LeftIndex];
				LeftIndex++;
			}
			else if (RightIndex < RightArr.size()) {
				arr[i] = RightArr[RightIndex];
				RightIndex++;
			}
			else {

			}
		}
		for (unsigned i = 0; i < arr.size(); i++) {
			if (i < length) {
				LeftArr.push_back(arr[i]);
			}
			else {
				RightArr.push_back(arr[i]);
			}
		}

		if (maxThreads <= 0) {
			Mergesort(LeftArr, 0);
			Mergesort(RightArr, 0);
		}
		else if (maxThreads == 1) {
			maxThreads -= 1;
			thread LeftThread([](vector <int> *a, int m = 0) {Mergesort(*a, m); }, &LeftArr, 0);
			Mergesort(RightArr, 0);
			LeftThread.join(); 
		}
		else {
			maxThreads -= 2; 
			thread LeftThread([](vector <int> *a, int m = 0) {Mergesort(*a, m); }, &LeftArr, maxThreads / 2);
			thread RightThread([](vector <int> *a, int m = 0) {Mergesort(*a, m); }, &RightArr, maxThreads - (maxThreads / 2));
			LeftThread.join(); 
			RightThread.join();
		}

		
	}
	return;
}

int main()
{
	unsigned vector_size;
	unsigned maxThreads;

	cout << "Enter the number of numbers" << endl;
	cin >> vector_size;

	cout << "Enter the number of threads" << endl;
	cin >> maxThreads;

	vector <int> arr(vector_size);

	cout << "Enter numbers"<<endl;

	for (unsigned i = 0; i < arr.size(); i++) {
		cin>>arr[i];
	}

	Mergesort(arr, maxThreads);

	cout << "Sorted numbers" << endl;
	for (unsigned i = 0; i < arr.size(); i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
	return 0;
}
