package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class CrackingTheCodingInterview {

  /**
   * Find addends of pNumber that are divisible by 2. For example, if pNumber is 7, then the addends
   * of 7 that meet this requirement are 1, 2, 4 (add 'em all together and you get 7). Then write the binary
   * representation of of each addend, combining them into a single string which is the binary representation
   * of 7: 0111.
   * The passed in StringBuilder contains the output, which is the binary representation as a string.
   */
  private static void probFiveDotTwoDecimalToBinary(double pNumber, StringBuilder pResult) {
    final int base = 2;
    int i = 0;
    while (true) {
      double divByTwo = Math.pow(base, i);
      if (pNumber == divByTwo) {
        writeBit(pResult, 32 - i, true);
        break;
      } else if (divByTwo > pNumber) {
        probFiveDotTwoDecimalToBinary(pNumber - Math.pow(base, i - 1), pResult);
        break;
      }
      ++i;
    }
    return;
  }




  private static void writeBit(StringBuilder pResult, int pPos, boolean pIsSet) {
    pResult.replace(pPos, pPos + 1, pIsSet ? "1" : "0");
  }


  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    double number = 7D;
    for (int i = 1; i <= 32; i++) {
      sb.append("0");
    }
    probFiveDotTwoDecimalToBinary(number, sb);
    System.out.println(sb.toString());
  }
}
