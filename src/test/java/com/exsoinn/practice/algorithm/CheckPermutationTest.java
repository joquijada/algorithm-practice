package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class CheckPermutationTest extends TestCase {
  private CheckPermutation checkPermutation = new CheckPermutation();

  public void testIsPermutation() {
    assertEquals(true, checkPermutation.permutation("abcd", "cabd"));
    assertEquals(true, checkPermutation.permutation("abc\u0000d", "\u0000cabd"));
  }


  public void testNoPermutation() {
    assertEquals(false, checkPermutation.permutation("abcd", "egab"));
  }
}