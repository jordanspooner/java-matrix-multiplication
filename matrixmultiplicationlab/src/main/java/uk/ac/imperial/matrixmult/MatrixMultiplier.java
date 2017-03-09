package uk.ac.imperial.matrixmult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplier {

  public static Matrix multiply(final Matrix a, final Matrix b) throws Exception {
    Matrix c = new SimpleArrayMatrix(a.getNumRows(), b.getNumColumns());
    ExecutorService pool = Executors.newFixedThreadPool(7);
    for (int row = 0; row < a.getNumRows(); row ++) {
      FastNaiveMultiply thread = new FastNaiveMultiply(a, b, c, row);
      pool.submit(thread);
    }
    pool.shutdown();
    pool.awaitTermination(2, TimeUnit.MINUTES);
    return c;
  }

}
