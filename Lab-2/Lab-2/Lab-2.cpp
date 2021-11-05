#include <Windows.h>
#include <conio.h>

#include <iostream>
#include <thread>

#include "ArraySet.h";
#include "ListSet.h"
#include "UniversumSet.h"
#include "WordSet.h"
using namespace std;

const char ALPHABET_FIRST_SYMBOL = 'а';
const char ALPHABET_LAST_SYMBOL = 'я';
const int ALPHABET_SIZE = 32;
const int INPUT_BUFFER_SIZE = 50;

char *aBuffer, *bBuffer, *cBuffer, *dBuffer;
int aBufferSize, bBufferSize, cBufferSize, dBufferSize;

int testIterationsCount;

ArraySet *aArraySet, *bArraySet, *cArraySet, *dArraySet;
ListSet *aListSet, *bListSet, *cListSet, *dListSet;
UniversumSet *aUniversumSet, *bUniversumSet, *cUniversumSet, *dUniversumSet;
WordSet *aWordSet, *bWordSet, *cWordSet, *dWordSet;

bool isRussianLetter(char c) {
  return c >= ALPHABET_FIRST_SYMBOL && c <= ALPHABET_LAST_SYMBOL;
}

bool containsInArray(char element, char* array, int size) {
  for (int i = 0; i < size; ++i) {
    if (element == array[i]) {
      return true;
    }
  }
  return false;
}

char* generateBuffer(char tag, int& size, int seed) {
  cout << "Введите размер множества " << tag << ": ";
  while (true) {
    cin >> size;
    if (size < 0 || size > ALPHABET_SIZE) {
      cout << "Введите корректный размер: ";
    } else {
      break;
    }
  }

  srand(seed);
  char* result = new char[size];
  for (int i = 0; i < size; ++i) {
    char new_element;
    do {
      new_element = ALPHABET_FIRST_SYMBOL + rand() % ALPHABET_SIZE;
    } while (containsInArray(new_element, result, i));
    result[i] = new_element;
  }

  return result;
}

char* inputBuffer(char tag, int& size) {
  char* buffer = new char[INPUT_BUFFER_SIZE];
  cout << "Введите множество " << tag << ": ";
  cin >> buffer;

  size = 0;
  for (int i = 0; i < INPUT_BUFFER_SIZE; ++i) {
    if (isRussianLetter(buffer[i]) && !containsInArray(buffer[i], buffer, i)) {
      ++size;
    } else {
      buffer[i] = '0';
    }
  }

  for (int i = 0; i < INPUT_BUFFER_SIZE - 1; ++i) {
    if (buffer[i] == '0') {
      for (int j = i + 1; j < INPUT_BUFFER_SIZE; ++j) {
        if (buffer[j] != '0') {
          char temp = buffer[i];
          buffer[i] = buffer[j];
          buffer[j] = temp;
        }
      }
    }
  }

  char* result = new char[size];
  for (int i = 0; i < size; ++i) {
    result[i] = buffer[i];
  }
  delete[] buffer;

  return result;
}

void initializeSets() {
  aArraySet = new ArraySet('A', aBuffer, aBufferSize, ALPHABET_FIRST_SYMBOL,
                           ALPHABET_SIZE);
  bArraySet = new ArraySet('B', bBuffer, bBufferSize, ALPHABET_FIRST_SYMBOL,
                           ALPHABET_SIZE);
  cArraySet = new ArraySet('C', cBuffer, cBufferSize, ALPHABET_FIRST_SYMBOL,
                           ALPHABET_SIZE);
  dArraySet = new ArraySet('D', dBuffer, dBufferSize, ALPHABET_FIRST_SYMBOL,
                           ALPHABET_SIZE);

  aListSet = new ListSet('A', aBuffer, aBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);
  bListSet = new ListSet('B', bBuffer, bBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);
  cListSet = new ListSet('C', cBuffer, cBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);
  dListSet = new ListSet('D', dBuffer, dBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);

  aUniversumSet = new UniversumSet('A', aBuffer, aBufferSize,
                                   ALPHABET_FIRST_SYMBOL, ALPHABET_SIZE);
  bUniversumSet = new UniversumSet('B', bBuffer, bBufferSize,
                                   ALPHABET_FIRST_SYMBOL, ALPHABET_SIZE);
  cUniversumSet = new UniversumSet('C', cBuffer, cBufferSize,
                                   ALPHABET_FIRST_SYMBOL, ALPHABET_SIZE);
  dUniversumSet = new UniversumSet('D', dBuffer, dBufferSize,
                                   ALPHABET_FIRST_SYMBOL, ALPHABET_SIZE);

  aWordSet = new WordSet('A', aBuffer, aBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);
  bWordSet = new WordSet('B', bBuffer, bBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);
  cWordSet = new WordSet('C', cBuffer, cBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);
  dWordSet = new WordSet('D', dBuffer, dBufferSize, ALPHABET_FIRST_SYMBOL,
                         ALPHABET_SIZE);

  delete aBuffer, bBuffer, cBuffer, dBuffer;
  cout << endl;
}

