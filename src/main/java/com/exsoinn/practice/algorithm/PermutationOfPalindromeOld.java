package com.exsoinn.practice.algorithm;

import com.exsoinn.practice.algorithm.util.Util;

/**
 * <pre>
 * Problem: Given a string, write a function to determine if it is a permutation of a palindrome. In
 * other words, is it a palindrome but scrambled? Example:
 * Input: "Tact Coa"
 * Output: true ("taco cat", "atco cta", etc. are permutations of input string that are also
 *   palindromes)
 *
 *
 * Questions:
 * 1. Can string contain spaces and/or permutation?
 * 2. Does case matter? Example: Is weryreW a permutation, even though W is upper case?
 *
 *
 * Brainstorm:
 * 1. Can try brute force by calculate permutations, stopping at the first one that is a palindrome. This would require two functions, one to generate the permutations, and a helper one to check if it s a palindrome or not.
 * 2. Permutations: abc
 *
 * Tree representation of recursive call stack:
 *
 *                                f("", abc)-----------------------------------------\
 *                              /          \                                          \
 *                       f(a, bc)          f(b, ac)-----------\                    f(c, ab)
 *                      /       \               /              \                    /     \
 *               f(ab, c)       f(ac, b)      f(ba, c)       f(bc, a)        f(ca, b)   f(cb, a)
 *                 /                \          /                 \               /          \
 *             f(abc, "")        f(acb, "")  f(bac, "")       f(bca, "")   f(cab, "")       f(cba, "")
 *
 *
 *
 *
 * Alternate representation of recursive call stack:
 * abc, pref=""
 *   bc, pref="a"
 *     c, pref="ab"
 *       "", pref="abc"
 *     b, pref="ac"
 *       "", pref="acb"
 *   ac, pref="b"
 *     c, pref="ba"
 *       "", pref="bac"
 *     a, pref="bc"
 *       "", pref="bca"
 *   ab, pref="c"
 *     b, pref="ca"
 *       "", pref="cab"
 *     a, pref="cb"
 *       "", pref="cba"
 *
 *
 * Algorithm: See Brainstorm above
 *
 *
 * Runtime:
 * isPalindromePermutationHorrendous():
 * let S equal string length
 * - There are S! permutations. On each permutation calculated, we're checking if it's a palindrome or not, which takes O(S) (eventhough we're traversing half of the array, it doesn't matter for purposes of big-O). That makes it O(S * S!)
 * - On each recursive call, we're concatenating a string of length S. How many recursive calls to permutationHelper are there? Each leaf node is attached to a path of S nodes. There are a total of S! leaf nodes, namely the number of permutations. So that's S * S! calls of permutationHelper() at the most, giving O(S * S * S!)
 * - That gives O((S * S!) + (S * S * S!)) = O(S! * (S * S + S)) (drop non-dominant terms) = O(S! * S * S) = O((S + 2)!) (at worst)
 *
 * The reason performance is awful for this algorithm is that I'm calculating the full set of permutations just to see if one permutation happens to be a palindrome!!!
 *
 * isPalindromePermutationImproved(): O(S), where S is the string length. Notice we just iterate each character of the string to apply our palindrome detection logic
 *
 *
 * Space:
 * isPalindromePermutationHorrendous(): O(S), namely the maximum size of the recursive call stack at any time, to permutate a string of size S
 *
 * isPalindromePermutationImproved(): O(1), why? If the input string can only contain alpha characters, then the bit vector size is fixed, namely 32 bits since a 32-bit integer is sufficient to store entire 26-letter English alphabet.
 * </pre>
 *
 * @author josequijada
 */
public class PermutationOfPalindromeOld {
  private final static Palindrome palindrome = new Palindrome();

  boolean isPalindromePermutationHorrendous(final String str) {
    final String strippedStr = Util.strip(str).toUpperCase();
    return permutationHelper(strippedStr, "");
  }


