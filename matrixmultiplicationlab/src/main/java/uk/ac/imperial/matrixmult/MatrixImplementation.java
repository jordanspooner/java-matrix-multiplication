package uk.ac.imperial.matrixmult;

public class MatrixImplementation implements Matrix {

  private double[][] matrix;

  public MatrixImplementation(int rows, int columns) {
    matrix = new double[rows][columns];
  }

  public double get(int row, int column) {
    return matrix[row][column];
  }

  public void set(int row, int column, double value) {
    matrix[row][column] = value;
  }

  public int getNumRows() {
    return matrix.length;
  }

  public int getNumColumns() {
    return matrix[0].length;
  }

}
