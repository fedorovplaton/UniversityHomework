#include <iostream>
#include <cmath>

using namespace std;

double t(double x) {
	return 64 * pow(x, 7) - 112 * pow(x, 5) + 56 * pow(x, 3) - 7 * x;
}

double t1(double x) {
	return 448 * pow(x, 6) - 560 * pow(x, 4) + 168 * pow(x, 2) - 7;
}

double t11(double x) {
	return 2688 * pow(x, 5) - 2240 * pow(x, 3) + 336 * x;
}

int main() {
	double x0, x1, x2, x3, x4, x5, x6,x7;
	x0 = -1;
	x1 = x0 - t(x0) / t1(x0);
	cout << "Roots of chebyshev polynomial: " << endl;
	while (abs(x0 - x1) > 0.000001) {
		x0 = x1;
		x1 = x0 - t(x0) / t1(x0);
	}
	cout << x1 << endl;

	x0 = x1 + 0.1;
	x2= x0 - t(x0) / t1(x0);
	while (abs(x0 - x2) > 0.000001) {
		x0 = x2;
		x2 = x0 - t(x0) / t1(x0);
	}
	cout << x2 << endl;

	x0 = x2 + 0.3;
	x3 = x0 - t(x0) / t1(x0);
	while (abs(x0 - x3) > 0.000001) {
		x0 = x3;
		x3 = x0 - t(x0) / t1(x0);
	}
	cout << x3 << endl;

	x0 = x3 + 0.3;
	x4 = x0 - t(x0) / t1(x0);
	while (abs(x0 - x4) > 0.000001) {
		x0 = x4;
		x4 = x0 - t(x0) / t1(x0);
	}
	cout << x4 << endl;
	x5 = (-1)*x3;
	x6 = (-1)*x2;
	x7 = (-1)*x1;
	cout << x5 << endl << x6 << endl << x7<<endl;

	x0 = -1;
	x1 = x0 - t1(x0) / t11(x0);
	while (abs(x0 - x1) > 0.000001) {
		x0 = x1;
		x1 = x0 - t1(x0) / t11(x0);
	}
	cout <<"Local max of chebyshev polynomial is at the point "<< x1 <<" and equals to 1"<< endl;

	x0 = x1+0.2;
	x2 = x0 - t1(x0) / t11(x0);
	while (abs(x0 - x2) > 0.000001) {
		x0 = x2;
		x2 = x0 - t1(x0) / t11(x0);
	}
	cout << "Local min of chebyshev polynomial is at the point " << x2 << " and equals to -1" << endl;

	x0 = x2+0.3;
	x3 = x0 - t1(x0) / t11(x0);
	while (abs(x0 - x3) > 0.000001) {
		x0 = x3;
		x3 = x0 - t1(x0) / t11(x0);
	}
	cout << "Local max of chebyshev polynomial is at the point " << x3 << " and equals to 1" << endl;
	cout << "Local min of chebyshev polynomial is at the point " << (-1)*x3 << " and equals to -1" << endl;
	cout << "Local max of chebyshev polynomial is at the point " << (-1)*x2 << " and equals to 1" << endl;
	cout << "Local min of chebyshev polynomial is at the point " << (-1)*x1 << " and equals to -1" << endl;
}