
/**
 * @author Vishal kalwani
 * @version 1.0
 * @since 24-10-2024
 */
package Main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class ArrayOperations, contains functions to operate on an array and
 * produce outputs:
 * 1. maxMirror() - Finds the length of the longest subarray whose mirror image
 * is also present.
 * 2. fixXY() - Fixes the positions of X and Y in the array and returns the
 * modified array.
 * 3. countClumps() - Finds the number of elements that are present in
 * consecutive order (2 or more together).
 * 4. splitArray() - Finds the pivot point in an array where the sum of the left
 * part equals the sum of the right part.
 */

public class ArrayOperations {
    static int maxvalue = 0;

    public static int menuInput = 1;

    /**
     * This functions validates that whether the input given by user is valid or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     * @return returns a valid integer value
     */
    private static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
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
     * This function find the maximum length of common subarray
     * 
     * @param dp     takes the 2D array for dynamic programming approach
     * @param inputA takes original array
     * @param inputB takes reversed array
     * @return returns the length of maximum common subarray
     */

    private int helper(int[][] dp, int[] inputA, int[] inputB) {
        int MaxSubarrayLength = 0;
        int arraysize = inputA.length;

        for (int indexI = 1; indexI <= arraysize; indexI++) {
            for (int indexJ = 1; indexJ <= arraysize; indexJ++) {
                if (inputA[indexI - 1] == inputB[indexJ - 1]) {
                    dp[indexI][indexJ] = 1 + dp[indexI - 1][indexJ - 1];
                } else {
                    dp[indexI][indexJ] = 0;
                }
                MaxSubarrayLength = Math.max(MaxSubarrayLength, dp[indexI][indexJ]);
            }
        }
        return MaxSubarrayLength;
    }

    /**
     * This function finds the length of the longest sub array whose mirror is also
     * present
     * 
     * @param inputArray takes array of integer in input
     * @return returns length of maximum mirror
     * @throws AssertionError if array's length is zero
     */
    public int maxMirror(int[] inputArray) throws AssertionError {
        if (inputArray.length == 0) {
            throw new AssertionError("Input array can't be empty");
        }
        int inputArraysize = inputArray.length;

        int[] reversedArray = new int[inputArraysize];
        int jIterator = inputArraysize;

        // revesing the original array and storing in reversedArray
        for (int i = 0; i < inputArraysize; i++) {
            reversedArray[jIterator - 1] = inputArray[i];
            jIterator = jIterator - 1;
        }

        // creating a 2D array dp for applying dynamic programming
        int[][] dp = new int[inputArraysize + 1][inputArraysize + 1];
        int MaxMirrorLength = helper(dp, inputArray, reversedArray);
        return MaxMirrorLength;
    }

    /**
     * This functions places all the occurance of Y after X
     * 
     * @param X     takes the value X
     * @param Y     takes the value Y
     * @param array takes the input array to be modiefied
     * @return returns the modified array
     * @throws AssertionError
     */

    public int[] fixXY(int X, int Y, int[] array) throws AssertionError {

        int inputSize = array.length;
        int[] modiefiedArray = new int[inputSize];

        for (int i = 0; i < inputSize; i++) {
            modiefiedArray[i] = array[i];
        }

        // Throws error if the input array is empty
        if (inputSize == 0) {
            throw new AssertionError("Input array can't be empty");
        }

        // Throws error if the last element of the input array is X
        if (modiefiedArray[inputSize - 1] == X) {
            throw new AssertionError("Array can't be fixed because last element is X itself");
        }
        int countX = 0;
        int countY = 0;
        for (int i = 0; i < inputSize; i++) {
            if (modiefiedArray[i] == X) {
                countX++;
            }
            if (modiefiedArray[i] == Y) {
                countY++;
            }

            // Throws error if there are two consecutive X's
            if (i < inputSize - 1 && ((modiefiedArray[i] == X) && (modiefiedArray[i + 1] == X))) {
                throw new AssertionError("Consicutive X");
            }
        }

        // Throws error if count of X and y are different
        if (countX != countY) {
            throw new AssertionError("Array is not fixable");
        }

        int Xindex = 0;
        int Yindex = 0;

        while (Yindex < inputSize) {
            while (Xindex < inputSize && modiefiedArray[Xindex] != X) {
                Xindex++;
            }
            while (Yindex < inputSize && modiefiedArray[Yindex] != Y) {
                Yindex++;
            }

            if (!(Xindex < inputSize - 1 && Yindex < inputSize)) {
                break;
            }
            int swapElement = modiefiedArray[Xindex + 1];
            modiefiedArray[Xindex + 1] = modiefiedArray[Yindex];
            modiefiedArray[Yindex] = swapElement;
            Yindex = Xindex + 2;
            Xindex++;

        }

        return modiefiedArray;

    }

