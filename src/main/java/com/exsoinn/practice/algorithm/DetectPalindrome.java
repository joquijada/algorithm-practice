package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class DetectPalindrome {

  private static boolean detectPalindrome(String pStr) {
    if (null == pStr || pStr.length() == 0) {
      return false;
    }

    pStr = stripNonAlpha(pStr);
    if (pStr.length() < 1) {
      return false;
    }

    if (pStr.length() == 1) {
      return true;
    }

    int head = 0;
    int tail = pStr.length() - 1;

    while (head < tail) {
      char f = Character.toLowerCase(pStr.charAt(head));
      char b = Character.toLowerCase(pStr.charAt(tail));

      if (f != b) {
        return false;
      }

      ++head;
      --tail;
    }

    return true;
  }

  private static String stripNonAlpha(String pStr) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < pStr.length(); i++) {
      char c = pStr.charAt(i);
      if (!characterIsAlphabetic(c)) {
        continue;
      }

      sb.append(c);
    }

    return sb.toString();
  }


  private static boolean characterIsAlphabetic(char pChar) {
    return (pChar >= 'A' && pChar <= 'Z') || (pChar >= 'a' && pChar <= 'z');
  }


  public static void main(String[] args) {
    String[] ary = {"blue", "blue jeans", "black   ", " ", "    ", "", "a", "toot",
            "Was it Eliot’s toilet I saw?"};
    for (String s : ary) {
      System.out.println("Is '" + s + "' a palindrome? " + detectPalindrome(s));
      System.out.println("");
    }
  }

}
