package com.exsoinn.practice.algorithm;

/**
 * <pre>
 * Problem: Write a method to URL-encode spaces in a sring. The URL encoding for a space is '%20'. Must do this operation in place, meaning have to use the same array for it
 *
 *
 * Questions:
 *
 *
 * Brainstorm:
 * 1. Can do one pass to identify total number of spaces, multiply by 3, and shift the real string to back of array by that many positions. Then start at beginning of the real string, moving it characters to the front one at a time, creating the '%20' character sequence for each space character encountered as I do so.
 *
 * "God is good"
 * New len = 15
 * "               God is good"
 *
 * // To test the newBeginning logic
 *     1234
 *
 * Algorithm: See brainstorm above
 *
 * Remarks: Spent too much time figuring out if newBeginning calculation would be correct (maybe 5 minutes spent here). Next time bypass and tell interviewer I'll come back to check it.
 *
 * Runtime: O(N), where N is the size of the strings (the total number of characters)
 *
 * Space: O(1), since we're doing things in place (I.e. no auxiliary data structures being used to solve the problem)
 *
 *
 * Test:
 * str = Where are you?
 * len = 14
 *
 * spaceCnt = 2
 * totalLen = 2*3 + 14 = 20
 * back = 19
 *
 * // Shift to back
 * back = 19, i = 13
 * ary[19] = ?
 *
 * back = 18, i = 12
 * ary[18] = u
 *
 * back = 17, i = 11
 * ary[17] = o,
 *
 * back = 16, i = 10
 * ary[16] = y
 *
 * ...
 *
 * back = 6, i = 0
 * ary[6] = W
 *
 *
 * // Final pass
 * newBeginning = 20 - 14 = 6
 *
 * </pre>
 *
 * @author josequijada
 */
public class URLifyOld {

  String urlify(String str, final int len) {
    final char[] ary = str.toCharArray();

    /*
     * Make room in front of array to accommodate the expanded spaces.
     * Note: I didn't have to move the string to back of array. I could have just started
     *       copying each character to the back of array right away, expanding as
     *       I go along. See the other version where I make this improvement.
     */
    final int spaceCnt = countSpaces(str, len);
    final int totalLen = (spaceCnt * 2) + len;
    int back = totalLen - 1;
    for (int i = len - 1; i >= 0; i--) {
      ary[back] = ary[i];
      --back;
    }

    // Now copy the string back to the front, expanding each space as I go along

    // no need to make 0-base (I.e. subtract 1), because I _am_ interested in the first character
    // of the string that just got moved to the back
    final int newBeginning = totalLen - len;
    int front = 0;
    for (int i = newBeginning; i < totalLen - 1; i++) {
      if (ary[i] == ' ') {
        ary[front] = '%';
        ary[++front] = '2';
        ary[++front] = '0';
      } else {
        ary[front] = ary[i];
      }
      ++front; // advance the pointer to get reay for next character insertion
    }

    return new String(ary);
  }


  String urlifyImproved(String str, final int len) {
    final char[] ary = str.toCharArray();

    /*
     * Calculate how by how many spaces to the right the string will grow
     * due to the escaping of the space characters.
     */
    final int spaceCnt = countSpaces(str, len);

    // Since each space will grow by two, multiply each space by two
    // and add that to current length
    final int totalLen = (spaceCnt * 2) + len;
    int back = totalLen - 1;

    // If there are extra spaces beyond the length of the URLified string, add
    // a NULL character to signify that spaces beyond are not escaped
    if (ary.length > totalLen) {
      ary[totalLen] = '\0';
    }

    // Start from back (right) side of array, expanding (I.e. escaping)
    // space characters
    for (int i = len - 1; i >= 0; i--) {
      if (ary[i] == ' ') {
        ary[back] = '0';
        ary[--back] = '2';
        ary[--back] = '%';
      } else {
        ary[back] = ary[i];
      }
      --back;
    }

    return new String(ary);
  }


  /**
   * Why does this method need the length to traverse to? Why can't it just use the length of the
   * input string? Because remember, the problem statement indicates that string will have extra
   * spaces at the back so that the expanded string fits. If we didn't limit the space count to the
   * string real length, we'd be erroneously counting the extra spaces provided!!!
   *
   * @param str
   * @param len
   * @return
   */
  private int countSpaces(String str, int len) {
    int cnt = 0;
    for (int i = 0; i < len; i++) {
      if (str.charAt(i) == ' ') {
        ++cnt;
      }
    }

    return cnt;
  }
}
