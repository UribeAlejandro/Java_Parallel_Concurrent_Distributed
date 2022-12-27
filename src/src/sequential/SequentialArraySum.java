package sequential;


public class SequentialArraySum {
    private final double[] array;
    private double sum = 0;

    public SequentialArraySum(double[] array) {
        this.array = array;
    }
    public void computeSum() {
        for (double v : array) {
            sum += v;
        }
    }
}
