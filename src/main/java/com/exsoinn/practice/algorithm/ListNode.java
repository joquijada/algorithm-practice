package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author josequijada
 */
public class ListNode<T> {
  T data;
  ListNode<T> next = null;
  ListNode<T> prev = null;

  public ListNode() {
    this(null);
  }

  public ListNode(T pData) {
    this.data = pData;
  }

  public T getData() {
    return data;
  }

  public T data() {
    return getData();
  }

  public void data(T data) {
    this.setData(data);
  }

  public void setData(T data) {
    this.data = data;
  }

  public ListNode<T> getNext() {
    return next;
  }

  public ListNode<T> next() {
    return getNext();
  }

  public void setNext(ListNode<T> next) {
    this.next = next;
  }

  public void next(ListNode<T> next) {
    this.setNext(next);
  }



  public ListNode<T> getPrev() {
    return prev;
  }

  public void prev(ListNode<T> prev) {
    this.setPrev(prev);
  }

  public void setPrev(ListNode<T> prev) {
    this.prev = prev;
  }


  public static <U> ListNode<U> buildList(U[] ary) {
    ListNode<U> head = new ListNode<>();
    ListNode n = head;
    int cnt = 0;
    for (U thing : ary) {
      n.setData(thing);

      // Do not create an empty node at the end of the list, meaning that the node before
      // last should be the last node to which a non-null next reference gets assigned. Anything after
      // that will bear NULL net reference
      if (cnt < ary.length - 1) {
        ListNode<U> newNode = new ListNode<>();
        n.setNext(newNode);
        n = newNode;
      }
      ++cnt;
    }
    return head;
  }


  public static <U> ListNode<U> convertFromLinkedList(LinkedList<U> pList) {
    ListNode<U> headNode = null;
    ListNode<U> prevNode = null;
    for (U n : pList) {
      ListNode<U> curNode =new ListNode<>();
      if (null == headNode) {
        headNode = curNode;
      }
      curNode.setData(n);
      if (null != prevNode) {
        prevNode.setNext(curNode);
      }
      prevNode = curNode;
    }

    return headNode;
  }


  public static String converToString(ListNode<Integer> n) {
    Collection<Object> nums = new ArrayList<>();
    ListNode.printList(n, nums);
    System.out.println();
    return nums.stream().map(e -> e.toString()).collect(Collectors.joining(""));
  }

  /**
   * Prepend "num" nodes to front of passed in linked list, using "val"
   * as the value to pad with.
   * @param n
   * @param num
   * @param val
   * @param <U>
   * @return
   */
  public static <U> ListNode<U> pad(ListNode<U> n, int num, U val) {
    ListNode<U> newHead = n;
    for (int i = 0; i < num; i++) {
      newHead = new ListNode<>();
      newHead.next(n);
      newHead.data(val);
      n = newHead;
    }
    return newHead;
  }

  /**
   * Used a wild card because this method can print any type of {@link ListNode} (I.e. it invokes
   * {@link Object} methods only).
   * @param head
   */
  public static void printList(ListNode<?> head) {
    printList(head, null);
  }


  public static void printList(ListNode<?> head, Collection<? super Object> res) {
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



  public static int size(ListNode<?> n) {
    int cnt = 0;
    while (n != null) {
      ++cnt;
      n = n.getNext();
    }

    return cnt;
  }

}
