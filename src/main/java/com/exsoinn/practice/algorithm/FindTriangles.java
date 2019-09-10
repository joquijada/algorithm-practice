package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author josequijada
 */
public class FindTriangles {

  public static void findTriangles(int[] ary, List<Set<Integer>> triangles) {
    if (null == ary || ary.length < 3) {
      return;
    }
    int k = 3;
    boolean[] available = new boolean[ary.length];
    for (int i = 0; i < available.length; i++) {
      available[i] = true;
    }
    findTrianglesHelper(ary, triangles, available, k, new ArrayList<>());
  }


  private static void findTrianglesHelper(int[] ary, List<Set<Integer>> triangles, boolean[] available,
          final int k, List<Integer> cur) {
    if (k <= 0) {
      Set<Integer> sides = new HashSet<>(cur);
      if (!triangles.contains(sides) && (cur.get(0) + cur.get(1) > cur.get(2)) && (
              cur.get(2) + cur.get(1) > cur.get(0)) && (cur.get(0) + cur.get(2) > cur.get(1))) {
        triangles.add(sides);
      }
      return;
    }

    for (int i = 0; i < ary.length; i++) {
      if (!available[i]) {
        continue;
      }
      available[i] = false;
      cur.add(ary[i]);
      findTrianglesHelper(ary, triangles, available, k - 1, cur);
      available[i] = true;
      cur.remove(cur.size() - 1);
    }
  }


  public static void main(String[] args) {
    int[] arr = {10, 21, 22, 100, 101, 200, 300};
    List<Set<Integer>> triangles = new ArrayList<>();
    findTriangles(arr, triangles);
    System.out.println("Total number of triangles is " + triangles);
  }
}
