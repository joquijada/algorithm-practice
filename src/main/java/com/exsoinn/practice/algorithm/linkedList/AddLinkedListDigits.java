package com.exsoinn.practice.algorithm.linkedList;


import com.exsoinn.practice.algorithm.Node;
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


  public Node<Integer> addForwardOrder(Node<Integer> lst1, Node<Integer> lst2) {
    // Make lists both the same length by padding 0's in front as needed
    // so that they are the same length
    int size1 = Node.size(lst1);
    int size2 = Node.size(lst2);
    Node<Integer> toPad = size1 > size2 ? lst2 : (size1 < size2 ? lst1 : null);
    if (null != toPad) {
      Node<Integer> newHead = Node.pad(toPad, Math.abs(size1 - size2), 0);
      // Figure out which head node to update
      if (toPad == lst1) {
        lst1 = newHead;
      } else {
        lst2 = newHead;
      }
    }

    Result<Integer> r = addForwardOrderHelper(lst1, lst2);

    // Don't forget to create a node for last carry amount if any
    if (r.carry() != 0) {
      Node<Integer> carry = new Node<>();
      carry.data(r.carry());
      carry.next(r.node());
      return carry;
    } else {
      return r.node();
    }
  }


  private Result addForwardOrderHelper(Node<Integer> lst1, Node<Integer> lst2) {
    Result prevRes = null;
    if (null != lst1.next() && null != lst2.next()) {
      prevRes = addForwardOrderHelper(lst1.next(), lst2.next());
    }

    Node<Integer> prevNode = null;
    int prevCarry = 0;
    if (null != prevRes) {
      prevCarry = prevRes.carry();
      prevNode = prevRes.node();
    }

    int res = lst1.data() + lst2.data() + prevCarry;

    int carry = res / 10;
    res %= 10;

    Node<Integer> n = new Node<>();
    n.next(prevNode);
    Result r = new Result();
    n.data(res);
    r.carry(carry);
    r.node(n);

    return r;
  }



  private static class Result<T> {

    private Node<T> node = null;
    private int carry = 0;

    private Node<T> node() {
      return node;
    }

    private void node(Node<T> n) {
      node = n;
    }

    private int carry() {
      return carry;
    }

    private void carry(int i) {
      carry = i;
    }
  }

}
