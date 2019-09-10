package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of a matrix, using a 2D int array
 *
 * Assumption: The matrix is NxN, meaning it's a square
 *
 * @author josequijada
 */
public class Matrix {
  private final int[][] matrix;

  public Matrix() {
    this(0, 0);
  }

  public Matrix(int pLength, int pHeight) {
    matrix = new int[pLength][pHeight];
  }


  /**
   * Rotate matrix 90 degrees clockwise
   */
  public void rotateByNinetyDegrees() {
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[x].length; y++) {
        /*
         * The recursion originating from a different point might have caused
         * the current point in this iteration to have been visited already, hence the reason
         * the visited boolean check below, else we end up rotating things 360 degrees, as earlier
         * tests showed
         */
        if (visited[x][y]) continue;
        Entry e = new Entry(x, y, matrix[x][y]);
        move(e, null, null, visited);
      }
    }
  }

  public void init(List<Integer> pVals) {
    for (int i = 0; i < pVals.size(); i += 3) {
      matrix[pVals.get(i)][pVals.get(i + 1)] = pVals.get(i + 2);
    }
  }


  public void render() {
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[x].length; y++) {
        if (matrix[x][y] == 0) {
          //continue;
        }
        System.out.println("x=" + x + ", y=" + y + " ==>> " + matrix[x][y]);
      }
    }
  }


  /**
   * The pVisitied boolean 2D array keeps track of nodes visited by move(), so that the calling for() loop
   * again does not try to re-rotate pixels already rotated
   * @param pSrcEntry
   * @param pOrigX
   * @param pOrigY
   * @param pVisited
   */
  private void move(Entry pSrcEntry, Integer pOrigX, Integer pOrigY, boolean[][] pVisited) {
    // Keep track of coordinates requested when method first entered
    // This breaks the cycle once we reach full circle
    if (null != pOrigX && null != pOrigY && pSrcEntry.x == pOrigX && pSrcEntry.y == pOrigY) {
      // We've reached full circle, return to break the cycle
      return;
    }


    // On first call to this method from another place, save coordinates. Calls of this method to itself
    // will see the original X and Y when this method was first called
    if (null == pOrigX) pOrigX = pSrcEntry.x;
    if (null == pOrigY) pOrigY = pSrcEntry.y;

    // Found the coordinates of the pixel that this pixel (pSrcEntry) should be moved to
    Entry targetEntry = map(pSrcEntry);
    // Before the mapped coordinates get updated, update the coordinates this mapped
    // coordinates map to (I.e. let target update its coordinates)
    move(targetEntry, pOrigX, pOrigY, pVisited);

    // Now replace the target coordinates (targetEntry) with these coordinates' pixel (pSrcEntry)
    update(pSrcEntry, targetEntry);

    // Mark this coordinates (I.e. pixel) as already visited/moved
    pVisited[pSrcEntry.x][pSrcEntry.y] = true;
  }



  /**
   * Updates pTarget coordinates with the value from pSrc.
   */
  private void update(Entry pSrc, Entry pTarget) {
    matrix[pTarget.x][pTarget.y] = pSrc.val;
  }


  /**
   * This is crucial helper method. It finds the coordinates that the passed in Entry coordinates
   * would replace in a 90 degree rotation scheme. It performs this calculation as a function of the source
   * coordinates. The function goes as follows:<br/><br/>
   * target.x = source.y<br/>
   * target.y = (N - 1) - source.x, where N is the length/height of the matrix<br/><br/>
   * TODO: Can provide option to rotate counter clockwise by modifying formulas for target x and y,
   * TODO: function then becomes target.x = (N - 1) - y, target.y = x if rotating counter clockwise
   *
   * @param pSrc
   * @return
   */
  private Entry map(Entry pSrc) {
    int newX = pSrc.y;
    int newY = (matrix.length - 1) - pSrc.x;
    Entry ent = new Entry(newX, newY, matrix[newX][newY]);
    //read(ent);
    return ent;
  }


  private void read(Entry pEntry) {
    pEntry.val = matrix[pEntry.x][pEntry.y];
  }



  private static class Entry {
    private int x;
    private int y;
    private int val;

    private Entry(int x, int y, int val) {
      this.x = x;
      this.y = y;
      this.val = val;
    }
  }

  private static class Point {
    private final int x;
    private final int y;

    private Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "x is " + x + ", y is " + y + "\n";
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Point)) {
        return false;
      }

      Point other = (Point) o;
      return this.x == other.x && this.y == other.y;
    }

  }


  public void counterClockwiseNavigation() {
    if (null == matrix || matrix.length < 1) {
      return;
    }

    Point mid = middle();
    int maxRadius = radius();
    for (int i = 0; i <= maxRadius; i++) {
      List<Point> points = points(i, mid);

      for (Point p : points) {
        print(p);
      }
    }
  }


  private void print(Point p) {
    System.out.println(p.toString());
  }


  private Point middle() {
    return new Point(matrix.length/2, matrix.length/2);
  }

  private int radius() {
    return matrix.length/2;
  }



  private List<Point> points(int r, Point p) {
    if (r == 0) {
      return Collections.singletonList(p);
    }


    // These are the bounds used to go around at current distance "r" from
    // from Point "p" (the caller should have passed the center/middle point in argument "p")
    int maxX = p.x + r;
    int minX = p.x - r;
    int maxY = p.y + r;
    int minY = p.y - r;

    List<Point> l = new ArrayList<>();

    // Starting point arbitrarily chosen to be to the right of middle, "r" sitance from it
    // on the x axis
    pointsHelper(maxX, minX, maxY, minY, l, new Point(p.x + r, p.y));
    return l;
  }


  private void pointsHelper(int maxX, int minX, int maxY, int minY, List<Point> l, Point p) {
    if (l.contains(p)) { // We already did a full circle, jet out
      return;
    } else {
      l.add(p);
    }

    // Calculate coordinates of next point based counter-clockwise rotaion; we
    // basically test movements to see which would put of out of bounds, and this way
    // decide which direction to go (left, down, right or up) so as to stay within bounds. It's like
    // giving eyes to a robot. This is done in reference to the max/min X ad Y coordinates passed in to
    // this method.
    Point nextP = null;
    if (p.x-1 >= minX && p.y+1 > maxY) {
      // left: Only when going up y axis would put us out of bounds, and as long we're
      //       within x axis left bound
      nextP = new Point(p.x - 1, p.y);
    } else if (p.x-1 < minX && p.y-1 >= minY) {
      // down: Only when going left on x axis would put us out of bounds, and as long as we're
      //       within y axis lower bound
      nextP = new Point(p.x, p.y - 1);
    } else if (p.y-1 < minY && p.x+1 <= maxX) {
      // right: Only when going down on on y axis would put us ou of bounds, and as long as we're
      //        within x axis right bound
      nextP = new Point(p.x + 1, p.y);
    } else if (p.x+1 > maxX && p.y+1 <= maxY) {
      // up: Only when going right on x axis would put us out of bounds, and as long as we're
      //     within y axis upper bound
      nextP = new Point(p.x, p.y + 1);
    }
    pointsHelper(maxX, minX, maxY, minY, l, nextP);
  }

  public  static void main(String[] pArgs) {
    int l = 5;
    int h = 5;
    Matrix m = new Matrix(5, 5);
    System.out.println("Populating...");
    populateMatrix(m, l, h);
    System.out.println("Before Rotation...");
    m.render();
    System.out.println("\n\n");
    System.out.println("Rotating...");
    m.rotateByNinetyDegrees();
    System.out.println("After Rotation...");
    m.render();

    System.out.println("Counter clockwise navigation from center outward...");
    m.counterClockwiseNavigation();

  }


  private static void populateMatrix(Matrix m, int l, int h) {
    List<Integer> vals = new ArrayList<>();

    for (int x = 0; x < l; x++) {
      for (int y = 0; y < h; y++) {
        vals.add(x);
        vals.add(y);
        vals.add(Integer.valueOf(x + "" + y));
      }
    }
    m.init(vals);
  }
}
