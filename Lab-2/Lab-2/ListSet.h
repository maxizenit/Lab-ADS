#pragma once
#include <iostream>
using namespace std;

struct Node {
  char data;
  Node* next;

  Node(char data) {
    this->next = nullptr;
    this->data = data;
  }
};

class ListSet {
 private:
  char tag;
  int size;
  Node* head;
  char alphabetFirstSymbol;
  int alphabetSize;

  void sortArray(char* buffer, int bufferSize) const;
  void pushBack(char newElement);

 public:
  ListSet(const ListSet& prototype);
  ListSet(char tag, char* buffer, int bufferSize, char alphabetFirstSymbol,
          int alphabetSize);
  ~ListSet();
  void setTag(char tag);
  bool contains(char element) const;
  void print() const;
  void clear();
  ListSet operator&(const ListSet& other) const;
  ListSet operator|(const ListSet& other) const;
  ListSet operator~() const;
};