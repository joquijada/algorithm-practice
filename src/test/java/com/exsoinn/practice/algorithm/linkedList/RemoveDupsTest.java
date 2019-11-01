package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.Node;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class RemoveDupsTest extends TestCase {

  private static final RemoveDups removeDups = new RemoveDups();

  public void testRemoveDups() {
    Integer[] nums = {5, 1, 8, 5, 94, 23, 94, 63, 1};
    Node<Integer> lst = Node.buildList(nums);
    removeDups.removeDups(lst);
    assertEquals("518942363", Node.converToString(lst));
  }
}