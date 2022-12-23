package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class CanSpell {
  public boolean canSpell(String word, String lyric) {
    String[] lyricWords = lyric.split("\\s+");
    char[] wordChars = word.toCharArray();

    // Optimizations
    //   1. Are there more characters than there are lyric words? Automatic false
    if (wordChars.length > lyricWords.length) {
      return false;
    }

    int wordCharIdx = 0;
    int lyricWordIdx = 0;
    while (lyricWordIdx < lyricWords.length && wordCharIdx < wordChars.length) {
      if (lyricWords[lyricWordIdx].indexOf(wordChars[wordCharIdx]) >= 0) {
        ++wordCharIdx;
      }
      ++lyricWordIdx;
    }

    // Were all the word characters matched?
    return wordCharIdx == wordChars.length;
  }
}
