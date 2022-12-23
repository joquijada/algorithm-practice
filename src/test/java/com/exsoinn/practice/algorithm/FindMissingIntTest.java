package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class FindMissingIntTest extends TestCase {
  private FindMissingInt findMissingInt = new FindMissingInt();

  public void testFindMissingIntNoneMissing() throws FileNotFoundException {
    assertEquals(-1L, findMissingInt.findMissingInt("missing-int-none-missing.txt"));
  }

  public void testFindMissingIntOneMissing() throws FileNotFoundException {
    assertEquals(4, findMissingInt.findMissingInt("missing-int-one-missing.txt"));
  }
}