/*
 * assigment 2 - question 1 : Static checking and code review
 * Auther : vishal kalwani
 */

package Assigments;

import java.util.Scanner;
/* This class HexCalc contains calculative functions which takes hexadecimal numbers and performs operations */

class HexCalc {

    /**
     * This function validates the hex code
     * 
     * @param hexCode
     * @return returns true if hex code if correct else returns false
     */
    boolean checkHex(String hexCode) {

        for (char ch : hexCode.toCharArray()) {

            if (!(ch >= '0' && ch <= '9') ||
                    (ch >= 'a' && ch <= 'f') ||
                    (ch >= 'A' && ch <= 'F')) {
                return false;
            }
        }
        return true;
    }

    /**
     * This function converts a decimal number into hexa decimal number
     * 
     * @param num takes decimal number
     * @return return hexadecimal number in form of string
     */
    String DecimalToHex(int num) {
        StringBuffer ans = new StringBuffer();
        while (num > 0) {
            int rem = num % 16;
            num /= 16;
            if (rem < 10) {
                ans.append(rem);
            } else {
                ans.append((char) (rem + 55));
            }
        }
        return ans.reverse().toString();

    }

    /**
     * This function converts hexadecimal to decimal
     * 
     * @param input takes hexadecimal number in form of string
     * @return return decimal integer number
     */

    static Integer HexToDecimal(String input) {
        int decimal = 0;
        int base = 1;
        for (int i = input.length() - 1; i >= 0; i--) {
            char ch = input.charAt(i);
            if (ch >= '0' && ch <= '9') {
                decimal += (ch - 48) * base;
            } else if (ch >= 'A' && ch <= 'F') {
                decimal += (ch - 55) * base;

            }
            base *= 16;
        }
        return decimal;

    }

    /**
     * This function performs addition of two hexadecimal numbers
     * 
     * @param a hexadecimal string
     * @param b hexadecimal string
     * @return sum of inputs in form of string
     */
    String add(String a, String b) {

        int x = HexToDecimal(a);
        int y = HexToDecimal(b);
        int result = x + y;
        return DecimalToHex(result);

    }

    /**
     * This function performs subtraction of two hexadecimal numbers
     * 
     * @param a hexadecimal string
     * @param b hexadecimal string
     * @return subtraction of inputs in form of string
     */
    String subtract(String a, String b) {

        int x = HexToDecimal(a);
        int y = HexToDecimal(b);
        int result = (x - y > 0 ? x - y : y - x);

        return DecimalToHex(result);

    }

    /**
     * This function performs multiply of two hexadecimal numbers
     * 
     * @param a hexadecimal string
     * @param b hexadecimal string
     * @return multiply of inputs in form of string
     */
    String multiply(String a, String b) {

        int x = HexToDecimal(a);
        int y = HexToDecimal(b);
        int result = x * y;
        return DecimalToHex(result);

    }

    /**
     * This function performs division of two hexadecimal numbers
     * 
     * @param a hexadecimal string
     * @param b hexadecimal string
     * @return division of inputs in form of string
     */
    String divide(String a, String b) {

        int x = HexToDecimal(a);
        int y = HexToDecimal(b);
        if (y == 0)
            return "NULL";

        int result = (Integer) y / x;
        // return String.valueOf(result);
        return DecimalToHex(result);

    }

    /**
     * This function checks equality of two hexadecimal strings
     * 
     * @param a hexadecimal string
     * @param b hexadecimal string
     * @return return true if equal or return false if no equal
     */
    boolean Equal(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This function checks whether a > b
     * 
     * @param a hexadecimal string
     * @param b hexadecimal string
     * @return returns equal if greater either returns false
     */
    boolean greaterThan(String a, String b) {
        if (a.length() > b.length()) {
            return true;
        } else if (a.length() > b.length()) {
            return false;
        } else {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) < b.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * This function checks whether a < b
     * 
     * @param a hexadecimal string
     * @param b hexadecimal string
     * @return returns equal if lesser either returns false
     */
    boolean lessThan(String a, String b) {
        if (a.length() < b.length()) {
            return true;
        } else if (a.length() < b.length()) {
            return false;
        } else {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) > b.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

};

public class Second {

    public static void main(String[] args) {

        HexCalc hexCalc = new HexCalc();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first hexadecimal numbers : ");

        String a = sc.next();
        // System.out.println(hexCalc.checkHex(a));
        while (hexCalc.checkHex(a) == false) {
            System.out.println("Enter a valid hexadecimal number");
            a = sc.next();
        }
        System.out.println("Enter second hexadecimal numbers : ");

        String b = sc.next();
        while (hexCalc.checkHex(b) == false) {
            System.out.println("Enter a valid hexadecimal number");
            b = sc.next();
        }

        int input;
        do {
            System.out.println("1 Addition");
            System.out.println("2 Subtraction");
            System.out.println("3 Multiplication");
            System.out.println("4 Division");
            System.out.println("5 Equality");
            System.out.println("6 Greaterthan");
            System.out.println("7 Lesserthan");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Addition : " + hexCalc.add(a, b));
                    break;
                case 2:
                    System.out.println("Subtraction : " + hexCalc.subtract(a, b));
                    break;
                case 3:
                    System.out.println("Multiplication : " + hexCalc.multiply(a, b));
                    break;
                case 4:
                    System.out.println("Division : " + hexCalc.divide(a, b));
                    break;
                case 5:
                    System.out.println((hexCalc.Equal(a, b)) ? "Equal" : "Not Equal");
                    break;

                case 6:
                    System.out.println((hexCalc.greaterThan(a, b)) ? "greater " : "Not greater");
                    break;

                case 7:
                    System.out.println((hexCalc.lessThan(a, b)) ? "lesser" : "Not lesser");
                    break;

                default:
                    System.out.println("Enter Valid choice");
                    break;
            }

            System.out.println("Enter '1' to use again and '0' to leave : ");
            input = sc.nextInt();
        } while (input > 0);
        sc.close();

    }

}
