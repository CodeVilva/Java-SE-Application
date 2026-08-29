package marksheet;

import java.util.Scanner;

public class MarkSheet {

    public static void main(String[] args) {

        String name, rollno, course;
        int sem;

        Scanner scanner = new Scanner(System.in);
        Services service = new Services(scanner);

        while (true) {

            System.out.println("========================================");
            System.out.println("             STUDENT MARKSHEET          ");
            System.out.println("========================================");
            System.out.println("  1. Generate Marksheet                 ");
            System.out.println("  2. Exit                               ");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.println("========================================");
                    System.out.println("            Marksheet Generator         ");
                    System.out.println("========================================");
                    System.out.println("           Enter Student Details        ");
                    System.out.println("========================================");

                    System.out.print("Enter Student Name: ");
                    name = scanner.nextLine();

                    System.out.print("Enter Roll Number: ");
                    rollno = scanner.nextLine();

                    System.out.print("Enter Course: ");
                    course = scanner.nextLine();

                    System.out.print("Enter Semester: ");
                    sem = Integer.parseInt(scanner.nextLine());

                    int[] marks = service.getMarks();

                    boolean isStudentValid =
                            service.validateStudent(name, rollno, course, sem);

                    boolean isMarkValid =
                            service.validateMarks(marks);

                    if (isStudentValid && isMarkValid) {

                        service.printDetails(
                                name,
                                rollno,
                                course,
                                sem,
                                marks
                        );

                        service.calculateResult(marks);

                    } else {

                        System.out.println("========================================");
                        System.out.println("Error: Entered Details are Invalid!");
                        System.out.println("========================================");
                    }

                    break;

                case "2":

                    System.out.println("========================================");
                    System.out.println("Message: You have exited!");
                    System.out.println("========================================");

                    scanner.close();
                    return;

                default:

                    System.out.println("========================================");
                    System.out.println("Message: Invalid choice!");
                    System.out.println("========================================");
            }
        }
    }
}