#include <iostream>
#include <cmath>

using namespace std;

double y[7][7];

double C(int k, double t) {
	double c = 1;
	for (int i = 1; i <= k; i++) 
		c *= (t - i + 1)/i;
	return c;
}

double fi(double t) {
	double f = 0.0397;
	for (int i = 2; i < 7; i++)
		f -= C(i,t)*y[i][3];
	return f / y[1][3];
}

int main() {
	for (int i = 0; i < 7; i++)
		for (int j = 0; j < 7; j++)
			y[i][j] = 0.0;
	y[0][0] = 0.3316;
	y[0][1] = 0.4582;
	y[0][2] = 0.5567;
	y[0][3] = 0.6403;
	y[0][4] = 0.7141;
	y[0][5] = 0.781;
	y[0][6] = 0.8426;
	for (int i = 1; i < 7; i++)
		for (int j = i / 2; j < (7 - (i + 1) / 2); j++)
			y[i][j] = y[i - 1][j + i % 2] - y[i - 1][j - (i + 1) % 2];
	for (int j = 0; j < 7; j++) {
		for (int i = 0; i < 7; i++)
			cout << y[i][j] << " ";
		cout << endl;
	}
	double t0 = 0.0, t1 = fi(t0);
	
	while (abs(t1 - t0) > 0.00001) {
		t0 = t1;
		t1 = fi(t0);
	}

	cout << 0.41 + t1*0.01 << endl;

}