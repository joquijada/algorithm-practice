package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class BubbleSort {

  static void sortBubble2(int[] numbersToSort) {
    int outerExecTimes = 0;
    int innerExecTimes = 0;
    int sumExecs = 0;
    for (int i = 0; i < numbersToSort.length; i++) {
      outerExecTimes++;
      for (int j = i + 1; j < numbersToSort.length; j++) {
        if (numbersToSort[i] > numbersToSort[j]) {
          int temp = numbersToSort[i];
          numbersToSort[i] = numbersToSort[j];
          numbersToSort[j] = temp;
        }
        innerExecTimes++;
      }
      System.out.println("Outer loop execution number: " + outerExecTimes);
      System.out.println("    Inner loop execution times: " + innerExecTimes);
      sumExecs = sumExecs + innerExecTimes;
      innerExecTimes = 0;
    }
    System.out.println("Total execs is " + sumExecs);
    System.out.println("Sorted array is ");
    for (int i = 0; i < numbersToSort.length; i++) {
      System.out.println(numbersToSort[i]);
    }
  }


  /*static void sortBubble(int[] numbersToSort) {
    int outerExecTimes = 0;
    int innerExecTimes = 0;

    boolean swapped = false;
    {

      outerExecTimes++;
      swapped = false;
      for (int i = 1; i < numbersToSort.length; i++) {
        if (numbersToSort[i - 1] > numbersToSort[i]) {
          int savedBiggerNum = numbersToSort[i - 1];
          numbersToSort[i - 1] = numbersToSort[i];
          numbersToSort[i] = savedBiggerNum;
          swapped = true;
        }
        innerExecTimes++;
      }
      System.out.println("Outer loop execution number: " + outerExecTimes);
      System.out.println("    Inner loop execution times: " + innerExecTimes);
    }
    while (!swapped) {

    }

    System.out.println("Sorted array is ");
    for (int i = 0; i < numbersToSort.length; i++) {
      System.out.println(numbersToSort[i]);
    }
  }*/


  // {4, 75, 74, 2, 54}
  static void sortBubble3(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (arr[i] < arr[j]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
          printIntArray(arr);
        } else {
          System.out.println("No swap: " + arr[i] + " " + arr[j]);
        }
      }
    }
  }

  private static void printIntArray(int[] arr) {
    for (int ent : arr) {
      System.out.print(ent + " ");
    }
    System.out.println("");
  }
}
