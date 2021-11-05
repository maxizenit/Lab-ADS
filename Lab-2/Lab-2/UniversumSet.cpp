#include "UniversumSet.h"

UniversumSet::UniversumSet(const UniversumSet& prototype) {
  setTag(prototype.tag);
  alphabetFirstSymbol = prototype.alphabetFirstSymbol;
  alphabetSize = prototype.alphabetSize;
  universum = new int[alphabetSize];
  copy(prototype.universum, prototype.universum + alphabetSize, universum);
}

UniversumSet::UniversumSet(char tag, char* buffer, int bufferSize,
                           char alphabetFirstSymbol, int alphabetSize) {
  setTag(tag);
  this->alphabetFirstSymbol = alphabetFirstSymbol;
  this->alphabetSize = alphabetSize;
  universum = new int[alphabetSize]{};

  for (int i = 0; i < bufferSize; ++i) {
    int index = (int)(buffer[i] - alphabetFirstSymbol);
    universum[index] = 1;
  }
}

UniversumSet::~UniversumSet() { delete[] universum; }

void UniversumSet::setTag(char tag) { this->tag = tag; }

bool UniversumSet::contains(char element) const {
  return universum[alphabetFirstSymbol - element];
}

void UniversumSet::print() const {
  cout << "Универсум " << tag << ":";
  for (int i = 0; i < alphabetSize; ++i) {
    cout << " " << universum[i];
  }
  cout << endl;
}

UniversumSet UniversumSet::operator&(const UniversumSet& other) const {
  UniversumSet result(*this);
  for (int i = 0; i < alphabetSize; ++i) {
    result.universum[i] = universum[i] & other.universum[i];
  }
  return result;
}

UniversumSet UniversumSet::operator|(const UniversumSet& other) const {
  UniversumSet result(*this);
  for (int i = 0; i < alphabetSize; ++i) {
    result.universum[i] = universum[i] | other.universum[i];
  }
  return result;
}

UniversumSet UniversumSet::operator~() const {
  UniversumSet result(*this);
  for (int i = 0; i < alphabetSize; ++i) {
    result.universum[i] = ~universum[i];
  }
  return result;
}