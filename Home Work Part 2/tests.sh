#!/bin/bash

make
ans=0
i=1
while [[ i -lt 11 ]]
do
./MyProg < ./Test/${i}_test_in.txt > ./Test/${i}_test_out.txt
x1=$(cat ./Test/${i}_test_res.txt)
x2=$(cat ./Test/${i}_test_out.txt)
if [[ x1 -eq x2 ]]
then
let "ans+=1"
fi
let "i+=1"
done
echo $ans