package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author josequijada
 */
public class Permutation {

  private int calls = 0;
  private int callsNonLeaf = 0;

  void permutationsHelper(final String pStr, final boolean[] pAvailable, final String pPrefix,
          final int len, final List<String> pResults) {
    // We have the requested length permutation, add it to results and exit
    ++calls;
    if (pPrefix.length() == len) {
      pResults.add(pPrefix);
      return;
    }

    ++callsNonLeaf;
    for (int i = 0; i < pStr.length(); i++) {
      // Check if this character is available for selection (I.e. further up the calling stack it hasn't
      // already been chosen to form this permutation
      if (!pAvailable[i]) {
        continue;
      }

      pAvailable[i] = false;
      permutationsHelper(pStr, pAvailable, pPrefix + pStr.charAt(i), len, pResults);
      pAvailable[i] = true;
    }
  }


  public List<String> permutations(final String pStr, final int len) {
    boolean[] available = new boolean[pStr.length()];
    // Initially all characters are available
    for (int i = 0; i < available.length; i++) {
      available[i] = true;
    }
    List<String> results = new ArrayList<>();
    permutationsHelper(pStr, available, "", len, results);
    return results;
  }

  public static void main(String[] args) {
    String str = "abcde";
    Permutation p = new Permutation();
    int len = 3;
    System.out.println(p.permutations(str, len).stream().collect(Collectors.joining("\n")));
    System.out.println("Total calls to permutationHelper(): " + p.calls);
    System.out.println("Total calls (leaves excluded): " + p.callsNonLeaf);
  }

}
