package com.exsoinn.practice.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <strong>Problem:</strong> Given two strings, determine if one is a permutation of the other,
 * <br/>
 * Date: 09/13/2019
 * Start: 08:06PM
 * Stop:  08:17PM
 * End:
 *
 * <br/><br/>
 * <strong>Questions:</strong><br/>
 * 1. Are we dealing with alphabet only, or entire 64K character set, or just ASCII (2^8 or 256 characters)?
 * 2. Are strings made up of unique characters only? If not, then it's just a matter of throwing str1 and str2 characters into two corresponding sets, and doing an equality comparison between the two.
 * 3. What's the maximum possible length of the string? If I need to keep a count, need to beware I don't over/under flow the integer type being used (byte, short, int, long) for example.
 *
 *
 * <br/><br/>
 * <strong>Brainstorm:</strong><br/>
 * - What is a permutation? Both strings contain same character set, **and** same count for each, regardless of the order in which the characters appear in either of the strings
 * - Can sort both strings and do a comparison (quick and easy, but performance-wise might not be optimal)
 * - Can store character and count of str1 in HashTable, then iterate str2 chars and decrease the count in the HashTable; anything reaches below 0, then it's not a permutation. But what about characters that remain? Need to scan Map for anything which value is not 0, to make final decision? Not really, on str2 inspection, as soon as character not found, or if found the count reaches -1, return 0
 * - Can initiate an int array to all zero's of alphabet of all possible characters, where the index in the array is the chracters number. THe array value is a counter to be incremented with each occurrence of a character in str1, and decreased per each occurrence of character in str2. If count reaches below 0 for any array entry, return false. It's important to know ahead of time the character set being used to initialize array with the correct size.
 *
 *
 * <br/><br/>
 * <strong>Algorithm:</strong><br/>
 * 1. Are strings different length? Return false
 * 2. Are strings equal? Return true
 *
 *
 * <br/><br/>
 *
 * <strong>Runtime:</strong><br/>
 *
 * - let s be length of longest string<br/>
 * - comparison for equality takes s<br/>
 * - storing count of character in Map takes s (because have to examine every character)<br/>
 * - likewise decrementing every character from Map takes s<br/>
 * <br/>
 * that's 3*s, which can be reduced to just O(s) - the algorithm is linear in nature
 * <br/><br/>
 * Note: I got these ideas from CTCI, 6th Edition, page #49, example #8
 * @author josequijada
 */
public class CheckPermutation {
  boolean permutation(String str1, String str2) {
    // Optimization: Not same length, not a permutation
    if (str1.length() != str2.length()) {
      return false;
    }

    if (str1.equals(str2)) {
      return true;
    }

    Map<Character, Integer> charCnt = new HashMap<>();
    boolean res = checkMap(str1, charCnt, true);

    res = checkMap(str2, charCnt, false);

    if (!res) {
      return false;
    }

    // Check that count of all characters in the Map is exactly 0
    Optional<Integer> opt = charCnt.values().stream().filter(e -> e != 0).findAny();

    return !opt.isPresent();


  }


  boolean checkMap(String str, Map<Character, Integer> charCnt, boolean increment) {
    char[] ary = str.toCharArray();

    for (int i = 0; i < ary.length; i++) {
      char curChar = ary[i];
      Integer cnt = charCnt.get(curChar);
      if (cnt == null) {
        // we're checking second string a this character not seen in previous string,
        // cannot be a permutation then
        if (!increment) {
          return false;
        }
        cnt = 0;
      }
      if (increment) {
        ++cnt;
      } else {
        --cnt;
        // second string had more of this characters than first string, cannot be a permutation,
        // return false;
        if (cnt < 0) {
          return false;
        }
      }
    }

    return true;
  }
}
