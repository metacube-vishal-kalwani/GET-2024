package Assigments;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * IntSetOperation
 */
public class IntSetOperation {

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

    private static IntSet getSetInput(Scanner scanner) {
        IntSet set = new IntSet();
        int setSize;
        System.out.print("Enter size of set : ");
        setSize = getValidIntegerInput(scanner);
        int setElement;
        for (int i = 0; i < setSize; i++) {
            while (true) {
                try {
                    System.out.print("Enter element " + i + " : ");
                    setElement = getValidIntegerInput(scanner);
                    set.addToSet(setElement);
                    break;
                } catch (AssertionError e) {
                    System.out.println(e.getMessage());
                    // scanner.next();
                }
            }
        }
        return set;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntSet intSet = getSetInput(scanner);

        while (menuInput == 1) {
            System.out.println("-----Int Set Operations------");
            System.out.println("1 isMember");
            System.out.println("2 isSubSet");
            System.out.println("3 getComplement");
            System.out.println("4 union");
            System.out.println("Enter your choice");
            int choice = getValidIntegerInput(scanner);

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter Input to check in Set : ");
                        int userInput = getValidIntegerInput(scanner);
                        System.out.println(intSet.isMember(userInput) ? "Yes" : "NO");
                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        IntSet subSetInput = getSetInput(scanner);
                        System.out.println(intSet.isSubSet(subSetInput) ? "Yes" : "No");

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        IntSet complementSet = intSet.getComplement();
                        complementSet.printAll();

                    } catch (AssertionError e) {
                        System.out.println(e.getMessage());

                    }
                    break;

                case 4:
                    try {
                        IntSet inputSet = getSetInput(scanner);
                        IntSet unionSet = intSet.union(inputSet);
                        unionSet.printAll();
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