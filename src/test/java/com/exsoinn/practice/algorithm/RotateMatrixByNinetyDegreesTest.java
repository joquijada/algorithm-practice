package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;


public class RotateMatrixByNinetyDegreesTest extends TestCase {

  private RotateMatrixByNinetyDegrees rotateMatrixByNinetyDegrees = new RotateMatrixByNinetyDegrees();

  private final static int beforeMatrix[][] = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11},
          {12, 13, 14, 15}};
  private final static int[][] afterMatrix = {{12, 8, 4, 0}, {13, 9, 5, 1}, {14, 10, 6, 2},
          {15, 11, 7, 3}};


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