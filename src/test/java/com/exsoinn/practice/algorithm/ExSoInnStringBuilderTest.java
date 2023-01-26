package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class ExSoInnStringBuilderTest extends TestCase {

  public void testAppend() {
    ExSoInnStringBuilder sb = new ExSoInnStringBuilder();
    sb.append("foo");
    sb.append(" is ");
    sb.append("bar");
    assertEquals("foo is bar", sb.toString());
  }

  public void testClear() {
    ExSoInnStringBuilder sb = new ExSoInnStringBuilder();
    sb.append("foo");
    sb.append(" is ");
    sb.append("bar");
    sb.clear();
    assertEquals("", sb.toString());
  }
}