#include <iostream>
#include "Cmplx_Nmbr.h"
#include "Cmplx_Vctr.h"

using namespace std;

void print(complexNumber a) {
	cout << a.Real();
	double b = a.Imag();
	if (b > 0) {
		cout << " + " << b << "i" << endl;
	}
	else if (b < 0) {
		cout << " - " << -1 * b << "i" << endl;
	}
	else {
		cout << endl;
	}
}

int main()
{
	complexVector v1(5);
	v1.resize(3);
	v1[0] -= 2;
	v1[1].set(2, 2);
	v1[1] = v1[1] + v1[0];
	v1[3] += (v1[0] * v1[2]);
	for (int i = 0; i < 3; ++i)
		print(v1[i]);

	v1.push_back(complexNumber(2, 2));
	v1.push_back(v1[1] - v1[2]);

	for (int i = 0; i < 5; ++i)
		print(v1[i]);

	complexVector v2(22);
	v2 = v1;
	bool a = v1 == v2;
	cout << a << endl;
	v2[v2.getLength() - 1].set(22, 2);
	a = v1 != v2;
	cout << a << endl;
	return 0;
}
