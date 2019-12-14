package com.exsoinn.practice.algorithm;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * <pre>
 * Problem: Given two integer arrays A and B, both are _sorted_, find a pair elements, one each per array that can be swapped so that the sum of each
 *   array is the same.
 *
 * Brainstorm:
 * Below is the formula that represents subtracting a member from sumA, adding it to sumB, and subtracting a member from sumB, and adding it to sumA.
 * sumA - a + b = sumB + a - b
 * sumA - sumB = 2a - 2b
 * (sumA - sumB)/2 = a - b
 *
 *
 * a: [1 5 5 6 7]
 * b: [2 4 6 8 10]
 *
 * sumA = 24
 * sumB = 30
 *
 * 24 - 30/2 = -3
 *
 * -3 < -1 (1 - 2) (oh no, the diff of the sums is less than the diff of the current ary members (the diff between the current members of the array was smaller),
 * what to do? Need to make the diff of the ary members bigger, how to do that? How can a diff be made larger? By increasing one of the operands. Therefore need to
 * pick the bigger member of the two arrays in the following iteration. If the diff was greater than the sum, then need to pick the smaller member in the two arrays
 * in the next iteration).
 *
 * -3 = -3 (1 - 4) // Next I can pick either 2 from `a` or 4 from `b`. I'll pick 4 from `b` to make the diff bigger.
 *
 * // Had I picked 2 from `a`, it would have ended up as per below, which is not what we want,
 * -3 < 0 (2 - 2)
 *
 * // If the formula were `(sumB - sumA)/2 = b - a`
 * 3 > 1  (2 - 1) // pick the next largest number, which is 5 from `a`?
 * 3 > -1 (4 - 5) // This is WRONG!!! Since we know arrays are in sorted order, and we are subtracting `a` from `b`, need to blindly advance `b`
 * to make the difference larger! We know that next member of `b` will be at least as big or bigger than current member. Again we advance `b` because the
 * formula is `b - a`. If it were the other way around (`a - b`), we'd advance `a` array instead.
 * </pre>
 *
 * @author Jose Quijada
 */
public class SumSwap {
  public int[] sumSwap(int[] a, int[] b) {
    final int sumA = sum(a);
    final int sumB = sum(b);
    if (sumA == sumB) {
      return null;
    }

    if ((sumB%2 == 0 && sumA%2 != 0) || (sumA%2 == 0 && sumB%2 != 0)) {
      return null;
    }
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
