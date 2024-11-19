package Assigments;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PolynomialOperations {

    private static int menuInput = 1;

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

    private static Polynomial getPolyInput(Scanner scanner) {
        Polynomial polynomial = new Polynomial();
        int polySize;
        System.out.print("Enter the number of cofficients you want to enter :  ");
        polySize = getValidIntegerInput(scanner);
        int degree;
        int cofficient;
        for (int i = 0; i < polySize; i++) {
            while (true) {
                try {
                    System.out.print("Enter the coffecient and degree respectively : ");
                    cofficient = getValidIntegerInput(scanner);
                    degree = getValidIntegerInput(scanner);
                    polynomial.addValues(degree, cofficient);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    // scanner.next();
                }
            }
        }
        return polynomial;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Polynomial polynomial = getPolyInput(scanner);

        polynomial.printPoly();

        while (menuInput == 1) {
            System.out.println("-----Int Set Operations------");
            System.out.println("1 Evaluate");
            System.out.println("2 Degree");
            System.out.println("3 Addition ");
            System.out.println("4 Multiplication");
            System.out.println("Enter your choice");
            int choice = getValidIntegerInput(scanner);

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter the value to evaluate expression : ");
                        int userInput = getValidIntegerInput(scanner);
                        System.out.println(polynomial.evaluate(userInput));
                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Degree : " + polynomial.getDegree());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        Polynomial userPoly = getPolyInput(scanner);
                        Polynomial newPoly = polynomial.addPoly(polynomial, userPoly);
                        newPoly.printPoly();

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        Polynomial userPoly = getPolyInput(scanner);
                        Polynomial newPoly = polynomial.multiplyPoly(polynomial, userPoly);
                        newPoly.printPoly();
                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid choice !!!");
                    break;
            }
            System.out.println("Press 1 to use Operations again or press 0 to exit");
            updateMenuInput(scanner);
        }

    }

}
