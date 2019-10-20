package com.exsoinn.practice.algorithm;

import com.exsoinn.practice.algorithm.util.Util;

/**
 * @author josequijada
 */
public class Palindrome {
  /**
   * Algorithm to check if a string is a paliondrome, obtained from
   * <a href="https://apps2.mdp.ac.id/perpustakaan/ebook/Karya%20Umum/Dsa.pdf">Data Structures and Algorithms</a>,
   * but with a slight variation in the end contidion of the while(), namely that the "left < right"
   * check is done ahead of the left/right size character quality check..
   * This algorithm assumes the input string has been stripped of non-alpha characters, and that
   * it is either all upper case or all lower case. This in effect will result
   * in a check that disregards both non-alpha characters (space, punctuation, etc.) and case.
   *
   * @param str String to inspect for palindrome
   * @return True if a palindrom, false otherwise
   */
  public boolean isPalindrome(final String str) {
    final String cleansed = Util.strip(str).toUpperCase();
    int left = 0;
    int right = cleansed.length() - 1;

    /*
     * Since a palindrome is a word that i the same when read from left to right and vice-versa,
     * start by comparing characters at both end, working our way in until we're in at the middle
     * character (odd length word case) or the left pointers have crossed into each other's territory
     * (even length word case)
     * In below quick tests, visualize it's two palindromes, one odd and the other even length.
     * For ease of test we just supply the character indeces:
     *   0 1 2 3 4 (odd length)
     *   0 1 2 4   (even length)
     *
     * Notice that in the odd length word case, the character in the middle will always be equal
     * to itself. In this case the left and right indeces will both point to the middle character
     * when both halves have been fully compared, at which time while() loop exits, because
     * "left < right" check gives "false" (because "left == right").
     * In even length case, the indeces are allowed to "cross" into each other's side as long
     * as the last characters of each half are the same, at which point the while() loop will exit
     * right away.
     */
    while (left < right && cleansed.charAt(left) == cleansed.charAt(right)) {
      ++left;
      --right;
    }

    // if true, it means unequal characters found before both halves were fully compared
    return left >= right;
  }

}
