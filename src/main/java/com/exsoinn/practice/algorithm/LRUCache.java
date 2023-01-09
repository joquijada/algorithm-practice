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

    // Remove before inserting. Why? We end up inserting duplicates into linked list otherwise!!! Also,
    //        we mistakenly report cache full, which results in unfair eviction.
    // But why bother re-inserting if it's already there? The user could be overwriting the value.
    removeKeyIfAlreadyPresent(key);

    if (cache.size() >= MAX_SIZE) {
      // Evict LRU node
      cache.remove(tail.key); // use tail.key to remove from cache map first, because tail ref will get updated by next line
      removeFromList(tail);
    }
    Node<K, V> n = new Node<>(key, value);
    cache.put(key, n);
    addToHeadOfList(n);
  }

  private void removeKeyIfAlreadyPresent(K key) {
    Node<K, V> found = cache.get(key);
    if (found == null) {
      return;
    }

    removeFromList(found);
    cache.remove(key);
  }

  public int size() {
    return cache.size();
  }

  public V retrieve(K key) {
    Node<K, V> found;
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
      head.prev = null;
    }
  }

  private void removeFromList(Node<K, V> n) {
    if (head == tail) {
      // one node only (cache size is 1)
      // null <- x -> null
      head = null;
      tail = null;
      return;
    }

    // "Unlink" this node, being careful to check if head or tail node,
    // to prevent NPE
    if (n.next != null) {
      n.next.prev = n.prev;
    }
    if (n.prev != null) {
      n.prev.next = n.next;
    }

    // Finally update head or tail as appropriate
    if (head == n) {
      head = head.next;
    }
    if (tail == n) {
      tail = tail.prev;
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