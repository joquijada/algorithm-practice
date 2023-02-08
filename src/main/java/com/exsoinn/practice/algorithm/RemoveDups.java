package com.exsoinn.practice.algorithm;

public class RemoveDups<E> {
  public void removeDups(ListNode<E> head) {
    if (head == null) {
      return;
    }

    ListNode<E> cur = head;
    while (cur != null) {
      ListNode<E> start = cur;
      while (start.next != null && !start.next.data.equals(cur.data)) {
        start = start.next;
      }

      while (start.next != null && start.next.data.equals(cur.data)) {
        start.next = start.next.next;
      }

      if (start.next == null) {
        // Only if current node was compared against all the nodes in front of it, advance to next node
        // I.e. the second inner 'while' loop above ended before EOL if not all nodes after 'cur' are equal to it.
        cur = cur.next;
      }
    }
  }
}
