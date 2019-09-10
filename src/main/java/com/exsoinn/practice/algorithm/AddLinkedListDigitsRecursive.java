package com.exsoinn.practice.algorithm;



/**
 * @author josequijada
 */
public class AddLinkedListDigitsRecursive {

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

    Node<Integer> newNode = add(null != n1 ? n1.getNext() : null, null != n2 ? n2.getNext() : null, rem);

    n.setNext(newNode);

    return n;
  }

  private static void performAddition(Integer[] ary1, Integer[] ary2) {
    Node<Integer> h = add(Node.buildList(ary1), Node.buildList(ary2), 0);
    Node.printList(h);
    System.out.println();
  }

  public static void main(String[] args) {
    Integer[] nums1 = {6, 1, 7};
    Integer[] nums2 = {5, 1, 8};
    performAddition(nums1, nums2);

    Integer[] nums3 = {9, 1, 7};
    Integer[] nums4 = {1, 8};
    performAddition(nums3, nums4);
  }

}
