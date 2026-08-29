package marksheet;

import java.util.Scanner;

public class Services {

    private Scanner scanner;

    public Services(Scanner scanner) {
        this.scanner = scanner;
    }

    public int[] getMarks() {

        System.out.println("========================================");
        System.out.println("            Enter Student Marks         ");
        System.out.println("========================================");

        int[] marks = new int[4];

        for (int i = 0; i < marks.length; i++) {

            System.out.print("Enter Mark " + (i + 1) + ": ");
            marks[i] = Integer.parseInt(scanner.nextLine());
        }

        return marks;
    }

    boolean validateStudent(
            String name,
            String rollno,
            String course,
            int sem) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        if (rollno == null || rollno.trim().isEmpty()) {
            return false;
        }

        if (course == null || course.trim().isEmpty()) {
            return false;
        }

        if (sem < 1 || sem > 6) {
            return false;
        }

        return true;
    }

    boolean validateMarks(int[] marks) {

        for (int i = 0; i < marks.length; i++) {

            if (marks[i] < 0 || marks[i] > 100) {
                return false;
            }
        }

        return true;
    }

    void printDetails(
            String name,
            String rollno,
            String course,
            int sem,
            int[] marks) {

        System.out.println("----------------------------------------");
        System.out.println("             Student Marksheet          ");
        System.out.println("----------------------------------------");

        System.out.println("              Student Details           ");
        System.out.println("----------------------------------------");

        System.out.println("Student Name: " + name);
        System.out.println("Student Roll No: " + rollno);
        System.out.println("Course Name: " + course);
        System.out.println("Semester: " + sem);

        System.out.println("----------------------------------------");
        System.out.println("              Marks Details             ");
        System.out.println("----------------------------------------");

        System.out.println("  Subject                | Marks        ");
        System.out.println("----------------------------------------");

        for (int i = 0; i < marks.length; i++) {

            System.out.println(
                    "Subject " + (i + 1)
                    + " Marks:           "
                    + marks[i]
            );
        }

        System.out.println("----------------------------------------");
    }

    void calculateResult(int[] marks) {

        int total = 0;
        double avg;

        String grade;
        String result = "PASS";

        for (int i = 0; i < marks.length; i++) {

            total += marks[i];

            if (marks[i] < 40) {
                result = "FAIL";
            }
        }

        avg = (double) total / marks.length;

        if ("PASS".equals(result)) {

            if (avg >= 90) {
                grade = "O";

            } else if (avg >= 80) {
                grade = "A";

            } else if (avg >= 70) {
                grade = "B";

            } else if (avg >= 60) {
                grade = "C";

            } else if (avg >= 50) {
                grade = "D";

            } else if (avg >= 40) {
                grade = "E";

            } else {
                grade = "NILL";
            }

        } else {

            grade = "NILL";
        }

        System.out.println("             Student Results            ");
        System.out.println("----------------------------------------");
        System.out.println(" Fields          | Values");
        System.out.println("----------------------------------------");

        System.out.println("Total:             " + total);
        System.out.println("Average:           " + avg);
        System.out.println("Grade:             " + grade);
        System.out.println("Result:            " + result);

        System.out.println("----------------------------------------");
    }
}