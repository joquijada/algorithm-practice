package com.exsoinn.practice.algorithm;

public class OneAway {
  public boolean oneAway(String s1, String s2) {
    int lengthDiff = s1.length() - s2.length();
    if (Math.abs(lengthDiff) > 1) {
      return false;
    }

    int idx1 = 0;
    int idx2 = 0;
    boolean editNeeded = false;
    while (idx1 < s1.length() && idx2 < s2.length()) {
      if (s1.charAt(idx1) == s2.charAt(idx2)) {
        // No edit needed
        ++idx1;
        ++idx2;
      } else {
        // edit need found
        if (lengthDiff > 0) {
          lengthDiff = 0;
          ++idx1;
        } else if (lengthDiff < 0) {
          lengthDiff = 0;
          ++idx2;
        } else {
          // same length strings
          ++idx1;
          ++idx2;
        }
        if (editNeeded) {
          return false;
        }
        editNeeded = true;
      }
    }

    return true;
  }
}
