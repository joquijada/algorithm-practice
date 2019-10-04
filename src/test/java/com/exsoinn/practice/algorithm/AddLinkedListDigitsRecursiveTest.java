package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class AddLinkedListDigitsRecursiveTest extends TestCase {

  public void testPerformAdditionOne() {
    Integer[] nums1 = {6, 1, 7};
    Integer[] nums2 = {5, 1, 8};
    assertEquals("1351", AddLinkedListDigitsRecursive.performAddition(nums1, nums2));
  }


  public void testPerformAdditionTwo() {
    Integer[] nums3 = {9, 1, 7};
    Integer[] nums4 = {1, 8};
    assertEquals("008", AddLinkedListDigitsRecursive.performAddition(nums3, nums4));
  }
}