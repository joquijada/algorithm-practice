package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author josequijada
 */
public class MedianTrackingList<T extends Number> {
  private final Heap<T> minHeap;
  private final Heap<T> maxHeap;
  private List<T> list = new ArrayList<>();
  private final Class<T> actualTypeParameterClass;

  public MedianTrackingList(Class<T> pActualTypeParam) {
    actualTypeParameterClass = pActualTypeParam;
    minHeap = new Heap(actualTypeParameterClass);
    maxHeap = new Heap(false, actualTypeParameterClass);
  }


  public void add(T pVal) {
    list.add(pVal);
    trackMedian(pVal);
  }



  private void trackMedian(T n) {
    if (minHeap.isEmpty() || n.intValue() >= minHeap.peek().intValue()) {
      minHeap.insert(n);
    } else if (maxHeap.isEmpty() || n.intValue() <= maxHeap.peek().intValue()) {
      maxHeap.insert(n);
    } else {
      // the new number coud be somewhere between, non-incusive, min heap max and max heap min. Account
      // for this scenario by picking any heap to insert to, make it the minHeap.
      minHeap.insert(n);
    }

    rebalance();
  }


  public Integer median() {
    if (minHeap.isEmpty() && maxHeap.isEmpty()) return null;

    Integer min = minHeap.isEmpty() ? null : minHeap.peek().intValue();
    Integer max = maxHeap.isEmpty() ? null : maxHeap.peek().intValue();
    if (null != min && null != max && min == max) {
      return min;
    } else if (null == min) {
      return max;
    } else if (null == max) {
      return min;
    } else {
      return (min + max)/2;
    }
  }


  private void rebalance() {
    if (Math.abs(minHeap.count() - maxHeap.count()) < 2) {
      return; // No rebalance necessary if different by no more than 1
    }

    Heap<T> src;
    Heap<T> target;


    // Decide what heap should supply, and which should receive. Obviously the one
    // with less elements should be the receiving one.
    int diff = minHeap.count() - maxHeap.count();
    if (diff > 0) {
      src = minHeap;
      target = maxHeap;
    } else {
      src = maxHeap;
      target = minHeap;
    }

    // Calculate how many elements each heap *should* have for equal size, given the total between the two
    int total = src.count() + target.count();
    int transferNum = (total >> 1) - target.count();

    for (int i = 1; i <= transferNum; i++) {
      T n = src.remove();
      target.insert(n);
    }
  }


  public static void main(String[] pArgs) {
    int[] ary = {5, 7, 10, 2, 6, 8};
    MedianTrackingList<Integer> list = new MedianTrackingList<>(Integer.class);
    for (Integer i : ary) {
      list.add(i);
    }
    System.out.println("Median is " + list.median());
  }

}