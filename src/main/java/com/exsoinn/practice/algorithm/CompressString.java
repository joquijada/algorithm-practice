package com.exsoinn.practice.algorithm;



/**
 * <pre>
 * Problem: Write a method that will compress a string by collapsing consecutive
 * occurences into one, apending a number indicating this number. For example
 * aaabbrrddsssj becomes a3b2r2d2s3j1
 *
 * If the string size were to stay the same or increase, return the original string
 *
 *
 * Questions:
 * 1. Should this be case sensitive?
 * 2. How long can the string potentially be? Very long? Or reasonably short?
 *
 Brainstorm:
 Example: In: bbrtttrrbbbb, Compressed: b2rt3r3b4

 Example: In: abcd, Compressed: a1b1c1d1 (resulting string is longer!!! Return original string in those cases)


 Example: In: cccaaacc, Compressed: c2a2c2


 1. Can I use an array, the array index is the letter numeric value, and the value at that index is the count? But then how do I keep track of the letter positions in the original string? Can a secondary array help?


 Position array (what character to print for at that position of the string):
 [0 1 2 3 ...] (initialize to size of the string)

 Each position in array above points to another array of the alphabet a-z. It contains count of letter to output at that position. If the resulting string turns out to be longer than original (see second example above), return the original uncompressed string.

 Test:
 (Edge case when we reach end of string, and that last two chars are the same, are we accounting for that one last character in the char occurrence count?)
 In: "aa"
 idx:0 < strLen-1:1? Yes, charOccurrenceCnt = 2
 idx:1 < strLen-1:1? No

 idx:2 < strLen2? No

 pos:1 >= strLen-1:1
 * </pre>
 * @author josequijada
 */
public class CompressString {
  String compress(String str) {
    final int strLen = str.length();
    // Add one because the subtraction below "chops off" one of the alphabet characters
    final int alphabetSize ='z' - 'a' + 1;
    final int[][] chars = new int[strLen][alphabetSize];

    int idx = 0;
    int pos = 0;
    while (idx < strLen && pos < chars.length) {
      int charOccurrenceCnt = 1;
      final char curChar = Character.toLowerCase(str.charAt(idx));
      int curCharVal = curChar - 'a';

      // Helper inner while() loop to keep advancing while cur character is same as the next one,
      // keeping track of the occurrence count of the char
      while (idx < strLen - 1
              && Character.toLowerCase(curChar) == Character.toLowerCase(str.charAt(idx + 1))) {
        ++charOccurrenceCnt;
        ++idx;
      }

      // Remember that above while() breaks out when idx comes within one position of end of str,
      // account for that
      ++idx;

      // Store the occurrence count for this character
      chars[pos][curCharVal] = charOccurrenceCnt;
      ++pos;
    }

    if (pos*2 >= strLen - 1) { // The "compressed" string stayed same size or is longer than orig, return original
      return str;
    } else {
      StringBuilder bldr = new StringBuilder();
      for (int i = 0; i < chars.length; i++) {
        // Find the alphabet letter to insert at this position, along with the occurrene count of such
        for (int j = 0; j < chars[i].length; j++) {
          if (chars[i][j] > 0) {
            // Convert back the numeric value to alpha character
            char c = (char) ('a' + j);
            bldr.append(c);
            bldr.append(chars[i][j]);
          }
        }
      }
      return bldr.toString();
    }
  }


  public String compressEarlier(String pStr) {
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
    char c = (char) 10;
    System.out.println("char is " + c);
    /*String str = "aAAAAAAAAafffeeagGgaaabbB";
    CompressString cs = new CompressString();
    System.out.println(cs.compressEarlier(str));

    System.out.println("'A' is " + (int)'A');
    System.out.println("'Z' is " + (int)'Z');
    System.out.println("91 is " + (char)91);
    System.out.println("'a' is " + (int)'a');
    System.out.println("'z' is " + (int)'z');
    System.out.println("'A' - 'a' is " + ('A' - 'a'));*/
  }
}
