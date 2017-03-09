//package uk.ac.imperial.matrixmult;
//
//public class ArrayMatrix implements Matrix {
//
//  private double[][] matrix;
//  private int rowStartIndex;
//  private int rowEndIndex;
//  private int colStartIndex;
//  private int colEndIndex;
//
//  public ArrayMatrix(int rows, int columns) {
//    matrix = new double[rows][columns];
//    rowStartIndex = 0;
//    rowEndIndex = rows;
//    colStartIndex = 0;
//    colEndIndex = columns;
//  }
//
//  public ArrayMatrix(double[][] matrix, int rowStartIndex, int rowEndIndex, int colStartIndex, int colEndIndex) {
//    this.matrix = matrix;
//    this.rowStartIndex = rowStartIndex;
//    this.rowEndIndex = rowEndIndex;
//    this.colStartIndex = colStartIndex;
//    this.colEndIndex = colEndIndex;
//  }
//
//  @Override
//  public double get(int row, int column) {
//    return matrix[rowStartIndex + row][colStartIndex + column];
//  }
//
//  @Override
//  public void set(int row, int column, double value) {
//    matrix[rowStartIndex + row][colStartIndex + column] = value;
//  }
//
//  @Override
//  public int getNumRows() {
//    return rowEndIndex - rowStartIndex;
//  }
//
//  @Override
//  public int getNumColumns() {
//    return colEndIndex - colStartIndex;
//  }
//
//  @Override
//  public Vector getRow(int row) {
//    System.out.println("ERROR: Operation getRow is not currently supported.");
//    return null;
//  }
//
//  @Override
//  public void add(Matrix a, Matrix b) {
//    int n = a.getNumRows();
//    for (int i = 0; i < n; i++) {
//      for (int j = 0; j < n; j++) {
//        try {
//          set(i, j, a.get(i, j) + b.get(i, j));
//        } catch (IndexOutOfBoundsException e) {
//          break;
//        }
//      }
//    }
//  }
//
//  @Override
//  public void subtract(Matrix a, Matrix b) {
//    int n = a.getNumRows();
//    for (int i = 0; i < n; i++) {
//      for (int j = 0; j < n; j++) {
//        try {
//          set(i, j, a.get(i, j) - b.get(i, j));
//        } catch (IndexOutOfBoundsException e) {
//          break;
//        }
//      }
//    }
//  }
//
//  @Override
//  public Matrix resizeTo(int rowStartIndex, int rowEndIndex, int colStartIndex, int colEndIndex) {
//    return new ArrayMatrix(matrix, rowStartIndex, rowEndIndex, colStartIndex, colEndIndex);
//  }
//
//}
