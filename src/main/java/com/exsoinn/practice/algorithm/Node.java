package com.exsoinn.practice.algorithm;

import java.util.Collection;

/**
 * @author josequijada
 */
public class Node<T> {
  private T data;
  private Node<T> next = null;
  private Node<T> prev = null;

  public Node() {
    this(null);
  }

  public Node(T pData) {
    this.data = pData;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public Node<T> getPrev() {
    return prev;
  }

  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }


  public static <U> Node<U> buildList(U[] ary) {
    Node<U> head = new Node<>();
    Node n = head;
    int cnt = 0;
    for (U thing : ary) {
      n.setData(thing);

      // Do not create an empty node at the end of the list, meaning that the node before
      // last should be the last node to which a non-null next reference gets assigned. Anything after
      // that will bear NULL net reference
      if (cnt < ary.length - 1) {
        Node<U> newNode = new Node<>();
        n.setNext(newNode);
        n = newNode;
      }
      ++cnt;
    }
    return head;
  }

  /**
   * Used a wild card because this method can print any type of {@link Node} (I.e. it invokes
   * {@link Object} methods only).
   * @param head
   */
  public static void printList(Node<?> head) {
    printList(head, null);
  }


  public static void printList(Node<?> head, Collection<? super Object> res) {
    int size = 0;
    if (null != head) {
      size = size(head);
    }

    if (size == 0) {
      System.out.println("[]");
    }
    System.out.print("[");
    int cnt = 1;
    while (null != head) {
      Object data = head.getData();
      System.out.print(data);
      if (null != res) {
        res.add(data);
      }
      if (cnt < size) {
        System.out.print(", ");
      }
      head = head.getNext();
      ++cnt;
    }

    System.out.print("]");
  }


  public static int size(Node<?> n) {
    int cnt = 0;
    while (n != null) {
      ++cnt;
      n = n.getNext();
    }

    return cnt;
  }

}
