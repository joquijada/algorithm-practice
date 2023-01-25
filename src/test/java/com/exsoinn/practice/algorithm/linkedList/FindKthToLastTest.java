package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.ListNode;
import com.exsoinn.practice.algorithm.linkedList.FindKthToLast.Found;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class FindKthToLastTest extends TestCase {
  private static final FindKthToLast findKthToLast = new FindKthToLast();

  public void testFindKthToLast() {
    Integer[] nums = {5, 1, 8, 10, 94, 23, 13, 63, 82};
    ListNode<Integer> lst = ListNode.buildList(nums);
    Found<ListNode<Integer>> kthToLast = findKthToLast.findKthToLast(lst, 6);
    assertTrue(kthToLast.node().getData() == 10);
  }
}