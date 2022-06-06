#pragma once
#include <iostream>
#include <string>
using namespace std;

struct Node {
  char data;
  Node *parent;
  Node *left, *middle, *right;
  Node(char data, Node *parent);
  ~Node();

  void directVisit(string &buffer);
  void reverseVisit(string &buffer);
  void internalVisit(string &buffer);
};

class TernaryTree {
 private:
  Node *root_;
  int size_;
  int depth_;

  int fillBuffersAndGetIndex(Node *currentNode, string buffers[],
                             int &lastDepthCounter, int currentDepth,
                             int width);

 public:
  TernaryTree();
  ~TernaryTree();

  int size();
  bool empty();
  void add(const string &word);
  void clear();
  void directPrint();
  void reversePrint();
  void internalPrint();
  void show();
};