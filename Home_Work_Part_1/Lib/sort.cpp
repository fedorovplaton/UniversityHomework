#include "sort.h"

void BubbleSort(vector<int>& v) {
	int p=0;
	for (int i=0;i<v.size()-1;i++){
		for (int j=i+1;j<v.size();i++){
			if (v[j]>v[i]) {
				p=v[j];
				v[j]=v[i];
				v[i]=p;
			}
		}
	}
}
