#include <iostream>
#include <cmath>

using namespace std;

double N(int k, double t) {
	double n = 1;
	if (k == 0)
		return n;
	else {
		for (int i = 1; i <= k; i++) {
			if (i % 2 == 1)
				n *= (t + i / 2) / i;
			else
				n *= (t - i / 2) / i;
		}
		return n;
	}
}

int main() {
	double t = 0.5;
	double y[7][7];
	double P = 0.0;
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
	for (int i = 0; i <= 6; i++) {
		P += N(i, t)*y[i][3];
	}
	cout << P << endl;
}