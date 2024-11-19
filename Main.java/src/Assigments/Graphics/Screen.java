package Assigments.Graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class will act as a screen on which we can place different kind of
 * shapes , Performs operations like-
 * add a shape , remove shape , sort all shapes , find the number of shapes
 * enclosing a point
 */
public class Screen {

    private ArrayList<Shape> shapeList;
    Factory factory = new Factory();
    int XLimit;
    int yLimit;
    static int timeStamp = 0;

    Screen(int XLimit, int yLimit) {
        this.XLimit = XLimit;
        this.yLimit = yLimit;
        shapeList = new ArrayList<>();
    }

    /**
     * Adds a new shape to screen
     * 
     * @param shapeInput shape object
     */
    public void addShapeToScreen(Shape shapeInput) {
        if (shapeInput.getOrigin().ptX > XLimit || shapeInput.getOrigin().ptY > yLimit) {
            throw new AssertionError("Shape is out of screen!!");
        }
        shapeList.add(shapeInput);
    }

    /**
     * This function removes a specific shape from screen
     * 
     * @param scanner scanner object to get the shapes number to delete it
     * @return returns true if shape deleted successfully
     */
    public boolean deleteShapeFromScreen(Scanner scanner) {
        // if screen is empty
        if (shapeList.size() == 0) {
            System.out.println("Screen is empty!!");
            return false;
        }
        int index = 1;
        for (Shape shape : shapeList) {
            System.out.println(index + " " + shape.getShapeType().toString());
            index++;
        }
        System.out.print("Enter Index to delete a shape: ");
        while (true) {
            try {
                index = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (index < 1 || index > shapeList.size()) {
                    throw new AssertionError("Index out of bound! Enter a valid Index: ");
                }
                break; // Exit the loop if a valid index is entered
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid Integer!");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        shapeList.remove(index - 1);
        return true;
    }

    /**
     * Prints information of all the shapes on screen
     * like - timestamp , shape's type , area perimeter, and it's origin
     */
    public void printAllShapesOnScreen() {
        if (shapeList.size() == 0) {
            System.out.println("Screen is empty!!");
            return;
        }
        System.out.println("TimeStamp" + " Shapetype" + " Area" + " Perimeter" + " Origin");
        for (Shape shape : shapeList) {
            System.out.println(shape.getTimeStamp() + "\t"
                    + shape.getShapeType().toString() + "\t"
                    + shape.getArea() + "\t"
                    + shape.getPerimeter() + "\t"
                    + shape.getOrigin().ptX + "," + shape.getOrigin().ptY);
        }
    }

    /**
     * Calculates distance of a points from origin by distance formula
     * 
     * @param point
     * @return
     */
    double calcDistFromOrigin(Point point) {

        double dist = Math.sqrt(point.ptX * point.ptX + point.ptY * point.ptY);
        return dist;
    }

    /**
     * Sorts (ascending) shapes present on screen based upon various parameters
     * 1 Area
     * 2 Perimeter
     * 3 TimeStamp
     * 4 distance from origin of screen
     * 
     * @param choice takes choice from user
     */
    public void sortShapes(int choice) {

        switch (choice) {
            case 1:
                Collections.sort(shapeList, (Shape s1, Shape s2) -> {
                    return Double.compare(s1.getArea(), s2.getArea());
                });
                break;
            case 2:
                Collections.sort(shapeList, (Shape s1, Shape s2) -> {
                    return Double.compare(s1.getPerimeter(), s2.getPerimeter());
                });
                break;
            case 3:
                Collections.sort(shapeList, (Shape s1, Shape s2) -> {
                    return Double.compare(s1.getTimeStamp(), s2.getTimeStamp());
                });
                break;
            case 4:
                Collections.sort(shapeList, (Shape s1, Shape s2) -> {
                    return Double.compare(calcDistFromOrigin(s1.getOrigin()), calcDistFromOrigin(s2.getOrigin()));
                });
                break;

            default:
                System.out.println("Invalid choice!!");
                break;
        }
    }

    /**
     * Gives the list of shapes which encloses a specific point
     * 
     * @param P
     * @return
     */
    ArrayList<Shape> ShapesEnclosingSpeceficPoint(Point P) {
        ArrayList<Shape> specificShapes = new ArrayList<>();
        for (Shape shape : shapeList) {
            if (shape.isPointEnclosed(P)) {
                specificShapes.add(shape);
            }
        }
        return specificShapes;
    }

}
