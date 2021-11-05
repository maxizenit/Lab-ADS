#include "Graph.h"

Graph::Graph(int vertexCount) : vertexCount_(vertexCount) {
  colored_ = false;
  colors_ = new int[vertexCount_]{};

  edges_ = new int*[vertexCount_];
  for (int i = 0; i < vertexCount_; ++i) {
    edges_[i] = new int[vertexCount_]{};
  }
}

Graph::~Graph() {
  for (int i = 0; i < vertexCount_; ++i) {
    delete[] edges_[i];
  }
  delete[] edges_;
  delete[] colors_;
}

Graph* Graph::create(int vertexCount) {
  Graph* graph = nullptr;
  if (vertexCount > 0) {
    graph = new Graph(vertexCount);
  }
  return graph;
}

void Graph::clear() {
  colored_ = false;
  for (int i = 0; i < vertexCount_; ++i) {
    for (int j = 0; j < vertexCount_; ++j) {
      edges_[i][j] = 0;
    }
  }
}

void Graph::show() {
  int fieldWidth = (int)log10(vertexCount_ - 1) + 2;
  int lineLength = fieldWidth * (vertexCount_ + 1) + 2;

  cout << setw(fieldWidth) << ""
       << " |";

  for (int i = 0; i < vertexCount_; ++i) {
    if (colored_) {
      HANDLE hConsoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
      SetConsoleTextAttribute(hConsoleHandle, 0 << 4 | colors_[i]);
    }

    cout << setw(fieldWidth) << i;

    if (colored_) {
      HANDLE hConsoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
      SetConsoleTextAttribute(hConsoleHandle, 0 << 4 | 15);
    }
  }
  cout << endl;

  for (int i = 0; i < lineLength; ++i) {
    cout << '-';
  }
  cout << endl;

  for (int i = 0; i < vertexCount_; ++i) {
    if (colored_) {
      HANDLE hConsoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
      SetConsoleTextAttribute(hConsoleHandle, 0 << 4 | colors_[i]);
    }

    cout << setw(fieldWidth) << i;

    if (colored_) {
      HANDLE hConsoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
      SetConsoleTextAttribute(hConsoleHandle, 0 << 4 | 15);
    }

    cout << " |";
    for (int j = 0; j < vertexCount_; ++j) {
      cout << setw(fieldWidth) << edges_[i][j];
    }
    cout << endl;
  }
}

void Graph::addEdge(int firstVertex, int lastVertex) {
  colored_ = false;
  if ((firstVertex != lastVertex) &&
      (firstVertex >= 0 && firstVertex < vertexCount_) &&
      (lastVertex >= 0 && lastVertex < vertexCount_)) {
    edges_[firstVertex][lastVertex] = edges_[lastVertex][firstVertex] = 1;
  }
}

void Graph::removeEdge(int firstVertex, int lastVertex) {
  colored_ = false;
  if ((firstVertex != lastVertex) &&
      (firstVertex >= 0 && firstVertex < vertexCount_) &&
      (lastVertex >= 0 && lastVertex < vertexCount_)) {
    edges_[firstVertex][lastVertex] = edges_[lastVertex][firstVertex] = 0;
  }
}

void Graph::colorize() {
  for (int i = 0; i < vertexCount_; ++i) {
    colors_[i] = 0;
  }

  int** matrix = new int*[vertexCount_];
  for (int i = 0; i < vertexCount_; ++i) {
    matrix[i] = new int[vertexCount_];
    for (int j = 0; j < vertexCount_; ++j) {
      matrix[i][j] = edges_[i][j];
    }
  }

  int currentColor = 1;
  vector<int>* colorGroup = new vector<int>();

  for (int i = 0; i < vertexCount_; ++i) {
    if (colors_[i] > 0) {
      continue;
    }

    colorGroup->push_back(i);

    for (int j = i + 1; j < vertexCount_; ++j) {
      if (colors_[j] > 0) {
        continue;
      }

      if (matrix[i][j] == 0) {
        for (int k = 0; k < vertexCount_; ++k) {
          if (matrix[j][k] == 1) {
            matrix[i][k] = 1;
          }
        }
        colorGroup->push_back(j);
      }
    }

    for (auto it = colorGroup->begin(); it != colorGroup->end(); ++it) {
      colors_[*it] = currentColor;
    }

    ++currentColor;
    colorGroup->clear();
  }

  delete colorGroup;
  colored_ = true;
}