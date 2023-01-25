package com.exsoinn.practice.algorithm;

public class FindMiddle {
  public ListNode<Integer> findMiddleNode(ListNode<Integer> head) {
    if (head == null) {
      return head;
    }

    ListNode first = head;
    ListNode second = first;
    int cnt = 1;
    while (second != null && second.getNext() != null) {
      first = first.getNext();
      second = second.getNext().getNext();
      cnt += 2;
    }

    if (second == null) {
      ++cnt;
    }

    if (cnt%2 == 0) {
      return first.getNext();
    } else {
      return first;
    }
  }
}
