#include <iostream>
#include <cmath>

using namespace std;

double f(double x) {
	if (x <= -1)
		return EXIT_FAILURE;
	return(1 / sqrt(1 + x));
}

double f1(double x) {
	if (x <= 1)
		return EXIT_FAILURE;
	return((-1) / (2 * pow(1 + x, 1.5)));
}

double g(double x) {
	return (x*x*x+3*x*x+3*x+1-0.3431457505);
}

double g1(double x) {
	return (3 * x*x + 6 * x + 3);
}

int main() {
	double k1 = f(1) - f(0), b1 = f(0);
	cout << "l1(x)=" << k1 << "*x+" << b1 << endl;
	double k2 = k1, b2 = 0.0, x0 = 0.0;
	x0 = 0.42836913892514;
	b2 = ((sqrt(2)-2)/2)*0.42836913892514 +1/(sqrt(1+ 0.42836913892514));
	cout << "l2(x)=" << k2 << "*x+" << b2 << endl;
	cout << "P(x)=" << (k1 + k2) / 2 << "*x+" << (b1 + b2) / 2 << endl;
	cout << "Chebyshev alternance points: (0," << f(0) << "); (" << x0 << "," << f(x0) << "); (1," << f(1) << ")." << endl;	
	cout << " Deviation " << abs(f(0) - (k1 * 0 + (b1 + b2) / 2)) << "; " << abs(f(x0) - (k1 * x0 + (b1 + b2) / 2)) << "; " << abs(f(1) - (k1 * 1 + (b1 + b2) / 2)) << endl;
	return EXIT_SUCCESS;
}