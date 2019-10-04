package com.exsoinn.practice.algorithm;



/**
 * @author josequijada
 */
public class CompressString {
  public String compress(String pStr) {
    if (null == pStr || pStr.length() == 0) return pStr;

    int matrix[][] = new int[pStr.length()][26];

    int idx = 0;
    int pos = 0;
    while(idx < pStr.length()) {
      char prev = Character.toLowerCase(pStr.charAt(idx));
      int num = charToNum(prev);

      // The first time this character is seen, the while() condition will always
      // evaluate to "true" and program execution will enter into the loop. This captures
      // single occurrence of this character.
      // As soon as the next character is different, while() loop will exit
      while (idx < pStr.length() && Character.toLowerCase(pStr.charAt(idx)) == prev) {
        matrix[pos][num]++;
        ++idx;
      }

      // Variable "pos" keeps track of the position of the occurrence(s) of this
      // character in this string
      ++pos;
    }

    // If the "compressed" string did not shrink, return original. We basically
    // multiply the number of compressed characters by 2, because we have a secondary
    // character per compressed character in the string, namely the number of times
    // the character appears consecutively at the specified position
    if ((pos)*2 >= pStr.length()) {
      return pStr;
    }

    // Now convert the matrix to the final string
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= pos; i++) {
      for (int j = 0; j < matrix.length; j++) {
        // Find the next character, it will have > 0 count (at least 1)
        if (matrix[i][j] <= 0) continue;

        char c = numToChar(j);
        sb.append(c);
        sb.append(matrix[i][j]);
      }
    }

    return sb.toString();
  }


  private char numToChar(int n) {
    // Check if num should be converted to upper or lower-case
    //char base = ('A' + n) >= 'A' && ('A' + n) <= 'Z' ? 'A' : 'a';
    char base = 'a';
    return (char)(base + n);
  }


  /**
   * The passed in character is assumed to be a letter from the alphabet. The method converts it to a number
   * based at 0 + offset, where offset is the distance of input character from letter 'a'. For example, if you
   * pass in 'f', the method returns 5. Letter 'a' gives back 0.
   * @param c
   * @return
   */
  private int charToNum(char c) {
    // Respect case
    //char base = c >= 'A' && c <= 'Z' ? 'A' : 'a';
    char base = 'a';
    return Character.toLowerCase(c) - base;
  }


  public static void main(String[] pArgs) {
    String str = "aAAAAAAAAafffeeagGgaaabbB";
    CompressString cs = new CompressString();
    System.out.println(cs.compress(str));

    System.out.println("'A' is " + (int)'A');
    System.out.println("'Z' is " + (int)'Z');
    System.out.println("91 is " + (char)91);
    System.out.println("'a' is " + (int)'a');
    System.out.println("'z' is " + (int)'z');
    System.out.println("'A' - 'a' is " + ('A' - 'a'));
  }
}
