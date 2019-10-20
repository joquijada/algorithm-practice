package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class PalindromeTest extends TestCase {
  private static final Palindrome palindrome = new Palindrome();

  public void testIsPalindrome() {
    String str = "Taco ocat";

    assertTrue(palindrome.isPalindrome(str));
  }

  public void testIsNotPalindrome() {
    String str = "Taco octa";

    assertTrue(!palindrome.isPalindrome(str));
  }
}