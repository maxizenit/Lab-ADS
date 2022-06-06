﻿#include <Windows.h>

#include <iostream>

#include "TernaryTree.h"
using namespace std;

const string ADD_COMMAND = "add";
const string CLEAR_COMMAND = "clear";
const string PRINT_COMMAND = "print";
const char DIRECT_CHOICE = 'd';
const char REVERSE_CHOICE = 'r';
const char INTERNAL_CHOICE = 'i';
const string SHOW_COMMAND = "show";
const string GETHELP_COMMAND = "help";
const string COMMAND_LIST =
    "add - добавить слово в дерево\nprint - вывести содержимое дерева в "
    "определённом порядке обхода\nclear - очистить дерево\nshow - вывести "
    "дерево на экран\nexit - выйти "
    "из программы";
const string EXIT_COMMAND = "exit";
const string WRONG_INPUT_MESSAGE = "Неверный ввод!";

int main() {
  SetConsoleCP(1251);
  SetConsoleOutputCP(1251);

  TernaryTree *tree = new TernaryTree();
  string input;
  char choice;

  while (true) {
    cin >> input;

    if (input == ADD_COMMAND) {
      cout << "Введите добавляемое слово: ";
      cin >> input;
      tree->add(input);
    } else if (input == CLEAR_COMMAND) {
      tree->clear();
    } else if (input == PRINT_COMMAND) {
      cout << "Совершить прямой, обратный или внутренний обход? (d/r/i): ";
      cin >> choice;
      if (choice == DIRECT_CHOICE) {
        tree->directPrint();
      } else if (choice == REVERSE_CHOICE) {
        tree->reversePrint();
      } else if (choice == INTERNAL_CHOICE) {
        tree->internalPrint();
      } else {
        cout << WRONG_INPUT_MESSAGE << endl;
      }
    } else if (input == SHOW_COMMAND) {
      tree->show();
    } else if (input == GETHELP_COMMAND) {
      cout << COMMAND_LIST << endl;
    } else if (input == EXIT_COMMAND) {
      break;
    } else {
      cout << WRONG_INPUT_MESSAGE << endl;
    }
  }

  delete tree;
}