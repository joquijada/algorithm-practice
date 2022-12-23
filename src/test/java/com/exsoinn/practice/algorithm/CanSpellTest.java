package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class CanSpellTest extends TestCase {
  private CanSpell canSpell = new CanSpell();

  public void testCanSpell() {
    assertTrue(canSpell.canSpell("google", "I gotta feeling that tonight's gonna be a good night A feeling That tonight's gonna be a good night"));
  }

  public void testCanSpellLyricWordsLessThanWordCharacters() {
    assertFalse(canSpell.canSpell("google", "I gotta feeling"));
  }
}