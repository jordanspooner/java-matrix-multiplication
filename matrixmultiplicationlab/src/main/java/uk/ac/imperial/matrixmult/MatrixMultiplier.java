package uk.ac.imperial.matrixmult;

public class MatrixMultiplier {

  public static Matrix multiply(Matrix a, Matrix b) throws Exception {
    Matrix c = new MatrixImplementation(a.getNumRows(), b.getNumColumns());
    for (int i = 0; i < a.getNumRows(); i ++) {
      for (int j = 0; j < a.getNumColumns(); j ++) {
        for (int k = 0; k < b.getNumColumns(); k ++) {
          c.set(i, k, c.get(i,k) + a.get(i, j) * b.get(j, k));
        }
      }
    }
    return c;
  }
  
}
