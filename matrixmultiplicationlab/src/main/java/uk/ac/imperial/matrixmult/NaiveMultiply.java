package uk.ac.imperial.matrixmult;

public class NaiveMultiply implements Runnable {

    private final Matrix a;
    private final Matrix b;
    private final Matrix c;
    private final int row;

    public NaiveMultiply(Matrix a, Matrix b, Matrix c, int row) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.row = row;
    }

    @Override
    public void run() {
        for (int k = 0; k < a.getNumColumns(); k ++) {
            for (int j = 0; j < b.getNumColumns(); j ++) {
                c.set(row, j, c.get(row,j) + a.get(row, k) * b.get(k, j));
            }
        }
    }

}
