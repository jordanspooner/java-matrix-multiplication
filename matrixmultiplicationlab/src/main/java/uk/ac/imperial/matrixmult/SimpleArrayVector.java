package uk.ac.imperial.matrixmult;

public class SimpleArrayVector implements Vector {

    private double[] vector;

    public SimpleArrayVector(double[] vector) {
        this.vector = vector;
    }

    @Override
    public double getColumn(int column) {
        return vector[column];
    }

    @Override
    public void setColumn(int column, double value) {
        vector[column] = value;
    }

}
