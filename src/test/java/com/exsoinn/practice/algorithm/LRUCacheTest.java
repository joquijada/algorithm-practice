package com.exsoinn.practice.algorithm;

import junit.framework.TestCase;

public class LRUCacheTest extends TestCase {
  public void testLeasRecentlyUsedEntryIsEvicted() {
    LRUCache<Integer, String> cache = new LRUCache<>(3);
    cache.insert(1, "first");
    cache.insert(2, "second");
    cache.insert(3, "third");
    // Only "use" 1 and 2
    for (int i = 0; i <= 10; i++) {
      cache.retrieve(1);
      cache.retrieve(2);
    }
    // now insert a 4th entry, 3rd should be gone
    cache.insert(4, "fourth");
    assertNull(cache.retrieve(3));
  }

  public void testSizeOneCache() {
    LRUCache<Integer, String> cache = new LRUCache<>(1);
    cache.insert(1, "first");
    cache.insert(2, "second");
    cache.insert(3, "third");
    assertEquals(cache.retrieve(3), "third");
    assertNull(cache.retrieve(1));
    assertNull(cache.retrieve(2));
  }

  public void testInsertSameValueAgain() {
    LRUCache<Integer, String> cache = new LRUCache<>(3);
    cache.insert(1, "first");
    cache.insert(3, "third");
    cache.insert(2, "second");
    cache.insert(2, "second");
    assertEquals(cache.retrieve(1), "first");
    assertEquals(cache.size(), 3);
  }
}