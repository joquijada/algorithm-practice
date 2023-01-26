package com.exsoinn.practice.algorithm;

public class IsPalindrome {
  public boolean isPalindrome(String s) {
    String sanitized = stripNonAlphaNum(s);
    int mid = sanitized.length()/2;
    for (int i = 0; i <= mid - 1; i++) {
      int left = i;
      int right = sanitized.length() - i - 1;
      if (sanitized.charAt(left) != (sanitized.charAt(right))) {
        return false;
      }
    }
    return true;
  }

  private String stripNonAlphaNum(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = Character.toLowerCase(s.charAt(i));
      if (c < 'a' || c > 'z') {
        continue;
      }
      sb.append(c);
    }
    return sb.toString();
  }
}
