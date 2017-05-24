#include <bits/stdc++.h>

using namespace std;

int* a;
int sz;

bool isNum(string s) {

    int i = 0;
    if(s[i] == '-') ++i;
    for(;i < s.length(); ++i)
        if(s[i] < '0' || s[i] > '9')
            return false;

    return true;
}
void arrayInsert() throw(string) {

    cout << "Example:\n5\n1 3 -4 0 12\n\n";

    cin >> sz;
    if(sz < 1)
        throw string("Incorrect input (size)");

    a = new int[sz];

    string num;
    for(int i = 0; i < sz; ++i) {

        cin >> num;
        if(!isNum(num))
            throw string("Incorrect input (element)");

        a[i] = atoi(num.c_str());
    }
}
void Sift_Down(int n, int i) {

    int p = i*2+1;
    if(p > n-1) return;
    if(p+1 < n)
        if(a[p+1] > a[p])
            ++p;
    if(a[i] < a[p]) {
        swap(a[i], a[p]);
        Sift_Down(n, p);
    }
}
void make_heap() {

    int k = 0;

    for(int i = sz-1; ; --i)
        if(i*2+1 < sz) {
            k = i; break;
        }

    for(k; k >= 0; --k)
        Sift_Down(sz, k);
}
void heapsort(int n) {
    if(n == 1) return;
    swap(a[0], a[n-1]);
    --n;
    Sift_Down(n, 0);
    heapsort(n);
}
void show() {
    cout << endl;
    for(int i = 0; i < sz; ++i)
        cout << a[i] << ' ';
    cout << endl;
}

int main()
{

    try {
        arrayInsert();
        make_heap();
        heapsort(sz);
        show();
    }
    catch(string error) {
        cout << "Exception: " << error << endl;
    }
    catch(...) {
        cout << "Exception undefined\n";
    }

    if(a != nullptr)
        delete[] a;

    return 0;
}
