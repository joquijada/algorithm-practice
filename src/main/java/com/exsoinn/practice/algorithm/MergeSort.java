package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class MergeSort {

  private static int[] mergeSort(int[] list) {
    if (null == list) {
      return null;
    }

    return doMergeSort(list, 0, list.length - 1);
  }


  private static int[] doMergeSort(int[] list, int start, int end) {
    if (start == end) {
      int[] ret = new int[1];
      ret[0] = list[start];
      return ret;
    }

    int end1 = (start + end)/2;
    int start2 = end1 + 1;

    return merge(doMergeSort(list, start, end1), doMergeSort(list, start2, end));
  }



  private static int[] merge(int[] lst1, int[] lst2) {
    int left = lst1.length;
    int right = lst2.length;
    int tot = left + right;
    int[] target = new int[tot];
    int idx = 0;

    while (1 == 1) {
      if (left <= 0 || right <= 0) {
        break;
      }

      int l = lst1.length - left;
      int r = lst2.length - right;

      if (lst1[l] < lst2[r]) {
        target[idx] = lst1[l];
        --left;
        ++idx;
      } else if (lst2[r] < lst1[l]) {
        target[idx] = lst2[r];
        --right;
        ++idx;
      } else {
        target[idx] = lst1[l];
        ++idx;
        target[idx] = lst2[r];
        --left;
        --right;
        ++idx;
      }
    }

    int[] rem = left > 0 ? lst1 : right > 0 ? lst2 : null;
    int remIdx = rem.length - (left > 0 ? left : right);

    if (null != rem) {
      for (int i = remIdx; i <= rem.length - 1; i++) {
        target[idx] = rem[remIdx];
        ++remIdx;
        ++idx;
      }
    }

    return target;
  }


  public static void main(String[] args) {
    int[] ary = {10, 15, 8, 13, 12};
    sortIt(ary);
    int[] ary2 = {10, 9, 8 ,7 ,6, 5};
    sortIt(ary2);
  }


  private static void sortIt(int[] ary) {
    int[] sorted = mergeSort(ary);
    System.out.println("Sorted is ");
    for (int i = 0; i < sorted.length; i++) {
      System.out.print(sorted[i] + ", ");
    }
    System.out.println();
  }
}
