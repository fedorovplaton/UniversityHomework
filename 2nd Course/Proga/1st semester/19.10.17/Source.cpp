#include "Thread_safe_vector.h"

using namespace std;

void prod(thread_safe_vector <int> &vec)
{
	vec.push_back(vec[vec.size() - 1] * vec[vec.size() - 2]);
}

int main()
{
	thread_safe_vector <int> vec(2);
	vec[0] = 1;
	vec[1] = 2;
	thread *t = new thread[10];
	for (unsigned i = 0; i < 10; i++) {
		t[i] = thread(prod, &vec);
	}
	for (unsigned i = 0; i < 10; i++) {
		t[i].join();
	}
	for (unsigned i = 0; i < vec.size(); i++)
		cout << vec[i];
	return 0;
}