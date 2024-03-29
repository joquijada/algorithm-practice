package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.ListNode;
import java.util.LinkedList;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class AddLinkedListDigitsTest extends TestCase {

  private final static AddLinkedListDigits addLinkedListDigits = new AddLinkedListDigits();

  public void testPerformAddition() {
    Integer[] nums1 = {6, 1, 7};
    Integer[] nums2 = {5, 1, 8};
    ListNode<Integer> lst1 = ListNode.buildList(nums1);
    ListNode<Integer> lst2 = ListNode.buildList(nums2);
    ListNode<Integer> h = addLinkedListDigits.add(lst1, lst2);
    assertEquals("1351", converToString(h));

    LinkedList<Integer> res = addLinkedListDigits.add2(lst1, lst2);
    assertEquals("1351", converToString(ListNode.convertFromLinkedList(res)));
  }


  public void testPerformAdditionDifferentLengthNumbers() {
    Integer[] nums1 = {9, 1, 7};
    Integer[] nums2 = {1, 8};
    ListNode<Integer> lst1 = ListNode.buildList(nums1);
    ListNode<Integer> lst2 = ListNode.buildList(nums2);
    ListNode<Integer> h = addLinkedListDigits.add(lst1, lst2);
    assertEquals("008", converToString(h));

    LinkedList<Integer> res = addLinkedListDigits.add2(lst1, lst2);
    assertEquals("008", converToString(ListNode.convertFromLinkedList(res)));
  }


  public void testAddForwardOrder() {
    Integer[] nums1 = {7, 1, 6};
    Integer[] nums2 = {8, 1, 5};
    ListNode<Integer> lst1 = ListNode.buildList(nums1);
    ListNode<Integer> lst2 = ListNode.buildList(nums2);
    ListNode<Integer> h = addLinkedListDigits.addForwardOrder(lst1, lst2);
    assertEquals("1531", converToString(h));
  }


  public void testAddForwardOrderDifferentLengthNumbers() {
    Integer[] nums1 = {6, 1, 7};
    Integer[] nums2 = {9, 5};
    ListNode<Integer> lst1 = ListNode.buildList(nums1);
    ListNode<Integer> lst2 = ListNode.buildList(nums2);
    ListNode<Integer> h = addLinkedListDigits.addForwardOrder(lst1, lst2);
    assertEquals("712", converToString(h));
  }


  public static String converToString(ListNode<Integer> n) {
    return ListNode.convertToString(n);
  }
}