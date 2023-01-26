package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class IsPalindromeTest extends TestCase {

  private IsPalindrome isPalindrome = new IsPalindrome();

  public void testIsPalindromeWithoutSpecialChars() {
    assertTrue(isPalindrome.isPalindrome("abcddcba"));
    assertFalse(isPalindrome.isPalindrome("abcedcba"));
    assertTrue(isPalindrome.isPalindrome("abcdcba"));
  }

  public void testIsPalindromeWithSpecialChars() {
    assertTrue(isPalindrome.isPalindrome("Was it Eliot's toilet I saw?"));
    assertFalse(isPalindrome.isPalindrome("as it Eliot's toilet I saw?"));
  }
}