#include "ListSet.h"

void ListSet::sortArray(char* array, int arraySize) const {
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

void ListSet::pushBack(char newElement) {
  Node* newNode = new Node(newElement);
  if (!head) {
    head = newNode;
  } else {
    Node* current = head;
    while (current->next) {
      current = current->next;
    }
    current->next = newNode;
  }
}

ListSet::ListSet(const ListSet& prototype) {
  setTag(prototype.tag);
  alphabetFirstSymbol = prototype.alphabetFirstSymbol;
  alphabetSize = prototype.alphabetSize;
  size = prototype.size;
  head = nullptr;
  if (prototype.head) {
    this->head = new Node(prototype.head->data);
    Node* currentPrototypeNode = prototype.head->next;
    Node* currentThisNode = head;
    while (currentPrototypeNode) {
      currentThisNode->next = new Node(currentPrototypeNode->data);
      currentThisNode = currentThisNode->next;
      currentPrototypeNode = currentPrototypeNode->next;
    }
  }
}

ListSet::ListSet(char tag, char* buffer, int bufferSize,
                 char alphabetFirstSymbol, int alphabetSize) {
  head = nullptr;
  setTag(tag);
  this->size = bufferSize;
  char* privateBuffer = new char[size];
  copy(buffer, buffer + bufferSize, privateBuffer);
  sortArray(privateBuffer, size);
  if (size > 0) {
    head = new Node(privateBuffer[0]);
    Node* current = head;
    for (int i = 1; i < size; ++i) {
      current->next = new Node(privateBuffer[i]);
      current = current->next;
    }
  }
  delete[] privateBuffer;
  this->alphabetFirstSymbol = alphabetFirstSymbol;
  this->alphabetSize = alphabetSize;
}

ListSet::~ListSet() { clear(); }

void ListSet::setTag(char tag) { this->tag = tag; }

bool ListSet::contains(char element) const {
  Node* current = head;
  while (current) {
    if (element == current->data) {
      return true;
    }
    current = current->next;
  }
  return false;
}

void ListSet::print() const {
  cout << "Список " << tag << ":";
  Node* current = head;
  while (current) {
    cout << " " << current->data;
    current = current->next;
  }
  cout << endl;
}

void ListSet::clear() {
  size = 0;
  Node* current = head;
  while (head) {
    current = current->next;
    delete head;
    head = current;
  }
}

ListSet ListSet::operator&(const ListSet& other) const {
  ListSet result(*this);
  result.clear();

  char* buffer = new char[min(size, other.size)];
  int counter = 0;

  Node* currentThisNode = head;
  while (currentThisNode) {
    if (other.contains(currentThisNode->data)) {
      buffer[counter++] = currentThisNode->data;
    }
    currentThisNode = currentThisNode->next;
  }

  sortArray(buffer, counter);
  result.size = counter;

  if (counter > 0) {
    result.head = new Node(buffer[0]);
    Node* current = result.head;
    for (int i = 1; i < result.size; ++i) {
      current->next = new Node(buffer[i]);
      current = current->next;
    }
  }

  delete[] buffer;
  return result;
}

ListSet ListSet::operator|(const ListSet& other) const {
  ListSet result(*this);
  result.clear();

  char* buffer = new char[size + other.size];
  int counter = 0;

  Node* currentThisNode = head;
  while (currentThisNode) {
    buffer[counter++] = currentThisNode->data;
    currentThisNode = currentThisNode->next;
  }

  currentThisNode = other.head;
  while (currentThisNode) {
    if (!contains(currentThisNode->data)) {
      buffer[counter++] = currentThisNode->data;
    }
    currentThisNode = currentThisNode->next;
  }

  sortArray(buffer, counter);
  result.size = counter;

  if (counter > 0) {
    result.head = new Node(buffer[0]);
    Node* current = result.head;
    for (int i = 1; i < result.size; ++i) {
      current->next = new Node(buffer[i]);
      current = current->next;
    }
  }

  delete[] buffer;
  return result;
}

ListSet ListSet::operator~() const {
  ListSet result(*this);
  result.clear();
  result.size = alphabetSize - size;

  Node* currentResultNode = nullptr;
  for (int i = 0; i < alphabetSize; ++i) {
    char newSymbol = alphabetFirstSymbol + i;
    if (!contains(newSymbol)) {
      if (result.head) {
        currentResultNode->next = new Node(newSymbol);
        currentResultNode = currentResultNode->next;
      } else {
        result.head = new Node(newSymbol);
        currentResultNode = result.head;
      }
    }
  }

  return result;
}