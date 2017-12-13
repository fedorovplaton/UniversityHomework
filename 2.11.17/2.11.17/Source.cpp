#include <iostream>
#include <string>
#include <fstream>
#include <regex>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	regex r(R"(^(https?:\/\/)?(www\.)?([-à-ÿa-z¸¨öóøùõúôûðý÷ñòüþ0-9_\.]{2,}\.)(ðô|[a-z]{2,6})((\/[-à-ÿ¸¨öóøùõúôûðý÷ñòüþa-z0-9_]{1,})?\/?([a-z0-9_-]{2,}\.[a-z]{2,6})?(\?[a-z0-9_]{2,}=[-0-9]{1,})?((\&[a-z0-9_]{2,}=[-0-9]{1,}){1,})?)$)");
	string s;
	while (fin >> s) {
		if (regex_match(s, r)) {
			fout << s << endl;
		}
	}
	return 0;
}