package com.exsoinn.practice.algorithm;

public class LinkedList<E> {
  private Node<E> head = null;
  private Node<E> tail = null;

  /**
   * Searches for the given data in the list by traversing it, stopping at the first occurrence
   * of a node with the given data.
   *
   * @param data the data to search
   * @return true if found, false otherwise
   */
  public boolean contains(E data) {
    Node<E> cur = head;
    while(cur != null && !cur.data.equals(data)) {
      cur = cur.next;
    }
    return cur != null;
  }

  /**
   * Calculates the size of the number of nodes in the list
   * @return the number of nodes in the list
   */
  public int size() {
    Node<E> cur = head;
    int cnt = 0;
    while(cur != null) {
      ++cnt;
      cur = cur.next;
    }
    return cnt;
  }

  /**
   * Deletes the passed in element from list, if found. The algorithm is as follows:
   * <p>
   * Case 1: List is empty
   * Case 2: List contains only one element and element matches
   * Case 3: Element to remove is head node
   * Case 4: Element to remove is tail node
   * Case 5: Element to remove is somewhere between head and tail node
   * Case 6: Element to remove is not found
   *
   * @param data
   * @return true if element found, false otherwise
   */
  public boolean delete(E data) {
    if (head == null) {
      // Case 1
      return false;
    }
    if (head == tail && head.data.equals(data)) {
      // Case 2
      tail = head = null;
      return true;
    } else if (head.data.equals(data)) {
      // Case 3
      head = head.next;
      return true;
    }

    // At this point safe to assume head did not match, else we wouldn't have come here (I.e.head matched above)
    // hence perfectly OK to advance to next node after head, which could very well be NULL if we're talking about
    // a one element list.
    Node<E> cur = head.next;
    while(cur != null && !cur.data.equals(data)) {
      cur = cur.next;
    }

    if (tail == cur) {
      // Case 4
      tail = cur.prev;
      tail.next = null;
      return true;
    } else if (cur != null) {
      // Case 5
      cur.prev.next = cur.next;
      cur.next.prev = cur.prev;
    }

    // Case 6
    return false;
  }

  /**
   * Creates a new node with the passed in data and appends it to the tail of the list. The algorithm
   * is as follows:
   * Case 1: list is initially empty
   * Case 2: List is not empty (has one or more elements already)
   *
   * @param data
   * @return this list object, for chaining
   */
  public LinkedList<E> insert(E data) {
    Node<E> n = new Node<>(data);
    if (head == null) {
      // Case 1
      head = tail = n;
    } else {
      // Case 2
      n.prev = tail;
      tail.next = n; // or n.prev.next = n, same thing
      tail = n;
    }
    return this;
  }

  private class Node<E> {
    private E data;
    private Node<E> prev;
    private Node<E> next;

    public Node(E data) {
      this.data = data;
    }
  }
}
