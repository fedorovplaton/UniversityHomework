#include <iostream>
#include <vector>
#include <algorithm>
#include "in.h"
#include "defs.h"

using namespace std;

int n, k, p = 0;
vector<int> v;
vector<bool> u;
int *lifo;

void dfs(int q);

int main() {
        in1();      
	v.resize(n);
	u.resize(n, 0);
	lifo = new int[n];
	in2();
	sort(v.begin(), v.end());
	for (int i = 0; i < n; i++)
		dfs(i);
	return 0;
}

