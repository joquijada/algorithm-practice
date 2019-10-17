package com.exsoinn.practice.algorithm;

/**
 * <pre>
 * Problem: Determine if string is made up of unique characters only. Cannot use additional data structures.
 *
 * Questions:
 * 1. Does case matter?
 * 2. Do characters come from the alphabet only (26 chars), ASCII (256 chars), or full Java chracter set of 2^16 (because char is 16 bits long, which yields approx 64K different characters)?
 * 3. How long can the string be expected to be?
 *
 * Date: 09/09/2019
 * Start: 7:18PM
 * End:
 *
 * Assumptions:
 * 1. Will assume characters can be any of Java's char 2^16 characters extended ASCII set.
 *
 *
 * Algorithm:
 * 1. Scan one character at a time, from left to right.
 * 2. Have I seen that chracter? Check my memory. If yes, return false right away, else continue to next step.
 * 3. Memorize the character just seen, and start from the top.
 *
 *
 * Runtime: O(N), where N is the total size of the string. But if we know that the character set size is 2^16 characters, and that at most I'll iterate through 2^16 chracters of any string, regardless of size, then might as well call it O(1). Why? Because as per the pigeon hole principle, in a string that contains characters from a set size C, the longest string I can possibly have with all unique characters is C in length, and also because the algorithm below will exit at such first repeated chracter found, which will happen at worst when the 2^16 character of the input string is seen. Or to be more precise, can call it O(min(C, N)), whichever is shorter between the string length N and character set size C.
 *
 * Space: O(1), because it stays constant in relation to input N string length. The constant storage/memory usage will always be 2^16 for this particular program, namely the alphabet bit vector we use to keep track of seen character count. The alphabet size is determined by whatever the interviewer says.
 * </pre>
 * @author josequijada
 */
public class AllUniqueCharacters {
  /**
   * I do use an additional data structure, namely a bit vector to use as my "alphabet" of
   * 2^16 extended ASCII characters to track seen character count. W/o additional data structure allowed,
   * I'd have to resort to an O(N^2) algorithm. The trade of is less space but at the expense for more CPU
   * and and longer computational time perhaps? Which is better? This approach will use 2^13 bytes, which is
   * roughly 8K's of memory, which is a fraction of a 4Gig RAM machine for example
   * Improvement: Refactor logic that deals with finding character's position in
   *       char vector into its own helper method(s)
   */
  boolean allUniqueCharacters(String str) {
    /*
     * Captures the maximum number of characters possible (2^16). Need an int to capture this,
     * because remember 0 is a number too. If I were to use a short, which accomodates
     * 2^16, then I'd get overflow, because the largest positively signed integer a short can
     * hold is number 2^16-1, which is one less than the total number of characters (2^16). The minute
     * I add one to it, because of Java modular
     * integer arithmetic, then I'd get -2^16, which is not what we're looking
     * for here.
     */
    final int maxChars = Character.MAX_VALUE + 1;

    // Optimization: A string longer than 2^16 characters can't possibly have all unique
    //               characters
    if (str.length() > maxChars) {
      return false;
    }


    /*
     * I need enough space to potentially store all 2^16 characters (in case
     * I'm given a string that's 2^16 chracters long). That means I need to be able to store
     * 64000 (64K) bits). If one byte is 8 bits long, how many bytes do I need to store
     * 64000 bits? Divide 2^16/2^3 = 2^13 bytes needed.
     */
    final short numBytes = maxChars/Byte.SIZE;
    final byte[] charVector = new byte[numBytes];

    final char[] chars = str.toCharArray();

    for (Character c : chars) {
      /*
       * Find the byte in the array of bytes that this character belongs to. Why do we divide the char
       * numeric value by 8 (Byte.SIZE)? Because we want to see how many bytes fit into that number,
       * since that will tell use the the position/location in the byte array defined above in
       * which this character belongs.
       */
      final int charNumVal = (int) c;
      short byteIdx = (short) (charNumVal/Byte.SIZE);

      // ...now find the location within the byte of this character.
      byte offset = (byte) (c % Byte.SIZE);

      byte charByte = charVector[byteIdx];
      /*
       * Create a mask to examine this character's position in the vector,
       * to see if it had been seen before.
       */
      byte mask = (byte) (1 << offset);
      if ((charByte & mask) != 0) {
        // Already seen, not unique
        return false;
      }

      // Character being seen for first time, remember it for future comparison
      charByte |= mask;
      charVector[byteIdx] = charByte; // Store it back, remember it's a primitive, not an object reference!
    }

    // All characters in string exhausted and not duplicate found,
    // it must be all unique characters string, therefore return true
    return true;
  }
}
