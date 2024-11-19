/**
 * Goal - software construction and fundamental
 * Assignment 5 : Recursion
 * @author Vishal kalwani
 * @version 1.0
 * 
 */

package Recursion;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This class Recursion contains some recurive methods
 * HCF() : finds highest common factor of two numbers
 * LCM() : finds longest common mulltiple of two numbers
 * nQueen() : takes a board (2D array) and modifies it to place all the queens
 * without conflicts
 * 
 */
public class Recursion {

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

    /**
     * Finds the HCF of two integers
     * 
     * @param inputA type : integer
     * @param inputB type : integer
     * @return returns HCF of A and B
     * 
     */
    public int HCF(int inputA, int inputB) {
        // Throwing error if elements are less than or equal to zero
        if (inputA <= 0 || inputB <= 0) {
            throw new AssertionError("Invalid input(inputs should be greater than 0)");
        }
        // Making sure that inputB is always less than inputA
        if (inputA > inputB)
            return HCF(inputB, inputA);

        if (inputA == inputB)
            return inputA;

        if (inputA == 1 || inputB == 1)
            return 1;

        return HCF(inputA, inputB - inputA);
    }

    private int helper(int inputA, int inputB, int largest) {
        if (largest % inputA == 0 && largest % inputB == 0) {
            return largest;
        } else {
            largest++;
            return helper(inputA, inputB, largest);
        }
    }

    /**
     * Finds the LCM of two integers
     * 
     * @param inputA type : integer
     * @param inputB type : integer
     * @return returns LCM of A and B
     * 
     */
    public int LCM(int inputA, int inputB) {

        // Throwing error if elements are less than or equal to zero
        if (inputA <= 0 || inputB <= 0) {
            throw new AssertionError("Invalid input(inputs should be greater than 0)");
        }
        return helper(inputA, inputB, Math.max(inputA, inputB));
    }

    /*
     * This function validates whether we can place a queen of a perticular row and
     * column
     */
    private boolean isvalidPosition(int[][] board, int row, int column, int n) {

        int currentRow = row;
        int currentColumn = column;

        // checking the left diagonal places
        while (currentRow >= 0 && currentColumn >= 0) {
            if (board[currentRow][currentColumn] == 1) {
                return false;
            }
            currentRow--;
            currentColumn--;
        }

        // checking the right digonal places
        currentRow = row;
        currentColumn = column;
        while (currentRow >= 0 && currentColumn < n) {

            if (board[currentRow][currentColumn] == 1) {
                return false;
            }
            currentRow--;
            currentColumn++;
        }

        // checking the column places
        currentRow = row;
        currentColumn = column;
        while (currentRow >= 0) {
            if (board[currentRow][currentColumn] == 1) {
                return false;
            }
            currentRow--;
        }
        return true;
    }

    /**
     * This is the backtracking functions which checks all the possible position ans
     * tells whether queens are placable or not
     * 
     * @param board     takes the 2D array of size = boardSize
     * @param row       takes the current row number to place a queen
     * @param boardSize takes the size of board
     * @return returns true if queen is placable else returns false
     */
    private boolean solveNqueen(int[][] board, int row, int boardSize) {
        if (row == boardSize) {
            return true;
        }

        for (int column = 0; column < boardSize; column++) {
            if (isvalidPosition(board, row, column, boardSize)) {
                board[row][column] = 1;
                if (solveNqueen(board, row + 1, boardSize)) {
                    return true;
                } else {
                    board[row][column] = 0;
                }
            }
        }
        return false;

    }

    /**
     * nQueen functions tells whethe its possible to place n Queens on chess board
     * of size nxn and also modifies the check board
     * 
     * @param board takes a 2D array of size nxn initialized with 0
     * @return returns true if it's possible to place n queen on chess board
     */
    public boolean nQueen(int[][] board) {
        int boardSize = board.length;

        return solveNqueen(board, 0, boardSize);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Recursion recursion = new Recursion();
        Search search = new Search();

        while (menuInput == 1) {
            System.out.println("----Recursion-------");
            System.out.println("1  HCF");
            System.out.println("2  LCM");
            System.out.println("3  NQueen");
            System.out.println("4  Linear Search");
            System.out.println("5 Binary Search");
            System.out.print("Enter you choice : ");
            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter input 1 : ");
                        int input1 = getValidIntegerInput(scanner);
                        System.out.println("Enter input 2 : ");
                        int input2 = getValidIntegerInput(scanner);
                        System.out.println("HCF is " + recursion.HCF(input1, input2));

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Enter input 1 : ");
                        int input1 = getValidIntegerInput(scanner);
                        System.out.println("Enter input 2 : ");
                        int input2 = getValidIntegerInput(scanner);
                        System.out.println("HCF is " + recursion.LCM(input1, input2));

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter board Size : ");
                        int boardSize = getValidIntegerInput(scanner);
                        int[][] board = new int[boardSize][boardSize];
                        for (int i = 0; i < boardSize; i++) {
                            for (int j = 0; j < boardSize; j++) {
                                board[i][j] = 0;
                            }
                        }
                        boolean isPossibleToPlaceQueens = recursion.nQueen(board);
                        System.out.println(isPossibleToPlaceQueens ? "Possible" : "Not Possible");
                        if (isPossibleToPlaceQueens) {
                            for (int i = 0; i < boardSize; i++) {
                                for (int j = 0; j < boardSize; j++) {
                                    System.out.print(board[i][j] + " ");
                                }
                                System.out.println();
                            }
                        }

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter array size");
                        int arraySize = getValidIntegerInput(scanner);
                        int[] array = new int[arraySize];

                        for (int i = 0; i < arraySize; i++) {
                            System.out.print("Enter element " + (i + 1) + " : ");
                            array[i] = getValidIntegerInput(scanner);
                        }

                        System.out.print("Enter element you want to search : ");
                        int searchElement = getValidIntegerInput(scanner);

                        int elementIndex = search.linearSearch(array, 0, searchElement);
                        if (elementIndex != -1) {
                            System.out.println("Element found at position : " + elementIndex + 1);
                        } else {
                            System.out.println("Element is not present in array");
                        }

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Enter array(sorted) size");
                        int arraySize = getValidIntegerInput(scanner);
                        int[] array = new int[arraySize];

                        for (int i = 0; i < arraySize; i++) {
                            System.out.print("Enter element " + (i + 1) + " : ");
                            array[i] = getValidIntegerInput(scanner);
                        }

                        System.out.println("Enter element you want to search : ");
                        int searchElement = getValidIntegerInput(scanner);

                        int elementIndex = search.binarySearch(array, searchElement);
                        if (elementIndex != -1) {
                            System.out.println("Element found at position : " + elementIndex + 1);
                        } else {
                            System.out.println("Element is not present in array");
                        }

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }

            System.out.println("Press 1 to you the functions again or press 0 to exit");
            updateMenuInput(scanner);
        }

    }

}
