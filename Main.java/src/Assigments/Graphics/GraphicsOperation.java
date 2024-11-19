package Assigments.Graphics;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements the graphics liberary performs screen operations
 */
public class GraphicsOperation {

    private static int menuInput = 1;
    static Factory factory;
    static int timeStamp = 0;
    static Screen screen;

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
     * This functions validates that whether the input given by user is a positive
     * integer or not
     * 
     * @param scanner takes scanner object to scan values from CLI
     * @return returns a valid positive integer value
     */
    public static int getValidPositiveIntegerInput(Scanner scanner) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input <= 0) {
                    throw new InputMismatchException();
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid positive integer input!!");
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
     * This functions creates a list of dimentions based on its shape
     * 
     * @param shapeType takes type of shape
     * @param scanner   takes scanner to get the dimensions from console
     * @return returns the list of dimensions of a specific shape type
     */
    static ArrayList<Integer> getDimensionsofShape(String shapeType, Scanner scanner) {
        ArrayList<Integer> dimensions = new ArrayList<>();
        switch (shapeType) {
            case "Circle":
                System.out.print("Enter radius of Circle : ");
                dimensions.add(getValidPositiveIntegerInput(scanner));
                break;

            case "Rectangle":
                System.out.print("Enter length and breadth of rectangle respectively : ");
                dimensions.add(getValidPositiveIntegerInput(scanner));
                dimensions.add(getValidPositiveIntegerInput(scanner));
                break;

            case "Square":
                System.out.print("Enter side of Square : ");
                dimensions.add(getValidPositiveIntegerInput(scanner));
                break;

            case "Triangle":
                System.out.print("Enter base and height of triangle respectively : ");
                dimensions.add(getValidPositiveIntegerInput(scanner));
                dimensions.add(getValidPositiveIntegerInput(scanner));
                break;

            case "Polygon":
                System.out.print("Enter side-length  of a polygon  : ");
                dimensions.add(getValidPositiveIntegerInput(scanner));
                System.out.print("Enter  number of sides  of polygon : ");
                int noOfSide = 0;
                while (true) {
                    noOfSide = getValidPositiveIntegerInput(scanner);
                    if (noOfSide < 3) {
                        System.out.println("number of sides can't be less than 3!!");
                        System.out.print("Enter again : ");
                    } else {
                        break;
                    }

                }
                dimensions.add(noOfSide);
                break;

            default:
                System.out.println("invalid shape!!!");
                break;
        }
        return dimensions;
    }

    /**
     * returns the string after changing it ot capitalize form (e.g - arrAy ->
     * Array)
     * 
     * @param input takes input String from user
     * @return new string
     */
    static String capitalize(String input) {
        String capitalizedStr = input.substring(0, 1).toUpperCase()
                + input.substring(1).toLowerCase();
        return capitalizedStr;
    }

    static String validateShape(Scanner scanner) {
        String[] possibleShapes = { "Circle", "Square", "Triangle", "Rectangle", "Polygon" };
        while (true) {
            try {
                String shape = capitalize(scanner.next());
                for (String validShape : possibleShapes) {
                    if (validShape.equals(shape)) {
                        return shape;
                    }
                }
                throw new AssertionError("Invalid Shape!! Enter a valid Shape : ");
            } catch (AssertionError e) {
                System.out.print(e.getMessage());
            }
        }
    }

    /**
     * Takes shapetype and all the shape's information from console and create new
     * shape
     * on screen
     * 
     * @param scanner
     * @return returns true if shape is successfully created
     */
    public static boolean createShape(Scanner scanner) {
        ArrayList<Integer> dimensions;
        String shapeType;
        Point origin;
        Shape newShape = null;
        try {

            System.out.print("Enter origin x and y cordinates respectively : ");
            int x = getValidIntegerInput(scanner);
            int y = getValidIntegerInput(scanner);

            origin = new Point(x, y);
            if (x < 0 || y < 0 || x >= screen.XLimit || y >= screen.yLimit) {
                throw new AssertionError("Invalid Cordinates!!!");
            }
            System.out.print("Enter shape type : ");
            shapeType = validateShape(scanner);
            dimensions = getDimensionsofShape(shapeType, scanner);
            scanner.nextLine();
            newShape = factory.createShape(shapeType, origin, dimensions, timeStamp);
            timeStamp++;
            screen.addShapeToScreen(newShape);
            return true;

        } catch (AssertionError e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static void main(String[] args) {
        screen = new Screen(100, 100);
        factory = new Factory();
        Scanner scanner = new Scanner(System.in);

        while (menuInput == 1) {
            System.out.println("-----Graphics Liberary------");
            System.out.println("\n1  Add shape to screen "
                    + "\n2  Remove Shape from screen"
                    + "\n3  Sort shapes "
                    + "\n4  Get all shapes enclosing a specific point"
                    + "\n5  Print all Shapes on screen");
            System.out.print("Enter your choice : ");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    try {
                        if (createShape(scanner))
                            System.out.println("Shape added successfully!!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        if (screen.deleteShapeFromScreen(scanner))
                            System.out.println("Shape deleted successfully!!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Sort By:- ");
                        System.out.println("\n1  Area"
                                + "\n2  Perimeter"
                                + "\n3  Timestamp"
                                + "\n4  Distance from screen origin");
                        System.out.println("Enter your Choice");
                        screen.sortShapes(getValidIntegerInput(scanner));
                        screen.printAllShapesOnScreen();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Enter x and y cordinates of specific point respectively :");
                        int x = getValidPositiveIntegerInput(scanner);
                        int y = getValidPositiveIntegerInput(scanner);
                        Point specificPoint = new Point(x, y);
                        ArrayList<Shape> list = screen.ShapesEnclosingSpeceficPoint(specificPoint);
                        for (Shape shape : list) {
                            System.out.println(shape.getTimeStamp() + " " + shape.getShapeType());
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    screen.printAllShapesOnScreen();
                    break;

                default:
                    System.out.println("Invalid choice !!!");
                    break;
            }

            System.out.print("press 1 to use again or press 0 to exit : ");
            updateMenuInput(scanner);

        }

    }
}
