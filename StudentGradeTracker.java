import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        int rollNo;
        ArrayList<Double> marks;

        Student(String name, int rollNo) {
            this.name = name;
            this.rollNo = rollNo;
            this.marks = new ArrayList<Double>();
        }

        double getAverage() {
            if (marks.isEmpty())
                return 0;

            double sum = 0;
            for (double m : marks)
                sum += m;

            return sum / marks.size();
        }

        double getHighest() {
            double max = marks.get(0);
            for (double m : marks)
                if (m > max)
                    max = m;
            return max;
        }

        double getLowest() {
            double min = marks.get(0);
            for (double m : marks)
                if (m < min)
                    min = m;
            return min;
        }

        String getGrade() {
            double avg = getAverage();

            if (avg >= 90)
                return "A+";
            else if (avg >= 80)
                return "A";
            else if (avg >= 70)
                return "B";
            else if (avg >= 60)
                return "C";
            else if (avg >= 50)
                return "D";
            else
                return "F";
        }
    }

    static ArrayList<Student> students = new ArrayList<Student>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            printMenu();
            choice = readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewSummaryReport();
                    break;

                case 3:
                    viewClassStatistics();
                    break;

                case 4:
                    searchStudent();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    static void printMenu() {
        System.out.println("\n===== STUDENT GRADE TRACKER =====");
        System.out.println("1. Add Student & Marks");
        System.out.println("2. View Summary Report");
        System.out.println("3. View Class Statistics");
        System.out.println("4. Search Student");
        System.out.println("5. Exit");
    }

    static void addStudent() {

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        int rollNo = readInt("Enter roll number: ");

        Student s = new Student(name, rollNo);

        int numSubjects = readInt("Enter number of subjects: ");

        for (int i = 1; i <= numSubjects; i++) {
            double mark = readDouble("Enter marks for subject " + i + ": ");
            s.marks.add(mark);
        }

        students.add(s);

        System.out.println("[SUCCESS] Student added successfully!");

        System.out.printf(
                "Average: %.2f | Highest: %.2f | Lowest: %.2f | Grade: %s%n",
                s.getAverage(),
                s.getHighest(),
                s.getLowest(),
                s.getGrade());
    }

    static void viewSummaryReport() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n-------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-10s %-10s %-6s%n",
                "Roll", "Name", "Average", "Highest", "Lowest", "Grade");
        System.out.println("-------------------------------------------------------------");

        for (Student s : students) {
            System.out.printf("%-5d %-15s %-10.2f %-10.2f %-10.2f %-6s%n",
                    s.rollNo,
                    s.name,
                    s.getAverage(),
                    s.getHighest(),
                    s.getLowest(),
                    s.getGrade());
        }

        System.out.println("-------------------------------------------------------------");
    }

    static void viewClassStatistics() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        double classTotal = 0;

        Student topStudent = students.get(0);
        Student lowStudent = students.get(0);

        for (Student s : students) {

            classTotal += s.getAverage();

            if (s.getAverage() > topStudent.getAverage())
                topStudent = s;

            if (s.getAverage() < lowStudent.getAverage())
                lowStudent = s;
        }

        double classAverage = classTotal / students.size();

        System.out.println("\n===== CLASS STATISTICS =====");
        System.out.printf("Total Students   : %d%n", students.size());
        System.out.printf("Class Average    : %.2f%n", classAverage);
        System.out.printf("Top Performer    : %s (%.2f)%n",
                topStudent.name,
                topStudent.getAverage());

        System.out.printf("Lowest Performer : %s (%.2f)%n",
                lowStudent.name,
                lowStudent.getAverage());
    }

    static void searchStudent() {

        int rollNo = readInt("Enter roll number to search: ");

        for (Student s : students) {

            if (s.rollNo == rollNo) {

                System.out.println("\n--- Student Found ---");
                System.out.println("Name    : " + s.name);
                System.out.println("Roll No : " + s.rollNo);
                System.out.println("Marks   : " + s.marks);

                System.out.printf("Average : %.2f%n", s.getAverage());
                System.out.printf("Highest : %.2f%n", s.getHighest());
                System.out.printf("Lowest  : %.2f%n", s.getLowest());
                System.out.println("Grade   : " + s.getGrade());

                return;
            }
        }

        System.out.println("[ERROR] Student with roll number " + rollNo + " not found.");
    }

    static int readInt(String prompt) {

        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    static double readDouble(String prompt) {

        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}