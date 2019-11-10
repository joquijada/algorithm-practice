package com.sho.hire.hw;


import junit.framework.TestCase;

/**
 *
 * Test:
 * 1. When the needle is found at end of stack, and the replacement length is longer than the needle (causes resulting haystack size to grow).
 * 2. Same as above, but the replacement is shorter than the needle (causes resulting haystack size to shrink).
 * 3. Same as above, but the replacement is same length as the needle.
 * 4. Replacement string is larger than target sentence
 */
public class ReplaceQuijadaJoseTest extends TestCase {

  private static final ReplaceQuijadaJose replaceQuijadaJose = new ReplaceQuijadaJose();


  public void testEcalpeResrever() {
    assertEquals("aBC", replaceQuijadaJose.ecalpeResrever("ABC", "A", "a"));
    assertEquals("Ba aB aA", replaceQuijadaJose.ecalpeResrever("AAA AAB BAA", "AA", "a"));
    assertEquals("Play. I", replaceQuijadaJose.ecalpeResrever("I Work.", "Work", "Play"));
    assertEquals("ok. just are Tests", replaceQuijadaJose.ecalpeResrever("Tests are the best!", "the best!","just ok."));
  }


  public void testReplacementStringBiggerThanSentence() {
    assertEquals("phrase short", replaceQuijadaJose.ecalpeResrever("short phrase", "much bigger phrase", "the replacement"));
  }

  public void testReplacementStringSameLengthAsSentence() {
    assertEquals("thing. another is This", replaceQuijadaJose.ecalpeResrever("I am a sentence.", "I am a sentence.", "This is another thing."));
  }


  public void testOneWordSentence() {
    assertEquals("singleWord", replaceQuijadaJose.ecalpeResrever("singleWord", "whatever", "whatever"));
  }


  public void testBlankReplacement() {
    assertEquals("bar foo", replaceQuijadaJose.ecalpeResrever("foo bar", "whatever", ""));
  }


  public void testBlankSearch() {
    assertEquals("bar foo", replaceQuijadaJose.ecalpeResrever("foo bar", "", "whatever"));
  }


  public void testExcessSpaces() {
    assertEquals("am I sam sam, am I", replaceQuijadaJose.ecalpeResrever("    I am    sam,  sam I    am   ", "", "whatever"));
  }


}