void printSets() {
  aArraySet->print();
  bArraySet->print();
  cArraySet->print();
  dArraySet->print();
  ArraySet eArraySet = *aArraySet & *bArraySet & *cArraySet & ~*dArraySet;
  eArraySet.setTag('E');
  eArraySet.print();
  cout << endl;

  aListSet->print();
  bListSet->print();
  cListSet->print();
  dListSet->print();
  ListSet eListSet = *aListSet & *bListSet & *cListSet & ~*dListSet;
  eListSet.setTag('E');
  eListSet.print();
  cout << endl;

  aUniversumSet->print();
  bUniversumSet->print();
  cUniversumSet->print();
  dUniversumSet->print();
  UniversumSet eUniversumSet =
      *aUniversumSet & *bUniversumSet & *cUniversumSet & ~*dUniversumSet;
  eUniversumSet.setTag('E');
  eUniversumSet.print();
  cout << endl;

  aWordSet->print();
  bWordSet->print();
  cWordSet->print();
  dWordSet->print();
  WordSet eWordSet = *aWordSet & *bWordSet & *cWordSet & ~*dWordSet;
  eWordSet.setTag('E');
  eWordSet.print();
  cout << endl;
}

void timeTestArrays() {
  time_t start, end;
  start = clock();
  for (int i = 0; i < testIterationsCount; ++i) {
    *aArraySet&* bArraySet&* cArraySet & ~*dArraySet;
  }
  end = clock();
  cout << "Массивы: " << difftime(end, start) / CLOCKS_PER_SEC << " с" << endl;
}

void timeTestLists() {
  time_t start, end;
  start = clock();
  for (int i = 0; i < testIterationsCount; ++i) {
    *aListSet&* bListSet&* cListSet & ~*dListSet;
  }
  end = clock();
  cout << "Списки: " << difftime(end, start) / CLOCKS_PER_SEC << " с" << endl;
}

void timeTestUniversums() {
  time_t start, end;
  start = clock();
  for (int i = 0; i < testIterationsCount; ++i) {
    *aUniversumSet&* bUniversumSet&* cUniversumSet & ~*dUniversumSet;
  }
  end = clock();
  cout << "Универсумы: " << difftime(end, start) / CLOCKS_PER_SEC << " с"
       << endl;
}

void timeTestWords() {
  time_t start, end;
  start = clock();
  for (int i = 0; i < testIterationsCount; ++i) {
    *aWordSet&* bWordSet&* cWordSet & ~*dWordSet;
  }
  end = clock();
  cout << "Машинные слова: " << difftime(end, start) / CLOCKS_PER_SEC << " с"
       << endl;
}

void timeTest() {
  thread testThreads[]{thread(timeTestArrays), thread(timeTestLists),
                       thread(timeTestUniversums), thread(timeTestWords)};
  for (int i = 0; i < 4; ++i) {
    testThreads[i].join();
  }
}

int main() {
  SetConsoleCP(1251);
  SetConsoleOutputCP(1251);

  char choice;
  cout << "Сгенерировать множества? (y/n): ";
  cin >> choice;
  if (choice == 'y') {
    int seed = (int)time(0);
    aBuffer = generateBuffer('A', aBufferSize, seed);
    bBuffer = generateBuffer('B', bBufferSize, seed + 1);
    cBuffer = generateBuffer('C', cBufferSize, seed + 2);
    dBuffer = generateBuffer('D', dBufferSize, seed + 3);
  } else {
    aBuffer = inputBuffer('A', aBufferSize);
    bBuffer = inputBuffer('B', bBufferSize);
    cBuffer = inputBuffer('C', cBufferSize);
    dBuffer = inputBuffer('D', dBufferSize);
  }

  initializeSets();
  printSets();

  cout << "Запустить временной тест? (y/n): ";
  cin >> choice;
  if (choice == 'y') {
    cout << "Введите число итераций теста: ";
    cin >> testIterationsCount;
    cout << endl;
    timeTest();
  }

  delete aArraySet, bArraySet, cArraySet, dArraySet;
  delete aListSet, bListSet, cListSet, dListSet;
  delete aUniversumSet, bUniversumSet, cUniversumSet, dUniversumSet;
  delete aWordSet, bWordSet, cWordSet, dWordSet;

  _getch();
}