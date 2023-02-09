package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class DeleteMiddleNodeTest extends TestCase {

  private DeleteMiddleNode<Integer> deleteMiddleNode = new DeleteMiddleNode<>();

  public void testDeleteMiddelNode() {
    Integer[] nums = {5, 1, 8, 5, 94, 23, 94, 63, 1};
    ListNode<Integer> head = ListNode.buildList(nums);
    ListNode<Integer> n = head.next.next.next.next;
    deleteMiddleNode.deleteMiddelNode(n);
    assertEquals("51852394631", ListNode.converToString(head));
  }

  public void testDeleteMiddelNodeNullGiven() {
    Integer[] nums = {5, 1, 8, 5, 94, 23, 94, 63, 1};
    ListNode<Integer> head = ListNode.buildList(nums);
    deleteMiddleNode.deleteMiddelNode(null);
    assertEquals("5185942394631", ListNode.converToString(head));
  }
}