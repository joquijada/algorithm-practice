package com.exsoinn.practice.algorithm;

import com.exsoinn.practice.algorithm.linkedList.FindKthToLastOld.Found;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class KthToLastTest extends TestCase {
  private static final KthToLast findKthToLast = new KthToLast();

  public void testFindKthToLast() {
    Integer[] nums = {5, 1, 8, 10, 94, 23, 13, 63, 82};
    ListNode<Integer> lst = ListNode.buildList(nums);
    KthToLast.Result<ListNode<Integer>> kthToLast = findKthToLast.kthToLast(lst, 6);
    assertEquals(10, kthToLast.node.data.intValue());
  }

  public void testFindKthToLastEmptyList() {
    KthToLast.Result<ListNode<Integer>> kthToLast = findKthToLast.kthToLast(null, 6);
    assertTrue(kthToLast.node == null);
  }

  public void testFindKthToLastIter() {
    Integer[] nums = {5, 1, 8, 10, 94, 23, 13, 63, 82};
    ListNode<Integer> lst = ListNode.buildList(nums);
    ListNode<Integer> found = findKthToLast.kthToLastIterative(lst, 6);
    assertEquals(10, found.data.intValue());
  }

  public void testFindKthToLastEmptyListIter() {
    ListNode<Integer> found = findKthToLast.kthToLastIterative(null, 6);
    assertTrue(found == null);
  }

  public void testFindKthToLastListLengthIsKIter() {
    Integer[] nums = {5, 1, 8, 10, 94, 23};
    ListNode<Integer> lst = ListNode.buildList(nums);
    ListNode<Integer> found = findKthToLast.kthToLastIterative(lst, 6);
    assertEquals(5, found.data.intValue());
  }
}