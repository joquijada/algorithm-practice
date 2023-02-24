package com.exsoinn.practice.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Partition<E extends Number> {
  public ListNode<E> partitionCci(ListNode<E> head, int x) {
    ListNode<E> tail = head;
    ListNode<E> node = head;
    while (node != null) {
      ListNode<E> next = node.next;
      if (node.data.intValue() >= x) {
        // add to tail
        tail.next = node;
        tail = node;
      } else {
        // add to head
        node.next = head;
        head = node;
      }
      node = next;
    }
    tail.next = null;
    return head;
  }
  public ListNode<E> partition(ListNode<E> head, int x) {
    // Create tail pointer and record length of list
    ListNode<E> tail = head;
    int size = 1;
    while (tail.next != null) {
      tail = tail.next;
      ++size;
    }

    // Move head as many times as necessary
    int cnt = 0;
    while (head != null && head.data.intValue() >= x && cnt < size) {
      tail.next = head;
      tail = tail.next;
      head = head.next;
      tail.next = null;
      ++cnt;
    }
    // h5 -> 5 -> 5 -> null
    // h5 -> 5 -> stop -> 5 -> null, cnt = 1
    // h5 -> stop -> 5 -> 5 -> null, cnt = 2
    // stop -> 5h -> 5 -> 5 -> null, cnt = 3
    // stop -> 5 -> h5 -> 5 -> t5 -> null, cnt = 4 (t5 is the original reference that caller passed in, and I'm setting next to NULL, so caller lost all nodes of the list except for the first one when I had above as 'cnt < size'. Best to avoid manipulating head reference altogether)

    ListNode<E> cur = head;
    ++cnt;
    while (cnt < size) {
      // Should I stay or should I go?
      if (cur.next.data.intValue() >= x) {
        // Move to tail
        tail.next = cur.next;
        tail = tail.next;
        cur.next = cur.next.next;
        tail.next = null;
      } else {
        // keep it on this side of the list, and move on to next
        cur = cur.next;
      }
      ++cnt;
    }
    return head;
  }

  public void partitionOne(ListNode<E> head, int x) {
    ListNode<E> left = head;
    ListNode<E> right = head;

    // Locate middle of list and place a pointer there
    // 1 -> 2 -> 3 -> 4
    // 1 -> 2 -> 3
    // The `left.next.next != null` is there to safeguard against NPE in even length linked lists, I.e. for every node
    // in right we advance left twice, so after the first node need an even number of nodes, in other words an even length list.
    while (left.next != null && left.next.next != null) {
      right = right.next;
      left = left.next.next;
    }

    ListNode<E> mid = right;
    left = head;

    // This loop will swap items between left and right side as needed. It swaps data though,
    // not move the nodes.
    while (left != mid.next && right != null) {
      // Scout for things on the left that belong
      // on right side, and vice versa in loop below
      while (left != mid && left.data.intValue() < x) {
        left = left.next;
      }

      while (right != null && right.data.intValue() >= x) {
        right = right.next;
      }

      if (left != mid.next && right != null) {
        // Just swap the data contained in the nodes
        E tmp = left.data;
        left.data = right.data;
        right.data = tmp;
        left = left.next;
        right = right.next;
      }
    }

    // This loop handles the case when either left or right side
    // were exhausted before the other, namely my moving actual nodes from
    // left to right or vice-versa
    while (left.next != mid.next || right.next != null) {
      // Scout for things on the left that belong
      // on right side, and vice versa in loop below
      while (left.next != mid && left.next.data.intValue() < x) {
        left = left.next;
      }

      while (right.next != null && right.next.data.intValue() >= x) {
        right = right.next;
      }

      if (left.next != mid.next) {
        // right side was exhausted, move this node to end of right side
        right.next = left.next;
        right = right.next;
        left.next = left.next.next;
        right.next = null;
      } else if (right.next != null) {
        // left side was exhausted, move this node right after head
        ListNode<E> tmp = head.next;
        head.next = right.next;
        right.next = right.next.next;
        head.next.next = tmp;
      }
    }
  }

  public void partitionTwo(ListNode<E> head, int x) {
    // Create tail pointer and record length of list
    ListNode<E> tail = head;
    int size = 1;
    while(tail.next != null) {
      tail = tail.next;
      ++size;
    }

    // Move head as many times as necessary
    int cnt = 0;
    while(head != null && head.data.intValue() >= x && cnt < size) {
      tail.next = head;
      tail = head;
      head = head.next;
      tail.next = null;
      ++cnt;
    }

    ListNode<E> cur = head;
    ++cnt;
    while(cnt < size) {
      // Should I stay or should I go?
      if (cur.next.data.intValue() >= x) {
        // Move to tail
        tail.next = cur.next;
        tail = tail.next;
        cur.next = cur.next.next;
        tail.next = null;
      } else {
        // keep it on this side of the list, and move on to next
        cur = cur.next;
      }
      ++cnt;
    }
  }

  private int partitionTest() {
    int[] ary = {10, 4, 5, 6, 10};
    int start = 0;
    int end = 4;
    int mid = (start + end) / 2;
    int pivot = ary[mid];
    int left = start;
    int right = end;
    while(left <= right) {
      while(ary[left] < pivot) {
        ++left;
      }

      while(ary[right] > pivot) {
        --right;
      }

      // The "<=" means that even the pivot gets "swapped". This really has no effect, other than
      // incrementing "left" to be 1 past the pivot index. The caller can thus blindly use "left" as
      // the starting index of the right half, and left-1 as the end left of left half.
      if (left <= right) {
        int save = ary[left];
        ary[left] = ary[right];
        ary[right] = save;
        ++left;
        --right;
      }
    }
    System.out.println(Arrays.stream(ary).mapToObj(e -> Integer.toString(e)).collect(Collectors.joining(", ")));
    return left;
  }

  public static void main(String[] args) {
    Partition partition = new Partition();
    System.out.println(partition.partitionTest());
  }
}
