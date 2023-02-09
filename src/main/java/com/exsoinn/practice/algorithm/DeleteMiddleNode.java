package com.exsoinn.practice.algorithm;

public class DeleteMiddleNode<E> {
  public void deleteMiddelNode(ListNode<E> node) {
    if (node == null) {
      return;
    }

    node.data = node.next.data;
    node.next = node.next.next;
  }
}
