#include <iostream>
#include <cmath>

using namespace std;

double f(double x) {
	if (x <= -1)
		return EXIT_FAILURE;
	return(1 / sqrt(1 + x));
}


int main() {
	double k1 = f(1) - f(0);
	double x0 = 0.0;
	double a = 2 * (3 - 2*sqrt(2));
	double b = 1.0 / 3.0;
	x0 = (1/pow(a,b))-1;
	double a0 = ((f(x0) + 1 - k1 * x0)) / 2;
	cout << "P(x)=" << k1 << "*x+" << a0<< endl;
	cout << "Chebyshev alternance points: (0," << f(0) << "); (" << x0 << "," << f(x0) << "); (1," << f(1) << ")." << endl;	
	cout << "Deviation " << abs(f(0) - (k1 * 0 + a0)) << "; " << abs(f(x0) - (k1 * x0 + a0)) << "; " << abs(f(1) - (k1 * 1 + a0)) << endl;
	return EXIT_SUCCESS;
}