#ifndef CMPLXNMBR_H_INCLUDED
#define CMPLXNMBR_H_INCLUDED

#include "Cmplx_Nmbr.cpp"

typedef class complexNumber;

const complexNumber& operator+ (complexNumber a, const complexNumber& b);
const complexNumber& operator- (complexNumber a, const complexNumber& b);
const complexNumber& operator* (complexNumber a, const double& b);
const complexNumber& operator* (const double& a, complexNumber b);
const complexNumber& operator* (complexNumber a, const complexNumber& b);

bool operator== (complexNumber& a, complexNumber& b);
bool operator!= (complexNumber& a, complexNumber& b);

double abs(complexNumber& a);


#endif
