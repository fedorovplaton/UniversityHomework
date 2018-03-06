#include <iostream>
#include <thread>

using namespace std;

void print() {
	cout << "Thread id: " << this_thread::get_id() << endl;
}

int main() {
	for (unsigned i = 0; i < 10; i++) {
		thread t(print);
		t.join();
	}

	return 0;
}
