package com.exsoinn.practice.algorithm;

public class CheckPermutation {
  public boolean permutation(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() != s2.length()) {
      return false;
    }

    int pageSize = Byte.SIZE;
    byte[] switches = new byte[(Character.MAX_VALUE+1)/pageSize];

    for (int i = 0; i < s1.length(); i++) {
      int charCode = s1.charAt(i);
      switches[charCode/pageSize] ^= 1 << charCode%pageSize;
    }

    for (int i = 0; i < s2.length(); i++) {
      int charCode = s2.charAt(i);
      switches[charCode/pageSize] ^= 1 << charCode%pageSize;
    }

    // If String B was a permutation of A, the switches should all be 0.
    for (int i = 0; i < switches.length; i++) {
      if ((switches[i] & (~1 ^ 1)) != 0) {
        return false;
      }
    }
    return true;
  }
}
