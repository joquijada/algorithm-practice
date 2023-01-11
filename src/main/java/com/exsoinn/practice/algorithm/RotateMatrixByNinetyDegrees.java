package com.exsoinn.practice.algorithm;

/**
 * <pre>
 * Problem: Rotate NxN image, where each pixel is represented by an integer. Do this in place.
 *
 *
 *
 * Questions:
 *
 *
 * Brainstorm:
 * - This reeks of a multi dimensional matrix
 *
 * Example: A 4x4 matrix
 *
 *
 *
 * Algorithm:
 * - Observe each layer a matrix is made up of layers. Layer 1 is the inner most, layer two the next layer out,
 * and so on. Each layer is composed of arrays. Can swap elements two arrays at a time, swapping
 * element by element, until all 4 arrays have been exchanged.
 *
 *
 *
 * Test:
 * Before:
 * -------
 * 3  3  7 11 15
 *
 * 2  2  6 10 14
 *
 * 1  1  5  9 13
 *
 * 0  0  4  8 12
 *
 *    0  1  2  3
 *
 * m[layerMin][fromMinToMax]= m[fromMaxToMin][layerMin];
 *
 *
 * After:
 * ------
 * 3  0  1  2  3
 *
 * 2  4  5  6  7
 *
 * 1  8  9 10 11
 *
 * 0 12 13 14 15
 *
 *    0  1  2  3
 *
 *
 *
 * m = 4x4, dim = 4, totalLayers = 2
 * i = 0, layerMax = 3, layerMin = 0
 *   fromMinToMax = 0, fromMaxToMin = 3
 *     temp = m[layerMin][fromMinToMax]:(0,0)
 *     m[layerMin][fromMinToMax]:(0,0) = m[fromMaxToMin][layerLow]:(3,0)
 *     m[fromMaxToMin][layerMin]:(3,0) = temp:(0,0)
 *
 *     temp = m[fromMaxToMin][layerMin]:(3,0)
 *     m[fromMaxToMin][layerMin]:(3,0) = m[layerMax][fromMaxToMin]:(3,3)
 *     m[layerMax][fromMaxToMin]:(3,3) = temp(3,0)
 *
 *     temp = m[layerMax][fromMaxToMin]:(3,3);
 *     m[layerMax][fromMaxToMin]:(3,3) = m[fromMinToMax][layerMax]:(0,3)
 *     m[fromMinToMax][layerMax](0,3) = temp:(3,3)
 *
 *   fromMinToMax = 1, fromMaxToMin = 2
 *     temp = m[layerMin][fromMinToMax]:(0,1)
 *     m[layerMin][fromMinToMax]:(0,1) = m[fromMaxToMin][layerLow]:(2,0)
 *     m[fromMaxToMin][layerMin]:(2,0) = temp:(0,1)
 *
 *     temp = m[fromMaxToMin][layerMin]:(2,0)
 *     m[fromMaxToMin][layerMin]:(2,0) = m[layerMax][fromMaxToMin]:(3,2)
 *     m[layerMax][fromMaxToMin]:(3,2) = temp(2,0)
 *
 *     temp = m[layerMax][fromMaxToMin]:(3,2);
 *     m[layerMax][fromMaxToMin]:(3,2) = m[fromMinToMax][layerMax]:(1,3)
 *     m[fromMinToMax][layerMax](1,3) = temp:(3,2)
 *
 *
 *
 * Runtime: O(N^2). Why? Because we have to touch every element in the matrix in order to move. The number of movements of each matrix point in the inner-most loop is immaterial here as far as big-O is concerned. Remember we're not trying to measure the time an individual algorithm takes to complete, rather big-O is a measure of how the algorithm scales with respect to some inputs. Remember we were given N in terms of the length of a side of an NxN matrix.
 *
 * </pre>
 *
 *
 * @author josequijada
 */
public class RotateMatrixByNinetyDegrees {

  public void rotateNinetyDegrees(int[][] matrix) {
    int layers = matrix.length/2;
    int matrixLen = matrix.length;
    for (int layer = 0; layer < layers; layer++) {
      int first = layer;
      int last = matrixLen - 1 - layer;

      for (int asc = first; asc < last; asc++) {
        int desc = matrixLen - 1 - asc;

        // save top
        int save = matrix[asc][last];

        // left -> top
        matrix[asc][last] = matrix[first][asc];

        // bottom -> left
        matrix[first][asc] = matrix[desc][first];

        // right -> bottom
        matrix[desc][first] = matrix[last][desc];

        // top -> right
        matrix[last][desc] = save; // saved top
      }
    }
  }


  /**
   * See {@link RotateMatrixByNinetyDegrees}
   * @param m The matrix to rotate by 90 degrees, expressed as a 2- dimensional array
   */
  public void rotateNinetyDegreesOld(int[][] m) {
    final int dim = m.length;
    final int totalLayers = dim/2;
    for (int layer = 0; layer < totalLayers; layer++) {
      final int layerMax = dim - layer - 1;
      final int layerMin = layer;
      /*
       * The rotation is done clock-wise. We rotate **each element one at a time** all the way around,
       * beginning from left row, swapping each element there with the bottom row, and so on until we
       * work our way to the top row. An entire row is swapped, one element at a time, on each layer
       * iteration (the outer for() loop.
       *
       * Notice we only need 3 swaps. Why?
       * Because by virtue of moving the same element around the matrix 3 times, that element is already
       * effectively rotated 90 degrees. In essence those elements are moved backward to as we advance
       * each layer array behind them forward. The target of the swap (the element that is getting displaced)
       * is rotated backwards 270 degrees if you will. It is the same as if we rotated that element
       * forward 90 degrees - the final destination is the same if we go 90 degrees forward or
       * 270 degrees backwards.
       *
       * Note: Why is the for() loop limited to less than `layerMax`? Because that element is in the corner
       * of the matrix, where arrays overlap/meet (they share an element in common in each corner), and
       * that element will have already been swapped when we first
       * started swapping that array. If we were to do `<=`, we'd end up erroneously swapping the same
       * element twice.
       */
      for (int fromMinToMax = layerMin; fromMinToMax < layerMax; fromMinToMax++) {
        int fromMaxToMin = dim - fromMinToMax - 1;
        int temp = m[layerMin][fromMinToMax];
        m[layerMin][fromMinToMax]= m[fromMaxToMin][layerMin];
        m[fromMaxToMin][layerMin] = temp;

        temp = m[fromMaxToMin][layerMin];
        m[fromMaxToMin][layerMin] = m[layerMax][fromMaxToMin];
        m[layerMax][fromMaxToMin] = temp;

        temp = m[layerMax][fromMaxToMin];
        m[layerMax][fromMaxToMin] = m[fromMinToMax][layerMax];
        m[fromMinToMax][layerMax] = temp;
      }
    }
  }

}
