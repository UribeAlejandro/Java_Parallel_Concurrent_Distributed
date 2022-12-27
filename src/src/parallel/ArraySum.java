package parallel;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.concurrent.RecursiveAction;

public abstract class ArraySum extends RecursiveAction {
    private double[] arr;
    private double sum;
    public void ArraySum(double[] arr) {
        this.arr = arr;
    }
    @Override
    protected void compute() {
        // TODO: Implement this.
    }
}
