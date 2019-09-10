package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author josequijada
 */
public class FindCommonElementsInTwoSortedArrays {

  public static List<Integer> findCommon(int[] a, int[] b) {
    // TODO: Do NULL checks/empty checks on a and b
    //       as appropriate

    int lenA = a.length;
    int lenB = b.length;

    // Optimization: If the arrays don't overlap at all, cannot have common elements
    if (a[0] > b[lenB - 1] || b[0] > a[lenA - 1]) {
      return Collections.emptyList();
    }

    int idxA = 0;
    int idxB = 0;

    List<Integer> common = new ArrayList<>();

    while (idxB < lenB && idxA < lenA) {
      // Ignore leading elements in array "a" that are less than current element in array "b"
      while (idxA < lenA && a[idxA] < b[idxB]) {
        ++idxA;
      }

      // ...and vice-versa, but check array "a" was not exhausted above
      if (idxA < lenA) {
        while (idxB < lenB && b[idxB] < a[idxA]) {
          ++idxB;
        }
      }

      // Before checking if current elements of "a" and "b" are equal, checks we haven't reached end of
      // either array. It takes two to tango - need to be two elements there to compare
      if (idxB < lenB && idxA < lenA) {
        if (a[idxA] == b[idxB]) {
          common.add(a[idxA]);
        }
        ++idxA;
        ++idxB;
      }
    }

    return common;
  }

  public static void main(String[] args) {
    //int[] a = {23, 37, 43, 55, 545, 712, 999};
    int[] a = {1000, 10001};
    int[] b = {23, 45, 55, 456, 874, 999};
    System.out
            .println("Common elements between " + Arrays.toString(a) + " and " + Arrays.toString(b)
                    + " are: " + findCommon(a, b));
  }


}
