package com.exsoinn.practice.algorithm;

public class KthToLast<E> {
  public ListNode<E> kthToLastIterative(ListNode<E> node, int k) {
    ListNode<E> front = node;
    ListNode<E> back = front;

    // Place front node k nodes in front of back node
    int cnt = 0;
    while (front != null && cnt < k) {
      front = front.next;
      ++cnt;
    }

    if (cnt != k) {
      // The list is not long enough to retrieve kth element from last (I.e. k is larger than list length)
      return null;
    } else if (front == null) {
      // List length is k
      return back;
    }


    while (front != null) {
      front = front.next;
      back = back.next;
    }
    return back;
  }
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
