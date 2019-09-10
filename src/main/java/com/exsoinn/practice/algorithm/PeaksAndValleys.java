package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class PeaksAndValleys {
  public static int findLargestVolume(int[] ary) {

    int len = ary.length;
    int peak1 = 0;
    int peak2 = 0;
    int max = 0;

    // Test: [6 5 3 5 4 2 3 7]
    // while1: peak1 = 0
    // peak2 = 0
    // while2: peak2 = 2, diff = 1 + 3 = 4
    // peak2 = 3, dist = 3, shaveOff = 2 * 1 = 2, diff = 2
    // max = 2, peak1 = 3
    // while1: peak1 = 3
    // peak2 = 3
    // while2: peak2 = 5, diff = 1 + 3 = 4
    // while3: diff = 4 + 2 = 6, peak2 = 7
    // dist = 4, shaveOff = N/A
    // max = 6
    /*
     * Why do we limit to "len - 2"? Because need at least 3 bars to have two peaks and a valley
     * for this particular problem
     */
    while (peak1 < len - 2) {
      // Find peak1. Since I'll be looking 1 ahead of peak1, need to check we don't go out of bounce while doing so,
      // hence the reason 1 is subtracted from "len" before we check the value after "peak1"
      while (peak1 < len - 1 && ary[peak1] <= ary[peak1 + 1]) {
        ++peak1;
      }

      peak2 = peak1;

      // Find peak2, descend down into the valley (i.e. drop as low as possible). Since we compared current step
      // the next step, when adding to the "diff" sum, need to advance to that step which is lower in the stair
      int diff = 0;
      while (peak2 < len - 1 && ary[peak2] >= ary[peak2 + 1]) {
        ++peak2;
        diff += ary[peak1] - ary[peak2];
      }

      // Because now we may start climbing, position at the bottom of valley
      ++peak2;

      // Below we calculate the volume current array entry contributes **before** we move on to next higher
      // point, because the next step could be the highest (the second peak), and we don't want to
      // include that in the sum of volume parts that each stair step contributes
      while (peak2 < len - 1 && ary[peak2] < ary[peak2 + 1]) {
        diff += ary[peak1] - ary[peak2];
        ++peak2;
      }

      // Test: [6 5 3 5 4 2 3 7 7]

      // Now adjust the total volume calculated if the peak we used to calcated the steps difference
      // was the higher one
      if (peak2 < len) { // If we're still within bounds
        int dist = peak2 - peak1;
        if (dist > 1 && ary[peak1] > ary[peak2]) { // If indeed there is a valley, and if "peak2" val is the
          // limiting factor
          // Account for lowest peak, and subtract bigger/smaller peak difference from "diff" calculated
          // above for every value inbtween
          // Note: "dist" is the number of "steps" to take to get to "peak2" from "peak1", inclusive
          //       of "peak2", therefor subtract 1 from "dist" because we're interested only in the "steps"
          //       between the 2 peaks, non-inclusive
          int shaveOff = (dist - 1) * Math.abs(ary[peak2] - ary[peak1]);
          diff -= shaveOff;
        }
      }

      max = Math.max(diff, max);

      // Get ready to find next set of peaks, starting from "peak2"
      peak1 = peak2;

    }

    return max;
  }

  public static void main(String[] args) {
    int[] ary = {6, 5, 3, 5, 4, 2, 2, 2, 3, 7, 7};
    System.out.println(findLargestVolume(ary));
  }
}
