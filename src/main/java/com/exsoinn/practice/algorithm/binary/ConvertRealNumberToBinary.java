package com.exsoinn.practice.algorithm.binary;

/**
 * @author josequijada
 */
public class ConvertRealNumberToBinary {

  public static String convertToBinarySuccint(double d) {
    if (d > 1) {
      return "Gt than 1, not sure what to do";
    }

    if (d == 1) {
      return "1";
    }

    if (d == 0) {
      return "0";
    }

    String out = "";
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


  public static String convertToBinary(double d) {
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


  public static String convertToBinaryAlt(double d) {
    if (d == 0) {
      return "0";
    }
    StringBuilder res = new StringBuilder("");
    int cnt = 1;
    while (d > 0.0D) {
      if (cnt > 32) {
        return "ERROR";
      }
      d *= 2;
      String next = "0";
      if (d >= 1) {
        next = "1";
      }
      res.append(next);
      d %= 1;
      ++cnt;
    }
    return res.toString();
  }
}
