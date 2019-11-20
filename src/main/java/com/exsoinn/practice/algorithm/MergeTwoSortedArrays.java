package com.exsoinn.practice.algorithm;

import java.util.Arrays;

/**
 * @author josequijada
 */
public class MergeTwoSortedArrays {

  public static void merge(int[] a, int[] b, int lastIndexA) {

    int lenB = b.length;

    // This is the spot (0-based index) in the back of array "a"
    // where we will begin adding the numbers
    int right = lenB + lastIndexA;
    int idxA = lastIndexA;
    int idxB = lenB - 1;

    while (right >= 0) {

      // Select from the back of "b" all elements > than the element of "a" at current index in the back. If "a"
      // is already exhausted and "b" isn't, simply copy remaining "b" into the empty front slots of "a"
      while (idxB >= 0 && (b[idxB] >= a[idxA] || idxA < 0)) {
        a[right] = b[idxB];
        --idxB;
        --right;
      }

      // Do same as above, but for elements at the back of "a". If "b" has been exhausted and "a" hasn't,
      // simply "copy" remaining "a" into "a". This is just overwriting with the same values from "a"
      // whatever is towards the front. We're just basically interested in making sure "right" reaches
      // "> 0" value, which is the ending condition for enclosing outer while() loop. W/o this will end up
      // in infinite looping!!!
      while ((idxB >= 0 && idxA >= 0 && a[idxA] >= b[idxB]) || (idxA >= 0 && idxB < 0)) {
        a[right] = a[idxA];
        --idxA;
        --right;
      }
    }
  }

  public static void main(String[] args) {
    int[] a = new int[30];
    int lastIdxA = 2;
    a[0] = 4;
    a[1] = 43;
    a[lastIdxA] = 55;
    int[] b = {4, 7, 10, 23, 36};
    merge(a, b, lastIdxA);
    System.out.println("Merged arrays is " + Arrays.toString(a));
  }

}
