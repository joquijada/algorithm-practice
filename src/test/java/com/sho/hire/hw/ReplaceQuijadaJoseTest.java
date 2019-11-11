package com.sho.hire.hw;


import junit.framework.TestCase;

/**
 * Test class for {@link ReplaceQuijadaJose}
 * @author Jose Quijada
 */
public class ReplaceQuijadaJoseTest extends TestCase {

  private static final ReplaceQuijadaJose replaceQuijadaJose = new ReplaceQuijadaJose();


  public void testEcalpeResrever() {
    assertEquals("aBC", replaceQuijadaJose.ecalpeResrever("ABC", "A", "a"));
    assertEquals("Ba aB aA", replaceQuijadaJose.ecalpeResrever("AAA AAB BAA", "AA", "a"));
    assertEquals("Play. I", replaceQuijadaJose.ecalpeResrever("I Work.", "Work", "Play"));
    assertEquals("ok. just are Tests",
      replaceQuijadaJose.ecalpeResrever("Tests are the best!", "the best!", "just ok."));
  }


  public void testReplacementStringBiggerThanSentence() {
    assertEquals("phrase short",
      replaceQuijadaJose.ecalpeResrever("short phrase", "much bigger phrase", "the replacement"));
  }

  public void testReplacementStringSameLengthAsSentence() {
    assertEquals("thing. another is This", replaceQuijadaJose
      .ecalpeResrever("I am a sentence.", "I am a sentence.", "This is another thing."));
  }


  public void testOneWordSentence() {
    assertEquals("singleWord",
      replaceQuijadaJose.ecalpeResrever("singleWord", "whatever", "whatever"));
  }


  public void testBlankReplacement() {
    assertEquals("bar foo", replaceQuijadaJose.ecalpeResrever("foo bar", "whatever", ""));
  }


  public void testBlankSearch() {
    assertEquals("bar foo", replaceQuijadaJose.ecalpeResrever("foo bar", "", "whatever"));
  }


  public void testExcessSpaces() {
    assertEquals("am I sam sam, am I",
      replaceQuijadaJose.ecalpeResrever("    I am    sam,  sam I    am   ", "", "whatever"));
  }


  public void testReplacementShorterThanSearchString() {
    assertEquals("be will replacement the what than shorter a with sentence a is This", replaceQuijadaJose.ecalpeResrever(
      "This is a sentence with a longer search string than what the replacement will be",
      "longer search string", "shorter"));
  }

  public void testReplacementLongerThanSearchString() {
    assertEquals("be will string search the what than string replacement longer a with sentence a is This", replaceQuijadaJose.ecalpeResrever(
      "This is a sentence with a longer replacement string than what the shorter will be",
      "shorter", "search string"));
  }


  public void testSearchCaseSensitivityNohit() {
    assertEquals("No replacement took place", replaceQuijadaJose.ecalpeResrever(
      "place took replacement No",
      "Replacement", "whatever"));
  }

  public void testSearchCaseSensitivityWithHit() {
    assertEquals("A Replacement did take place", replaceQuijadaJose.ecalpeResrever(
      "place take did replacement A",
      "replacement", "Replacement"));
  }

}
