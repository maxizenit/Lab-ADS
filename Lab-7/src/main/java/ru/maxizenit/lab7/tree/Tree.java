package ru.maxizenit.lab7.tree;

import java.util.Iterator;

public class Tree<T extends Comparable<T>> implements Iterable<T> {

  private TreeNode<T> root = null;
  private int size = 0;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (T element : this) {
      sb.append(element.toString());
    }

    return sb.toString();
  }

  @Override
  public Iterator<T> iterator() {
    return new TreeIterator<>(root);
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void clear() {
    root = null;
    size = 0;
  }

  public boolean contains(T element) {
    TreeNode<T> current = root;

    while (current != null) {
      if (current.getData().equals(element)) {
        return true;
      }

      if (current.getData().compareTo(element) > 0) {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }

    return false;
  }

  public void add(T newElement) {
    if (contains(newElement)) {
      return;
    }

    TreeNode<T> newNode = new TreeNode<>(newElement);

    if (isEmpty()) {
      root = newNode;
    } else {
      TreeNode<T> current = root;

      while (current != null) {
        if (current.getData().compareTo(newElement) > 0) {
          if (current.getLeft() == null) {
            current.setLeft(newNode);
            break;
          } else {
            current = current.getLeft();
          }
        } else {
          if (current.getRight() == null) {
            current.setRight(newNode);
            break;
          } else {
            current = current.getRight();
          }
        }
      }
    }

    ++size;
  }

  public Tree<T> union(Tree<T> other) {
    Tree<T> result = new Tree<>();

    for (T element : this) {
      result.add(element);
    }

    for (T element : other) {
      result.add(element);
    }

    return result;
  }

  public Tree<T> intersection(Tree<T> other) {
    Tree<T> result = new Tree<>();

    for (T c1 : this) {
      for (T c2 : other) {
        if (c1.equals(c2)) {
          result.add(c1);
        }
      }
    }

    return result;
  }

  public Tree<T> symmetricDifference(Tree<T> other) {
    Tree<T> result = new Tree<>();
    Tree<T> intersection = intersection(other);

    for (T element : this) {
      if (!intersection.contains(element)) {
        result.add(element);
      }
    }

    for (T element : other) {
      if (!intersection.contains(element)) {
        result.add(element);
      }
    }

    return result;
  }
}
