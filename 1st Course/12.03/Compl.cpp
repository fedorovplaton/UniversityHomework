#include<iostream>

using namespace std;

class Compl {
	int im, re;
public:
	Compl(int r = 0, int i = 0) : im(i), re(r) {}
	Compl operator+(const Compl& obj);
	Compl operator-(const Compl& obj);
	Compl operator*(const Compl& obj);
    friend int Re(const Compl& obj) { 
		return obj.re;
	}
	friend int Im(const Compl& obj) {
		return obj.im;
	}	
	friend double abs(const Compl& obj) {
		return sqrt(obj.im*(obj.im) + obj.re*(obj.re));
	}
	bool operator==(const Compl& obj) { 
		return (im == obj.im && re == obj.re); 
	}
	bool operator!=(const Compl& obj) {
		return (im != obj.im || re != obj.re);
	}
	friend ostream& operator<<(ostream& stream, const Compl& obj);
	friend istream& operator >> (istream& stream, Compl& obj);
};

istream& operator >> (istream& stream, Compl& obj) {
	char i;
	stream >> obj.re >> obj.im >> i; 
	return stream;
}
ostream& operator<<(ostream& stream, const Compl& obj) {
	stream << obj.re;
	if (obj.im < 0) stream << obj.im << "i";
	else stream << "+" << obj.im << "i";
	return stream;
}
Compl Compl::operator+(const Compl& obj) {
	Compl tmp;
	tmp.im = im + obj.im;
	tmp.re = re + obj.re;
	return tmp;
}
Compl Compl::operator-(const Compl& obj) {
	Compl tmp;
	tmp.im = im - obj.im;
	tmp.re = re - obj.re;
	return tmp;
}
Compl Compl::operator*(const Compl& obj) {
	Compl tmp;
	tmp.im = im*(obj.im) - re*(obj.re);
	tmp.re = im*(obj.re) + re*(obj.im);
	return tmp;
}
