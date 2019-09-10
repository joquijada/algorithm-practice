package com.exsoinn.practice.algorithm;

/**
 * What is the definition of a prime? A number that is only divisible by 1 or itself. Therefore it follows that
 * if a number N is prime, there are no two numbers below it that give N as a result of multiplication. <br/>
 * <br/>
 * Example: If N is 5, no two numbers less that 5 give 5 when multiplied. Number 6 on the other hand is
 * not prime because 3 * 2 = 6.
 *
 * @author josequijada
 */
public class PrimalityTest {


  /**
   * As per CCI 6th Edition, Example 10, the runtime complexity is O(\u221AN). I'm confused because how come
   * when doing an inner loop that starts at i+1, where i is the index of outer loop
   * currently being iterated over, we don't take into account the 1/2 as in n(n-1)/2? Ans: The 1/2 is a constant,
   * where as the \u221AN puts a tighter bound than plain O(N) would.
   * @param n
   * @return
   */
  public static boolean prime(int n) {
    double sqrt = Math.sqrt(n);
    for (int i = 2; i <= sqrt; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] pArgs) {
    int max = 133;
    for (int i = 0; i <= max; i++) {
      System.out.println("Is " + i + " prime? " + prime(i));
    }
  }

}