    /**
     * This function finds the number of clumps in the array
     * Clump in an array is a series of 2 or more adjacent elements of the same
     * value.
     * 
     * @param array takes integer array as an input
     * @return return the number of clumps
     * @throws AssertionError
     */
    public int countClumps(int[] array) {
        if (array.length == 0) {
            throw new AssertionError("Input array can't be empty");
        }

        int clumpCount = 0;
        boolean inClump = false;

        for (int i = 0; i < array.length; i++) {
            // Check if the current element is the same as the next one
            if (i < array.length - 1 && array[i] == array[i + 1]) {
                // We are in a clump
                if (!inClump) {
                    clumpCount++;
                    inClump = true; // Mark that we are in a clump
                }
            } else {
                // We are not in a clump anymore
                inClump = false;
            }
        }

        return clumpCount;
    }

    /**
     * This Function finds the index where sum to the left array of pivot is equal
     * to sum of right array of that index
     * 
     * @param array takes integer array as an input
     * @return return the pivot point
     * @throws AssertionError
     */

    public int splitArray(int[] array) throws AssertionError {

        // Throws error if the input array is empty
        if (array.length == 0) {
            throw new AssertionError("Input Error can't be empty");
        }
        int[] prefixSumArray = new int[array.length]; // stores the Sum of array upto that index
        int totalSum = array[0];
        prefixSumArray[0] = array[0];

        for (int i = 1; i < array.length; i++) {
            prefixSumArray[i] = array[i] + prefixSumArray[i - 1];
            totalSum += array[i];
        }

        int splitPoint = -1;
        for (int i = 0; i < array.length; i++) {
            if (totalSum == 2 * prefixSumArray[i]) {
                splitPoint = i;
            }
        }
        return (splitPoint != -1 ? splitPoint + 1 : splitPoint);

    }

    public static void main(String[] args) {

        ArrayOperations arrayOperations = new ArrayOperations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements : ");
        int inputArraySize = getValidIntegerInput(scanner);
        int[] inputArray = new int[inputArraySize];

        for (int i = 0; i < inputArraySize; i++) {
            System.out.print("Enter element " + i + " : ");
            inputArray[i] = getValidIntegerInput(scanner);

        }

        while (menuInput == 1) {
            System.out.println("----array operations--------");
            System.out.println("1 Mirror subArray");
            System.out.println("2 fixXY");
            System.out.println("3 count the clumps");
            System.out.println("4 split Array");
            System.out.println("Enter your choice");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Length of maximum mirror : " + arrayOperations.maxMirror(inputArray));

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter X : ");
                        int x = getValidIntegerInput(scanner);
                        System.out.println("Enter Y : ");
                        int y = getValidIntegerInput(scanner);
                        int[] modiefiedArray = arrayOperations.fixXY(x, y, inputArray);
                        for (int i : modiefiedArray) {
                            System.out.print(i + " ");
                        }
                        System.out.println();
                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    try {
                        System.out.println("Number of clumps : " + arrayOperations.countClumps(inputArray));

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:
                    try {
                        System.out.println("Split Point : " + arrayOperations.splitArray(inputArray));
                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid choice!!");
                    break;
            }

            System.out.println("press 1 to use again or press 0 to exit : ");
            updateMenuInput(scanner);

        }

    }
}
