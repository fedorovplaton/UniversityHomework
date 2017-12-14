
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


class ComplVec {
	Compl* v;
	size_t Size;
public:
	ComplVec(size_t size = 0);
	ComplVec(const ComplVec& obj);
	~ComplVec();
	ComplVec(ComplVec&& obj);
	ComplVec& operator=(const ComplVec& obj);	
	ComplVec& operator=(ComplVec&& obj);
	friend void swap(ComplVec& obj1, ComplVec& obj2);
};

ComplVec::ComplVec(size_t size) {
	v = nullptr, Size = size;
	if (Size)
		v = new Compl[Size];
}
ComplVec::ComplVec(const ComplVec& obj) {
	v = nullptr, Size = obj.Size;
	if (obj.v != nullptr) {
		Size = obj.Size;
		v = new Compl[Size];
	}
}
ComplVec::~ComplVec() {
	if (v != nullptr)
		delete[] v;
}
ComplVec::ComplVec(ComplVec&& obj) : v(nullptr), Size(0) {
	v = obj.v; Size = obj.Size;
	obj.v = nullptr; obj.Size = 0;
}
ComplVec& ComplVec::operator=(const ComplVec& obj) {
	if (v != nullptr)
		delete[] v;
	Size = obj.Size;
	v = new Compl[Size];
	for (size_t i = 0; i < Size; i++)
		v[i] = obj.v[i];
	return *this;
}
ComplVec& ComplVec::operator=(ComplVec&& obj) {
	if (this != &obj) {
		if (v != nullptr)
			delete[] v;
		v = obj.v; Size = obj.Size;
		obj.v = nullptr; obj.Size = 0;
	}
	return *this;
}
void swap(ComplVec& obj1, ComplVec& obj2) {
	swap(obj1.Size, obj2.Size);
	swap(obj1.v, obj2.v);
}
