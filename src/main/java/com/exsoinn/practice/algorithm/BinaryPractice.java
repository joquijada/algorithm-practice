package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class BinaryPractice {
  /*
   * TRICK:  If 0-based (ask interviewer), subtract i from 31 (31 - i) to get the "up to and including from right
   *         to left" bit that's supposed to remain. Then add 1 to it to get total number of bits that are going
   *         to remain. That's why "my way" below works, because the "i" happens to be the number of bits that will
   *         remain: remain = (31 - i) + 1, or 32 - i.
   *         Always think in terms of the i'th bit, starting from 0.
   */
    /*System.out.println("Clear from most significant bit, up to i (inclusive), CCI way " + Integer.toBinaryString((1 << i) - 1));
    System.out.println("Clear from most significant bit, up to i (inclusive), my way  " + Integer.toBinaryString(-1 >>> -i));

    System.out.println("Clear i through 0 least significant bits (inclusive), CCI way " + Integer.toBinaryString(~(-1 >>> 18)));*/

  /*
   * Must add 1 to i because of the inclusive requirement, and the fact that bit position starts with "0"
   * TRICK: When the interviewer asks "clear for me the least significant bits from i through 0, re-confirm
   *        that's 0-based. Which it must be if he's talking about bit "number" 0 (must mean bit at index 0).
   *        Taking into account that 0 is considered an integer, that means there are "i + 1" total number
   *        of least significant bits she wants you to dispose of.
   */
  /*System.out.println("Clear i through 0 least significant bits (inclusive), my way  " + Integer.toBinaryString(-1 << (13 + 1)));*/


  /**
   *
   */
  private static int clearMostSignificantBitsCci(int n, int i) {
    int mask = (1 << i) - 1;
    System.out.println(
            "Mask to clear from most significant bit, up to " + i + " (inclusive), CCI way "
                    + Integer
                    .toBinaryString(mask));
    return n & mask;
  }


  /**
   * Exploits the fact that Java uses 2's complement to represent negative numbers.
   * For example -25 is represented as a 7 in the 5 least significant bits (bits 4 - 0), and all
   * ones in bits 31 - 5.
   * 11111111111111111111111111111111
   * When using a number like this as the shifter, the first 5 least significant
   * bits are used if the shiftee is an integer (if shiftee was a byte, then only the first 3 least significant bits
   * get used), etc. The reason for this is that 5 bits is enough to
   * represent all integers 31 - 0 (2^32) - what's the point in having a number greater than
   * 2^5 if there are only 5 bits??? Example (pretend we're dealing with byte's instead of
   * int's, to save space):
   *
   * mask = -[7 6 5 4 3 2 1 0] (this array denotes the position), it's really -1, so a 1 is there in every bit)
   *
   * i = 5, negate i:
   *
   * -5 = will be 011 (3)
   *
   * mask gets shifted (including the 1 at position "7") by 3. The leaves 3 0's followed 5 1's
   */
  private static void clearMostSignificantBitsInclusiveMyWay(int n, int i) {
    int mask = -1 >>> -i;
    System.out.println(
            "Mask to clear from most significant bits, up to " + i + " (inclusive), my way "
                    + Integer
                    .toBinaryString(mask));
    System.out.println("Before: " + Integer.toBinaryString(n));
    System.out.println("After: " + Integer.toBinaryString(n & mask));
  }


  /**
   * This is broken, will not work on edge-case where i == 31. Why? Because we're adding 1 to i, it
   * results in 32, which shifts the absolute( -1) back around to the beginning (remember this is
   * modular, like integers - it will not give an overflow exception!!!)
   */
  private static int clearLeastSignificantBitsInclusiveMyWay(int n, int i) {
    int mask = (-1 << (i + 1));
    System.out.println(
            "Mask Clear " + i + " through 0 least significant bits (inclusive), my way " + Integer
                    .toBinaryString(mask));
    return n & mask;
  }


  /**
   * Example: i = 5, pretend n is a byte (to avoid having to type 32 bits). The starting mask
   * is -1:
   *
   * mask = -[7 6 5 4 3 2 1 0]
   *
   * Logical-shift mask to the right by (7 - i) positions, which is (7 - 5 = 2):
   *
   * mask = [0 0 5 4 3 2 1 0]
   *
   * The negated mask (the complement) is [7 6 0 0 0 0 0 0]. Notice bits 5 - 0 are "0', hence all those
   * bits will get cleared, which is that this method promises.
   */
  private static int clearLeastSignificantBitsInclusiveCci(int n, int i) {
    int mask = ~(-1 >>> (31 - i));
    System.out.println(
            "Mask Clear " + i + " through 0 least significant bits (inclusive), cci   " + Integer
                    .toBinaryString(mask));
    return n & mask;
  }


  /**
   * First clear the bit to update:
   * 1. Shifting 1 (the mask) to the left by "i", which gives something like 0001000,
   * 2. NOT the mask created above to get 1110111
   * 3. AND this mask with "n".
   *
   * Finally set the new bit value:
   * 1. Shift 1 or 0 (depending on whether we're updating with 1 or 0 respectively) to the left (<<) by i.
   * 2. OR the above to "n", which bit in question had been cleared first as per above
   */
  private static int updateBit(int n, int i, boolean set) {
    int val = set ? 1 : 0;
    int mask = ~(1 << i);
    return (n & mask) | (val << i);
  }


  /**
   * Updates the bit "i" of "n" to 1 if "set" is true, 0 otherwise
   */
  private static int updateBitPractice(int n, int i, boolean set) {
    // First clear the ith bit
    n &= ~(1 << i);

    // If the bit should be set, do so, else leave as is
    if (set) {
      n |= (1 << i);
    }
    return n;
  }


  public static void main(String[] args) {
    int i = 31;
    //clearLeastSignificantBitsInclusiveMyWay(367, i);
    //clearLeastSignificantBitsInclusiveFixed(367, 30);
    //clearLeastSignificantBitsInclusiveCci(367, i);
    //clearMostSignificantBitsMyWay(367, 30);

    //System.out.println(Integer.toBinaryString(-1 << i));
    //System.out.println(Integer.toBinaryString(1 << i));

    //int i2 = 23;
    //System.out.println("Negation of " + i2 + " is: " + Integer.toBinaryString(-i2));
    //System.out.println("(1 << " + i2 + ") - 1) is: " + Integer.toBinaryString((1 << i2) - 1));
    System.out.println(Integer.toBinaryString(-6));
  }


}
