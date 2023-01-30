package com.exsoinn.practice.algorithm;

public class MyHashTable<K, V> {
  /*
   * Object[] can't be cast to Node<K, V>[], I guess because of erasure? So opted
   * for using Node as a rawtype to create the array
   */
  @SuppressWarnings("unchecked")
  final private Node<K, V>[] table = (Node<K, V>[])new Node[16];

  public V get(K key) {
    Node<K, V> found = findNodeWithKey(key);
    if (found != null) {
      return found.value;
    }
    return null;
  }

  public void put(K key, V value) {
    int bucket = key.hashCode()%table.length;
    Node<K, V> n = new Node<>();
    n.key = key;
    n.value = value;
    Node<K, V> existing = findNodeWithKey(key);
    if (existing != null) {
      // Overwrite key with value if already there
      existing.value = value;
    } else {
      // insert new node at the head of this bucket's list
      n.next = table[bucket];
      table[bucket] = n;
    }
  }

  private Node<K, V> findNodeWithKey(K key) {
    int bucket = key.hashCode()%table.length;
    Node<K, V> cur = table[bucket];

    // Traverse the linked list associated with this bucket
    while (cur != null) {
      if (cur.key.equals(key)) {
        return cur;
      }
      cur = cur.next;
    }
    return null;
  }

  private static class Node<K, V> {
    private K key;
    private V value;
    private Node<K, V> next;
  }
}
