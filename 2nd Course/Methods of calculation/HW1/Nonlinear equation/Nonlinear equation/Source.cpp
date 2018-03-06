#include <iostream>
#include <cmath>

using namespace std;

double f(double x) {                      //Original function
	if ((2 * x + 3) == 0) {
		cout << "Dividing by zero" << endl;
		exit(EXIT_FAILURE);
	}
	return(63*x*x*x*x*x-70*x*x*x+15*x-8/(2*x+3));
}

double f1(double x) {                     //Derivative of function
	if ((2 * x + 3) == 0) {
		cout << "Dividing by zero" << endl;
		exit(EXIT_FAILURE);
	}
	return(315 * x*x*x*x - 210 * x*x + 15 + 16 / ((2 * x + 3)*(2*x+3)));
}

void root(double a, double b, int k) {    //Searching for root in range from a to b
	cout << "Searching for "<<k<<" root in interval from " << a << " to " << b << endl;
	double x1 = a, x2 = b;
	double m = -100;
	int n = 1;
	double i = a;
	while (i <= b) {
		if (f1(i) > m)
			m = f1(i);
		i += 0.0001;
	}
	cout << "Maximum of derivative of function is " << m << endl;
	while (abs(x2 - x1) > 0.000001) {
		x1 = x2;
		x2 = x1 - f(x1) / m;
		cout << f1(x1) << endl;
		cout << "Number of iteration: " << n << endl;
		cout << "Root: " << x2 << endl;
		n++;
	}
	cout << "Final value of root: " << x2 << endl;
}

int main() {
	double a1, b1, a2, b2, a3, b3, a4, b4, i;
	i = -10;

	while (f(i)<0) {
		a1 = i;
		i += 0.01;
	}
	b1 = i;
	cout << "The first root is in interval from " << a1 << " to " << b1 << endl;
	i = -1.499;
	while (f(i) < 0) {
		a2 = i;
		i += 0.001;
	}
	b2 = i;
	cout << "The second root is in interval from " << a2 << " to " << b2 << endl;

	while (f(i)>0) {
		a3 = i;
		i += 0.001;
	}
	b3 = i;
	cout << "The third root is in interval from " << a3 << " to " << b3 << endl;

	while (f(i) < 0) {
		a4 = i;
		i += 0.001;
	}
	b4 = i;
	cout << "The fourth root is in interval from " << a4 << " to " << b4 << endl;
	root(a1, b1, 1);
	root(a2, b2, 2);
	root(a3, b3, 3);
	root(a4, b4, 4);
	return EXIT_SUCCESS;
}

