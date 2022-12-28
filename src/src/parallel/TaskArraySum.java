package parallel;

import java.util.concurrent.RecursiveTask;

public class TaskArraySum extends RecursiveTask<Double> {
    private final double[] array;
    private double sum;
    private final int l;
    private final int h;

    public TaskArraySum(int l, int h, double[] array) {
        this.l = l;
        this.h = h;
        this.array = array;
    }

    @Override
    protected Double compute() {
        if (l-h <= 1000) {
            for (int i = l; i< h; i++) {
                sum += array[i];
            }
        } else {
            TaskArraySum left = new TaskArraySum(l, (l + h) / 2, array);
            TaskArraySum right = new TaskArraySum((l + h) / 2, h, array);
            left.fork();
            right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }
}
