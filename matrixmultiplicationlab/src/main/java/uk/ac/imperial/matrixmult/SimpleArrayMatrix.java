package uk.ac.imperial.matrixmult;

public class SimpleArrayMatrix implements Matrix {

  public double[][] matrix;

  public SimpleArrayMatrix(int rows, int columns) {
    matrix = new double[rows][columns];
  }

  public SimpleArrayMatrix(double[][] matrix) {
    this.matrix = matrix;
  }

  @Override
  public double get(int row, int column) {
    return matrix[row][column];
  }

  @Override
  public void set(int row, int column, double value) {
    matrix[row][column] = value;
  }

  @Override
  public int getNumRows() {
    return matrix.length;
  }

  @Override
  public int getNumColumns() {
    return matrix[0].length;
  }

}
