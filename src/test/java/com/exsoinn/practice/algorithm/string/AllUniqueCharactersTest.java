package com.exsoinn.practice.algorithm.string;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class AllUniqueCharactersTest extends TestCase {
  private AllUniqueCharacters allUniqueCharacters = new AllUniqueCharacters();

  public void testAllUniqueCharactersTrue() {
    String s ="abcdefgh!@#$%^&*()";
    assertTrue(allUniqueCharacters.allUniqueCharacters(s));
  }


  public void testAllUniqueCharactersFalse() {
    String s ="abc#defgh!@$%^&*()#";
    assertFalse(allUniqueCharacters.allUniqueCharacters(s));
  }

  /**
   * Strings longer than C size, where C is character set, should cause the method to
   * automatically return false
   */
  public void testAllUniqueCharactersStringLimitCheck() {
    // Build > chracter set size String
    StringBuilder sb = new StringBuilder();
    for (int i = 0 ; i <= Character.MAX_VALUE + 1; i++) {
      sb.append((char) i);
    }
    assertFalse(allUniqueCharacters.allUniqueCharacters(sb.toString()));
  }


}