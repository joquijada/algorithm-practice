package com.exsoinn.practice.algorithm.util;

/**
 * @author josequijada
 */
public class Util {
  /**
   * Remove non-alpha characters from the input string.
   */
  public static String strip(String str) {
    int aVal = Character.getNumericValue('a');
    int zVal = Character.getNumericValue('z');
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      int val = Character.getNumericValue(c);
      if (val < aVal || val > zVal) {
        continue;
      }
      sb.append(c);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(Character.getNumericValue('A'));
  }
}
