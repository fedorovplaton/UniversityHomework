#include <fstream>
#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

void main() {
	char s[72];

	FILE *file;
	//char *fname = "sequence_large.fasta";
	file = fopen("sequence_super_large.fasta", "r");

	FILE *file1;
	//char *fname1 = "output.txt";	
	file1 = fopen("output.txt", "w");
	//std::ofstream h("output1.txt");
	int i = -1;

	fgets(s, sizeof(s), file);
	fputs(s, file1);
	while (fgets(s, sizeof(s), file) != 0) {
		//h << s;
		fputs(s, file1);
		i++;
	};
	cout << i;
	//std::ifstream f("sequence_large.fasta");

	//getline(f, s);
	//while (s!="")
	//{
	//	i++;
	//	h << s<<endl;
	//	getline(f, s);


	//};		
	//h << s;
	//f.close();
	//h.close();
}