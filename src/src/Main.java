import sequential.SequentialArraySum;
import parallel.ParallelArraySum;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        long startTime;
        long finishTime;
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        double arr[] = LongStream.range(-98765432, 23456789)
                .mapToDouble(i -> i)
                .toArray();

        startTime = System.nanoTime();
        SequentialArraySum sSum = new SequentialArraySum(arr);
        sSum.computeSum();
        finishTime = System.nanoTime();
        System.out.println("Sequential Sum took: " + (finishTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        ParallelArraySum pSum = new ParallelArraySum(0, arr.length, arr);
        pSum.computeSum();
        finishTime = System.nanoTime();
        System.out.println("Parallel Sum took: " + (finishTime - startTime) + " nanoseconds.");

    }
}