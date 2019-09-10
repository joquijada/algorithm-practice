package com.exsoinn.practice.algorithm;

import java.util.List;

/**
 * 0 + 1 = 1
 * 1 + 1 = 2
 * 1 + 2 = 3
 * 2 + 3 = 5
 * 3 + 5 = 8
 * 5 + 8 = 13
 * 8 + 13 = 21
 *
 * @author josequijada
 */
public class FibonacciSeries {
  private static int fibonacci(int n) {
    if (n < 1) {
      return 0;
    } else if (n < 2) {
      return 1;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
  }

}
