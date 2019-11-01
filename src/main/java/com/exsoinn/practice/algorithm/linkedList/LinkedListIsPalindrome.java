package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Algorithm
 * 1. Strip out all non-alpha characters
 * 2. Push all alpha characters up to the the middle of the string into a stack. A stack is LIFO
 * 3. Now continue iterating over the other half of the string, at each link popping out a character from the stack and
 *    comparing itto the current link. If all characters from stack and 1/2 linked list match, then it's a palindrome
 * @author josequijada
 */
public class LinkedListIsPalindrome {

  private static boolean isPalindrome(Node<Character> head) {
    if (null == head) {
      return false;
    }

    int size;
    if ((size = Node.size(head)) == 1) {
      return true;
    }
    head = removeNonAlpha(head);
    if ((size = Node.size(head)) == 0) {
      // Only special characters contained in string
      // QUESTION: Can palindromes be all non-alpha char's?
      return false;
    }

    if ((size = Node.size(head)) == 1) {
      // After stripping all special characters, only one strig
      return true;
    }

    boolean even = size % 2 == 0;
    int mid = size / 2;
    int i = 1;
    Stack<Character> stk = new Stack<>();
    Node<Character> n = head;
    while (null != n) {
      // In odd character count scenario, the middle character acts as the pivot point of list,
      // hence does not make sense comparing it to another character, because to its left and right there's
      // an even character count
      if (!even && i == (mid + 1)) {
        n = n.getNext();
        ++i;
        continue;
      }

      if (i <= mid) {
        // Push characters into stack up to the middle of list
        stk.push(n.getData());
      } else {
        // We're already past the middle point, so we're in stack pop+comparison mode
        // Note: No need to check if pop() operation will return NULL. Why? Because the count in this 1/2
        //       of the linked list is guaranteed to match the count of items in the stack. How so? Because
        //       since we divided linked list in half, and pushed just as many characters into the stack as
        //       we're popping off of it.
        Character c = stk.pop();
        // Upper case everything to ensure we're comparing apples to apples (this whole thing is case
        // insensitive)
        if (Character.toUpperCase(n.getData()) !=  Character.toUpperCase(c)) {
          return false;
        }
      }
      n = n.getNext();
      ++i;
    }

    return true;
  }




  private static Node<Character> removeNonAlpha(Node<Character> head) {
    // Deal with leading non-alpha's
    while (null != head && !isAlpha(head.getData())) {
      head = head.getNext();
    }

    Node<Character> n = head;
    while (null != n.getNext()) {
      if (!isAlpha(n.getNext().getData())) {
        n.setNext(n.getNext().getNext());
      } else {
        n = n.getNext();
      }
    }
    return head;
  }


  private static boolean isAlpha(Character c) {
    c = Character.toUpperCase(c);
    return c >= 'A' && c <= 'Z';
  }




  public static void main(String[] args) {
    List<Node<Character>> heads = new ArrayList<>();
    Character[] chars = {'t', 'o', 'o', 't'};
    //heads.add(buildList(chars));
    Character[] chars2 = {'t', 'o', 't'};
    //heads.add(buildList(chars2));
    Character[] chars3 = {'W', 'a', 's', ' ', 'i', 't', ' ', 'e', 'l', 'l', 'i', 'o', 't', '\'', 's',
    ' ', 't', 'o', 'i', 'l', 'e', 't', ' ', 'I', ' ', 's', 'a', 'w', '?'};
    //heads.add(buildList(chars3));
    Character[] chars4 = {'W', 'a', 's', ' ', 'i', 't', ' ', 'e', 'l', 'i', 'o', 't', '\'', 's',
            ' ', 't', 'o', 'i', 'l', 'e', 't', ' ', 'I', ' ', 's', 'a', 'w', '?'};
    heads.add(Node.buildList(chars4));
    for (Node<Character> h : heads) {
      Node.printList(h);
      System.out.println("Is it a palindrome? " + isPalindrome(h));
      System.out.println("");
    }
  }

  private static Node<Character> buildList(char[] ary) {
    Node<Character> head = new Node<>();
    Node n = head;
    int cnt = 0;
    for (char c : ary) {
      n.setData(c);

      // Do not create an empty node at the end of the list, meaning that the node before
      // last should be the last node to which a non-null next reference gets assigned. Anything after
      // that will bear NULL net reference
      if (cnt < ary.length - 1) {
        Node<Character> newNode = new Node<>();
        n.setNext(newNode);
        n = newNode;
      }
      ++cnt;
    }
    return head;
  }

}
