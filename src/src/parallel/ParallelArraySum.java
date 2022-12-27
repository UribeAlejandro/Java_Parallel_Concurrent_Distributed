package parallel;

import java.util.concurrent.RecursiveAction;

public class ParallelArraySum extends RecursiveAction {
    private final double[] array;
    private double sum;
    private final int l;
    private final int h;

    public ParallelArraySum(int l, int h, double[] array) {
        this.l = l;
        this.h = h;
        this.array = array;
    }
    public void computeSum() {
        compute();
        System.out.println(sum);
    }
    @Override
    protected void compute() {
        int SEQUENTIAL_THRESHOLD = 1000;
        if (h - l <= SEQUENTIAL_THRESHOLD) {
            for (int i = l; i<h; ++i) {
                sum += array[i];
            }
        } else {
            ParallelArraySum left = new ParallelArraySum(this.l, (l + h) / 2, this.array);
            ParallelArraySum right = new ParallelArraySum((l + h) / 2, h,  this.array);
            left.fork();
            right.compute();
            left.join();
            sum = left.sum + right.sum;
        }
    }
}
