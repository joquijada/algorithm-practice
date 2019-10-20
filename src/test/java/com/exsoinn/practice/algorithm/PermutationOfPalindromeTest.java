package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class PermutationOfPalindromeTest extends TestCase {

  private static final PermutationOfPalindrome permutationOfPalindrome = new PermutationOfPalindrome();

  public void testIsPalindromePermutationHorrendous() {
    assertTrue(permutationOfPalindrome.isPalindromePermutationHorrendous("Tact Coa"));
  }

  public void testIsPalindromePermutationImproved() {
  }

}