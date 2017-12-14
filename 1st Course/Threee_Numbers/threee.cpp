using namespace std;

typedef class threee {
	int* num;
public:
	int getNum(unsigned pos) { 
		return num[pos];
	}
	threee(int a = 0, int b = 0, int c = 0) {
		num = new int[3];
		num[0] = a;
		num[1] = b;
		num[2] = c;
	}
	threee(const threee& a) {
		num = new int[3];
		for (int i = 0; i < 3; i++) {
			this->num[i] = a.num[i];
		}
	}
	threee& operator = (const threee& a) {
		for (int i = 0; i < 3; i++) {
			this->num[i] = a.num[i];
		}
		return *this;
	}
	~threee() {
		delete num;
	}
};

