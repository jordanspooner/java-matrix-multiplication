package uk.ac.imperial.matrixmult;

public class MatrixBuilder {

  public static Matrix build(double[][] source) {
    int nRows = source.length;
    int nCols = source[0].length;
    Matrix matrix = new ArrayMatrix(nRows, nCols);
    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        matrix.set(row, col, source[row][col]);
      }
    }
    return matrix;
  }

  public static Matrix build(int nRows, int nCols) {
    Matrix matrix = new ArrayMatrix(nRows, nCols);
    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        matrix.set(row, col, 0);
      }
    }
    return matrix;
  }

}