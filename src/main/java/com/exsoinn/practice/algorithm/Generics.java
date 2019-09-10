package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author josequijada
 */
public class Generics {

  public static void main(String[] args) {
    List<TreeNode> treeNodesOnly = new ArrayList<>();
    List<TreeNodeChild> treeNodeChildrenOnly = new ArrayList<>();
    /*
     * Lower bounded wildcard
     */
    List<? super TreeNode> canHoldTreeNodesOrAbove;
    canHoldTreeNodesOrAbove = treeNodesOnly;
    canHoldTreeNodesOrAbove.addAll(treeNodesOnly);

    // Does not compile because TODO
    //treeNodesOnly.addAll(canHoldTreeNodesOrAbove);

    // Does not compile because TreeNodeChild is below TreeNode, the lower bound
    //canHoldTreeNodesOrAbove = treeNodeChildrenOnly;



    /*
     * Upper bounded wildcard
     */
    List<? extends TreeNode> canHoldTreeNodesOrBelow;
    canHoldTreeNodesOrBelow = treeNodesOnly;

    // Does not compile because TODO
    //canHoldTreeNodesOrBelow.addAll(treeNodesOnly);

    treeNodesOnly.addAll(canHoldTreeNodesOrBelow);

    // Works because canHoldTreeNodesOrBelow can be assigned a list that holds TreeNode's or children thereof
    canHoldTreeNodesOrBelow = treeNodeChildrenOnly;


    List<Object> objList = new ArrayList<>();

    // Does not compile because because as per EJ, 3rd edition, p139 top, parameterized types are invariant:
    // a List<Object> is not a parent of List<? extends TreeNode> canHoldTreeNodesOrBelow
    //objList = canHoldTreeNodesOrBelow;

    // Works because you **can** add anything to a List<Object> type. Assignment to it of something other than a
    // List<Object> is the problem.
    objList.addAll(canHoldTreeNodesOrBelow);

  }

  private static class TreeNodeChild extends TreeNode {

    public TreeNodeChild(Object pData) {
      super(pData);
    }
  }

}
