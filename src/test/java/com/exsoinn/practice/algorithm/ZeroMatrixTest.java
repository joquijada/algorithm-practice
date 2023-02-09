package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class ZeroMatrixTest extends TestCase {
  private ZeroMatrix zeroMatrix = new ZeroMatrix();
  private final static int matrix[][] = {{0, 1, 2, 3, 4, 5, 11}, {6, 7, 8, 9, 10, 11, 99}, {12, 13, 14, 15, 16, 17, 65},
          {18, 19, 20, 21, 22, 23, 50}, {24, 25, 26, 27, 28, 29, 34}, {30, 31, 0, 33, 34, 35, 43}};

  public void testZeroMatrix() {
    //printMatrix(matrix);
    zeroMatrix.zeroMatrix(matrix);
    printMatrix(matrix);
  }

  private void printMatrix(int[][] m) {
    int dim = m.length;
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        System.out.printf("(%d,%d)=%d ", i, j, m[i][j]);
      }
    }
  }
}