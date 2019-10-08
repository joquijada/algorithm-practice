package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class CompressStringTest extends TestCase {

  private static final String str1 = "AAaaaAAaaaAAAbbqpr";
  private static final String str1Compressed = "a13b2q1p1r1";

  private static final String str2 = "AbC";
  private static final String str2Compressed = "AbC";

  private CompressString compressString = new CompressString();

  public void testCompress() {
    assertEquals(str1Compressed, compressString.compress(str1));
    assertEquals(str2Compressed, compressString.compress(str2));
  }

  public void testCompressEarlier() {
    assertEquals(str1Compressed, compressString.compressEarlier(str1));
    assertEquals(str2Compressed, compressString.compressEarlier(str2));
  }
}