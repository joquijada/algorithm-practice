package com.exsoinn.practice.algorithm;

/**
 * Problem: Rotate NxN image, where each pixel is represented by an integer. Do this in place.
 *
 * <br/><br/>
 *
 *
 *
 * Brainstorm:
 * - This reeks of a multi dimensional matrix
 *<br/><br/>
 *
 *
 *<br/><br/>
 * Algorithm:<br/>
 * 1. Observe each layer is an array. Can swap one layer array with another, going around 4 times
 * around each layer.<br/>
 *
 * <br/><br/>
 * Test:<br/>
 *
 *
 * 3  <br/><br/>
 *
 * 2  <br/><br/>
 *
 * 1  <br/><br/>
 *
 * 0  <br/><br/>
 *
 *    0  1  2  3<br/><br/><br/><br/>
 *
 *
 *
 * m = 4x4, dim = 4, totalLayers = 2<br/>
 * i = 0, layerMax = 3, layerMin = 0<br/>
 *   fromMinToMax = 0, fromMaxToMin = 3<br/>
 *     temp = m[layerMin][fromMinToMax]:(0,0)<br/>
 *     m[layerMin][fromMinToMax]:(0,0) = m[fromMaxToMin][layerLow]:(3,0)<br/>
 *     m[fromMaxToMin][layerMin]:(3,0) = temp:(0,0)<br/>
 * <br/><br/>
 *     temp = m[fromMaxToMin][layerMin]:(3,0)<br/>
 *     m[fromMaxToMin][layerMin]:(3,0) = m[layerMax][fromMaxToMin]:(3,3)<br/>
 *     m[layerMax][fromMaxToMin]:(3,3) = temp(3,0)<br/>
 * <br/><br/>
 *     temp = m[layerMax][fromMaxToMin]:(3,3)<br/>
 *     m[layerMax][fromMaxToMin]:(3,3) = m[fromMinToMax][layerMax]:(0,3)<br/>
 *     m[fromMinToMax][layerMax](0,3) = temp:(3,3)<br/>
 * <br/><br/>
 *   fromMinToMax = 1, fromMaxToMin = 2<br/>
 *     temp = m[layerMin][fromMinToMax]:(0,1)<br/>
 *     m[layerMin][fromMinToMax]:(0,1) = m[fromMaxToMin][layerLow]:(2,0)<br/>
 *     m[fromMaxToMin][layerMin]:(2,0) = temp:(0,1)<br/>
 * <br/><br/>
 *     temp = m[fromMaxToMin][layerMin]:(2,0)<br/>
 *     m[fromMaxToMin][layerMin]:(2,0) = m[layerMax][fromMaxToMin]:(3,2)<br/>
 *     m[layerMax][fromMaxToMin]:(3,2) = temp(2,0)<br/>
 * <br/><br/>
 *     temp = m[layerMax][fromMaxToMin]:(3,2)<br/>
 *     m[layerMax][fromMaxToMin]:(3,2) = m[fromMinToMax][layerMax]:(1,3)<br/>
 *     m[fromMinToMax][layerMax](1,3) = temp:(3,2)<br/>
 * <br/><br/>
 *
 *
 * Runtime: O(N^2). Why? Because we have to touch every element in the matrix in order to move. The
 * number of movements of each matrix point in the inner-most loop is immaterial here as far as big-O is concerned. Remember we're not trying to measure the time an individual algorithm takes to complete, rather big-O is a measure of how the algorithm scales with respect to some inputs. Remember we were given N in terms of the length of a side of an NxN matrix.
 *
 * @author josequijada
 */
public class RotateMatrixByNinetyDegrees {


  void rotateNinetyDegrees(int[][] m) {
    final int dim = m.length;
    final int totalLayers = dim/2;
    for (int layer = 0; layer < totalLayers; layer++) {
      final int layerMax = dim - layer - 1;
      final int layerMin = layer;
      for (int fromMinToMax = layerMin; fromMinToMax <= layerMax; fromMinToMax++) {
        int temp = m[layerMin][fromMinToMax];
        int fromMaxToMin = dim - fromMinToMax - 1;
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
