package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class CompressStringTest extends TestCase {

  private CompressString compressString = new CompressString();

  public void testCompress() {
    assertEquals("A2a3b3B3", compressString.compress("AAaaabbbBBB"));
    assertEquals("AAaabbBB", compressString.compress("AAaabbBB"));
  }
}