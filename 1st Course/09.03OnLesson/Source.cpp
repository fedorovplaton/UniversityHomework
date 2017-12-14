#include <iostream>
#include <fstream>

using namespace std;

void fcn(ostream &err) {
	err << "Error"<<endl;
}

std::ofstream
	err("error.txt", std::ofstream::app);

struct node {
	int val;
	node *next, *prev;
};

class List {
	node *b, *e;
	int size;
public:
	List();
	~List();
	int sizeList() { return size; }
	void addBegin(int v);
	void addEnd(int v);
	void addFromNum(int v, int n);
	bool empty() {
		if (size == 0)
			return true;
		else
			return false;

	};
	int& operator [](int n) {
		if (n > size || n < 1) {
			
			//cerr << "ERROR" << endl;
			fcn(err);
			return b->val;
		}
		node *l = b;
		for (int i = 0; i < n; i++)
			l = l->next;
		cout << "OK ";
		return l->val;
	};
	void del(int n);
};

List::List() {
	size = 0;
	b = new node; e = new node;
	b->prev = 0; e->next = 0;
	e->prev = b; b->next = e;
}
List::~List() {
	node *cur;
	while (b) {
		cur = b->next;
		delete b;
		b = cur;
	}
}
void List::addBegin(int v) {
	size++;
	node *cur = new node;
	cur->val = v;
	cur->next = b->next;
	b->next->prev = cur;
	cur->prev = b;
	b->next = cur;
	cout << "ADD BEGIN OK" << endl;
}
void List::addEnd(int v) {
	size++;
	node *cur = new node;
	cur->val = v;
	cur->next = e;
	e->prev->next = cur;
	cur->prev = e->prev;
	e->prev = cur;
	cout << "ADD END OK" << endl;
}
void List::addFromNum(int v, int n) {
	if (n > size - 1 || n < 1) {
		cout << "ERROR" << endl;
		return;
	}
	size++;
	node *l = b;
	for (int i = 0; i < n; i++)
		l = l->next;
	node *cur = new node;
	cur->val = v;
	cur->next = l->next;
	l->next = cur;
	cur->prev = l;
	cout << "ADD FROM OK" << endl;
}

void List::del(int n) {
	if (n > size || n < 1) {
		cout << "ERROR" << endl;
		return;
	}
	size--;
	node *l = b;
	for (int i = 0; i < n; i++)
		l = l->next;
	l->prev->next = l->next;
	l->next->prev = l->prev;
	delete l;
	cout << "DEL OK" << endl;
}



int main() {

	 
    //fcn(err);
	List myList;
	myList.addBegin(1);
	myList.addEnd(3);
	myList.addFromNum(1, 2);
	cout << myList[4] << endl;
	myList.del(1); myList.del(2); myList.del(1);
	if (myList.empty())
		cout << "EMPTY" << endl;
	else cout << "NO EMPTY" << endl;
	err.close();
	return 0;
}
