/*
 * Assignment 3 - question 1 : Software specifications
 * Author : vishal kalwani GET Oct 2024
 */

package Assigments;

import java.util.InputMismatchException;
import java.util.Scanner;

/* CustomString class contains features - 
 * 1 - compare two strings and tells if they are equal or not
 * 2 - reverses the string
 * 3 - toggles the case of string
 * 4 - returns the largest word from a string 
 */
class CustomString {

    private static final int ASCII_UPPERCASE_START = 65; // 'A'
    private static final int ASCII_UPPERCASE_END = 90; // 'Z'
    private static final int ASCII_LOWERCASE_START = 97; // 'a'
    private static final int ASCII_LOWERCASE_END = 122; // 'z'
    private static final int CASE_DIFFERENCE = 32;

    /**
     * function to check equality of strings
     * 
     * @param input1
     * @param input2
     * @return return false if two strings are not equal either returns true
     */
    public boolean compareStr(String input1, String input2) {
        if (input1.length() != input2.length()) {
            return false;
        }
        for (int i = 0; i < input1.length(); i++) {
            if (input1.charAt(i) != input2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * function to reverse a string
     * 
     * @param input takes the string st as an input
     * @return returns the reversed string
     */

    public String reverseStr(String input) {
        String reversedString = "";
        int inputSize = input.length();

        for (int i = inputSize - 1; i >= 0; i--) {
            reversedString += input.charAt(i);
        }
        return reversedString;
    }

    /**
     * Fuction to change all the upper case to lower case and all lower case to
     * upper case
     * 
     * @param input takes the input string
     * @return returns the toggled string
     * 
     */
    public String replaceCase(StringBuffer input) {
        StringBuffer newString = new StringBuffer();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (isUpperCase(currentChar)) {
                newString.append(toLowerCase(currentChar));
            } else if (isLowerCase(currentChar)) {
                newString.append(toUpperCase(currentChar));
            } else {
                newString.append(currentChar);
            }
        }
        return newString.toString();
    }

    private boolean isUpperCase(char c) {
        return c >= ASCII_UPPERCASE_START && c <= ASCII_UPPERCASE_END;
    }

    private boolean isLowerCase(char c) {
        return c >= ASCII_LOWERCASE_START && c <= ASCII_LOWERCASE_END;
    }

    private char toLowerCase(char c) {
        return (char) (c + CASE_DIFFERENCE);
    }

    private char toUpperCase(char c) {
        return (char) (c - CASE_DIFFERENCE);
    }

    /**
     * Function to find the largest word in a string
     * 
     * @param input
     * @return returns the word of largest length
     * 
     */
    String largestWord(String input) {

        StringBuffer longestWord = new StringBuffer(""); // stores the longest word
        int longestWordLength = 0; // stores the length of longest word
        StringBuffer currentWord = new StringBuffer(""); // stores the current word

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                currentWord.append(input.charAt(i));
            } else {
                if (currentWord.length() >= longestWordLength) {
                    longestWordLength = currentWord.length();
                    longestWord.delete(0, longestWord.length());
                    longestWord.append(currentWord);
                }
                currentWord.delete(0, currentWord.length());

            }
        }

        if (currentWord.length() >= longestWordLength) {
            longestWordLength = currentWord.length();
            longestWord.delete(0, longestWord.length());
            longestWord.append(currentWord);
        }

        return longestWord.toString();

    }
};

public class StringOperations {

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
        CustomString customString = new CustomString();
        String inputString;
        while (menuInput == 1) {
            System.out.println("----------String manipulations --------");

            System.out.println("1 Reverse");
            System.out.println("2 Find largest word");
            System.out.println("3 Toggle Case ");
            System.out.println("4 Check equal strings ");

            System.out.println("Enter your choice");
            int choice = getValidIntegerInput(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter a String : ");
                    inputString = scanner.nextLine();
                    System.out.println(customString.reverseStr(inputString));
                    break;

                case 2:
                    System.out.println("Enter the line or paragragh (don't press enter)");
                    inputString = scanner.nextLine();
                    System.out.println(customString.largestWord(inputString));
                    break;

                case 3:
                    System.out.println("Enter a String : ");
                    StringBuffer inputStr2 = new StringBuffer(scanner.nextLine());
                    System.out.println(customString.replaceCase(inputStr2));
                    break;

                case 4:
                    System.out.println("Enter String 1 : ");
                    String string1 = scanner.nextLine();
                    System.out.println("Enter String 2 : ");
                    String string2 = scanner.nextLine();
                    System.out.println(customString.compareStr(string1, string2) ? "yes" : "no");
                    break;

                default:
                    System.out.println("Enter a valid choice");
                    break;
            }

            System.out.println("press 1 to use the functions again or press 0 to exit");

            updateMenuInput(scanner);

        }
        scanner.close();
    }

}
