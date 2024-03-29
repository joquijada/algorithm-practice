package com.exsoinn.practice.algorithm;

public class PalindromePermutation {
  public boolean palindromePermutation(String s) {
    int letters = 0;

    for (int i = 0; i < s.length(); i++) {
      char cur = s.charAt(i);
      if (!isLetter(cur)) {
        continue;
      }

      // Toggle this letter on/off
      letters ^= 1 << Character.toLowerCase(cur) - 'a';
    }

    // Now check that at most only one letter has an odd number of appearances
    int cnt = 0;
    boolean oddCntFound = false;
    while (cnt < 32) {
      if ((letters & 1 << cnt) != 0) {
        if (oddCntFound) {
          return false;
        }
        oddCntFound = true;
      }
      ++cnt;
    }

    return true;
  }

  private boolean isLetter(char c) {
    if (Character.toLowerCase(c) < 'a' || Character.toLowerCase(c) > 'z') {
      return false;
    }
    return true;
  }

}
