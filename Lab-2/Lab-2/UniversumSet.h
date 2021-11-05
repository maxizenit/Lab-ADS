#pragma once
#include <iostream>
using namespace std;

class UniversumSet {
 private:
  char tag;
  int* universum;
  char alphabetFirstSymbol;
  int alphabetSize;

 public:
  UniversumSet(const UniversumSet& prototype);
  UniversumSet(char tag, char* buffer, int bufferSize, char alphabetFirstSymbol,
               int alphabetSize);
  ~UniversumSet();
  void setTag(char tag);
  bool contains(char element) const;
  void print() const;
  UniversumSet operator&(const UniversumSet& other) const;
  UniversumSet operator|(const UniversumSet& other) const;
  UniversumSet operator~() const;
};