package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {

  public void testFindsNode() {
    LinkedList<Integer> l = createList();
    assertTrue(l.contains(1));
    assertTrue(l.contains(2));
    assertTrue(l.contains(3));
  }

  public void testSize() {
    LinkedList<Integer> l = createList();
    assertEquals(l.size(), 3);
  }

  public void testDelete() {
    LinkedList<Integer> l = createList();
    assertTrue(l.contains(2));
    l.delete(2);
    assertEquals(l.size(), 2);
    assertTrue(!l.contains(2));
  }

  public void testInsert() {
    LinkedList<Integer> l = createList();
    l.insert(4);
    assertEquals(l.size(), 4);
    assertTrue(l.contains(4));
  }

  private LinkedList<Integer> createList() {
    LinkedList<Integer> l = new LinkedList<>();
    l.insert(1).insert(2).insert(3);
    return l;
  }
}