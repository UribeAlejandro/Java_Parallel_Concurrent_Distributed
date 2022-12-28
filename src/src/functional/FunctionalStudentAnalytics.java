package functional;

import utils.Student;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FunctionalStudentAnalytics {

    public double averageAgeOfEnrolledStudents(final Student[] studentArray) {
        double avgAge = Stream.of(studentArray)
                .parallel()
                .filter(Student::checkIsCurrent)
                .mapToDouble(Student::getAge)
                .average()
                .getAsDouble();
        return avgAge;
    }

    public String mostCommonFirstNameOfInactiveStudents(final Student[] studentArray) {
        String mostCommonFirstName = Stream.of(studentArray)
                .parallel()
                .filter(s -> !s.checkIsCurrent())
                .collect(
                        Collectors.groupingBy(
                                Student::getFirstName,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        return mostCommonFirstName;
    }

    public int countNumberOfFailedStudentsOlderThan20(final Student[] studentArray) {
        int count = (int) Stream.of(studentArray)
                .parallel()
                .filter(
                        s -> !s.checkIsCurrent() && s.getAge() > 20 && s.getGrade() < 65
                )
                .count();
        return count;
    }
}
