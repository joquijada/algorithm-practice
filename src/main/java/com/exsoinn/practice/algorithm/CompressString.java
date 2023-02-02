package com.exsoinn.practice.algorithm;

public class CompressString {
  public String compress(String s) {
    StringBuilder sb = new StringBuilder();
    int pos = 0;
    while (pos < s.length()) {
      int charCnt = 1;
      char curChar = s.charAt(pos);

      while ((pos + 1) < s.length() && curChar == s.charAt(pos + 1)) {
        curChar = s.charAt(++pos);
        ++charCnt;
      }

      sb.append(curChar);
      sb.append(charCnt);
      ++pos;
    }

    String ret = sb.toString();
    return ret.length() < s.length() ? ret : s;
  }

}
