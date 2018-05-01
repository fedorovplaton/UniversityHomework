#include<iostream>
#include<cmath>

using namespace std;

int main() {
	double z[12]= { -2,-2,-2,0,0,0,1,1,1,3,3,3 };
	double f[12][12];
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			f[i][j] = 0;
		}
	}
	f[3][0] = 6;
	f[4][0] = 6;
	f[5][0] = 6;
	f[4][1] = -5;
	f[5][1] = -5;
	f[5][2] = 4;
	cout << "Matrix of divided differences" << endl;
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j <= i; j++) {
			if (z[i]!=z[i - j]) {
				f[i][j] = (f[i][j - 1] - f[i - 1][j - 1]) / (z[i] - z[i - j]);
			}
			cout << f[i][j] << ' ';
		}
		cout << endl;
	}
	cout << "Generated polynomial is:" << endl 
		 << "P(x)= "<<f[0][0];
	for (int i = 1; i < 12; i++) {
		if (f[i][i] >= 0)
			cout << '+';
		cout << f[i][i];
		if ((i >= 1) && (i <= 3))
			cout << "*(x+2)^(" << i << ')';
		else if ((i >= 4) && (i <= 5))
			cout << "*(x+2)^(3)*x^(" << i % 3 << ')';
		else if (i == 6)
			cout << "*(x+2)^(3)*x^(3)";
		else if ((i >= 7) && (i <= 8))
			cout << "*(x+2)^(3)*x^(3)*(x-1)^(" << i % 3 << ')';
		else if (i == 9)
			cout << "*(x+2)^(3)*x^(3)*(x-1)^(3)";
		else if ((i >= 10) && (i <= 11))
			cout << "*(x+2)^(3)*x^(3)*(x-1)^(3)*(x-3)^(" << i % 3 << ')';
	}
	cout << endl << "Hermite interpolation polynomial: 0.104167 x^11 - 0.578705 x^10 - 0.5625 x^9 + 6.98613 x^8 - 2.78008 x^7 - 27.5695 x^6 + 22.2014 x^5 + 33.088 x^4 - 35.8889 x^3 + 4 x^2 - 5 x + 6";
}