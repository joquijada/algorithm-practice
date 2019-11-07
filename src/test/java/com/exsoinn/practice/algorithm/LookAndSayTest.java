package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class LookAndSayTest extends TestCase {
  private static final LookAndSay lookAndSay = new LookAndSay();

  public void testLookAndSay() {
    lookAndSay.lookAndSay(5);
  }
}