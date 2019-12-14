package com.exsoinn.practice.algorithm;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

public class FindPairsWithKDiffTest extends TestCase {

  private static final FindPairsWithKDiff findPairsWithKDiff = new FindPairsWithKDiff();

  public void testFindPairsWithDifference() {
    List<Integer> l = Arrays.asList(1, 7, 5, 9, 2, 12, 3);
    List<List<Integer>> pairs = findPairsWithKDiff.findPairsWithDifference(l, 2);
    assertEquals(4, pairs.size());
    assertEquals(pairs.get(0).get(0), (Integer) 1);
    assertEquals(pairs.get(0).get(1), (Integer) 3);
    assertEquals(pairs.get(1).get(0), (Integer) 7);
    assertEquals(pairs.get(1).get(1), (Integer) 9);
    assertEquals(pairs.get(2).get(0), (Integer) 5);
    assertEquals(pairs.get(2).get(1), (Integer) 7);
    assertEquals(pairs.get(3).get(0), (Integer) 3);
    assertEquals(pairs.get(3).get(1), (Integer) 5);
  }
}