package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class PartitionTest extends TestCase {
  private Partition partition = new Partition();

  public void testPartition() {
    Integer[] nums = {10, 9, 7, 8, 2, 1, 0};
    ListNode<Integer> lst = ListNode.buildList(nums);
    lst = partition.partitionCci(lst, 2);
    assertEquals("01109782", ListNode.convertToString(lst));
  }

  public void testPartitionAllGtEqX() {
    Integer[] nums = {5, 5, 5};
    ListNode<Integer> lst = ListNode.buildList(nums);
    lst = partition.partitionCci(lst, 2);
    assertEquals("555", ListNode.convertToString(lst));
  }

  public void testPartitionAllLtX() {
    Integer[] nums = {1, 2, 3, 4};
    ListNode<Integer> lst = ListNode.buildList(nums);
    lst = partition.partitionCci(lst, 4);
    assertEquals("3214", ListNode.convertToString(lst));
  }
}