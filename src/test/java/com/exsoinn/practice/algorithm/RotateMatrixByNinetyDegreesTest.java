package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;


public class RotateMatrixByNinetyDegreesTest extends TestCase {

  private RotateMatrixByNinetyDegrees rotateMatrixByNinetyDegrees = new RotateMatrixByNinetyDegrees();

  // Think of each sub-array as a column
  private final static int beforeMatrix[][] = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}, {12, 13, 14, 15, 16, 17},
          {18, 19, 20, 21, 22, 23}, {24, 25, 26, 27, 28, 29}, {30, 31, 32, 33, 34, 35}};
  private final static int[][] afterMatrix = {{30, 24, 18, 12, 6, 0}, {31, 25, 19, 13, 7, 1}, {32, 26, 20, 14, 8, 2},
          {33, 27, 21, 15, 9, 3}, {34, 28, 22, 16, 10, 4}, {35, 29, 23, 17, 11, 5}};


  public void testRotateNinetyDegrees() {
    printMatrix(beforeMatrix);
    rotateMatrixByNinetyDegrees.rotateNinetyDegrees(beforeMatrix);
    assertTrue(validateNinetyDegreeRotation(beforeMatrix, afterMatrix));
    System.out.println();
    printMatrix(beforeMatrix);
  }


  private void printMatrix(int[][] m) {
    int dim = m.length;
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        System.out.printf("(%d,%d)=%d ", i, j, m[i][j]);
      }
    }
  }

  private boolean validateNinetyDegreeRotation(int[][] m1, int[][] m2) {
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1.length; j++) {
        if (m1[i][j] != m2[i][j]) {
          return false;
        }
      }
    }
    return true;
  }
}