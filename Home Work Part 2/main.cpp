#include <iostream>
#include <vector>
using namespace std;
int main() {
int n, m;
cin >> n >> m;
vector< vector<int> > vv(n + 2, vector<int>(m + 2, 0));
if (!n || !m) return 0;
vv[2][2] = 1;
for(int j = 3; j < m + 2; j++)
vv[2][j] = vv[2][j - 2];
for (int i = 3; i < n + 2; i++)
for (int j = 2; j < m + 2; j++)
vv[i][j] = vv[i - 2][j] + vv[i][j - 2] + vv[i - 1][j - 1];
cout << vv[n + 1][m + 1] << endl;
return 0;
}
