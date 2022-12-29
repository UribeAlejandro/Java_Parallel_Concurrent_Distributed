package par_conc_dis.utils;

import java.util.Random;

public class Helpers {

  public static void printResults(long startTime, long finishTime, String task) {
    System.out.println(task + " took: " + (finishTime - startTime) / 1000000 + " milliseconds.");
  }

  public static Student[] generateStudentData() {
    final int REPEATS = 10;
    final int N_STUDENTS = 2000000;
    final int N_CURRENT_STUDENTS = 600000;
    final String[] firstNames = {"Sanjay", "Yunming", "John", "Vivek", "Shams", "Max"};
    final String[] lastNames = {"Chatterjee", "Zhang", "Smith", "Sarkar", "Imam", "Grossman"};

    Student[] students = new Student[N_STUDENTS];
    Random r = new Random(123);

    for (int s = 0; s < N_STUDENTS; s++) {
      final String firstName = firstNames[r.nextInt(firstNames.length)];
      final String lastName = lastNames[r.nextInt(lastNames.length)];
      final double age = r.nextDouble() * 100.0;
      final int grade = 1 + r.nextInt(100);
      final boolean current = (s < N_CURRENT_STUDENTS);

      students[s] = new Student(firstName, lastName, age, grade, current);
    }

    return students;
  }
}
