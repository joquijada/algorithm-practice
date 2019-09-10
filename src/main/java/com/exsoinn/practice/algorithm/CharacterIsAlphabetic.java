package com.exsoinn.practice.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author josequijada
 */
public class CharacterIsAlphabetic {
  private static final Set<Character> alphaCharSet = new HashSet<>();

  static {
    for (int i = 'A'; i <= 'Z'; i++) {
      alphaCharSet.add(Character.valueOf((char)i));
    }

    for (int i = 'a'; i <= 'z'; i++) {
      alphaCharSet.add(Character.valueOf((char)i));
    }
  }

  private static boolean characterIsAlphabetic(char pChar) {
    return alphaCharSet.contains(pChar);
  }

  private static boolean characterIsAlphabetic2(char pChar) {
    return (pChar >= 'A' && pChar <= 'Z') || (pChar >= 'a' && pChar <= 'z');
  }


  public static void main(String[] args) {
    char[] ary = {'$', 'A', 'f', 'Z', 'a', 'z', 'z' + 1, 'A' - 1, 'Z' + 1};
    for (char c : ary) {
      System.out.println("Is '" + c + "' (" + (int)c + ")" + " alpha? " + characterIsAlphabetic(c));
      System.out.println("Is '" + c + "' (" + (int)c + ")" + " alpha2? " + characterIsAlphabetic2(c));
    }
  }

}
