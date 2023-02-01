package com.exsoinn.practice.algorithm;

public class URLify {
  public void urlify(char[] s, int size) {
    int pos = calculateNewLength(s, size) - 1;

    for (int i = size - 1; i >= 0; i--) {
      char cur = s[i];
      if (cur == ' ') {
        s[pos--] = '0';
        s[pos--] = '2';
        s[pos--] = '%';
      } else {
        s[pos--] = cur;
      }
    }
  }

  private int calculateNewLength(char[] s, int size) {
    int l = 0;
    for (int i = 0; i < size; i++) {
      if (s[i] == ' ') {
        l += 3;
      } else {
        ++l;
      }
    }
    return l;
  }
}
