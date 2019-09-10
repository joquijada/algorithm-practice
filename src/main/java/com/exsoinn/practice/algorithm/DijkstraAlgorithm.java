package com.exsoinn.practice.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <strong>Runtime efficiency analysis</strong><br/>
 * The operations involved here are,<br/>
 * 1. Updating path weight<br/>
 * 2. Updating the previous vertex<br/>
 * 3. Removing an item from the priority queue<br/>
 *
 * (Note: Number 1 and 2 either happen together or they don't, whenever and if a shortest path has been
 * detected when a vertex being visited from multiple adjacent vertices)<br/><br/>
 * <i><u>If the priority queue has been implemented as a min heap</u></i><br/>
 * Operations #1 and #2 above both take O(1), because former is looking up the vertex in a Map, and the latter
 * indexes right into the array using the vertex number - both are constant operations. These operations can
 * happen every time a vertex is visited as a neighbor of the vertex currently puled from the priority queue.
 * Assuming worst case scenario where there's a 2-way connection between **every* pair of vertices,
 * then for every vertex can do these pair of operations up to V - 1 times (because these vertex can be a neighbor of the
 * other vertices except itself).<br/>
 * Removal from a heap is O(log V), because after removal the heap needs to be re-heapified to maintain
 * its properties (see <a href="https://apps2.mdp.ac.id/perpustakaan/ebook/Karya%20Umum/Dsa.pdf">DSA book</a>,
 * search for "algorithm MinHeapify()"). Since we have to do this for every vertex V, the time complexity
 * becomes O(V log V). Therefore we have a grand total of O(E + V log V). We renamed "V - 1" to just E, because
 * those are two separate variables, else we would have had to drop the non-dominant term "V - 1". <strong>But</strong>
 * since we know in this case that E can <strong>never</strong> surpass V, it is OK to drop it in this instance,
 * so we're left with O(V log V). Contrast this with <strong>Cracking The Coding Interview, 6th
 * edition, page 635</strong>. The reason she has O((E + V) log V) is because the path weight has two be updated
 *
 *<br/><br/>
 * <i><u>If the priority queue has been implemented as an array</u></i><br/>
 * Operation #1 and #2 complexity is same as for min heap above, namly O(E) (remember we renamed "V - 1" to
 * just E, because V and # should be treated as separate variables for big O purposes).<br/>
 * Then we're left with operation #3. We'd have to search the array for the item with the lowest path weight. This
 * is a linear operation, therefore O(V), where V are the vertices contained in the array. Since we have
 * to remove every vertex, this becomes a O(V\u00B2) operation. So we have a grand total here of O(E + V\u00B2).
 * <br/><br/>
 *
 * FIXME: The {@link PriorityQueue} of lowest path weight needs to be rebalanced everytime the path weight
 * of a vertex gets updated.
 *
 * @author josequijada
 */
public class DijkstraAlgorithm {

  public static void findShortestPath(int[][] adj, final int[][] edgeWeight, int start, int end) {

    // Init an array to store the previous node of each "graph" node passed in "adj". Initially
    // previous is set to -1 for all. The "prev" array serves like breadcrumbs to find our path back
    // from destination, so that we can print the shortes route from start to end, see printPath() method below
    int[] prev = initPrevious(adj);

    // Init our priority queue with all of the nodes in "adj". Priority is infinity for all,
    // except for the origin ("start") which we set to "0"
    PriorityQueue<Vertex> pq =
            new PriorityQueue<>(
                    Comparator.comparingInt(o -> o.pathWeight));
    /*
     * This map is used as convenience, to get a reference to a vertex in order to
     * update it's "pathWeight" (I.e. its priority in the PriorityQueue)
     */
    Map<Integer, Vertex> vMap = populateQueue(adj, pq);
    vMap.get(start).pathWeight = 0;

    while (!pq.isEmpty()) {
      // Visit the neighbors of the node with currently the lowest path weight in the queue
      int cur = pq.poll().number;
      for (int i = 0; i < adj[cur].length; i++) {
        int neighbor = adj[cur][i];
        boolean shouldUpdateNodeInfo = false;
        // Update path weight
        if (vMap.get(neighbor).pathWeight == Integer.MAX_VALUE) {
          // This neighbor had not been visited yet
          shouldUpdateNodeInfo = true;
        } else {
          /*
           * This neighbor had been visited already via a different vertex. Let's see
           * if the new vertex visiting us provides a shorter route (aka path) to us, in which
           * I will update my previous reference to you, as well as my path weight. If my
           * path weight is updated and it becomes the lowest path weight in the priority queue,
           * then I will be picked next.
           */
          if ((edgeWeight[cur][i] + vMap.get(cur).pathWeight) < vMap
                  .get(neighbor).pathWeight) {
            shouldUpdateNodeInfo = true;
          }
        }

        if (shouldUpdateNodeInfo) {
          vMap.get(neighbor).pathWeight = edgeWeight[cur][i] + vMap.get(cur).pathWeight;
          prev[neighbor] = cur;
        }
      }
    }
    printPath(end, prev, vMap);
  }


  private static void printPath(final int destination, int[] prev, Map<Integer, Vertex> vMap) {
    LinkedList<Character> path = new LinkedList<>();
    int cur = destination;
    while (prev[cur] != -1) {
      path.addFirst(translateToChar(cur));
      cur = prev[cur];
    }

    // Add the origin, which above while() did not add because
    // it has prev set to -1
    path.addFirst(translateToChar(cur));

    System.out.println("Shortest path is " + path);
  }

  /**
   * Adds the passed in integer to character 'a' to see what letter in the alphabet it yields. In
   * other words the passed in argument is treated as an offset from 'a' in the English alphabet.
   * Number 26 for example yields letter 'z'.
   */
  private static char translateToChar(int n) {
    return (char) ('a' + n);
  }

  private static Map<Integer, Vertex> populateQueue(int[][] adj, PriorityQueue<Vertex> q) {
    Map<Integer, Vertex> map = new HashMap<>();
    for (int i = 0; i < adj.length; i++) {
      Vertex v = new Vertex(i);
      map.put(i, v);
      q.add(v);
    }

    return map;

  }

  private static int[] initPrevious(int[][] adj) {
    int[] ary = new int[adj.length];
    for (int i = 0; i < adj.length; i++) {
      ary[i] = -1;
    }
    return ary;
  }


  private static class Vertex {

    int number;
    int pathWeight = Integer.MAX_VALUE;

    private Vertex(int pNum) {
      number = pNum;
    }
  }

  public static void main(String[] args) {
    int[][] adj = {{1, 2, 4}, {3}, {1, 3}, {0, 6, 7}, {0, 7, 8}, {1, 6}, {2, 8}, {2, 5, 6}, {}};
    int[][] edgeWeight = {{5, 3, 2}, {2}, {1, 1}, {1, 2, 1}, {1, 4, 7}, {3, 1}, {3, 2}, {2, 2, 2},
            {}};
    findShortestPath(adj, edgeWeight, 0, 8);
  }
}
