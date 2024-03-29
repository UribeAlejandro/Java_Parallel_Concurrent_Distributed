package parallel.concurrent.distributed.sequential;


public class ArraySum {

  private final double[] array;
  private double sum = 0;

  public ArraySum(double[] array) {
    this.array = array;
  }

  public double computeSum() {
    for (double v : array) {
      sum += v;
    }
    return sum;
  }
}
