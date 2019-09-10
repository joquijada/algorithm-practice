package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class RectanglesAndSquares {
  public static int totalRectanglesAndSquares(int[] ary) {
    // TODO: Appropriate checks


    int maxSize = max(ary); // TODO: Implement max()
    int[] sticks = new int[maxSize + 1]; // Need to add one so that array can accomodate it as the bigges index
    for (int i = 0; i < ary.length; i++) {
      ++sticks[ary[i]];
    }

    int pairs = 0;
    for (int i = 0; i < sticks.length; i++) {
      pairs += sticks[i]/2;
    }

    return pairs/2;
  }


  private static int max(int[] ary) {
    int max = 0;
    for (Integer i : ary) {
      if (i > max) max = i;
    }
    return max;
  }


  public static void main(String[] args) {
    //int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    int[] arr = {1, 2, 1, 2, 2, 2, 2, 2};
    System.out.println("Total rectangles/square: " + totalRectanglesAndSquares(arr));
  }

}
