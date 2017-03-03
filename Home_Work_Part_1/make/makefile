objs = main.o in.o  defs.o 
MyProg : $(objs)
	g++ -o MyProg $(objs)
main.o : main.cpp in.h defs.h
	g++ -c main.cpp
inout.o : in.cpp in.h
	g++ -c in.cpp
way.o : defs.cpp defs.h
	g++ -c defs.cpp