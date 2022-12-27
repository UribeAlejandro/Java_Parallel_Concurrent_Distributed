import sequential.ArraySum;

public class Main {
    public static void main(String[] args) {
        double[] arr = new double[]{1, 2, 3, 4, 5};
        ArraySum aSum = new ArraySum(arr);
        System.out.println(aSum.computeSum());
    }
}