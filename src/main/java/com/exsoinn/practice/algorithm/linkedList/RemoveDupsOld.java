package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.ListNode;

/**
 * <pre>
 *   Problem: Remove dups from an unsorted linked list
 *
 * Algorithm: Have two pointers, first one visits a link at a time, the second one "looks ahead"
 *   and removes all links that have same data as the current link that the first pointer points to.
 *
 *
 *
 * Assumptions:
 * 1. It's a doubly-linked list
 * 2. The Node's contain Integer's
 *
 *
 * Runtime: O(N^2), explanation - For every node C, we're traversing the nodes in front of it via the inner while() loop. The outer while() loop iterates N times, where N is the length of the chain/linked list. On each such iteration, inner while() loop iterates one less time than it did on previous iteration of outer while() loop, beginning with N - 1 inner loop iterations when things first get started:
 *
 * (N - 1) + (N - 2) + ... + 1 + 0
 *
 * N = 5
 *
 * (5 - 1) + (5 - 2) + (5 - 3) + (5 - 4)
 *
 * The sum looks like this: 4 + 3 + 2 + 1 (notice that the max number in the sum is one less than the input size)
 *
 * 4 + 1 = 5
 * 3 + 2 = 5
 *
 * There are two pairs in the sum, that each add up to N (5). Notice the number of pairs is (5 - 1)/2. The total number of iterations of inner while() loop can thus be expressed as:
 *
 * (5 - 1)/2 * 5, or (N - 1)*N/2, which is O(N^2), because that's the tightest bound we can have (dividing a power of 2 in half doesn't do much to decrease time complexity as far as big-O is concerned).
 *
 * But what N, the size of the linked list, was not an odd number, but an even number instead? Then it's not true that there will be (N - 1)/2 pairs, but N/2 instead, for example:
 *
 * N = 6
 *
 * The sum looks like this: 5 + 4 + 3 + 2 + 1
 *
 * 5 + 0
 * 4 + 1
 * 3 + 2
 *
 * But the (N - 1)*N/2 formula for total inner while() iterations still holds true.
 *
 * Space: O(1) - No additional storage required
 * </pre>
 * @author josequijada
 */
public class RemoveDupsOld {
  public void removeDups(ListNode<Integer> head) {

    ListNode<Integer> cur = head;
    ListNode<Integer> la = cur;

    while (null != cur.next()) {
      // Look ahead in front of "cur" to the end of list, removing
      // any duplicates encountered.
      while (null != la.next()) {
        if (la.next().data() == cur.data()) {
          // Duplicate found, remove it from the chain
          la.setNext(la.next().next());
          // Check if "la" didn't become the tail node, which will be the case
          // when the dup found was at the tail of the list
          if (null != la.next()) {
            la.next().prev(la);
          }
        } else {
          // la.next() passed the test (it's not a dup of "cur"), prepare "la"
          // pointer to look at the next link (I.e. the one that immediately follows la.next())
          la = la.next();
        }
      }

      // We're done removing all the dups of "cur",
      // Move first pointer to next link, reset "la", wash/rinse/repeat cycle repeat
      cur = cur.next();
      la = cur;
    }
  }
}
