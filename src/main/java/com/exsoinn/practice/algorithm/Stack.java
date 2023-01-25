package com.exsoinn.practice.algorithm;

public class Stack<E> {
  private Node<E> top = null;

  public E peek() {
    if (top == null) {
      return null;
    }
    return top.data;
  }

  public void push(E element) {
    Node<E> node = new Node<>();
    node.data = element;
    if (top != null) {
      node.next = top;
    }
    top = node;
  }

  public E pop() {
    if (top == null) {
      return null;
    }
    Node<E> ret = top;
    top = ret.next;
    return ret.data;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public int size() {
    int cnt = 0;
    Node<E> cur = top;
    while (cur != null) {
      cur = cur.next;
      ++cnt;
    }
    return cnt;
  }

  private static class Node<E> {
    private E data;
    private Node<E> next;
  }
}
