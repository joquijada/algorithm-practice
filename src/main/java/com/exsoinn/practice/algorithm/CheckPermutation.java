package com.exsoinn.practice.algorithm;

public class CheckPermutation {
  public boolean permutation(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() != s2.length()) {
      return false;
    }

    int switches = 0;

    for (int i = 0; i < s1.length(); i++) {
      switches ^= 1 << (Character.toLowerCase(s1.charAt(i)) - 'a');
    }

    for (int i = 0; i < s2.length(); i++) {
      switches ^= 1 << (Character.toLowerCase(s2.charAt(i)) - 'a');
    }

    return (switches & (~1 ^ 1)) == 0;
  }
}
