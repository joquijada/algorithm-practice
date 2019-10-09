package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class URLifyTest extends TestCase {
  private static final String in = "My house is made of bricks          ";
  private static final String out = "My%20house%20is%20made%20of%20bricks";
  private final URLify urLify = new URLify();

  public void testUrlify() {
    assertEquals(out, urLify.urlify(in, 26));
  }

  public void testUrlifyImproved() {
    assertEquals(out, urLify.urlifyImproved(in, 26));
  }
}