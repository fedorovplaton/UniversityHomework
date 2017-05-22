#include <iostream>
#include "threee.h"

using namespace std;



int main()
{
	threee a(1, 2, 3);
	cout << a.getNum(0) << " " << a.getNum(1) << " " << a.getNum(2) << endl;
	threee b(a);
	cout << b.getNum(0) << " " << b.getNum(1) << " " << b.getNum(2) << endl;
	threee c;
	c = a;
	cout << c.getNum(0) << " " << c.getNum(1) << " " << c.getNum(2) << endl;
	return 0;
}