package com.exsoinn.practice.algorithm;

/**
 * My attempt at a very simple implementation of ArrayList in Java.
 *
 * NOT THREAD SAFE!!!
 */
public class ArrayList<E> {
  private Object[] ary = new Object[1];
  int numElements = 0;

  /**
   * Appends element to end of list. Returns this list object,
   * useful for chaining.
   * @param element
   * @return
   */
  public ArrayList<E> add(E element) {
    if (numElements >= ary.length) {
      resize();
    }

    ary[numElements++] = element;
    return this;
  }

  /**
   * Removes the first occurrence of the given element if found.
   */
  public boolean delete(E element) {
    for (int i = 0; i < ary.length; i++) {
      if (ary[i] != null && ary[i].equals(element)) {
        ary[i] = null;
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the given element is found in the list.
   */
  public boolean contains(E element) {
    for (int i = 0; i < ary.length; i++) {
      if (ary[i] != null && ary[i].equals(element)) {
        return true;
      }
    }
    return false;
  }


  /**
   * Returns how many elements are contained in the list.
   */
  public int size() {
    int size = 0;
    for (int i = 0; i < ary.length; i++) {
      if (ary[i] != null) {
        ++size;
      }
    }
    return size;
  }

  /**
   * Doubles the array size, copies elements from old to new array.
   */
  private void resize() {
    int curSize = ary.length;
    Object[] newAry = new Object[curSize*2];

    for (int i = 0; i < ary.length; i++) {
      newAry[i] = ary[i];
    }
    ary = newAry;
  }
}
