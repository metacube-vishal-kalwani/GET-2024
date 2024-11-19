package Assigments;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MaxtrixOperations {

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

    public static Matrix getMatrixInput(Scanner scanner) {
        System.out.print("Enter order of matrix (row column) : ");
        int maxRows = -1;
        int maxCols = -1;

        while (maxCols <= 0 || maxRows <= 0) {
            try {
                maxRows = getValidIntegerInput(scanner);
                maxCols = getValidIntegerInput(scanner);
                if (maxRows < 0 || maxCols < 0) {
                    throw new AssertionError("Order can't be negetive and zero! Enter positive order : ");
                }
                break;
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

        Matrix matrix = new Matrix(maxRows, maxCols);
        System.out.println("Enter Matrix elements");
        int element;
        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxCols; column++) {
                element = getValidIntegerInput(scanner);
                matrix.addValues(row, column, element);
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Matrix matrix = getMatrixInput(scanner);

        while (menuInput == 1) {
            System.out.println("----Matrix operations-------");
            System.out.println("1 transpose"
                    + "\n2 is symmetric"
                    + "\n3 addition"
                    + "\n4 multiplication"
                    + "\n5 Enter your choice : ");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    try {
                        Matrix transposeMatrix = matrix.transpose();
                        transposeMatrix.printMatrix();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println(matrix.isSymetric() ? "Yes" : "No");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                    break;
                case 3:
                    try {
                        Matrix inputMatrix = getMatrixInput(scanner);
                        Matrix additionMatrix = matrix.addition(inputMatrix);
                        additionMatrix.printMatrix();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                    break;

                case 4:
                    try {
                        Matrix inputMatrix = getMatrixInput(scanner);
                        Matrix multiplicationMatrix = matrix.multiplication(inputMatrix);
                        multiplicationMatrix.printMatrix();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:

                    System.out.println("Invalid Choice!!!");
                    break;
            }

            System.out.println("Press 1 to use operations again or press 0 to exit!!!");
            updateMenuInput(scanner);

        }
    }

}
