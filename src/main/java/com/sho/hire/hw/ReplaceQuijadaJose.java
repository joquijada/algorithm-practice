package com.sho.hire.hw;


/**
 *
 */
public class ReplaceQuijadaJose {

  public ReplaceQuijadaJose() {
  }



  /**
   *  In <strong>case sensitive manner</strong> replaces all occurrences of "needle" in "haystack", and then reverses the words, before finally
   *  returning the modified string.
   *  <br/><br/>
   *  <strong>Note: If the input haystack/sentence contains excess spaces, either leading/trailing or in-between words, those will get trimmed. An example can help better
   *  illustrate:</strong><br/><br/>
   *  In:  "   I    am     Sam   "<br/>
   *  Out: "Sam am I"
   * @param haystack the sentence to operate on
   * @param needle the sub-string to search in the haystack
   * @param replacement the sub-string that will replace all occurrences of "needle", if any found
   * @return Haystack with search/ replacements made if any, and words reversed
   */
  public String ecalpeResrever(final String haystack, final String needle, final String replacement) {
    String newHaystack = replace(haystack, needle, replacement);
    return reverse(newHaystack);
  }



  /**
   *  <pre>
   *  Replaces all occurrences of "search" with "replacement". If any of the arguments is NULL or
   *  empty, the passed in "str" is silently returned as is! The exception is that "replacement" can just be an empty string
   *  (""), which will then cause all occurrences of "search" to be replaced with an empty string.
   *
   *  Algorithm:
   *  At each character of input "str", scan the next window of X characters, inclusive against the X characters of
   *  "search". If all characters in both sequences match, advance the next character right after the just matched sequence, use the
   *  "replacement" as the string to append next into the return string, and
   *  repeat the process. If the sequences did not match, just append current character of "str" to the return string, and move on to next
   *  character.
   *  </pre>
   * @param str The target string
   * @param search What to search for
   * @param replacement Use this to replace each occurrence of "search"
   * @return String with replacements made
   */
  private String replace(final String str, final String search, final String replacement) {
    if (isEmpty(str) || isEmpty(search) || null == replacement) {
      return str;
    }

    // Used to capture the string after all replacements, if any
    final StringBuilder sb = new StringBuilder();

    final int searchLen = search.length();
    final int strLen = str.length();
    int i = 0;
    while (i < strLen) {
      // Check if the next sequence of characters matches "search", and if so,
      // append "replacement" to the StringBuilder; else just add the current character and move
      // on to next character.
      // Variable "la" is a helper look-ahead "pointer" to compare one-by-one
      // the next "search.length()" characters of "search" and "str"
      int la = i;
      int cnt = 0;

      /*
       * Is there even enough "runway" left in the input string against
       * which to compare the search string? If not don't even bother
       */
      if (searchLen <= (strLen - i)) {
        while (la < strLen && cnt < searchLen) {
          if (str.charAt(la) != search.charAt(cnt)) {
            break;
          }
          ++cnt;
          ++la;
        }
      }

      // A needle in the haystack found!?!?!? Replace it...
      if (cnt == searchLen) {
        sb.append(replacement);
        // Fast-forward "i" to bypass the sub-string just "replaced", by basically advancing "i"
        // by "searchLen" steps (the length of the search string), which will basically
        // put "i" at the character right after the character sequence just replaced
        i += searchLen;
      } else {
        // A needle not found this time around :-(, append this character and advance
        // to the next, and continue our search for a "needle"
        sb.append(str.charAt(i));

        ++i;
      }
    }

    return sb.toString();
  }

  private boolean isEmpty(String s) {
    return null == s || s.length() < 1;
  }



  /**
   * <pre>
   * This method reverses the words in the passed in sentence.
   *
   * Algorithm:
   * Scan "haystack" right to left, appending each word to the end of a StringBuilder initialized to
   * an empty string. This has the effect of reversing the words contained in in the passed in "sentence".
   *
   * Runtime: O(N), where N is the number of characters in the input string
   *
   * </pre>
   * @param sentence The sentence which words to reverse
   * @return Sentence with words reversed
   */
  private String reverse(final String sentence) {
    if (isEmpty(sentence)) {
      return sentence;
    }

    final int len = sentence.length();
    int start = len - 1;
    int end = start;
    StringBuilder sb = new StringBuilder();

    while (start >= 0) {
      // Skip trailing spaces to position "end"" at end of the
      // next word in "haystack" from right to left
      while (end >= 0 && sentence.charAt(end) == ' ') {
        --end;
      }

      start = end;

      // Now position "start" at the beginning of the word
      while (start >= 0 && sentence.charAt(start) != ' ') {
        --start;
      }


      /*
       * The "start" and "end" of word has been identified; now we're ready to
       * copy it character-by-character to the StringBuilder.
       * Observe that "start" got decremented one too many times in while() loop above,
       * because once inside the "while()" loop above, we decrement "start"
       * *before* checking if it's a non-space character. Hence the reason that "for()" loop
       * below begins at "start + 1"
       */
      for (int i = start + 1; i <= end; i++) {
        sb.append(sentence.charAt(i));
      }

      // Add the word separator character
      if (start > 0) {
        sb.append(' ');
      }

      /*
       * Prepare for the next iteration. Basically decrement "start" by one
       * since anything from "start" on the the right has already been processed. The "end"
       * pointer gets also set to the new "start" to repeat the process anew.
       */
      --start;
      end = start;
    }

    /*
     * Check if we have added one space too many. This will happen when the input string has leading space, for example "   my sentence"
     */
    if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
      sb.deleteCharAt(sb.length() - 1);
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    ReplaceQuijadaJose replaceQuijadaJose = new ReplaceQuijadaJose();
    if (null == args || args.length != 3) {
      System.err.println("Usage: java -cp . com.sho.hire.hw.ReplaceQuijadaJose \"<haystack/sentence>\" \"<needle/search>\" \"<replacement>\"");
      System.exit(1);
    }
    System.out.println(replaceQuijadaJose.ecalpeResrever(args[0], args[1], args[2]));
  }

}