  boolean permutationHelper(String str, String prefix) {
    if (null == str || str.length() < 1) {
      if (palindrome.isPalindrome(prefix)) {
        return true;
      } else {
        return false;
      }
    }

    /*
     * From the passed in String `str`, we need to select out one character at a time, and
     * then permutate the rest of the strings, combining each permutation with the character
     * selected. This process is done recursively on the string that remains after a character
     * is selected out.
     */
    for (int i = 0; i < str.length(); i++) {

      // a b c
      // i = 0: a b c, str.substring(0, i) produces "",   str.substring(i + 1) produces "bc"
      // i = 1: a b c, str.substring(0, i) produces "a",  str.substring(i + 1) produces "c"
      // i = 2: a b c, str.substring(0, i) produces "ab", str.substring(i + 1) produces ""
      if (permutationHelper(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i))) {
        return true;
      }
    }

    return false;
  }






  /**
   * Date: 10/12/2019 Start:  07:44AM Pause:  07:59AM Resume: 08:01AM End:    08:18AM
   *
   * No need to generate full set of permutations (computationally expensive!) just to determine if
   * the word is a permutated palindrome. Instead the logic is that palindromes have an even number
   * for each character. In case of odd number of characters, there will be at most one character
   * w/o a corresponding "twin" (the one in the middle). Given this info can use an alphabet bit
   * vector that starts with all 0's. Everytime we acounter a character, we flip its corresponding
   * bit (I.e. if it was 0, flip it to 1, and vice versa). In the end should have a bit vector
   * that's all 0's, with the exception of a lone 1 in the case where the word had **at most** one
   * character where the count was odd. Example:
   *
   * "abecdeeedceba"
   *
   * In the above, notice all characters are evenly balanced (I.e. there's an even count of each),
   * except for 'e', which appears 5 times. This means that 'e' is the only character which will
   * have a "switch" (a bit) in the "on position" (1). The rest will all be 0. This means above can
   * be classified as a palindrome, even if the characters were scrambled.
   *
   * Assumptions: 1. Input string contains characters only from the English alphabet
   */
  boolean isPalindromePermutationImproved(final String str) {
    // Create the alphabet bit vector (I.e. a list of switches)
    int switches = 0;
    final String strippedStr = Util.strip(str);

    // Flip the switch on/off as each character is seen
    char[] ary = strippedStr.toCharArray();

    for (int i = 0; i < ary.length; i++) {
      final char curChar = ary[i];
      int pos = convertToNumeric(curChar);

      // Create the mask by setting 1 in the position in the alphabet vector
      // that corresponds to this character
      int mask = 1 << pos;

      // XOR will flip the switch: if switch is off (0), a 1 will flip it on; if
      // switch is on (1), a 1 will flip it off
      switches ^= mask;
    }

    return atMostOneBitSet(switches);
  }


  /**
   * Determine if only a single switch is in the "on" position, which the corresponding bit will
   * have a value of "1" We shift out one bit at a time of the total 32 (because we use an "int" as
   * the bit vector, and int's are 32 bits long) and examine it. As soon as more than one bit is
   * found to be in the "on" positon (a "1"), we return false
   */
  private boolean atMostOneBitSet(int vector) {
    boolean oneSwitchOn = false;
    for (int i = 0; i < 32; i++) {
      int bit = vector >> 1;
      if ((bit & 1) >= 1) {
        if (oneSwitchOn) {
          return false;
        }
        oneSwitchOn = true;
      }
    }

    return true;
  }


  /**
   * Does same thing as atMostOneBitSet(), but at the expense of readability? Basically any bit
   * vector with at most one bit set (or all 0's for that matter) will give zero when AND'ed with
   * itself and one subtracted. For example 00010000 -1 -------- 00001111
   *
   * If more than one bit is set, the result will be other than 0.
   */
  private boolean atMostOneBitSetAlt(int vector) {
    return (vector & (vector - 1)) == 0;
  }


  private int convertToNumeric(char c) {
    int charA = Character.getNumericValue('a');
    return Character.getNumericValue(c) - charA;

  }
}
