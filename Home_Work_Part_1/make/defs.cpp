#include "defs.h"

void dfs(int q) {
	lifo[p++] = q; u[q] = 1;
	if (p >= k) {
		for (int i = 0; i < k; i++)
			cout << v[lifo[i]] << ' ';
		cout << endl;
	}
	else
		for (int i = 0; i < n; i++)
			if (!u[i]) dfs(i);
	--p; u[q] = 0;
}
