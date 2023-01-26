package com.exsoinn.practice.algorithm;

public class ExSoInnStringBuilder {
  private ArrayList<Character> buffer = new ArrayList<>();

  public void append(String s) {
    for (int i = 0; i < s.length(); i++) {
      buffer.add(s.charAt(i));
    }
  }

  public String toString() {
    String str = "";
    for (int i = 0; i < buffer.size(); i++) {
      str += buffer.get(i);
    }

    return str;
  }

  public void clear() {
    buffer = new ArrayList<>();
  }
}
