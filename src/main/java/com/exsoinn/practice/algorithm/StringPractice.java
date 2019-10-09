package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class StringPractice {

  private static void testCharacterApi() {
    System.out.println("'A' numeric value is " + Character.getNumericValue('A'));
    System.out.println("'a' numeric value is " +Character.getNumericValue('a'));
  }

  public static String reverse(String pStr) {
    if (null == pStr || pStr.length() == 0) {
      return null;
    }

    char[] ary = pStr.toCharArray();
    for (int i = 0; i < ary.length / 2; i++) {
      int leftIdx = i;
      int rightIdx = ary.length - 1 - i;

      char save = ary[leftIdx];
      ary[leftIdx] = ary[rightIdx];
      ary[rightIdx] = save;
    }

    String ret = "";
    for (Character c : ary) {
      ret += c;
    }

    return ret;
  }


  private static void subString(String pStr, int start, int end) {
    System.out.println(
            String.format("Substring from index %d to index %d for string %s is ", start, end,
                    pStr));
  }

  public static void main(String[] pArgs) {
    int idx = 1;
    int s = 0;
    String str = "cd";
    //String subStr1 = str.substring(s, idx);
    String subStr1 = "";

    /*
     * (03/28/2019) Interesting, String.substring(int) will not throw
     * IndexOutOfBounds as long as the start index is not more
     * than 1 greater than max index of string. For example,
     * "cd".substring(2) will return empty string, but "cd".substring(3)
     * will throw IndexOutOfBoundsException
     */
    String subStr2 = str.substring(idx + 1);
    String rem = subStr1 + " " + subStr2;
    System.out.println("rem is " + rem);
    System.out.println("str.charAt(" + idx + ") (prefix) is " + str.charAt(idx));

    String toReverse = "This is awesome!!!";
    System.out.println("Reverse of " + toReverse + " is " + reverse(toReverse));

    String s2 = "abc";
    subString("abc", 0, 0);

    testCharacterApi();
  }

}
