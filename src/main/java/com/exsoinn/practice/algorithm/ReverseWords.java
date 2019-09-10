package com.exsoinn.practice.algorithm;

/**
 * Traverses a string backwards, adding each word to a {@link StringBuilder}, for the purpose of
 * reversing the words.
 *
 * ASSUMPTION: Separator is a single blank space.
 *
 * @author josequijada
 */
public class ReverseWords {

  private static String reverseWords(String sen) {
    int cur = sen.length() - 1;
    int ci = cur;
    if (null == sen || sen.length() < 1) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    while (cur >= 0) {

      /*
       * Consume white-space up until and including last character of next word going backwards
       */
      while (ci >= 0 && sen.charAt(ci) == ' ') {
        --ci;
      }

      /*
       * Move ci to beginning of word - 1, and cur is positioned at end of word (calculated above)
       */
      cur = ci;
      while (ci >= 0 && sen.charAt(ci) != ' ') {
        --ci;
      }


      /*
       * Ready to add our word to the StringBuilder, remember that ci got moved to one
       * character before beginning of word, hence account for that in initial "i" value
       */
      for (int i  = ci + 1; i <= cur; i++) {
        sb.append(sen.charAt(i));
      }

      if (ci >= 0) {
        sb.append(' ');
      }

      --ci;
      cur = ci;
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    String[] ary = {"Today is a Sunny Day!", "Nice to see you"};
    for (String s : ary) {
      System.out.println("Original: '" + s + "', reversed: is '" + reverseWords(s) + "'");
      System.out.println("");
    }
  }
}
