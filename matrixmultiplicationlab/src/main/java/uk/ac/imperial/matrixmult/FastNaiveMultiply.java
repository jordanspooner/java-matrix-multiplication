package uk.ac.imperial.matrixmult;

public class FastNaiveMultiply implements Runnable {

    private final SimpleArrayMatrix a;
    private final SimpleArrayMatrix b;
    private final SimpleArrayMatrix c;
    private final int i;

    public FastNaiveMultiply(Matrix a, Matrix b, Matrix c, int row) {
        this.a = (SimpleArrayMatrix) a;
        this.b = (SimpleArrayMatrix) b;
        this.c = (SimpleArrayMatrix) c;
        this.i = row;
    }

    @Override
    public void run() {
        double[] ai = a.matrix[i];
        double[] ci = c.matrix[i];
        for (int k = 0; k < a.getNumColumns(); k ++) {
            double[] bk = b.matrix[k];
            double aik = ai[k];
            for (int j = 0; j < b.getNumColumns(); j ++) {
                ci[j] += aik * bk[j];
            }
        }
    }

}
