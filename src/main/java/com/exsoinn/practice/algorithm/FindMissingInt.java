package com.exsoinn.practice.algorithm;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindMissingInt {
  public long findMissingInt(String fileName) throws FileNotFoundException {
    byte pageSize = Byte.SIZE; // pageSize aka pageSize
    // long totalInts = (Integer.MAX_VALUE+1L)*2;
    // If the total number of ints is NOT a power of two, then need to allocate
    // an extra page to accommodate the remainder
    long totalInts = (long)Math.pow(2, 4);
    long totalPages = totalInts/pageSize;
    byte[] vector = new byte[(int)totalPages];

    // "Load" all integers into vector
    Scanner file = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
    while(file.hasNextLine()) {
      String line = file.nextLine();
      long num = Long.parseLong(line);

      // Find the page that this integer belongs to
      // E.g. number 3 will be in first byte, number 8 will be right at beginning of second byte, and so on
      // num modules val is between 0 and val-1 (inclusive)
      int idx = (int)(num/pageSize);

      // find the spot in this page that the number is in, and set that bit
      byte mask = (byte)(1 << num%pageSize);
      vector[idx] |= mask;
    }

    // byte = [7 6 5 4 3 2 1 0]
    // All integers loaded, now find the first '0' bit
    for (long num = 0; num < totalInts; num++) {
      int idx = (int)(num/pageSize);
      byte page = vector[idx];
      byte offset = (byte)(num%pageSize);

      if ((page & (1 << offset)) == 0) {
        return num;
      }
    }
    return -1L; // all "integers" accounted for
  }
}
