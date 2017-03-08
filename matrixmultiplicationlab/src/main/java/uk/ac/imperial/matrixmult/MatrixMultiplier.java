package uk.ac.imperial.matrixmult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplier {

//  // ****  NO MULTITHREADING **** //
//
//  public static Matrix multiply(Matrix a, Matrix b) throws Exception {
//    Matrix c = new MatrixImplementation(a.getNumRows(), b.getNumColumns());
//    for (int i = 0; i < a.getNumRows(); i ++) {
//      for (int k = 0; k < a.getNumColumns(); k ++) {
//        for (int j = 0; j < b.getNumColumns(); j ++) {
//          c.set(i, j, c.get(i,j) + a.get(i, k) * b.get(k, j));
//        }
//      }
//    }
//    return c;
//  }



  // ****  MULTITHREADING NAIVE IMPLEMENTATION **** //

  public static Matrix multiply(final Matrix a, final Matrix b) throws Exception {
    Matrix c = new MatrixImplementation(a.getNumRows(), b.getNumColumns());
    ExecutorService pool = Executors.newFixedThreadPool(10);
    for (int row = 0; row < a.getNumRows(); row ++) {
      MatrixMultiply thread = new MatrixMultiply(a, b, c, row);
      pool.submit(thread);
    }
    pool.shutdown();
    pool.awaitTermination(2, TimeUnit.MINUTES);
    return c;
  }



//  // ****  MULTITHREADING STRASSEN'S IMPLEMENTATION **** //
//
//  public static Matrix multiply(final Matrix a, final Matrix b) throws Exception {
//    //TODO
//  }

}
