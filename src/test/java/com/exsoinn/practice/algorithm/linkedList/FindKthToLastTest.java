package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.Node;
import com.exsoinn.practice.algorithm.linkedList.FindKthToLast.Found;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class FindKthToLastTest extends TestCase {
  private static final FindKthToLast findKthToLast = new FindKthToLast();

  public void testFindKthToLast() {
    Integer[] nums = {5, 1, 8, 10, 94, 23, 13, 63, 82};
    Node<Integer> lst = Node.buildList(nums);
    Found<Node<Integer>> kthToLast = findKthToLast.findKthToLast(lst, 6);
    assertTrue(kthToLast.node().getData() == 10);
  }
}