#include <Windows.h>

#include <iostream>

#include "Graph.h"
using namespace std;

const string CREATE_COMMAND = "create";
const string CLEAR_COMMAND = "clear";
const string COLORIZE_COMMAND = "colorize";
const string ADD_COMMAND = "add";
const string REMOVE_COMMAND = "remove";
const string SHOW_COMMAND = "show";
const string EXIT_COMMAND = "exit";

int main() {
  SetConsoleCP(1251);
  SetConsoleOutputCP(1251);
  setlocale(LC_ALL, "Russian");

  string command;
  Graph *graph = nullptr;

  while (true) {
    cin >> command;
    if (command == CREATE_COMMAND) {
      cout << "Введите число вершин графа: ";

      int vertexCount;
      cin >> vertexCount;

      if (graph) {
        delete graph;
      }
      graph = Graph::create(vertexCount);
    } else if (command == CLEAR_COMMAND) {
      if (graph) {
        graph->clear();
      }
    } else if (command == COLORIZE_COMMAND) {
      if (graph) {
        graph->colorize();
      }
    } else if (command == ADD_COMMAND) {
      if (graph) {
        cout << "Введите через пробел первое и второе рёбра: ";

        int firstVertex, lastVertex;
        cin >> firstVertex >> lastVertex;

        graph->addEdge(firstVertex, lastVertex);
      }
    } else if (command == REMOVE_COMMAND) {
      if (graph) {
        cout << "Введите через пробел первое и второе рёбра: ";

        int firstVertex, lastVertex;
        cin >> firstVertex >> lastVertex;

        graph->removeEdge(firstVertex, lastVertex);
      }
    } else if (command == SHOW_COMMAND) {
      if (graph) {
        graph->show();
      }
    } else if (command == EXIT_COMMAND) {
      break;
    }
  }

  if (graph) {
    delete graph;
  }
}