package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import junit.framework.TestCase;

/**
 * @author josequijada
 */
public class AddLinkedListDigitsTest extends TestCase {

  private final static AddLinkedListDigits addLinkedListDigits = new AddLinkedListDigits();

  public void testPerformAddition() {
    Integer[] nums1 = {6, 1, 7};
    Integer[] nums2 = {5, 1, 8};
    Node<Integer> lst1 = Node.buildList(nums1);
    Node<Integer> lst2 = Node.buildList(nums2);
    Node<Integer> h = addLinkedListDigits.add(lst1, lst2);
    assertEquals("1351", converToString(h));

    LinkedList<Integer> res = addLinkedListDigits.add2(lst1, lst2);
    assertEquals("1351", converToString(Node.convertFromLinkedList(res)));
  }


  public void testPerformAdditionDifferentLengthNumbers() {
    Integer[] nums1 = {9, 1, 7};
    Integer[] nums2 = {1, 8};
    Node<Integer> lst1 = Node.buildList(nums1);
    Node<Integer> lst2 = Node.buildList(nums2);
    Node<Integer> h = addLinkedListDigits.add(lst1, lst2);
    assertEquals("008", converToString(h));

    LinkedList<Integer> res = addLinkedListDigits.add2(lst1, lst2);
    assertEquals("008", converToString(Node.convertFromLinkedList(res)));

  }




  public static String converToString(Node<Integer> n) {
    Collection<Object> digits = new ArrayList<>();
    Node.printList(n, digits);
    System.out.println();
    return digits.stream().map(e -> e.toString()).collect(Collectors.joining(""));
  }
}