package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class MyHashTableTest extends TestCase {

  public void testGet() {
    MyHashTable<String, Integer> table = createHashTable();
    assertEquals((Integer) 1, table.get("one"));
    assertEquals((Integer) 2, table.get("two"));
    assertEquals((Integer) 3, table.get("three"));
    assertEquals((Integer) 4, table.get("four"));
    assertEquals((Integer) 5, table.get("five"));
    assertEquals((Integer) 6, table.get("six"));
  }

  public void testGetNotFound() {
    MyHashTable<String, Integer> table = createHashTable();
    assertEquals(null, table.get("seven"));
  }

  public void testPut() {
    MyHashTable<String, Integer> table = createHashTable();
    table.put("eight", 8);
    assertEquals((Integer) 8, table.get("eight"));
  }

  public void testReplace() {
    MyHashTable<String, Integer> table = createHashTable();
    table.put("six", 999);
    assertEquals((Integer) 999, table.get("six"));
  }

  private MyHashTable<String, Integer> createHashTable() {
    MyHashTable<String, Integer> table = new MyHashTable<>();
    table.put("one", 1);
    table.put("two", 2);
    table.put("three", 3);
    table.put("four", 4);
    table.put("five", 5);
    table.put("six", 6);
    return table;
  }
}