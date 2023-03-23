package parallel.concurrent.distributed;

import parallel.concurrent.distributed.sequential.SequentialStudentAnalytics;
import parallel.concurrent.distributed.utils.Helpers;
import parallel.concurrent.distributed.utils.Student;
import parallel.concurrent.distributed.functional.FunctionalStudentAnalytics;
import parallel.concurrent.distributed.parallel.ManyTaskArraySum;
import parallel.concurrent.distributed.sequential.ArraySum;
import parallel.concurrent.distributed.parallel.ActionArraySum;
import parallel.concurrent.distributed.parallel.TaskArraySum;

import java.util.stream.LongStream;

import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {
    long startTime;
    long finishTime;
    double real;
    double ans;
    int numTasks = 8;

    double[] arr = LongStream.range(-98765432, 23456789).mapToDouble(i -> i).toArray();

    ForkJoinPool pool = new ForkJoinPool(numTasks);

    startTime = System.nanoTime();
    ArraySum sSum = new ArraySum(arr);
    real = sSum.computeSum();
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Sequential Sum");

    startTime = System.nanoTime();
    ActionArraySum apSum = new ActionArraySum(0, arr.length, arr);
    pool.invoke(apSum);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Action - Parallel Sum");

    startTime = System.nanoTime();
    TaskArraySum tpSum = new TaskArraySum(0, arr.length, arr);
    ans = pool.invoke(tpSum);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Task - Parallel Sum");

    assert ans == real;

    startTime = System.nanoTime();
    ans = ManyTaskArraySum.parallelManyTaskParallelArraySum(arr, numTasks);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Task - Parallel Sum " + numTasks + " cores");

    assert ans == real;

    System.out.println("*****************************");

    // ***************************** //
    // Functional Parallel Programming
    String s_real;
    String s_ans;
    int i_real;
    int i_ans;
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
        String.valueOf(numTasks));
    Student[] students = Helpers.generateStudentData();

    FunctionalStudentAnalytics funcAnalytics = new FunctionalStudentAnalytics();
    SequentialStudentAnalytics seqAnalytics = new SequentialStudentAnalytics();

    startTime = System.nanoTime();
    real = seqAnalytics.averageAgeOfEnrolledStudents(students);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Sequential AvgAge");

    startTime = System.nanoTime();
    ans = funcAnalytics.averageAgeOfEnrolledStudents(students);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Functional AvgAge");

    assert real == ans;

    startTime = System.nanoTime();
    s_real = seqAnalytics.mostCommonFirstNameOfInactiveStudents(students);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Sequential MostCommonFirstName");

    startTime = System.nanoTime();
    s_ans = funcAnalytics.mostCommonFirstNameOfInactiveStudents(students);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Functional MostCommonFirstName");

    assert s_real == s_ans;

    startTime = System.nanoTime();
    i_real = seqAnalytics.countNumberOfFailedStudentsOlderThan20(students);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Sequential count");

    startTime = System.nanoTime();
    i_ans = funcAnalytics.countNumberOfFailedStudentsOlderThan20(students);
    finishTime = System.nanoTime();
    Helpers.printResults(startTime, finishTime, "Functional count");

    assert i_real == i_ans;

  }
}
