package com.exsoinn.practice.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
  private final int MAX_SIZE;
  private final Map<K, Node<K, V>> cache;
  private Node<K, V> head = null;
  private Node<K, V> tail = null;

  public LRUCache(int size) {
    cache = new HashMap<>(size);
    MAX_SIZE = size;
  }

  public void insert(K key, V value) {
    if (MAX_SIZE < 1) {
      return;
    }

    if (cache.size() >= MAX_SIZE) {
      // Evict LRU node
      cache.remove(tail.key);
      removeFromList(tail);
    }
    Node<K, V> n = new Node<>(key, value);
    cache.put(key, n);
    addToHeadOfList(n);

  }

  public V retrieve(K key) {
    Node<K, V> found = null;
    if ((found = cache.get(key)) == null) {
      return null;
    } else {
      if (head != found) {
        removeFromList(found);
        addToHeadOfList(found);
      }
      return found.value;
    }
  }

  private void addToHeadOfList(Node<K, V> n) {
    if (head == null) {
      // first entry
      head = n;
      tail = n;
    } else {
      // one or more nodes already in the list
      // add a: null <- b <-> c
      n.next = head;
      head.prev = n;
      head = n;
    }
  }

  private void removeFromList(Node<K, V> n) {
    // Note: Will never be invoked when head == n because of calling code check
    if (head == tail) {
      // one node only (cache size is 1)
      // null <- x -> null
      head = null;
      tail = null;
    } else if (tail == n) {
      // node to remove is at tail of list
      // y <-> x -> null
      tail.prev.next = null;
      tail = n.prev;
      n.prev = null;
    } else {
      // node somewhere in between tail and head
      // y <-> x <-> z -> null
      n.prev.next = n.next;
      n.next.prev = n.prev;
      n.prev = null;
    }
  }

  private static class Node<K, V> {
    private Node<K, V> prev = null;
    private Node<K, V> next = null;
    private final K key;
    private final V value;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}