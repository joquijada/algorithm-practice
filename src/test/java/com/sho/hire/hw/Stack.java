package com.sho.hire.hw;

/**
 * @author Showtime
 */

import java.util.*;

public class Stack<T> {

  private Object[] elements;
  private int size = 0;

  public Stack(int initialCapacity) {
    this.elements = new Object[initialCapacity];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
    //elements[++size] = e;
  }

  public Object pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    Object pop = elements[--size];
    elements[size] = null;
    return pop;
  }

  /**
   * Ensure space for at least one more element, roughly doubling the capacity each time the array
   * needs to grow.
   */
  private void ensureCapacity() {
    if (elements.length == size) {
      Object[] oldElements = elements;
      elements = new Object[2 * elements.length + 1];
      System.arraycopy(oldElements, 0, elements, 0, size);
    }
  }
}
