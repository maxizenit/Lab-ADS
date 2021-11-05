#include "ArraySet.h"

void ArraySet::sortArray(char* array, int arraySize) const {
  for (int i = 0; i < arraySize - 1; ++i) {
    int minIndex = i;
    for (int j = i + 1; j < arraySize; ++j) {
      if (array[j] < array[minIndex]) {
        minIndex = j;
      }
    }
    if (i != minIndex) {
      char temp = array[i];
      array[i] = array[minIndex];
      array[minIndex] = temp;
    }
  }
}

ArraySet::ArraySet(const ArraySet& prototype) {
  alphabetFirstSymbol = prototype.alphabetFirstSymbol;
  alphabetSize = prototype.alphabetSize;
  size = prototype.size;
  set = new char[size];
  copy(prototype.set, prototype.set + prototype.size, set);
}

ArraySet::ArraySet(char tag, char* buffer, int bufferSize,
                   char alphabetFirstSymbol, int alphabetSize) {
  setTag(tag);
  this->size = bufferSize;
  set = new char[size];
  copy(buffer, buffer + bufferSize, set);
  sortArray(set, size);
  this->alphabetFirstSymbol = alphabetFirstSymbol;
  this->alphabetSize = alphabetSize;
}

ArraySet::~ArraySet() { delete[] set; }

void ArraySet::setTag(char tag) { this->tag = tag; }

bool ArraySet::contains(char element) const {
  for (int i = 0; i < size; ++i) {
    if (element == set[i]) {
      return true;
    }
  }
  return false;
}

void ArraySet::print() const {
  cout << "Массив " << tag << ":";
  for (int i = 0; i < size; ++i) {
    cout << " " << set[i];
  }
  cout << endl;
}

void ArraySet::clear() {
  size = 0;
  delete set;
}

ArraySet ArraySet::operator&(const ArraySet& other) const {
  ArraySet result(*this);
  result.clear();
  char* buffer = new char[min(size, other.size)];
  int counter = 0;

  for (int i = 0; i < size; ++i) {
    if (other.contains(set[i])) {
      buffer[counter++] = set[i];
    }
  }

  sortArray(buffer, counter);
  result.size = counter;
  result.set = new char[result.size];
  copy(buffer, buffer + result.size, result.set);

  delete[] buffer;
  return result;
}

ArraySet ArraySet::operator|(const ArraySet& other) const {
  ArraySet result(*this);
  result.clear();
  char* buffer = new char[size + other.size];
  int counter = 0;

  for (int i = 0; i < size; ++i) {
    buffer[counter++] = set[i];
  }
  for (int i = 0; i < other.size; ++i) {
    if (!contains(other.set[i])) {
      buffer[counter++] = other.set[i];
    }
  }

  sortArray(buffer, counter);
  result.size = counter;
  result.set = new char[result.size];
  copy(buffer, buffer + result.size, result.set);

  delete[] buffer;
  return result;
}

ArraySet ArraySet::operator~() const {
  ArraySet result(*this);
  result.clear();
  result.size = alphabetSize - size;
  result.set = new char[result.size];
  int counter = 0;

  for (int i = 0; i < alphabetSize; ++i) {
    char newSymbol = alphabetFirstSymbol + i;
    if (!contains(newSymbol)) {
      result.set[counter++] = newSymbol;
    }
  }

  return result;
}