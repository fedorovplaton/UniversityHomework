objs = main.o sort.o 
MyProg : $(objs)
	g++ -o MyProg $(objs)
main.o : main.cpp sort.h
	g++ -c main.cpp
sort.o : sort.cpp sort.h
	g++ -c sort.cpp
