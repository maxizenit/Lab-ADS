package ru.maxizenit.lab8.tree;

import java.util.Iterator;
import java.util.LinkedList;

class TreeIterator<T extends Comparable<T>> implements Iterator<T> {

  private final LinkedList<T> elements = new LinkedList<>();

  public TreeIterator(TreeNode<T> root) {
    addElement(root);
  }

  private void addElement(TreeNode<T> node) {
    if (node != null) {
      addElement(node.getLeft());
      elements.add(node.getData());
      addElement(node.getRight());
    }
  }

  @Override
  public boolean hasNext() {
    return !elements.isEmpty();
  }

  @Override
  public T next() {
    return elements.removeFirst();
  }
}
