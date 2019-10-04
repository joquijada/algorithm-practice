package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class CheckPermutationTest extends TestCase {
  private CheckPermutation checkPermutation = new CheckPermutation();

  public void testIsPermutation() {
    assertEquals(true, checkPermutation.permutation("WHat the heck is this?", "this what heck is the"));
  }


  public void testNoPermutation() {
    assertEquals(false, checkPermutation.permutation("WHat the check is this?", "thiswhatheckthe"));
  }
}