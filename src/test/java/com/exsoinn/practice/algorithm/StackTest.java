package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class StackTest extends TestCase {

  public void testPush() {
    Stack<Integer> s = new Stack<>();
    s.push(1);
    s.push(2);
    s.push(3);
    assertTrue(s.size() == 3);
  }

  public void testPop() {
    Stack<Integer> s = new Stack<>();
    s.push(1);
    s.push(2);
    s.push(3);
    assertTrue(s.size() == 3);
    s.pop();
    s.pop();
    s.pop();
    assertTrue(s.size() == 0);
  }

  public void testIsEmpty() {
    Stack<Integer> s = new Stack<>();
    assertTrue(s.isEmpty());
    s.push(999);
    assertTrue(!s.isEmpty());
  }

  public void testPeek() {
    Stack<Integer> s1 = new Stack<>();
    s1.push(1);
    s1.push(2);
    s1.push(3);
    assertEquals((Integer)3, s1.peek());
    Stack<Integer> s2 = new Stack<>();
    assertEquals(null, s2.peek());
  }

}