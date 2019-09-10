package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class ConvertRealNumberToBinary {

  private static String convertToBinarySuccint(double d) {
    if (d > 1) {
      return "Gt than 1, not sure what to do";
    }

    if (d == 1) {
      return "1";
    }

    String out = "0.";
    int cnt = 1;
    while (d > 0) {
      if (cnt > 32) {
        return "ERROR";
      }
      d *= 2;
      out += d >= 1 ? "1" : "0";
      d %= 1;
      ++cnt;
    }
    return out;
  }


  private static String convertToBinary(double d) {
    if (d > 1) {
      return "";
    } else if (d == 1) {
      return "1";
    }

    final int limit = 32;
    int cnt = 1;
    String out = "";
    double prevRes = d;
    while (1 == 1) {
      if (cnt > limit) {
        return "ERROR";
      }


      double rem = prevRes%1;
      if (rem <= 0) {
        break;
      }
      prevRes = rem*2;

      if (prevRes >= 1) {
        out += "1";
      } else {
        //if (rem > 0) {
          out += "0";
        //}
      }

      ++cnt;
    }

    return out;
  }


  public static void main(String[] args) {
    //double[] ary = {.72, 1.54, 0.39, .586903748506693739563748593750583949596};
    double[] ary = {.72, .625, 1, 0, 1.1};
    for (double d : ary) {
      System.out.println("Number " + d + " as binary is: " + convertToBinary(d));
      System.out.println("");
      System.out.println("Number " + d + " as binary is (succint): " + convertToBinarySuccint(d));
      System.out.println("");
    }
  }
}
