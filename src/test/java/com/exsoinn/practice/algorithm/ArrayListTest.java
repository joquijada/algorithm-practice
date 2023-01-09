package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class ArrayListTest extends TestCase {

  public void testAdd() {
    ArrayList<String> l = createList();
    assertEquals(l.size(), 3);
  }

  public void testDelete() {
    ArrayList<String> l = createList();
    l.delete("two");
    assertEquals(l.size(), 2);
    assertTrue(!l.contains("two"));
  }

  public void testContains() {
    ArrayList<String> l = createList();
    assertTrue(l.contains("one"));
    assertTrue(l.contains("two"));
    assertTrue(l.contains("three"));
  }

  public void testSize() {
    ArrayList<String> l = createList();
    assertEquals(l.size(), 3);
  }

  public void testAddMany() {
    ArrayList<String> l = createList();
    for (int i = 0; i < 100; i++) {
      l.add("" + i);
    }
    assertEquals(103, l.size());
    for (int i = 0; i < 10; i++) {
      l.add("" + i);
    }
    assertEquals(113, l.size());
  }

  private ArrayList<String> createList() {
    ArrayList<String> l = new ArrayList<>();
    l.add("one").add("two").add("three");
    return l;
  }
}