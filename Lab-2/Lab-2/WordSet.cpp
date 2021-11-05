#include "WordSet.h"

WordSet::WordSet(const WordSet& prototype) {
  setTag(prototype.tag);
  alphabetFirstSymbol = prototype.alphabetFirstSymbol;
  alphabetSize = prototype.alphabetSize;
  word = prototype.word;
}

WordSet::WordSet(char tag, char* buffer, int bufferSize,
                 char alphabetFirstSymbol, int alphabetSize) {
  setTag(tag);
  this->alphabetFirstSymbol = alphabetFirstSymbol;
  this->alphabetSize = alphabetSize;
  word = 0;

  for (int i = 0; i < bufferSize; ++i) {
    int index = buffer[i] - alphabetFirstSymbol;
    word |= (1 << index);
  }
}

void WordSet::setTag(char tag) { this->tag = tag; }

bool WordSet::contains(char element) const {
  return (word >> (alphabetFirstSymbol - element)) & 1;
}

void WordSet::print() const {
  cout << "Машинное слово " << tag << ":";
  for (int i = 0; i < WORD_SIZE; ++i) {
    cout << " " << ((word >> i) & 1);
  }
  cout << endl;
}

WordSet WordSet::operator&(const WordSet& other) const {
  WordSet result(*this);
  result.word = word & other.word;
  return result;
}

WordSet WordSet::operator|(const WordSet& other) const {
  WordSet result(*this);
  result.word = word | other.word;
  return result;
}

WordSet WordSet::operator~() const {
  WordSet result(*this);
  result.word = ~word;
  return result;
}