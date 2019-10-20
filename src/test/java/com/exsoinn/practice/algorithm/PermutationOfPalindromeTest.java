package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class PermutationOfPalindromeTest extends TestCase {
  private static final String permPalin = "Tact Coa";
  private static final String notPermPalin = "Tact Co";

  private static final PermutationOfPalindrome permutationOfPalindrome = new PermutationOfPalindrome();

  public void testIsPalindromePermutationHorrendous() {
    assertTrue(permutationOfPalindrome.isPalindromePermutationHorrendous(permPalin));
  }

  public void testIsPalindromePermutationImproved() {
    assertTrue(permutationOfPalindrome.isPalindromePermutationHorrendous(permPalin));
  }


  public void testIsNotPalindromePermutationHorrendous() {
    assertTrue(!permutationOfPalindrome.isPalindromePermutationHorrendous(notPermPalin));
  }

  public void testIsNotPalindromePermutationImproved() {
    assertTrue(!permutationOfPalindrome.isPalindromePermutationHorrendous(notPermPalin));
  }

}