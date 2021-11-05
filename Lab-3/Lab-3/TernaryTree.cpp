#include "TernaryTree.h"

Node::Node(char data, Node* parent) {
  this->data = data;
  this->parent = parent;
  left = middle = right = nullptr;
}

Node::~Node() {
  if (left) delete left;
  if (middle) delete middle;
  if (right) delete right;
}

void Node::directVisit(string& buffer) {
  buffer += data;
  if (left) left->directVisit(buffer);
  if (middle) middle->directVisit(buffer);
  if (right) right->directVisit(buffer);
}

void Node::reverseVisit(string& buffer) {
  if (right) right->reverseVisit(buffer);
  if (middle) middle->reverseVisit(buffer);
  if (left) left->reverseVisit(buffer);
  buffer += data;
}

void Node::internalVisit(string& buffer) {
  if (left) left->internalVisit(buffer);
  buffer += data;
  if (middle) middle->internalVisit(buffer);
  if (right) right->internalVisit(buffer);
}

int TernaryTree::fillBuffersAndGetIndex(Node* currentNode, string buffers[],
                                        int& lastDepthCounter, int currentDepth,
                                        int width) {
  int index;
  if (currentDepth == depth_) {
    index = lastDepthCounter + (lastDepthCounter / 3);
    if (currentNode) {
      buffers[currentDepth - 1][index] = currentNode->data;
    }
    ++lastDepthCounter;
  } else {
    Node *left, *middle, *right;
    left = middle = right = nullptr;

    if (currentNode) {
      left = currentNode->left;
      middle = currentNode->middle;
      right = currentNode->right;
    }

    fillBuffersAndGetIndex(left, buffers, lastDepthCounter, currentDepth + 1,
                           width);
    index = fillBuffersAndGetIndex(middle, buffers, lastDepthCounter,
                                   currentDepth + 1, width);
    fillBuffersAndGetIndex(right, buffers, lastDepthCounter, currentDepth + 1,
                           width);

    if (currentNode) {
      buffers[currentDepth - 1][index] = currentNode->data;
    }
  }
  return index;
}

TernaryTree::TernaryTree() {
  root_ = nullptr;
  size_ = depth_ = 0;
}

int TernaryTree::size() { return size_; }

bool TernaryTree::empty() { return size_ == 0; }

TernaryTree::~TernaryTree() {
  if (root_) delete root_;
}

void TernaryTree::add(const string& word) {
  int wordSize = word.size();
  if (wordSize > 0) {
    int index = 0;
    Node* current;

    if (!root_) {
      root_ = new Node(word[index++], nullptr);
      depth_ = size_ = 1;
      current = root_;

      while (index < wordSize) {
        if (word[index] < current->data) {
          current->left = new Node(word[index++], current);
          current = current->left;
        } else if (word[index] == current->data) {
          current->middle = new Node(word[index++], current);
          current = current->middle;
        } else {
          current->right = new Node(word[index++], current);
          current = current->right;
        }
        ++depth_;
        ++size_;
      }
    } else {
      current = root_;
      int currentDepth = 1;

      while (index < wordSize) {
        if (word[index] < current->data) {
          if (!current->left) {
            current->left = new Node(word[index++], current);
            current = current->left;
            ++size_;
            ++currentDepth;
            break;
          } else if (word[index + 1] == current->left->data) {
            current = current->left;
            ++index;
            ++currentDepth;
          } else {
            if (current->middle) {
              current = current->middle;
              ++currentDepth;
            } else {
              current->middle = new Node(current->data, current);
              current = current->middle;
              ++size_;
              ++currentDepth;
            }
          }
        } else if (word[index] == current->data) {
          if (!current->middle) {
            current->middle = new Node(word[index++], current);
            current = current->middle;
            ++size_;
            ++currentDepth;
            break;
          } else if (word[index + 1] == current->middle->data) {
            current = current->middle;
            ++index;
            ++currentDepth;
          } else {
            if (current->middle) {
              current = current->middle;
              ++currentDepth;
            } else {
              current->middle = new Node(current->data, current);
              current = current->middle;
              ++size_;
              ++currentDepth;
            }
          }
        } else {
          if (!current->right) {
            current->right = new Node(word[index++], current);
            current = current->right;
            ++size_;
            ++currentDepth;
            break;
          } else if (word[index + 1] == current->right->data) {
            current = current->middle;
            ++index;
            ++currentDepth;
          } else {
            if (current->middle) {
              current = current->middle;
              ++currentDepth;
            } else {
              current->middle = new Node(current->data, current);
              current = current->middle;
              ++size_;
              ++currentDepth;
            }
          }
        }
      }

      while (index < wordSize) {
        if (word[index] < current->data) {
          current->left = new Node(word[index++], current);
          current = current->left;
        } else if (word[index] == current->data) {
          current->middle = new Node(word[index++], current);
          current = current->middle;
        } else {
          current->right = new Node(word[index++], current);
          current = current->right;
        }
        ++currentDepth;
        ++size_;
      }

      if (currentDepth > depth_) {
        depth_ = currentDepth;
      }
    }
  }
}

void TernaryTree::clear() {
  if (root_) delete root_;
  root_ = nullptr;
  size_ = depth_ = 0;
}

void TernaryTree::directPrint() {
  cout << "Прямой обход: ";
  if (root_) {
    string buffer;
    root_->directVisit(buffer);
    cout << buffer;
  }
  cout << endl;
}

void TernaryTree::reversePrint() {
  cout << "Обратный обход: ";
  if (root_) {
    string buffer;
    root_->reverseVisit(buffer);
    cout << buffer;
  }
  cout << endl;
}

void TernaryTree::internalPrint() {
  cout << "Внутренний обход: ";
  if (root_) {
    string buffer;
    root_->internalVisit(buffer);
    cout << buffer;
  }
  cout << endl;
}

void TernaryTree::show() {
  if (root_) {
    int width = 1;
    if (depth_ > 1) {
      width = pow(3, depth_ - 1) + pow(3, depth_ - 2) - 1;
    }

    string* buffers = new string[depth_];
    int lastDepthCounter = 0;
    for (int i = 0; i < depth_; ++i) {
      for (int j = 0; j < width; ++j) {
        buffers[i] += '.';
      }
    }

    fillBuffersAndGetIndex(root_, buffers, lastDepthCounter, 1, width);

    for (int i = 0; i < depth_; ++i) {
      cout << buffers[i] << endl;
    }

    delete[] buffers;
  }
}