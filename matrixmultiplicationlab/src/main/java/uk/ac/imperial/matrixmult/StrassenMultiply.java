//package uk.ac.imperial.matrixmult;
//
//public class StrassenMultiply implements Runnable {
//
//  private Matrix a;
//  private Matrix b;
//  private Matrix c;
//  private final static int LEAF_SIZE = MatrixMultiplier.LEAF_SIZE;
//
//  public StrassenMultiply(Matrix a, Matrix b, Matrix c) {
//    this.a = a;
//    this.b = b;
//    this.c = c;
//  }
//
//  private static void naiveMultiply(Matrix a, Matrix b, Matrix c) {
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
//  }
//
//  @Override
//  public void run() {
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
//      naiveMultiply(a, b, c);
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
//      Matrix p1 = new ArrayMatrix(newSize, newSize);
//      Thread t1 = new Thread(new StrassenMultiply(aResult, bResult, p1));
//      t1.start();
//
//      aResult.add(a21, a22);
//      Matrix p2 = new ArrayMatrix(newSize, newSize);
//      Thread t2 = new Thread(new StrassenMultiply(aResult, b11, p2));
//      t2.start();
//
//      bResult.subtract(b12, b22);
//      Matrix p3 = new ArrayMatrix(newSize, newSize);
//      Thread t3 = new Thread(new StrassenMultiply(a11, bResult, p3));
//      t3.start();
//
//      bResult.subtract(b21, b11);
//      Matrix p4 = new ArrayMatrix(newSize, newSize);
//      Thread t4 = new Thread(new StrassenMultiply(a22, bResult, p4));
//      t4.start();
//
//      aResult.add(a11, a12);
//      Matrix p5 = new ArrayMatrix(newSize, newSize);
//      Thread t5 = new Thread(new StrassenMultiply(aResult, b22, p5));
//      t5.start();
//
//      aResult.subtract(a21, a11);
//      bResult.add(b11, b12);
//      Matrix p6 = new ArrayMatrix(newSize, newSize);
//      Thread t6 = new Thread(new StrassenMultiply(aResult, bResult, p6));
//      t6.start();
//
//      aResult.subtract(a12, a22);
//      bResult.add(b21, b22);
//      Matrix p7 = new ArrayMatrix(newSize, newSize);
//      Thread t7 = new Thread(new StrassenMultiply(aResult, bResult, p7));
//      t7.start();
//
//      try {
//        t1.join();
//        t2.join();
//        t3.join();
//        t4.join();
//        t5.join();
//        t6.join();
//        t7.join();
//      } catch (Exception e) {
//        System.out.println("Exception: failed to wait for threads to finish.");
//      }
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
//    }
//  }
//
//}