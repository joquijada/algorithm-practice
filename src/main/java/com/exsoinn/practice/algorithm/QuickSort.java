package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author josequijada
 */
public class QuickSort {

  private static int[] quickSort(int[] ary) {
    if (null == ary || ary.length < 2) {
      return ary;
    }

    int med = ary[findMedianIndex(ary)];

    List<Integer> less = new ArrayList<>();
    List<Integer> equals = new ArrayList<>();
    List<Integer> greater = new ArrayList<>();

    for (int i = 0; i < ary.length; i++) {
      int cur = ary[i];
      if (cur < med) {
        less.add(cur);
      } else if (cur > med) {
        greater.add(cur);
      } else {
        equals.add(cur);
      }
    }
    return concat(quickSort(toArray(less)), toArray(equals), quickSort(toArray(greater)));
  }


  private static int findMedianIndex(int[] ary) {
    if (null == ary || ary.length == 0) {
      return -1;
    }

    if (ary.length < 3) {
      return 0;
    }

    int size = ary.length;
    int half = size/2;
    List<Integer> cands = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      int cur = ary[i];
      int gte = 0;
      for (int j = 0; j < size; j++) {
        int curInner = ary[j];
        if (curInner >= cur) {
          ++gte;
        }

        if (gte == half + 1) {
          cands.add(i);
          break;
        }
      }
    }

    return max(ary, cands);
  }

  private static int max(int[] ary, List<Integer> idx) {
    int max = Integer.MIN_VALUE;
    int idxMax = 0;
    for (Integer i : idx) {
      if (ary[i] > max) {
        max = ary[i];
        idxMax = i;
      }
    }

    return idxMax;
  }

  private static int[] concat(int[] a1, int[] a2, int[] a3) {
    int s = a1.length + a2.length + a3.length;
    int[] a = new int[s];
    List<int[]> l = new ArrayList<>();
    l.add(a1);
    l.add(a2);
    l.add(a3);
    int cnt = 0;
    for (int[] ary : l) {
      for (int i : ary) {
        a[cnt]= i;
        ++cnt;
      }
    }

    return a;
  }

  private static int[] toArray(List<Integer> l) {
    int[] ary = new int[l.size()];
    int cnt = 0;
    for (int i : l) {
      ary[cnt] = i;
      ++cnt;
    }
    return ary;
  }


  public static void main(String[] args) {
    int[] ary = {4, 75, 74, 2, 54};
    performSort(ary);
    int[] ary2 = {101, 100, 100};
    performSort(ary2);
    int[] ary3 = {999};
    performSort(ary3);
    int[] ary4 = {1000, 1};
    performSort(ary4);
    int[] ary5 = {1000, 1};
    performSort(ary5);
  }



  private static void performSort(int[] ary) {
    int[] sorted = quickSort(ary);
    for (int i : sorted) {
      System.out.print(i + ", ");
    }
    System.out.println();
  }
}
