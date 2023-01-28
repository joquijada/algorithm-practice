package com.exsoinn.practice.algorithm;

import java.lang.reflect.Array;

/**
 * Implementation of a Heap, algortihms for insertion, deletion and heapification learned from
 * https://apps2.mdp.ac.id/perpustakaan/ebook/Karya%20Umum/Dsa.pdf.
 * TODO: Write unit test case
 * @author josequijada
 */
public class Heap<T extends Number> {
  // By default, it's a min heap
  private boolean min = true;

  private final static int MAX = 100;

  // Fix at 100 items max to avoid having to resize array
  private final T[] ary;

  private int count = 0;

  public Heap(Class<T> pActualTypeParam) {
    this(true, pActualTypeParam);
  }

  public Heap(boolean pMin, Class<T> pActualTypeParam) {
    min = pMin;
    /*
     * To capture at run time the class of the actual generic formal parameter type, see
     * https://stackoverflow.com/questions/3437897/how-to-get-a-class-instance-of-generics-type-t which is
     * where I got this from. This is used to tell this class the component/class type to use for the
     * array that will back up this heap.
     */
    ary = (T[]) Array.newInstance(pActualTypeParam, MAX);
  }



  /**
   * Currently only deletion of top item supported (I.e. the item at index 0).
   */
  public T remove() {
    if (count < 1) {
      return null;
    }

    T retVal = ary[0];
    if (count > 1) {
      // If more than one element currently stored in heap, move the last to the location
      // of the element just deleted
      ary[0] = ary[count - 1];
    }
    --count;
    heapifyAfterRemove(0);
    return retVal;
  }


  public void insert(T pVal) {
    if (count >= 100) {
      throw new UnsupportedOperationException("Heap already full, only a max of " + MAX + " items allowed");
    }
    ary[count] = pVal; // add new element at end of list
    ++count;
    if (count > 1) {
      heapifyAfterInsert();
    }
  }


  /**
   * Gives whatever is stored at the top of the heap (this will always return Frank Sinatra ;-).
   */
  public T peek() {
    return ary[0];
  }


  private void heapifyAfterRemove(int pStart) {
    if (count < 2) { // Does not make sense to heapify when only a single item in heap
      return;
    }

    int parent = pStart;
    int left = child(parent, true);
    int right = child(parent, false);
    Integer parentVal = ary[parent].intValue();
    Integer leftVal = ary[left].intValue();
    Integer rightVal = ary[right].intValue();
    int leftTestRes = parentVal.compareTo(leftVal);
    int rightTestRes = parentVal.compareTo(rightVal);
    while(left < count && ((min && leftTestRes > 0 || rightTestRes > 0) || (!min && leftTestRes < 0 || rightTestRes < 0))) {
      int swapSrc;
      if (min) {
        if (leftVal < rightVal) {
          swapSrc = left;
        } else {
          swapSrc = right;
        }
      } else {
        if (leftVal > rightVal) {
          swapSrc = left;
        } else {
          swapSrc = right;
        }
      }
      swap(swapSrc, parent);
      parent = swapSrc;
      // repeat process but for new parent's left and right children; parent got updated
      // above to either left or right depending on which was less (or more in case this is a max heap)
      left = child(parent, true);
      right = child(parent, false);

      if (left < count) {
        leftVal = ary[left].intValue();
        rightVal = ary[right].intValue();
        leftTestRes = parentVal.compareTo(leftVal);
        rightTestRes = parentVal.compareTo(rightVal);
      }
    }
  }


  public int count() {
    return count;
  }



  public boolean isEmpty() {
    return count < 1;
  }


  private int child(int n, boolean left) {
    return 2*n + (left ? 1 : 2);

  }

  private int parent(int n) {
    return (n - 1)/2;
  }


  private void heapifyAfterInsert() {
    if (count < 2) { // Does not make sense to heapify when only a single item in heap
      return;
    }

    int i = count - 1; // find last element, which is the newly inserted one
    int parent = parent(i);
    while (i > 0 && (min && ary[i].intValue() < ary[parent].intValue()) || (!min && ary[i].intValue() > ary[parent].intValue())) {
      swap(i, parent);
      i = parent;
      parent = parent(i);
    }

  }


  private void swap(int idx1, int idx2) {
    T save = ary[idx1];
    ary[idx1] = ary[idx2];
    ary[idx2] = save;
  }
}
