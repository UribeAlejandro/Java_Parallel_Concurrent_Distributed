package sequential;


public class ArraySum {
    private final double[] array;
    private double sum = 0;

    public ArraySum(double[] array) {
        this.array = array;
    }
    public double computeSum() {
        long startTime = System.nanoTime();
        for (double v : this.array) {
            this.sum += v;
        }
        long finishTime = System.nanoTime();

        System.out.println("Sequential Sum took:" + (finishTime - startTime) + " nanoseconds");
        return this.sum;
    }
}
