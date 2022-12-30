package com.exsoinn.practice.algorithm;

public class LinkedList<T> {

  public void insert(T data) {
    Node<T> n = new Node<>(data);
    n.next = null;
  }

  private class Node<T> {

    static String myStr = "";
    T data;
    private Node<T> prev;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
    }
  }
}
