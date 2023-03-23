package parallel.concurrent.distributed.parallel;

import java.util.concurrent.RecursiveAction;

public class ActionArraySum extends RecursiveAction {

  private final double[] array;
  private final int l;
  private final int h;
  private double sum;


  public ActionArraySum(int l, int h, double[] array) {
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
    if (h - l <= 1000) {
      for (int i = l; i < h; ++i) {
        sum += array[i];
      }
    } else {
      ActionArraySum left = new ActionArraySum(l, (l + h) / 2, array);
      ActionArraySum right = new ActionArraySum((l + h) / 2, h, array);
      left.fork();
      right.compute();
      left.join();
      sum = left.sum + right.sum;
    }
  }
}
