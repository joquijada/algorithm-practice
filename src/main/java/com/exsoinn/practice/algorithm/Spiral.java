package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class Spiral {

  int[][] spiral(int n) {
    int layers = n / 2;
    int[][] matrix = new int[n][n];
    int cnt = 1;
    for (int l = 0; l < layers; l++) {
      final int high = n - 1 - l;
      final int low = l;

      // Top row
      int y = high;
      int x = low;
      while (x <= high) {
        matrix[x][y] = cnt;
        ++x;
        ++cnt;
      }

      // Right row
      x = high;
      y = high;
      while (y >= low) {
        matrix[x][y] = cnt;
        --y;
        ++cnt;
      }

      // Bottom row
      x = high;
      y = low;
      while (x >= low) {
        matrix[x][y] = cnt;
        --x;
        ++cnt;
      }

      // Left row
      x = low;
      y = low;
      while (y <= high - 1) {
        matrix[x][y] = cnt;
        ++y;
        ++cnt;
      }
    }

    return matrix;
  }

}
