package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 *
 * @author josequijada
 */
public class URLifyTest extends TestCase {
  private final URLify urLify = new URLify();

  public void testUrlify() {
    char[] before = {'M', 'y', ' ', 'h', 'o', 'u', 's', 'e', ' ', 'i', 's', ' ', 'm', 'a', 'd', 'e', ' ', 'o', 'f', ' ', 'b', 'r', 'i', 'c', 'k', 's', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    char[] after = {'M', 'y', '%','2', '0', 'h', 'o', 'u', 's', 'e', '%','2', '0', 'i', 's', '%','2', '0', 'm', 'a', 'd', 'e', '%','2', '0', 'o', 'f', '%','2', '0', 'b', 'r', 'i', 'c', 'k', 's', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    urLify.urlify(before, 26);
    assertTrue(Arrays.equals(before, after));
  }
}