#include <iostream>
#include <cmath>

using namespace std;

double f(double x) {
	return sin(x*x);
}

const double Pi = 3.14159265359;

int main() {
	int n = 309;
	double h = 0.002546479;
	double integral = 0;
	double x = Pi / 4;
	for (int i = 0; i < n; i++) {		
		integral += f(x);
		x += h;
	}
	cout << "The integral is calculated by the formula of left rectangles: " << integral * h << endl;
	integral = Pi / 48 * (f(Pi / 4) + 4 * (f(5 * Pi / 16) + f(7 * Pi / 16)) + 2 * f(3 * Pi / 8) + f(Pi / 2));
	cout << "The integral is calciulated by the Simpson's formula: " << integral << endl;

	integral = 0;
	h = 0.1;
	n = 8;
	x = Pi / 4;
	for (int i = 0; i < n; i++) {
		integral += f(x);
		x += h;
	}
	cout << "The integral is calculated by the formula of left rectangles: " << integral * h << endl;


	integral = 0;
	h = 0.05;
	n = 16;
	x = Pi / 4;
	for (int i = 0; i < n; i++) {
		integral += f(x);
		x += h;
	}
	cout << "The integral is calculated by the formula of left rectangles: " << integral * h << endl;
}