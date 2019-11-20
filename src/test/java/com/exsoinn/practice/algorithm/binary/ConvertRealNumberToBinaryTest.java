package com.exsoinn.practice.algorithm.binary;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

public class ConvertRealNumberToBinaryTest extends TestCase {
  public void testConvertToBinaryAlt() {
    Map<Double, String> test = new HashMap<>();
    test.put(.72, "ERROR");
    test.put(.625, "101");
    test.put(1D, "1");
    test.put(0D, "0");
    for (Map.Entry<Double, String> e: test.entrySet()) {
      assertEquals(e.getValue(), ConvertRealNumberToBinary.convertToBinaryAlt(e.getKey()));
      assertEquals(e.getValue(), ConvertRealNumberToBinary.convertToBinarySuccint(e.getKey()));
    }
  }
}

/*
Number 0.72 as binary is: ERROR



Number 0.0 as binary is:

Number 0.0 as binary is (succint): 0.

Number 1.1 as binary is:

Number 1.1 as binary is (succint): Gt than 1, not sure what to do

 */