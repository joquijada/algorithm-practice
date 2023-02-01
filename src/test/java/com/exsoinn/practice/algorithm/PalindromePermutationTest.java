package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class PalindromePermutationTest extends TestCase {
  private static final PalindromePermutation palindromePermutation = new PalindromePermutation();




  public void testIsPalindromePermutation() {
    assertTrue(palindromePermutation.palindromePermutation("Tact Coa"));
  }

  public void testIsNotPalindromePermutation() {
    assertTrue(!palindromePermutation.palindromePermutation("Tact Co"));
  }
}