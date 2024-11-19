package Assigments;

/*
 * assignment-3 question-2 software specifications
 * author - vishal kalwani GET Oct 2024
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/* Student class maintains the grade of a perticular student */
class Student {
    private int grade;

    Student(int grade) {
        this.grade = grade;
    }

    int getGrade() {
        return grade;
    }
};

/*
 * MarkSheet class maintains the record of n students and provides features like
 * - min, max, avg, %age
 */
class MarkSheet {
    private ArrayList<Student> data = new ArrayList<Student>();
    private int maximumMarks;
    private float averageMarks;
    private int minimumMarks;
    private float percentage;
    private int studentSize;
    private int gradeSum;
    private int PassedStudents;

    MarkSheet() {
        this.maximumMarks = 0;
        this.minimumMarks = Integer.MAX_VALUE;
        this.averageMarks = 0;
        this.percentage = 0;
        this.studentSize = 0;
        this.gradeSum = 0;
        this.PassedStudents = 0;

    }

    /**
     * 
     * @param grade taking grade as an input
     * @return returns true if grade is valid else return false
     *         this functions validates if the grade is valid or not
     */

    boolean valid(int grade) {
        if (grade < 0 || grade > 100) {
            return false;
        }
        return true;
    }

    /**
     * This function add a grade of a student to the arraylist of
     * students and calculates all the other parameters of a marksheet like minimum
     * , maximum average, sum , percentage etc
     * 
     * @param grade takes grade of a student
     * 
     */
    void AddData(int grade) {
        Student temp = new Student(grade);
        data.add(temp);
        studentSize++;
        if (grade >= 40)
            PassedStudents++;

        maximumMarks = Math.max(maximumMarks, grade);
        minimumMarks = Math.min(grade, minimumMarks);
        gradeSum += grade;
        averageMarks = (float) gradeSum / (float) studentSize;
        percentage = ((float) PassedStudents / (float) studentSize) * 100;

    }

    /**
     * Finds the minimum grade from all grade
     * 
     * @return returns minimum grade
     */
    int getMinimum() {
        return minimumMarks;
    }

    /**
     * Finds the maximum grade from all grades
     * 
     * @return returns maximum grade
     */
    int getMaximum() {
        return maximumMarks;
    }

    /**
     * Finds the average of all grades
     * 
     * @return returns average of all grades
     */
    float getAverage() {
        return averageMarks;
    }

    /**
     * Finds the percentage of students who passed the exam
     * 
     * @return returns percentage of students passed
     */
    float getPercentageOfPassedStudent() {
        return percentage;
    }

}

public class StudentManagement {

    public static int menuInput = 1; // Input to run the menu again and again

    /**
     * This functions validates that whether the input given by user is valid or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     * @return returns a valid integer value
     */

    public static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid integer input!!");
                scanner.next();
            }
        }
    }

    /**
     * This functions updates the static menuInput with the value given by user
     * menuInput defines whether we want to use the functionality again or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     */
    public static void updateMenuInput(Scanner scanner) {
        while (true) {
            menuInput = getValidIntegerInput(scanner);
            if (menuInput == 1 || menuInput == 0)
                break;
            else {
                System.out.println("please Enter a valid Input (0 or 1)!!");
            }
        }
    }

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        /* Taking the number of students from user */
        System.out.print("Enter the number of students:");
        int studentCount = getValidIntegerInput(scanner);

        MarkSheet markSheet = new MarkSheet();

        for (int i = 0; i < studentCount; i++) {
            System.out.print("Enter Grade of student " + (i + 1) + ": ");
            int studentGrade = getValidIntegerInput(scanner);
            while (!markSheet.valid(studentGrade)) {
                System.out.println("Enter a valid grade (grade >= 0 && grade < 100) : ");
                studentGrade = scanner.nextInt();
            }
            markSheet.AddData(studentGrade);

        }

        while (menuInput == 1) {
            System.out.println("-----Student management system---------");
            System.out.println("1 print the maximum grade");
            System.out.println("2 print the minimum grade");
            System.out.println("3 print the average grade");
            System.out.println("4 Percentage of students passed");
            System.out.println("Enter your choice");
            int choice = getValidIntegerInput(scanner);
            switch ((choice)) {
                case 1:
                    System.out.println(markSheet.getMaximum());
                    break;
                case 2:
                    System.out.println(markSheet.getMinimum());
                    break;
                case 3:
                    System.out.println(markSheet.getAverage());
                    break;
                case 4:
                    System.out.println(markSheet.getPercentageOfPassedStudent());
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("press 1 if you want to use again else press 0");
            updateMenuInput(scanner);
        }

        scanner.close();
    }
}
