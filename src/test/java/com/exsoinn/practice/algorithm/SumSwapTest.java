package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class SumSwapTest extends TestCase {
  private static final SumSwap sumSwap = new SumSwap();

  public void testSumSwap() {
    int[] a = {1, 5, 5, 6, 7};
    int[] b = {2, 4, 6, 8, 10};
    int[] res = sumSwap.sumSwap(a, b);
    assertEquals(1, res[0]);
    assertEquals(4, res[1]);
  }
}