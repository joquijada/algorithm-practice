package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class OneAwayTest extends TestCase {

  private final OneAway oneAway = new OneAway();
  public void testIsOneAway() {
    assertTrue(oneAway.oneAway("pale", "ale"));
    assertTrue(oneAway.oneAway("alep", "ale"));
    assertTrue(oneAway.oneAway("pales", "paes"));
    assertTrue(oneAway.oneAway("pale", "bale"));
  }

  public void testIsNotOneAway() {
    assertTrue(!oneAway.oneAway("bake", "pale"));
  }
}