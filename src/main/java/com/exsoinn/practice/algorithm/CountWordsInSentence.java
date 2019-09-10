package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class CountWordsInSentence {

  private static int countWordsDsaVersion(String pStr) {
    if (null == pStr || pStr.length() == 0) {
      return 0;
    }
    boolean inWord = true;
    int wordCnt = 0;
    int index = 0;

    int len = pStr.length();
    // skip initial whitespace
    while (index < len && pStr.charAt(index) == ' ') {
      ++index;
    }

    // was the string just whitespace?
    if (index == len && pStr.charAt(index - 1) == ' ') {
      return 0;
    }

    while (index < len) {
      char ch = pStr.charAt(index);
      if (ch == ' ') {
        // skip all whitespace
        while (pStr.charAt(index) == ' ' && index < len - 1) {
          ++index;
        }
        inWord = false;
        /*
         * The only reason this block doesn't give a false positive when pStr is al blanks, is because
         * of the initial all blank space check at beginning of this method
         */
        ++wordCnt;
      } else {
        inWord = true;
      }
      ++index;
    }

    // last word may have not been followed by whitespace
    if (inWord) {
      ++wordCnt;
    }

    return wordCnt;

  }


  private static int countWords(String pStr) {
    if (null == pStr || pStr.length() == 0) {
      return 0;
    }
    int cnt = 0;
    int cur = 0;
    int len = pStr.length();
    char ch = pStr.charAt(cur);
    while (cur < len) {
      while (isBlank(ch) && cur < len) {
        ch = pStr.charAt(cur);
        ++cur;
      }

      if (cur >= len) {
        break;
      }

      ++cnt;

      while (!isBlank(ch) && cur < len) {
        ch = pStr.charAt(cur);
        ++cur;
      }
    }
    return cnt;
  }


  private static boolean isBlank(char c) {
    return ' ' == c;
  }


  public static void main(String[] args) {
    String[] ary = {"blue", "blue jeans", "black   ", " ", "    ", ""};
    for (String s : ary) {
      System.out.println("Word count of '" + s + "' is " + countWords(s));
      System.out.println("DSA Word count of '" + s + "' is " + countWordsDsaVersion(s));
      System.out.println("");
    }
  }

}
