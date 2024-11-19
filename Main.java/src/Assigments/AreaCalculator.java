package Assigments;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the interface Shapes which contains a function declaration calcArea
 * and a static constant variable PI.
 * All the other classes will implement this interface.
 */
interface Shapes {
    double PI = 3.14;

    double calcArea();
}

/**
 * This class Circle implements the Shapes interface and calculates the area of
 * a circle.
 */
class Circle implements Shapes {
    private double radius;

    // Constructor to initialize values
    Circle(double radius) {
        this.radius = radius;
    }

    public double calcArea() {
        if (radius < 0.0) {
            throw new IllegalArgumentException("Radius can't be negative");
        }
        return PI * radius * radius;
    }
}

/**
 * This class Triangle implements the Shapes interface and calculates the area
 * of a triangle.
 */
class Triangle implements Shapes {
    private double height;
    private double width;

    // Constructor to initialize values
    Triangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double calcArea() {
        if (height < 0 || width < 0) {
            throw new IllegalArgumentException("Height and width can't be negative");
        }
        return 0.5 * height * width;
    }
}

/**
 * This class Rectangle implements the Shapes interface and calculates the area
 * of a rectangle.
 */
class Rectangle implements Shapes {
    private double length;
    private double breadth;

    // Constructor to initialize values
    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double calcArea() {
        if (length < 0 || breadth < 0) {
            throw new IllegalArgumentException("Length and breadth can't be negative");
        }
        return length * breadth;
    }
}

/**
 * This class Square implements the Shapes interface and calculates the area of
 * a square.
 */
class Square implements Shapes {
    private double side;

    // Constructor to initialize values
    Square(double squareSide) {
        this.side = squareSide;
    }

    public double calcArea() {
        if (side < 0) {
            throw new IllegalArgumentException("Side of a square can't be negative");
        }
        return side * side;
    }
}

/**
 * This class AreaCalculator provides a command-line interface for calculating
 * areas of different shapes.
 */
public class AreaCalculator {

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
     * This functions validates that whether the input given by user is valid or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     * @return returns a valid double value
     */
    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter  double values");
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (menuInput == 1) {
            System.out.println("Area Calculator-----");
            System.out.println("1. Triangle");
            System.out.println("2. Rectangle");
            System.out.println("3. Square");
            System.out.println("4. Circle");
            System.out.print("Enter your choice: ");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter height and width of a Triangle: ");
                    try {
                        double triangleHeight = getValidDoubleInput(scanner);
                        double triangleWidth = getValidDoubleInput(scanner);
                        Triangle triangle = new Triangle(triangleHeight, triangleWidth);
                        System.out.println("Area: " + triangle.calcArea());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    try {
                        System.out.print("Enter length and breadth of a Rectangle: ");
                        double rectangleLength = getValidDoubleInput(scanner);
                        double rectangleBreadth = getValidDoubleInput(scanner);
                        Rectangle rectangle = new Rectangle(rectangleLength, rectangleBreadth);
                        System.out.println("Area: " + rectangle.calcArea());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    try {
                        System.out.print("Enter side of a Square: ");
                        double squareSide = getValidDoubleInput(scanner);
                        Square square = new Square(squareSide);
                        System.out.println("Area: " + square.calcArea());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:
                    try {
                        System.out.print("Enter radius of a Circle: ");
                        double circleRadius = getValidDoubleInput(scanner);
                        Circle circle = new Circle(circleRadius);
                        System.out.println("Area: " + circle.calcArea());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                default:
                    System.out.println("Choice is not valid!!");
                    break;
            }

            System.out.print("Press 1 if you want to calculate more area, else press 0: ");
            updateMenuInput(scanner);
        }
        scanner.close();
    }

}
