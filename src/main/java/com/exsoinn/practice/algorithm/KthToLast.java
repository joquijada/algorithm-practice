package com.exsoinn.practice.algorithm;

public class KthToLast<E> {
  public Result kthToLast(ListNode<E> node, int k) {
    if (node == null) {
      return new Result();
    }

    Result r = kthToLast(node.next, k);
    ++r.k;
    if (r.k == k) {
      r.node = node;
    }

    return r;
  }

  static class Result<T> {
    int k = 0;
    T node = null;
  }
}
