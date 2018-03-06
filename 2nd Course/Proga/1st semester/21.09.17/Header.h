#ifndef GRAPH_H_INCLUDED
#define GRAPH_H_INCLUDED

#include <vector>
#include <thread>
#include <mutex>

using namespace std;


template <typename T> class Graph; 

template <typename T> class node; 
template <typename T> void depthwrap(Graph <T> *graph, int(*function)(T &, unsigned), unsigned start, bool *visited, unsigned *created, unsigned *finished);
mutex _mutex; 

						
template <typename T> class node
{
	friend class Graph <T>;
	friend void depthwrap <T>(Graph <T> *graph, int(*function)(T &, unsigned), unsigned start, bool *visited, unsigned *created, unsigned *finished);

	T data; 
	Graph <T> *father;
	vector < node <T> *> neighbors; 

	node(T d, Graph <T> *f) 
	{
		data = d;
		father = f;
	}

	unsigned index()
	{
		for (unsigned i = 0; i < father->vec.size(); i++) {
			if (father->vec[i] == this) {
				return i;
			}
		}
	}
	
	unsigned neighborPos(unsigned index) 
	{
		for (unsigned i = 0; i < neighbors.size(); i++) {
			if (neighbors[i] == father->vec[index]) {
				return i;
			}
		}
	}

};

template <typename T> class Graph
{
public:
	Graph() 
	{
	}

	~Graph() 
	{
		for (unsigned i = 0; i < vec.size(); i++) {
			delete vec[i];
		}
		vec.clear();
	}
	
	unsigned size() 
	{
		return vec.size();
	}

	unsigned push(T d, unsigned neighborsAmount = 0, ...) 
	{
		node <T> *newNode = new node <T>(d, this);
		
		unsigned *ptr = &neighborsAmount;
		for (unsigned i = 0; i < neighborsAmount; i++) {
			unsigned neighborIndex = *(ptr + i + 1);
			if (neighborIndex >= vec.size()) {
				exit(EXIT_FAILURE);
			}
			else {
				newNode->neighbors.push_back(vec[neighborIndex]); 
				vec[neighborIndex]->neighbors.push_back(newNode); 
			}
		}
		vec.push_back(newNode);
		return vec.size();
	}

	T pop(unsigned index) 
	{
		if (index >= vec.size()) {
			exit(EXIT_FAILURE);
		}
		for (unsigned i = 0; i < vec[index]->neighbors.size(); i++) {
			node <T> *neighbor = vec[index]->neighbors[i];
			unsigned pos = neighbor->neighborPos(index);
			neighbor->neighbors.erase(neighbor->neighbors.begin() + pos); 
		}
		T result = vec[index]->data;
		delete vec[index];
		vec.erase(vec.begin() + index);
		return result;
	}

	T &operator[] (unsigned index) 
	{
		if (index >= vec.size()) {
			exit(EXIT_FAILURE);
		}
		return vec[index]->data;
	}

	bool check(unsigned Index_1, unsigned Index_2) 
	{
		if (Index_1 >= vec.size() || Index_2 >= vec.size()) {
			exit(EXIT_FAILURE);
		}
		for (unsigned i = 0; i < vec[Index_1]->neighbors.size(); i++) {
			if (vec[Index_2] == vec[Index_1]->neighbors[i]) { 
				return true;
			}
		}
		return false;
	}

	bool link(unsigned Index_1, unsigned Index_2) 
	{
		if (check(Index_1, Index_2)) {
			return false;
		}
		
		vec[Index_1]->neighbors.push_back(vec[Index_2]);
		vec[Index_2]->neighbors.push_back(vec[Index_1]);
		return true;
	}
	void depth(int(*function)(T &, unsigned), unsigned start = 0)
	{
		if (start >= vec.size()) {
			exit(EXIT_FAILURE);
		}
		bool *visited = new bool[vec.size()]; 
		for (unsigned i = 0; i < vec.size(); i++) {
			visited[i] = false; 
		}
		unsigned *created = new unsigned(1);
		unsigned *finished = new unsigned(0);
		depthwrap(this, function, start, visited, created, finished); 
		while (*created != *finished) {}
		delete[] visited; 
	}
	
	
private:
	friend class node <T>;
	friend void depthwrap <T>(Graph <T> *graph, int(*function)(T &, unsigned), unsigned start, bool *visited, unsigned *created, unsigned *finished);

	vector <node <T> *> vec; 
};

template <typename T> void depthwrap(Graph <T> *graph, int(*function)(T &, unsigned), unsigned start, bool *visited, unsigned *created, unsigned *finished)
{
	_mutex.lock(); 

	if (start >= graph->vec.size()) {
		exit(EXIT_FAILURE);
	}
	if (!visited[start]) {
		visited[start] = true; 
		function(graph->vec[start]->data, start); 
		_mutex.unlock(); 
		for (unsigned i = 0; i < graph->vec[start]->neighbors.size(); i++) {
			_mutex.lock(); 
			*created = *created + 1;
			_mutex.unlock(); 

			unsigned newIndex = graph->vec[start]->neighbors[i]->index();
			thread inDepthThread(depthwrap <T>, graph, function, newIndex, visited, created, finished);
			inDepthThread.detach(); 
		}
		_mutex.lock(); 
	}
	*finished = *finished + 1;
	_mutex.unlock(); 
}


#endif 
