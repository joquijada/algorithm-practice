package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Problem: Given an array of integers, count number of pairs that have difference k
 *
 * Example,
 *
 * In: [1, 7, 5, 9, 2, 12, 3]
 * Out: 4 pairs, (1, 3), (3, 5), (5, 7), (7, 5)
 *
 * The array will not contain duplicates, I.e. all integers will be unique in the array.
 *
 *
 * Brainstorm:
 * - Can try naive approach of iterating list for every member of the list itself, checking if the difference of the two is K (skipping current member being examined of course, which anyways would give a difference of 0, as in X - X - 0).
 * - Given K, what is the formula to find X, given Y is a number from the list? `X - Y = K`, so to find X, add Y to K, as in `X = K + Y`. But what if `X` and `Y` were reversed, as in `Y - X = K`? Then formula to find X becomes ``-X = K - Y -> X = Y - K`. Therefore in order to find out if `Y` and `X` diff is `K`, need to do either `Y - K` or `Y + K`.
 *   Example:
 *     K = 4, X = 7, Y = 3
 *     X - Y -> 7 - 3 = 4  (Y + K -> 3 + 4 = 7)
 *     Y - X -> 3 - 7 = -4 (Y - K -> 3 - (-4) = 7)
 *
 * Question: Why not needed to do _both_ `Y - K` and `Y + K`? Because we will touch both members of the pair whether we do one or the other. So for example if doing `Y + K`, as we iterate the array with `K` in one hand, even though `4 + 7 = 11` does not yield a member of the array, once we hit `3`, `4 + 3 = 7` does yield a member int he array, namely `7.`
 *
 *
 * - Can the same number pair with more than one member of the list? Of course, consider [1, 3, 4, 7], and K = 3, 4 pairs with both 1 and 7, because 4 - 1 = 3 and 7 - 4 = 3
 *
 *
 *
 * Runtime: O(N), because need to traverse list just one in order to find the pairs which difference is K
 *
 *
 * Space: O(1), no extra storage needed
 * </pre>
 * @author Jose Quijada
 */
public class FindPairsWithKDiff {
  List<List<Integer>> findPairsWithDifference(List<Integer> l, int k) {
    // Throw all the members of the array into a Map to be able to do O(1) look up.
    final Map<Integer, Integer> m = new HashMap<>();
    for (Integer i : l) {
      m.put(i, i);
    }

    List<List<Integer>> ret = new ArrayList<>();

    // Iterate over each member, doing Y + K and Y - K to see if either equals a member in the Map,
    // and if so and if that pair hadn't already been seen, add it to list of Pair's
    for (Integer i : l) {
      Integer found = m.get(i + k);
      if (null != found) {
        List<Integer> p = new ArrayList<>(i);
        p.add(i);
        p.add(found);
        ret.add(p);
      }
    }


    return ret;
  }
}
