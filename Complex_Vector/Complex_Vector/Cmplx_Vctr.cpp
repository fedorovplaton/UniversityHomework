#include "Cmplx_Nmbr.h"

using namespace std;

typedef class complexVector {
	unsigned length;
	complexNumber* vect; 
	void swap(complexVector& a, complexVector& b) {
		unsigned tl = b.length;
		complexNumber* ta = b.vect;
		b.length = a.length;
		b.vect = a.vect;
		a.length = tl;
		a.vect = ta;
	}
public:

	complexVector(unsigned a) {
		length = a;
		vect = new complexNumber[length];
	}
	~complexVector() {
		delete vect;
	}
	unsigned getLength() {
		return length;
	}
	void resize(unsigned newLength) {
		complexNumber* temp = new complexNumber[newLength];
		for (unsigned i = 0; i < newLength && i < length; i++) {
			temp[i] = vect[i];
		}
		length = newLength;
		delete vect;
		vect = temp;
	}
	void push_back(complexNumber a) {
		resize(length + 1);
		vect[length - 1] = a;
	}

	complexNumber& operator[](unsigned a) {
		return vect[a];
	}


	complexVector(const complexVector& a) {
		length = a.length;
		vect = new complexNumber[length];
		for (unsigned i = 0; i < length; i++) {
			vect[i] = a.vect[i];
		}
	}
	complexVector& operator= (const complexVector& a) {
		complexVector temp(a);
		swap(*this, temp);
		return *this;
	}
	complexVector& operator= (complexVector&& a) {
		swap(*this, a);
		return *this;
	}
};

bool operator== (complexVector& a, complexVector& b) {
	if (a.getLength() != b.getLength()) {
		return false;
	}
	else {
		for (unsigned i = 0; i < a.getLength(); i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
	}
	return true;
}
bool operator!= (complexVector& a, complexVector& b) {
	return !(a == b);
}