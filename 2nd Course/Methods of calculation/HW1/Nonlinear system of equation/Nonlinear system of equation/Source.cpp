#include <iostream>
#include <cmath>

using namespace std;

double f(double x, double y) {                  //First function
	return(x*x*x - 2.3*x*y*y + 0.043*x*x - 0.063*y + 0.125);
}

double g(double x, double y) {                  //Second function
	return(1.4*x*x*y - y*y*y + 0.027*x*y + 0.032*x - 0.138);
}

double fx(double x, double y) {                 //Derivative of the first function of the first variable
	return(3 * x*x - 2.3*y*y + 0.086*x);
}

double fy(double x, double y) {					//Derivative of the first function of the second variable
	return(-4.6*x*y - 0.063);
}

double gx(double x, double y) {					//Derivative of the second function of the first variable
	return(2.8*x*y + 0.027*y + 0.032);
}

double gy(double x, double y) {					//Derivative of the second function of the second variable
	return(1.4*x*x - 3 * y*y + 0.027*x);
}

double J(double x, double y) {					//Jacobian
	return(fx(x, y)*gy(x, y) - fy(x, y)*gx(x, y));
}

double dx(double x, double y) {
	return((fy(x,y)*g(x,y)-f(x,y)*gy(x,y))/J(x,y));
}

double dy(double x, double y) {
	return((f(x, y)*gx(x, y) - fx(x, y)*g(x, y)) / J(x, y));
}

void root(double a, double b, double c, double d) {
	double x1 = a, x2 = b, y1 = c, y2 = d;
	int n = 1;
	while ((abs(x2-x1)>0.01)||(abs(y2-y1)>0.01)){
		x1 = x2;
		y1 = y2;
		x2 = x1 + dx(x1, y1);
		y2 = y1 + dy(x1, y1);
		cout << "Number of iteration: " << n << endl;
		cout << "Root: (" << x2 << "," << y2 << ")" << endl;
		n++;
	}
	cout << "FInal solution "<<" is (" << x2 << "," << y2 << ")" << endl;

}

int main() {
	root(0.5, 0.6,0.5, 0.6);
	return EXIT_SUCCESS;
}