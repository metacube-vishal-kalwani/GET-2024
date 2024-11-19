package Assigments.Graphics;

public class Point {
    public int ptX;
    public int ptY;

    public Point(int xCordinate, int yCordinate) {

        try {
            // if (xCordinate < 0 || yCordinate < 0) {
            // throw new AssertionError("Cordinates can't be negetive!!!");
            // }
            ptX = xCordinate;
            ptY = yCordinate;
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }

    }
}
