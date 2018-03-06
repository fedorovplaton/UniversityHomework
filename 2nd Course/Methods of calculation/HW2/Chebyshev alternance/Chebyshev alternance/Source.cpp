#include <iostream>
#include <cmath>

using namespace std;

double f(double x) {
	return pow(x, 7);
}

double p(double x) {
	return 1.75*x*x*x*x*x - 0.875*pow(x, 3) + 0.109375*x;
}

double g(double x) {
	return (x*x*x*x*x*x*x - 1.75*x*x*x*x*x + 0.875*pow(x,3) - 0.109375*x);
}

double g1(double x) {
	return (7 *x*x*x*x*x*x - 8.75*x*x*x*x + 2.625*x*x - 0.109375);
}

double g11(double x) {
	return (42 * x*x*x*x*x - 35 * x*x*x + 5.25*x);
}

int main() {
	double x[8] = { 0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0 };
	double x0 = 0.0, x1 = 1.0;
	while (abs(x1-x0)>0.000001){
		x0 = x1;
		x1 = x0 - g1(x0) / g11(x0);
	}
	double x2 = 0.7;
	x0 = 0.6;
	while (abs(x2 - x0) > 0.000001) {
		x0 = x2;
		x2 = x0 - g1(x0) / g11(x0);
	}

	double x3 = 0.3;
	x0 = 0;
	while (abs(x3 - x0) > 0.000001) {
		x0 = x3;
		x3 = x0 - g1(x0) / g11(x0);
	}
	
	double x4 = -0.3;
	x0 = 0;
	while (abs(x4 - x0) > 0.000001) {
		x0 = x4;
		x4 = x0 - g1(x0) / g11(x0);
	}

	double x5 = -0.7;
	x0 = 0;
	while (abs(x5 - x0) > 0.000001) {
		x0 = x5;
		x5 = x0 - g1(x0) / g11(x0);
	}

	double x6 = -1;
	x0 = 0;
	while (abs(x6 - x0) > 0.000001) {
		x0 = x6;
		x6 = x0 - g1(x0) / g11(x0);
	}
	cout << "Alternance point " << 1 <<" Deviation " <<abs(p(1)-f(1)) << endl;
	cout << "Alternance point " << x1 <<" Deviation "<< abs(p(x1)-f(x1)) << endl;
	cout << "Alternance point " << x2 <<" Deviation "<< abs(p(x2)-f(x2)) << endl;
	cout << "Alternance point " << x3 << " Deviation "<< abs(p(x3)- f(x3)) << endl;
	cout << "Alternance point " << x4 << " Deviation " << abs(p(x4)- f(x4)) << endl;
	cout << "Alternance point " << x5 << " Deviation "<< abs(p(x5)-f(x5)) << endl;
	cout << "Alternance point " << x6 << " Deviation " <<abs(p(x6)-f(x6)) << endl;
	cout << "Alternance point " << -1 << " Deviation " << abs(p(-1) - f(-1)) << endl;

}