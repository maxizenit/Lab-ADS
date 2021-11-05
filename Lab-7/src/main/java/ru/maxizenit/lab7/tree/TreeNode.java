package ru.maxizenit.lab7.tree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class TreeNode<T extends Comparable<T>> {

  private T data;
  private TreeNode<T> left = null;
  private TreeNode<T> right = null;

  public TreeNode(T data) {
    this.data = data;
  }
}
