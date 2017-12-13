#include <iostream>
#include "Header.h"

using namespace std;

int f(int &data, unsigned index)
{
	data+=2;
	cout << "There is " << data << " in " << index << " node" << endl;
	return 0;
}


int main()
{
	Graph <int> g;
	g.push(0); 
	g.push(1, 1, 0); 
	g.push(2, 2, 1, 0); 
	g.push(3, 3, 0, 1, 2);
	g.push(4, 4, 0, 1, 2, 3); 
	g.push(5, 5, 0, 1, 2, 3, 4); 
	g.depth(f, 1);
}