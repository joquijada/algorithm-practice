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

  public void testSumSwapNotFound() {
    int[] a = {1, 4, 5, 5, 6, 7, 8, 10, 1000};
    int[] b = {2, 4, 6, 8, 10};
    int[] res = sumSwap.sumSwap(a, b);
    assertEquals(0, res.length);
  }

  public void testSumSwapEvenOdd() {
    int[] a = {1, 1, 5, 5, 6, 7};
    int[] b = {2, 4, 6, 8, 10};
    int[] res = sumSwap.sumSwap(a, b);
    assertEquals(0, res.length);
  }

  public void testSumSwapAlreadyEqual() {
    int[] a = {2, 4, 6, 8, 10};
    int[] b = {2, 4, 6, 8, 10};
    int[] res = sumSwap.sumSwap(a, b);
    assertEquals(0, res.length);
  }
}