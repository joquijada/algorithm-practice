package com.exsoinn.practice.algorithm;

/**
 * In order for a tree to be balanced, **every** node must have left and right heights differ by no more
 * than 1.
 *
 * Runtime complexity is O(n*log n), why? During height calculation The same nodes get touched again for every tree
 * level above it. The bottom-most nodes get hit the hardest, since they will get touched "log n" times, where
 * "log n" is the height of the tree
 *
 * TODO: Improve runtime by eliminating redundant depth() calls
 *
 * @author josequijada
 */
public class TreeIsBalanced {


  private static boolean balanced(TreeNode<Integer> root) {
    if (null == root || null == root.getLeft() && null == root.getRight()) {
      return true;
    }

    // At first subtree found that's unbalanced, return false;
    if (Math.abs(depth(root.getLeft()) - depth(root.getRight())) > 1) {
      return false;
    } else {
      // Go down one level and check individually if left subtree and right subtree are themselves balanced.
      return balanced(root.getLeft()) && balanced(root.getRight());
    }
  }



  private static int depth(TreeNode n) {
    if (null == n) {
      return 0;
    }
    return Math.max(depth(n.getLeft()), depth(n.getRight())) + 1;
  }



  public static void main(String[] args) {
    Integer[] ary = {23, 31, 14, 7, 17, 9, 18, 30, 32};
    TreeNode<Integer> tree = TreeNode.buildTree(ary);
    System.out.println("Is balanced? " + balanced(tree));

    Integer[] ary2 = {23, 31, 14, 7, 17, 9, 18};
   tree = TreeNode.buildTree(ary2);
    System.out.println("Is balanced? " + balanced(tree));
  }

}
