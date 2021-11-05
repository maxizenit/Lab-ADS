#pragma once
#include <iostream>
using namespace std;

class WordSet {
 private:
  const int WORD_SIZE = sizeof(int) * 8;
  char tag;
  int word;
  char alphabetFirstSymbol;
  int alphabetSize;

 public:
  WordSet(const WordSet& prototype);
  WordSet(char tag, char* buffer, int bufferSize, char alphabetFirstSymbol,
          int alphabetSize);
  void setTag(char tag);
  bool contains(char element) const;
  void print() const;
  WordSet operator&(const WordSet& other) const;
  WordSet operator|(const WordSet& other) const;
  WordSet operator~() const;
};