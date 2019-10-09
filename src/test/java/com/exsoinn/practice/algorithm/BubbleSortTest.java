package com.exsoinn.practice.algorithm;

import java.util.Arrays;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class BubbleSortTest extends TestCase {

  /*public void testSort1() {
    int[] numbersToSort = {4, 75, 74, 2, 54};
    BubbleSort.sortBubble(numbersToSort);
    final int[] sorted = {2, 4, 54, 74, 75};
    assertEquals(Arrays.toString(sorted), Arrays.toString(numbersToSort));
  }*/

  public void testSort2() {
    int[] numbersToSort = {4, 75, 74, 2, 54};
    BubbleSort.sortBubble2(numbersToSort);
    final int[] sorted = {2, 4, 54, 74, 75};
    assertEquals(Arrays.toString(sorted), Arrays.toString(numbersToSort));
  }

  public void testSort3() {
    int[] numbersToSort = {4, 75, 74, 2, 54};
    BubbleSort.sortBubble3(numbersToSort);
    final int[] sorted = {2, 4, 54, 74, 75};
    assertEquals(Arrays.toString(sorted), Arrays.toString(numbersToSort));
  }

}