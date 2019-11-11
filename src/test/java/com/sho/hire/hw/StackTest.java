package com.sho.hire.hw;

import junit.framework.TestCase;

public class StackTest extends TestCase {
  private Stack stack = new Stack(201);


  public void testLeakiness() {
    for (int i = 0; i <= 200; i++) {
      stack.push(i);
    }
    for (int i = 0; i <= 200; i++) {
      stack.pop();
    }

    System.out.println("break");
  }
}