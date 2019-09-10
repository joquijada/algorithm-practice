package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class TreeNode<T> {
  private TreeNode<T> left = null;
  private TreeNode<T> right = null;
  private T data;

  public TreeNode(T pData) {
    this.data = pData;
  }

  public static <U extends Comparable> TreeNode<U> buildTree(U[] ary) {
    TreeNode<U> root = new TreeNode<>(ary[0]);

    int cnt = 1;
    for (U i : ary) {
      if (cnt < 2) {
        ++cnt;
        continue;
      }
      //insert(root, i);
      insertIterative(root, i);
      ++cnt;
    }
    return root;

  }

  public static <U extends Comparable> void insert(TreeNode<U> root, U val) {
    if (null == root) {
      return;
    }

    if (val.compareTo(root.getData()) < 0) {
      if (null == root.getLeft()) {
        root.setLeft(new TreeNode<>(val));
      } else {
        insert(root.getLeft(), val);
      }
    } else {
      if (null == root.getRight()) {
        root.setRight(new TreeNode<>(val));
      } else {
        insert(root.getRight(), val);
      }
    }
  }


  public static <U extends Comparable> void insertIterative(TreeNode<U> root, U val) {
    if (null == root) {
      return;
    }

    while (true) {
      if (val.compareTo(root.getData()) < 0) {
        if (null == root.getLeft()) {
          root.setLeft(new TreeNode<>(val));
        } else {
          root = root.getLeft();
        }
      } else {
        if (null == root.getRight()) {
          root.setRight(new TreeNode<>(val));
        } else {
          root = root.getRight();
        }
      }
    }
  }

  public TreeNode<T> getLeft() {
    return left;
  }

  public void setLeft(TreeNode<T> left) {
    this.left = left;
  }

  public TreeNode<T> getRight() {
    return right;
  }

  public void setRight(TreeNode<T> right) {
    this.right = right;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
