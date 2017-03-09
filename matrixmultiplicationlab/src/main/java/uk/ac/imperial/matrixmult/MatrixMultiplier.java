package uk.ac.imperial.matrixmult;

public class MatrixMultiplier {

//  // ****  NO MULTITHREADING NAIVE IMPLEMENTATION **** //
//
//  public static Matrix multiply(Matrix a, Matrix b) throws Exception {
//    Matrix c = new ArrayMatrix(a.getNumRows(), b.getNumColumns());
//    for (int i = 0; i < a.getNumRows(); i ++) {
//      for (int k = 0; k < a.getNumColumns(); k ++) {
//        for (int j = 0; j < b.getNumColumns(); j ++) {
//          c.set(i, j, c.get(i,j) + a.get(i, k) * b.get(k, j));
//        }
//      }
//    }
//    return c;
//  }



//  // ****  MULTITHREADING NAIVE IMPLEMENTATION **** //
//
//  public static Matrix multiply(final Matrix a, final Matrix b) throws Exception {
//    Matrix c = new ArrayMatrix(a.getNumRows(), b.getNumColumns());
//    ExecutorService pool = Executors.newFixedThreadPool(10);
//    for (int row = 0; row < a.getNumRows(); row ++) {
//      NaiveMultiply thread = new NaiveMultiply(a, b, c, row);
//      pool.submit(thread);
//    }
//    pool.shutdown();
//    pool.awaitTermination(2, TimeUnit.MINUTES);
//    return c;
//  }



//  // ****  NO MULTITHREADING STRASSEN'S IMPLEMENTATION **** //
//
//  public final static int LEAF_SIZE = 100;
//
//  public static Matrix multiply(Matrix a, Matrix b) {
//    return strassen(a, b);
//  }
//
//  private static Matrix naiveMultiply(Matrix a, Matrix b) {
//    Matrix c = new ArrayMatrix(a.getNumRows(), b.getNumColumns());
//    for (int i = 0; i < a.getNumRows(); i ++) {
//      for (int k = 0; k < a.getNumColumns(); k ++) {
//        double aik;
//        try {
//          aik = a.get(i, k);
//        } catch (IndexOutOfBoundsException e) {
//          break;
//        }
//        for (int j = 0; j < b.getNumColumns(); j ++) {
//          try {
//            c.set(i, j, c.get(i, j) + aik * b.get(k, j));
//          } catch (IndexOutOfBoundsException e) {
//            break;
//          }
//        }
//      }
//    }
//    return c;
//  }
//
//  private static Matrix strassen(Matrix a, Matrix b) {
//    Matrix c = strassenRec(a, b);
//    return c.resizeTo(0, a.getNumRows(), 0, b.getNumColumns());
//  }
//
//  private static Matrix strassenRec(Matrix a, Matrix b) {
//    int aRows = a.getNumRows();
//    int aCols = a.getNumColumns();
//    int bCols = b.getNumColumns();
//    int n = Math.max(aRows, aCols);
//    n = Math.max(n, bCols);
//    int m = n % 2 == 0 ? n : n + 1;
//    Matrix resizeA = a.resizeTo(0, m, 0, m);
//    Matrix resizeB = b.resizeTo(0, m, 0, m);
//
//    if (n <= LEAF_SIZE) {
//      return naiveMultiply(a, b);
//    } else {
//      int newSize = m / 2;
//      Matrix a11 = resizeA.resizeTo(0, newSize, 0, newSize);
//      Matrix a12 = resizeA.resizeTo(0, newSize, newSize, m);
//      Matrix a21 = resizeA.resizeTo(newSize, m, 0, newSize);
//      Matrix a22 = resizeA.resizeTo(newSize, m, newSize, m);
//      Matrix b11 = resizeB.resizeTo(0, newSize, 0, newSize);
//      Matrix b12 = resizeB.resizeTo(0, newSize, newSize, m);
//      Matrix b21 = resizeB.resizeTo(newSize, m, 0, newSize);
//      Matrix b22 = resizeB.resizeTo(newSize, m, newSize, m);
//
//      Matrix aResult = new ArrayMatrix(newSize, newSize);
//      Matrix bResult = new ArrayMatrix(newSize, newSize);
//
//      aResult.add(a11, a22);
//      bResult.add(b11, b22);
//      Matrix p1 = strassenRec(aResult, bResult);
//
//      aResult.add(a21, a22);
//      Matrix p2 = strassenRec(aResult, b11);
//
//      bResult.subtract(b12, b22);
//      Matrix p3 = strassenRec(a11, bResult);
//
//      bResult.subtract(b21, b11);
//      Matrix p4 = strassenRec(a22, bResult);
//
//      aResult.add(a11, a12);
//      Matrix p5 = strassenRec(aResult, b22);
//
//      aResult.subtract(a21, a11);
//      bResult.add(b11, b12);
//      Matrix p6 = strassenRec(aResult, bResult);
//
//      aResult.subtract(a12, a22);
//      bResult.add(b21, b22);
//      Matrix p7 = strassenRec(aResult, bResult);
//
//      Matrix c = new ArrayMatrix(m, m);
//
//      c = c.resizeTo(0, newSize, newSize, m);
//      c.add(p3, p5);
//
//      c = c.resizeTo(newSize, m, 0, newSize);
//      c.add(p2, p4);
//
//      c = c.resizeTo(0, newSize, 0, newSize);
//      aResult.add(p1, p4);
//      bResult.add(aResult, p7);
//      c.subtract(bResult, p5);
//
//      c = c.resizeTo(newSize, m, newSize, m);
//      aResult.add(p1, p3);
//      bResult.add(aResult, p6);
//      c.subtract(bResult, p2);
//
//      c = c.resizeTo(0, n, 0, n);
//      return c;
//    }
//  }



  // ****  MULTITHREADING STRASSEN'S IMPLEMENTATION **** //

  public final static int LEAF_SIZE = 100;

  public static Matrix multiply(Matrix a, Matrix b) {
    return strassen(a, b);
  }

  private static Matrix strassen(Matrix a, Matrix b) {
    Matrix c = new ArrayMatrix(a.getNumRows(), b.getNumColumns());
    Thread t = new Thread(new StrassenMultiply(a, b, c));
    t.start();
    try {
      t.join();
    } catch (Exception e) {
      System.out.println("Exception: failed to wait for main thread to finish.");
    }
    return c.resizeTo(0, a.getNumRows(), 0, b.getNumColumns());
  }

}
