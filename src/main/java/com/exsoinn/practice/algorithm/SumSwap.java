package com.exsoinn.practice.algorithm;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @author Jose Quijada
 */
public class SumSwap {
  public int[] sumSwap(int[] a, int[] b) {
    final int sumA = sum(a);
    final int sumB = sum(b);
    final int sumDiff = (sumA - sumB)/2;
    int idxA = 0;
    int idxB = 0;
    while (idxA < a.length && idxB < b.length) {
      int curA = a[idxA];
      int curB = b[idxB];

      int diff = curA - curB;
      if (sumDiff < diff) {
        /*
         * "diff" was too big, advance "b" array to make it smaller, since we are doing "curA - curB"
         */
        ++idxB;
      } else if (sumDiff > diff) {
        /*
         * "diff" was too small, advance "a" array to make it bigger, since we are doing "curA - curB"
         */
        ++idxA;
      } else {
        // Found our swap pair, return it
        int[] ret = new int[2];
        ret[0] = curA;
        ret[1] = curB;
        return ret;
      }
    }

    return null;
  }

  private int sum(int[] ary) {
    OptionalInt opt = Arrays.stream(ary).reduce(Integer::sum);
    return opt.getAsInt();
  }

}
