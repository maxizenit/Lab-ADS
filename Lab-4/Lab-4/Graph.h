#pragma once

#include <Windows.h>

#include <iomanip>
#include <iostream>
#include <vector>
using namespace std;

class Graph {
 private:
  bool colored_;
  int *colors_;
  int vertexCount_;
  int **edges_;

  Graph(){};
  Graph(int vertexCount);

 public:
  ~Graph();

  static Graph *create(int vertexCount);

  void clear();
  void show();
  void addEdge(int firstVertex, int lastVertex);
  void removeEdge(int firstVertex, int lastVertex);
  void colorize();
};