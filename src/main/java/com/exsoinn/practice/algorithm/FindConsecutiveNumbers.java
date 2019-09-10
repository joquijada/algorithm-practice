package com.exsoinn.practice.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: You have an array with a series of numbers.  Find consecutive numbers and list how frequently they occur
 * @author josequijada
 */
public class FindConsecutiveNumbers {
  public Map<Integer, Integer> consecutiveNumbers(int[] ary) {
    if (null == ary || ary.length == 0) return null;


    Map<Integer, Integer> retMap = new HashMap<>();


// 2 2 3 3 7 0 3 14 1 1 2 2 2 2 15 21 99
// cur = 2
// cur == ary[1] (2)? Yes
// cur == ary[2] (3)? No
// cur = 3
// cur == ary[3] (3)? Yes
// cur == ary[4] (7)? No
// cur == 7
// cur == ary[5] (0)? No
// cur = 0
// cur == ary[6] (3)? No

    int i = 0;
    int len = ary.length;
    while (i < len) {
      int cur = ary[i];
      ++i;
      Integer cnt = null;
      while (i < len && cur == ary[i]) {
        // This if() below is for helping us count the first occurrence of the repeated
        // number detected outside this while(). W/o it we'd always be counting
        // total number of occurrences - 1 (a bug)
        if (null == cnt) {
          if (retMap.containsKey(cur)) { // Not the first time this number has appeared consecutively
            cnt = retMap.get(cur);
            // Count the repeated occurrences of the number that got us inside this while() loop
            ++cnt;
          } else { // First time this number has appeared consecutively
            // See comment above "cnt" above, same applies here
            cnt = 1;
          }
        }
        retMap.put(cur, ++cnt);
        ++i; // Get ready to examine next number in the array
      }
    }

    return retMap;
  }

  public static void main(String[] pArgs) {
    int[] ary = {2, 2, 3, 3, 7, 0, 3, 14, 1, 1, 2, 2, 2, 2, 15, 21, 99};
    FindConsecutiveNumbers fcn = new FindConsecutiveNumbers();
    System.out.println(fcn.consecutiveNumbers(ary));
  }
}
