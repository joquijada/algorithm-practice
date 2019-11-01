package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.Node;

/**
 * <pre>
 *   Problem: Given a linked list, find the kth element from the last. So for example if k == 3, counting from end of the list, return the 3rd element
 *
 *
 *
 * Algorithm:
 * - Recurse all the way to the end of the list in binary tree "post/in-order" type fashion, returning index 1 when we hit the end of the linked list, and incrementing index by 1 as we traverse our way back to the fron of the list. At each return step check if the index value matches the current node, and store that in the object to return
 * - We opted for use of a type called Found, just to encapsulate the found node, along with the position of the current node in the calling stack that we keep track of.
 *

 *
 * Assumptions:
 *
 *
 * Runtime: O(N), because have to traverse entire list
 *
 * Space: O(N), because have to recurse all the way to end of list. This can get problematic if the list is very long, as we might hit StackOverFlow error in Java.
 * </pre>
 * @author josequijada
 */
public class FindKthToLast {

  Found findKthToLast(Node<Integer> n, int k) {
    if (null == n) {
      // This will return one for the index of the calling node, namely the last one
      // in the linked list. How do we know it's the last one? Because what came after it
      // was NULL (the end of the linked list)
      return new Found(null, 1);
    }

    Found<Node<Integer>> f = findKthToLast(n.next(), k);

    // As the function unwinds, check if this node's position corresponds
    // to the requested one, and if so set it in the return Found object
    if (f.index() == k) {
      f.node(n);
    }

    // Always update the index to reflect the calling node's position in the
    // chain
    f.index(f.index() + 1);

    return f;
  }


  public static class Found<T> {
    private int i;
    private T node;
    Found(T node, int i) {
      this.i = i;
      this.node = node;
    }


    public int index() {
      return i;
    }

    public T node() {
      return node;
    }

    public void index(int i) {
      this.i = i;
    }

    public void node(T node) {
      this.node = node;
    }
  }
}
