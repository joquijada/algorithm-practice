package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class PartitionTest extends TestCase {
  private Partition partition = new Partition();

  public void testPartition() {
    Integer[] nums = {1, 2, 3};
    ListNode<Integer> lst = ListNode.buildList(nums);
    lst = partition.partition(lst, 2);
    assertEquals("123", ListNode.convertToString(lst));
  }

  public void testPartitionAllGtEqToX() {
    Integer[] nums = {5, 5, 5};
    ListNode<Integer> lst = ListNode.buildList(nums);
    lst = partition.partition(lst, 2);
    assertEquals("555", ListNode.convertToString(lst));
  }

  public void testPartitionAllLtToX() {
    Integer[] nums = {1, 2, 3};
    ListNode<Integer> lst = ListNode.buildList(nums);
    lst = partition.partition(lst, 4);
    assertEquals("123", ListNode.convertToString(lst));
  }
}