#ifndef HDS_H_INCLUDED
#define _CRT_SECURE_NO_WARNINGS
#define HDS_H_INCLUDED

#include <iostream>
#include <string>
#include <fstream>
#include <regex>
#include <cstdlib>
#include <cstdio>

typedef std::regex_token_iterator<const char *> Myiter;

size_t length(const char* path) {
	int c;
	size_t i=0;
	FILE* file;
	fopen_s(&file, path, "rt");
	do {
		i++;
		c = getc(file);
	} while (c != EOF);
	fclose(file);
	return i;
}

void findHD(const char* path) {
	size_t _length = length(path);
	char *line = new char[_length];
	FILE * file;
	fopen_s(&file,path, "rt");		
	std::string seq;
	int ch=1;
	int l=0;
	if (file == NULL) perror("CAN'T OPEN FILE");
	else {		
		do {
			ch = getc(file);
			while ((ch != '\n')&&(ch!=EOF)) {				
				line[l] = (unsigned char)ch;
				l++;
				ch = getc(file);
			}			
			
		}
		 while (ch != EOF);
	}
	line[_length - 1] = '\0';
	Myiter::regex_type rx("(CAG){2,}");
	Myiter next(line, line + strlen(line), rx);
	Myiter end;
	unsigned matches = 0;
	for (; next != end; ++next) {
		seq = next->str();
		if (seq.length() / 3 > matches) {
			matches = seq.length() / 3;
			}
	}
	if (matches < 26) {
		std::cout << "There is " << matches << " CAG codons in your gene." << std::endl
			<< "Classification: Normal." << std::endl
			<< "Disease status: Will not be affected." << std::endl
			<< "Risk to offspring: None." << std::endl;
	}
	else if (matches <= 35) {
		std::cout << "There is " << matches << " CAG codons in your gene." << std::endl
			<< "Classification: Intermediate." << std::endl
			<< "Disease status: Will not be affected." << std::endl
			<< "Risk to offspring: Elevated but <<50%." << std::endl;
	}
	else if (matches <= 39) {
		std::cout << "There is " << matches << " CAG codons in your gene." << std::endl
			<< "Classification: Reduced Penetrance." << std::endl
			<< "Disease status: May or may not be affected." << std::endl
			<< "Risk to offspring: 50%." << std::endl;
	}
	else {
		std::cout << "There is " << matches << " CAG codons in your gene." << std::endl
			<< "Classification: Full Penetrance." << std::endl
			<< "Disease status: Will be affected." << std::endl
			<< "Risk to offspring: 50%." << std::endl;
	}
}



#endif // !HDS_H_INCLUDED
