package parallel.concurrent.distributed.sequential;

import parallel.concurrent.distributed.utils.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequentialStudentAnalytics {

  public double averageAgeOfEnrolledStudents(final Student[] studentArray) {
    List<Student> activeStudents = new ArrayList<Student>();

    for (Student s : studentArray) {
      if (s.checkIsCurrent()) {
        activeStudents.add(s);
      }
    }

    double ageSum = 0.0;
    for (Student s : activeStudents) {
      ageSum += s.getAge();
    }

    return ageSum / (double) activeStudents.size();
  }

  public String mostCommonFirstNameOfInactiveStudents(final Student[] studentArray) {
    List<Student> inactiveStudents = new ArrayList<Student>();

    for (Student s : studentArray) {
      if (!s.checkIsCurrent()) {
        inactiveStudents.add(s);
      }
    }

    Map<String, Integer> nameCounts = new HashMap<String, Integer>();

    for (Student s : inactiveStudents) {
      if (nameCounts.containsKey(s.getFirstName())) {
        nameCounts.put(s.getFirstName(),
            new Integer(nameCounts.get(s.getFirstName()) + 1));
      } else {
        nameCounts.put(s.getFirstName(), 1);
      }
    }

    String mostCommon = null;
    int mostCommonCount = -1;
    for (Map.Entry<String, Integer> entry : nameCounts.entrySet()) {
      if (mostCommon == null || entry.getValue() > mostCommonCount) {
        mostCommon = entry.getKey();
        mostCommonCount = entry.getValue();
      }
    }

    return mostCommon;
  }

  public int countNumberOfFailedStudentsOlderThan20(
      final Student[] studentArray) {
    int count = 0;
    for (Student s : studentArray) {
      if (!s.checkIsCurrent() && s.getAge() > 20 && s.getGrade() < 65) {
        count++;
      }
    }
    return count;
  }

}
