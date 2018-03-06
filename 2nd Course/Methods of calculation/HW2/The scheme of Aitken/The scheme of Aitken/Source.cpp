#include <iostream>
#include <cmath>

using namespace std;

double det(double x0, double y0, double x1, double y1) {
	return x0*y1 - y0*x1;
}

int main() {
	double p[9][9];
	for (int j = 0; j < 9; j++) {
		for (int i = 0; i < 9; i++) {
			p[i][j]=0.0;
		}
	}
	double y[9] = { 0.3316,0.4582,0.5567,0.6403,0.7141,0.781,0.8426,0.9,0.9539 };
	p[0][0] = 0.11;
	double t = 0.815;
	for (int i = 1; i <9; i++) {
		p[0][i] = p[0][0] + i*0.1;
		p[1][i] = det(y[0], p[0][0] - t, y[i], p[0][i] - t)/(p[0][i]-p[0][0]);
	}

	for (int j = 2; j < 9; j++) {			 //Column
		for (int i = j; i < 9; i++) {		 //String
			p[j][i] = det(p[j - 1][j - 1], p[0][j - 1] - t, p[j - 1][i], p[0][i] - t) / (p[0][i] - p[0][j - 1]);
		}
	}
	cout << "The scheme of Aitken: " << endl;
	for (int j = 0; j < 9; j++) {
		for (int i = 0; i < 9; i++) {
			cout << p[i][j] << " ";
		}
		cout << endl;
	}
	cout <<"The value of the interpolation polinomial of the original function at the point "<< t<<" is "<< p[8][8] << endl;

}