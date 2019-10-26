package com.exsoinn.practice.algorithm;


import java.util.LinkedList;

/**
 * @author josequijada
 */
public class AddLinkedListDigits {

  public Node<Integer> add(Node<Integer> n1, Node<Integer> n2) {
    return add(n1, n2, 0);
  }

  private static Node<Integer> add(Node<Integer> n1, Node<Integer> n2, int rem) {
    if (null == n1 && null == n2 && rem == 0) {
      return null;
    }

    int i1 = null != n1 ? n1.getData() : 0;
    int i2 = null != n2 ? n2.getData() : 0;

    int res = i1 + i2 + rem;

    // To capture the overflow when addition of the two digits results in a number > 9. When we're talking
    // about adding 2 digits, the max we can have is 9+9 = 18. Therefore the overflow will always be 1 or 0.
    // Now, if this was 3 or more digit addition, overflow could definitely be greater than 1, but the problem states
    // that it's only 2 numbers being added.
    rem = res > 9 ? 1 : 0;

    /*
     * This is for scenario when addition of the 2 digits results in a number above 9. For example, 1 + 9 = 10, the
     * below would capture "res" as "0", the last digit of the result, and remainder above ("rem") would capture the
     * left digit.
     */
    res = res % 10;

    Node<Integer> n = new Node<>();
    n.setData(res);

    Node<Integer> newNode = add(null != n1 ? n1.getNext() : null, null != n2 ? n2.getNext() : null,
            rem);

    n.setNext(newNode);

    return n;
  }


  /**
   * I redid the same problem a few months later. The solution is similar with some details being
   * different, for example the use of a {@link LinkedList} as the consumer that collects the
   * results of adding the two input lists. Another difference is that this method checks if the end
   * of both lists to
   */
  void add2(LinkedList<Integer> newList, Node<Integer> lst1, Node<Integer> lst2, int carry) {
    if (null == lst1 && null == lst2 && carry == 0) {
      return;
    }

    int dig1 = 0;
    int dig2 = 0;

    if (null != lst1) {
      dig1 = lst1.data();
    }

    if (null != lst2) {
      dig2 = lst2.data();
    }

    int res = dig1 + dig2 + carry;

    carry = res > 9 ? res / 10
            : 0; // Calculate "carry" before we mutate "res" below, else "carry" will be wrong!!!
    res = res % 10; // Example (9+1=10)%10 = 0;

    newList.add(res);

    // Recurse only if one or both lists have nodes remaining to add
    if (null != lst1 || null != lst2) {
      add2(newList, null != lst1 ? lst1.next() : null, null != lst2 ? lst2.next() : null, carry);
    }
  }

  LinkedList<Integer> add2(Node<Integer> lst1, Node<Integer> lst2) {
    LinkedList<Integer> l = new LinkedList<>();
    add2(l, lst1, lst2, 0);
    return l;
  }


}
