#ifndef HDS_H_INCLUDED
#define HDS_H_INCLUDED

#include <iostream>
#include <string>
#include <fstream>
#include <regex>
#include <cstdlib>
#include <cstdio>

void findHD(const char* path) {
	FILE * file;
	fopen_s(&file, path, "rt");
	char ch = 1;
	char c = 1;
	char a = 1;
	char g = 1;
	int l = 0;
	int count = 0;
	unsigned matches = 0;
	unsigned max = 0;
	int met = 0;
	int opal = 0, amber = 0, ochre = 0;

	if (file == NULL) perror("CAN'T OPEN FILE");
	else {
		do {
			ch = getc(file);
			while ((ch != '\n') && (ch != EOF)) {
				count = 0;
				if ((ch == 'A') && (met == 0)) {
					met++;
				}
				else if (((ch == 'T') || (ch == 'U')) && (met == 1))
					met++;
				else if ((ch == 'G') && (met == 2)) {
					do {
						ch = getc(file);
						while ((ch != '\n') && (ch != EOF)) {
							if ((ch == 'T') || (ch == 'U') && ((opal == 0) || (ochre == 0) || (amber == 0))) {
								opal++;
								ochre++;
								amber++;
							}
							else if ((ch == 'G') && (opal == 1)) {
								opal++;
								ochre = 0;
								amber = 0;
							}
							else if ((ch == 'A') && ((ochre == 1) || (amber == 1))) {
								ochre++;
								amber++;
							}
							else if (((ch == 'A') && ((opal == 2) || ochre == 2)) || ((ch == 'G') && (amber == 2))) {
								opal = 0;
								ochre = 0;
								amber = 0;
								met = 0;
								break;
							}
							else{
								ochre = 0;
								amber = 0;
								opal = 0;
							}

							if ((ch == 'C') && (count % 3 == 0)) {
								c = 'C';
								count++;
							}
							else if ((c == 'C') && (ch == 'A')) {
								a = 'A';
								count++;
							}
							else if ((c == 'C') && (a == 'A') && (ch == 'G')) {
								matches++;
								count = 0;
							}
							else {
								if (matches > max) {
									max = matches;									
									matches = 0;
								}
								c = 1;
								a = 1;
								g = 1;
								count++;
							}
							ch = getc(file);
						}
					} while (ch != EOF);
				}
				else
					met = 0;
				ch = getc(file);
			}
		} while (ch != EOF);
	}
	if (max < 26) {
		std::cout << "There is " << max << " CAG codons in your gene." << std::endl
			<< "Classification: Normal." << std::endl
			<< "Disease status: Will not be affected." << std::endl
			<< "Risk to offspring: None." << std::endl;
	}
	else if (max <= 35) {
		std::cout << "There is " << max << " CAG codons in your gene." << std::endl
			<< "Classification: Intermediate." << std::endl
			<< "Disease status: Will not be affected." << std::endl
			<< "Risk to offspring: Elevated but <<50%." << std::endl;
	}
	else if (max <= 39) {
		std::cout << "There is " << max << " CAG codons in your gene." << std::endl
			<< "Classification: Reduced Penetrance." << std::endl
			<< "Disease status: May or may not be affected." << std::endl
			<< "Risk to offspring: 50%." << std::endl;
	}
	else {
		std::cout << "There is " << max << " CAG codons in your gene." << std::endl
			<< "Classification: Full Penetrance." << std::endl
			<< "Disease status: Will be affected." << std::endl
			<< "Risk to offspring: 50%." << std::endl;
	}
}



#endif
