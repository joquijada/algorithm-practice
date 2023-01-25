package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class FindMiddleTest extends TestCase {
  private FindMiddle findMiddle = new FindMiddle();

  public void testFindMiddleNodeInEvenLengthList() {
    ListNode<Integer> n = findMiddle.findMiddleNode(createList(6));
    assertEquals(3, (int)(n.getData()));
  }

  public void testFindMiddleNodeInOddLengthList() {
    ListNode<Integer> n = findMiddle.findMiddleNode(createList(5));
    assertEquals(3, (int)(n.getData()));
  }

  private ListNode<Integer> createList(int length) {
    Integer[] nums = new Integer[length];
    for(int i = 0; i < length; i++) {
      nums[i] = i;
    }
    return ListNode.buildList(nums);
  }
}