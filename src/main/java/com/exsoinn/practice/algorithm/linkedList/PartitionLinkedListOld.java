package com.exsoinn.practice.algorithm.linkedList;

import com.exsoinn.practice.algorithm.ListNode;
import com.exsoinn.practice.algorithm.TreeNode;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Assumptions: 1. No duplicates. Why? It's hard to decide which Node to partition around when they
 * have same value X!!!
 *
 * @author josequijada
 */
public class PartitionLinkedListOld {

  public ListNode<Integer> partitionAroundX(ListNode<Integer> h, int x, boolean simpler) {
    if (null == h || null == h.getNext()) {
      return null;
    }

    // Find the node to partition around
    ListNode<Integer> n = findNode(h, x);

    if (null == n) {
      return null;
    }

    // Build a BST out of the linked list, using partition Node as the root
    // Note: This will not be an AVL tree because I didn't write code for it, but we'll pretend
    //       it is and also pretend it's balanced
    TreeNode<Integer> tn = new TreeNode<>(n.getData());
    addNodes(h, tn);

    // Now just simply do an in-order traversal, building a linked list. Any values less than X
    // will be to the left of it, anything >= than X to the right
    // Note: I could have chosen to just simply use Java List<Integer>, but opted for building
    //       my own linked list from scratch for sake of practice
    return convertToLinkedList(tn, simpler);
  }


  private ListNode<Integer> findNode(ListNode<Integer> h, int x) {
    if (h.getData() == x) {
      return h;
    }

    ListNode<Integer> n = h;
    while (null != n) {
      if (n.getData() == x) {
        return n;
      }

      n = n.getNext();
    }
    return null;
  }

  private void addNodes(ListNode<Integer> h, TreeNode<Integer> root) {
    ListNode<Integer> n = h;
    while (null != n) {
      if (root.getData() != n.getData()) {
        // Avoid adding partition value X again to tree, it is already root of tree in
        // "root" argument to this method
        insertTreeNode(n, root);
      }
      n = n.getNext();
    }
  }


  private void insertTreeNode(ListNode<Integer> n, TreeNode<Integer> tn) {
    TreeNode<Integer> newTreeNode = new TreeNode<>(n.getData());
    while (true) {
      if (newTreeNode.getData() < tn.getData()) {
        if (null == tn.getLeft()) {
          tn.setLeft(newTreeNode);
          break;
        } else {
          tn = tn.getLeft();
        }
      } else {
        if (null == tn.getRight()) {
          tn.setRight(newTreeNode);
          break;
        } else {
          tn = tn.getRight();
        }
      }
    }
  }


  private ListNode<Integer> convertToLinkedList(TreeNode<Integer> root, boolean simpler) {
    AtomicReference<ListNode<Integer>> holder = new AtomicReference<>();
    if (simpler) {
      convertToLinkedListHelperSimpler(root, holder);
    } else {
      convertToLinkedListHelper(root, holder);
    }
    return holder.get();

  }


  /**
   * This method is basically doing an in-order traversal and building a list from smallest to
   * biggest number. The Holder<Node> is passed to capture head; this is an imperative operation,
   * meaning that the function will populate head node via side-effects
   */
  private ListNode<Integer> convertToLinkedListHelper(TreeNode<Integer> root,
                                                      AtomicReference<ListNode<Integer>> h) {
    if (null == root) {
      return null;
    }

    // Build a new linked list node for current node
    ListNode<Integer> newNode = new ListNode<>(root.getData());

    ListNode<Integer> left = convertToLinkedListHelper(root.getLeft(), h);

    // Save head, which is essentially the left-most and first visited node in the tree
    if (null == h.get()) {
      h.set(newNode);
    }

    // When the left node is returned, add this node (root) to the end of the linked list built thus
    // far. That's because anything to the left of root is < than root
    while (null != left) {
      if (null == left.getNext()) { // Reached end of list, add our new node to the end
        left.setNext(newNode);
        newNode.setPrev(left);
        break;
      } else {
        left = left.getNext();
      }
    }

    ListNode<Integer> right = convertToLinkedListHelper(root.getRight(), h);

    // When the right node returns, find the left most (beginning of partial linked list),
    // then connect it to current node. That's because anything to the right of root is >= to root
    while (null != right) {
      if (null == right.getPrev()) {
        newNode.setNext(right);
        right.setPrev(newNode);
        break;
      } else {
        right = right.getPrev();
      }
    }

    // return current node, so that further up the calling chain the same process
    // can be repeated
    return newNode;
  }


  private void convertToLinkedListHelperSimpler(TreeNode<Integer> root,
          AtomicReference<ListNode<Integer>> h) {
    if (null == root) {
      return;
    }

    // Build a new linked list node fof the currently visited tree node
    ListNode<Integer> newNode = new ListNode<>(root.getData());

    // This will request into the deepest, left most node of the BST, which by consequence
    // is the smallest, since we're dealing with a BST
    convertToLinkedListHelperSimpler(root.getLeft(), h);

    // If head is NULL, it means we're at left most node of tree
    if (null == h.get()) {
      h.set(newNode);
    } else {
      // Else, add this tree node (root) to the end of the list
      ListNode<Integer> n = h.get();
      // Need to find tail since we're not given a pointer to it, only to head
      while (null != n.getNext()) {
        n = n.getNext();
      }
      n.setNext(newNode);
    }

    convertToLinkedListHelperSimpler(root.getRight(), h);
  }


  public static void main(String[] pArgs) {
    Integer[] ary = {10, 3, 16, 20, 1, 8};
    ListNode<Integer> h = ListNode.buildList(ary);
    PartitionLinkedListOld pll = new PartitionLinkedListOld();
    ListNode.printList(pll.partitionAroundX(h, 10, false));
    System.out.println("\n");
    ListNode.printList(pll.partitionAroundX(h, 10, true));
  }
}
