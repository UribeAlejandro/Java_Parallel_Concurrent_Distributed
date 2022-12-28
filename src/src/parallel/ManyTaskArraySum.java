package parallel;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ManyTaskArraySum extends RecursiveTask<Double> {
    private final int startIndexInclusive;
    private final int endIndexExclusive;
    private final double[] input;
    private double value;

    public ManyTaskArraySum(final int setStartIndexInclusive, final int setEndIndexExclusive, final double[] setInput) {
        this.startIndexInclusive = setStartIndexInclusive;
        this.endIndexExclusive = setEndIndexExclusive;
        this.input = setInput;
    }

    private static int getChunkSize(final int nChunks, final int nElements) {
        return (nElements + nChunks - 1) / nChunks;
    }

    private static int getChunkStartInclusive(final int chunk, final int nChunks, final int nElements) {
        final int chunkSize = getChunkSize(nChunks, nElements);
        return chunk * chunkSize;
    }

    private static int getChunkEndExclusive(final int chunk, final int nChunks,
            final int nElements) {
        final int chunkSize = getChunkSize(nChunks, nElements);
        final int end = (chunk + 1) * chunkSize;
        if (end > nElements) {
            return nElements;
        } else {
            return end;
        }
    }

    @Override
    protected Double compute() {
        if (endIndexExclusive - startIndexInclusive <= 1000) {
            for(int i=startIndexInclusive; i < endIndexExclusive; i++){
                value += input[i];
            }
        } else {
            int mid = (startIndexInclusive + endIndexExclusive) / 2;

            ManyTaskArraySum left = new ManyTaskArraySum(startIndexInclusive, mid, input);
            ManyTaskArraySum right = new ManyTaskArraySum(mid, endIndexExclusive, input);

            left.fork();
            right.fork();

            value = left.join() + right.join();
        }
        return value;
    }

    public static double parallelManyTaskParallelArraySum(double[] input, int numTasks) {
        double sum = 0;
        ForkJoinPool pool = new ForkJoinPool(numTasks);
        ArrayList<ManyTaskArraySum> ArraySumTaskList = new ArrayList<>();

        for(int i=0; i<numTasks; i++){
            int start = getChunkStartInclusive(i, numTasks,input.length);
            int end = getChunkEndExclusive(i, numTasks,input.length);
            ArraySumTaskList.add(new ManyTaskArraySum(start, end, input));
        }
        ForkJoinTask.invokeAll(ArraySumTaskList);
        for(ManyTaskArraySum rast : ArraySumTaskList){
            sum += pool.invoke(rast);
        }
        return sum;
    }
}
