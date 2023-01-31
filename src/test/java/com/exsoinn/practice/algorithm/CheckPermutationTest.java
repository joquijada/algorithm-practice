package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class CheckPermutationTest extends TestCase {
  private CheckPermutation checkPermutation = new CheckPermutation();

  public void testIsPermutation() {
    assertEquals(true, checkPermutation.permutation("abcd", "cabd"));
  }


  public void testNoPermutation() {
    assertEquals(false, checkPermutation.permutation("abcd", "egab"));
  }
}