#include <cmath>
#include <iostream>

using namespace std;

typedef class complexNumber {
	double Re;
	double Im;
public:
	complexNumber() {
		Re = 0;
		Im = 0;
	}
	complexNumber(double a, double b = 0) {
		Re = a;
		Im = b;
	}
	complexNumber(const complexNumber& a) {
		Re = a.Re;
		Im = a.Im;
	}
	~complexNumber() {
	}
	void set(double a, double b) {
		Re = a;
		Im = b;
	}
	double Real() {
		return Re;
	}
	double Imag() {
		return Im;
	}

	double abs() {
		return sqrt(Re * Re + Im * Im);
	}

	const complexNumber& operator+= (const complexNumber& a) {
		Re += a.Re;
		Im += a.Im;
		return *this;
	}
	const complexNumber& operator-= (const complexNumber& a) {
		Re -= a.Re;
		Im -= a.Im;
		return *this;
	}
	const complexNumber& operator*= (const complexNumber a) { 
		double temp = Re;
		Re = Re * a.Re - Im * a.Im;
		Im = Im * a.Re + temp * a.Im;
		return *this;
	}
	const complexNumber& operator*= (const double& a) {
		Re *= a;
		Im *= a;
		return *this;
	}
	
};

const complexNumber& operator+ (complexNumber a, const complexNumber& b) {
	return a += b;
}
const complexNumber& operator- (complexNumber a, const complexNumber& b) {
	return a -= b;
}
const complexNumber& operator* (complexNumber a, const double& b) {
	return a *= b;
}
const complexNumber& operator* (const double& a, complexNumber b) {
	return b *= a;
}
const complexNumber& operator* (complexNumber a, const complexNumber& b) {
	return a *= b;
}
bool operator== (complexNumber& a, complexNumber& b) {
	return (a.Real() == b.Real()) && (a.Imag() == b.Imag());
}
bool operator!= (complexNumber& a, complexNumber& b) {
	return !(a == b);
}

double abs(complexNumber& a) {
	return a.abs();
}