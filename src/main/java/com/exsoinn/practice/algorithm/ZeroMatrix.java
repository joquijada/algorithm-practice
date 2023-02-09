package com.exsoinn.practice.algorithm;

import java.util.HashSet;
import java.util.Set;

public class ZeroMatrix {
  public void zeroMatrix(int[][] m) {
    Set<Integer> rows = new HashSet<>();
    Set<Integer> cols = new HashSet<>();
    for (int x = 0; x < m.length; x++) {
      for (int y = 0; y < m[x].length; y++) {
        if (m[x][y] == 0) {
          cols.add(x);
          rows.add(y);
        }
      }
    }

    for (Integer c : cols) {
      for (int i = 0; i < m[c].length; i++) {
        m[c][i] = 0;
      }
    }

    for (Integer r : rows) {
      for (int i = 0; i < m.length; i++) {
        m[i][r] = 0;
      }
    }
  }
}
