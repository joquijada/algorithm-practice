package com.exsoinn.practice.algorithm;

/**
 * <pre>
 * Problem: Implement a function that outputs the Look and Say sequence:
 *
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * 312211
 * 13112221
 * 1113213211
 * 31131211131221
 * 13211311123113112211
 *
 *
 * Brainstorm:
 * - Can use case and build pattern?
 * - The first thing that comes to mind when I see this is Fibonacci sequence, which relies on previous to calculate the next
 * - Can have a function that takes as input the number of sequences to output, similar to the Fib function f(N). So for example, to print the first 5:
 *
 * 1 = 1
 * 2 = 11
 * 3 = 21
 * 4 = 1211
 * 5 = 111221
 *
 *
 *
 * Runtime: We have quasi the classic sum of a series 1 + 2 + 3 + 4 + ... + N . Each recursive call, when it unwinds, does, roughly speaking, one more than the previous, namely printing each character of what was "looked" in order to say it.
 *
 * If N is even, we will have N/2 pairs, if N is odd we have (N+1)/2 pairs. In the former, each pair adds up to N + 1, in the latter each pair adds up to N. So we
 * have a total of N(N+1)/2 and (N+1)N/2 respectively, which reduces to O(N^2). I gleaned this info by looking at the output, which more or less looks like
 * a pyramid, where the stack underneath is roughly one more in size that the one on top.
 *
 * Space: O(N) - Number of call stacks, which is equal to the requested number of sequences
 * </pre>
 * @author josequijada
 *
 */
public class LookAndSay {

  public void lookAndSay(int n) {
    lookAndSayHelper(n);
  }

  private String lookAndSayHelper(int n) {
    if (n <= 0) {
      return "0";
    }

    /*
     * When n == 1, thew recursive call returns "0" right away, which say() has been programmed to "say"
     * "1" - this is how we satisfy the correct "said" value at each level 1, 2, 3, 4, for level one
     * effectively produced "1", and on from there.
     */
    String said = say(lookAndSayHelper(n - 1));
    System.out.println(said);
    return said;
  }


  // 111221
  // 312211
  private String say(String look) {
    if ("0".equals(look)) {
      return "1";
    }

    char[] ary = look.toCharArray();
    int i = 0;
    final StringBuilder say = new StringBuilder();
    final int len = ary.length;
    while (i < len) {
      char cur = ary[i];
      int cnt = 1;

      // Ensure there's one char left after "i" to compare it to, hence the reason
      // for the first "i < len - 1" test, lest we want to throw IndexOutOfBoundsException if we
      // were to try to access char at "i + 1", yet we're "i" is already at last character
      while (i < len - 1 && cur == ary[i + 1]) {
        ++cnt;
        ++i;
      }

      say.append(cnt);
      say.append(cur);
      ++i;
    }

    return say.toString();
  }

}
