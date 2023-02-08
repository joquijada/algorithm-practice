package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.ListNode;
import com.exsoinn.practice.algorithm.RemoveDups;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class RemoveDupsTest extends TestCase {

  private static final RemoveDups removeDups = new RemoveDups();

  public void testRemoveDups() {
    Integer[] nums = {5, 1, 8, 5, 94, 23, 94, 63, 1};
    ListNode<Integer> lst = ListNode.buildList(nums);
    removeDups.removeDups(lst);
    assertEquals("518942363", ListNode.converToString(lst));

    Integer[] nums2 = {9, 9, 5, 9, 8, 1};
    ListNode<Integer> lst2 = ListNode.buildList(nums2);
    removeDups.removeDups(lst2);
    assertEquals("9581", ListNode.converToString(lst2));

    Integer[] nums3 = {9, 10, 5, 7, 8, 9};
    ListNode<Integer> lst3 = ListNode.buildList(nums3);
    removeDups.removeDups(lst3);
    assertEquals("910578", ListNode.converToString(lst3));
  }

  public void testNoDups() {
    Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    ListNode<Integer> lst = ListNode.buildList(nums);
    removeDups.removeDups(lst);
    assertEquals("123456789", ListNode.converToString(lst));
  }
}