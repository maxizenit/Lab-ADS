#pragma once
#include <iostream>
using namespace std;

class ArraySet {
 private:
  char tag;
  int size;
  char* set;
  char alphabetFirstSymbol;
  int alphabetSize;

  void sortArray(char* array, int arraySize) const;

 public:
  ArraySet(const ArraySet& prototype);
  ArraySet(char tag, char* buffer, int bufferSize, char alphabetFirstSymbol,
           int alphabetSize);
  ~ArraySet();
  void setTag(char tag);
  bool contains(char element) const;
  void print() const;
  void clear();
  ArraySet operator|(const ArraySet& other) const;
  ArraySet operator&(const ArraySet& other) const;
  ArraySet operator~() const;
};