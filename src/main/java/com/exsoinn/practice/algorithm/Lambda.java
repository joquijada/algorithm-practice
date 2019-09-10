package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author josequijada
 */
public class Lambda {

  public static void main(String[] args) {
    List<Function<Void, Integer>> myLambdas = new ArrayList<>();

    final int[] finalInt = new int[1];
    for (int i = 1; i <= 5; i++) {
      finalInt[0] = i;
      int tmp = i;
      //
      /*
       * Below gives compile error, as variables defined outside of scope of Lambda must be
       * final or effectively final. Contrast with JavaScript, where there's no such restriction - if
       * I define a function that accesses variables declared outside its scope, I'm able to update
       * those all I want.
       * Note: The comments above apply to primitives only in both languages. Complex object
       *       types, as long as not immutable (mutable) I can still mutate.
       *
       */
      //myLambdas.add(e -> tmp++);
      // Below works, but if I try to change tmp farther below, that also gives compile error
      myLambdas.add(e -> tmp);
      //tmp = 7;
    }

    int k = 1;
    for (Function<Void, Integer> f : myLambdas) {
      System.out.println("Function " + k + " says: " + f.apply(null));
      ++k;
    }
  }
}
