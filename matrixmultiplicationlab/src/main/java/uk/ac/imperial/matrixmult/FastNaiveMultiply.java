package uk.ac.imperial.matrixmult;

public class FastNaiveMultiply implements Runnable {

    private final Matrix a;
    private final Matrix b;
    private final Matrix c;
    private final int i;

    public FastNaiveMultiply(Matrix a, Matrix b, Matrix c, int row) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.i = row;
    }

    @Override
    public void run() {
        Vector ai = a.getRow(i);
        Vector ci = c.getRow(i);
        for (int k = 0; k < a.getNumColumns(); k ++) {
            Vector bk = b.getRow(k);
            double aik = ai.getColumn(k);
            for (int j = 0; j < b.getNumColumns(); j ++) {
                ci.setColumn(j, ci.getColumn(j) + aik * bk.getColumn(j));
            }
        }
    }

}
