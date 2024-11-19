package Assigments.Graphics.Shapes;

import java.util.ArrayList;

import Assigments.Misc;
import Assigments.Graphics.Point;
import Assigments.Graphics.Shape;

public class Polygon implements Shape {
    private int side;
    private int numberOfsides;
    Point origin;
    double apothem;
    static final double PI = Math.PI;
    ArrayList<Point> allVertices = new ArrayList<>();
    public ShapeType shapeType = ShapeType.Polygon;
    private int timeStamp;

    public Polygon(Point originPoint, ArrayList<Integer> properties, int timeStamp) {
        this.side = properties.get(0);
        this.numberOfsides = properties.get(1);
        this.origin = originPoint;
        apothem = side / (2 * Math.tan(PI / numberOfsides));
        this.timeStamp = timeStamp;

        // Calculating all the vertices of polygon
        double angleIncrement = 2 * Math.PI / numberOfsides;
        double radius = side / (2 * Math.sin(Math.PI / numberOfsides));

        // Calculate the center of the polygon
        double centerX = origin.ptX + radius * Math.cos(Math.PI / 2);
        double centerY = origin.ptY - radius * Math.sin(Math.PI / 2);

        // Calculate all vertices
        for (int i = 0; i < numberOfsides; i++) {
            double angle = i * angleIncrement;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            Point vertex = new Point((int) x, (int) y);
            allVertices.add(vertex);
        }

    }

    @Override
    public double getPerimeter() {
        return Misc.roundOff((double) numberOfsides * side);
    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public double getArea() {

        double area = (double) (0.5) * numberOfsides * side * apothem;
        return Misc.roundOff(area);
    }

    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point point) {

        // Count of intersections
        int count = 0;

        // Iterate through each edge of the polygon
        for (int i = 0; i < numberOfsides; i++) {
            Point p1 = allVertices.get(i);
            // Ensure the last point connects to the first point
            Point p2 = allVertices.get((i + 1) % (int) numberOfsides);

            // Check if the point's y-coordinate is within the
            // edge's y-range and if the point is to the left of
            // the edge
            if ((point.ptY > Math.min(p1.ptY, p2.ptY))
                    && (point.ptY <= Math.max(p1.ptY, p2.ptY))
                    && (point.ptX <= Math.max(p1.ptX, p2.ptX))) {
                // Calculate the x-coordinate of the
                // intersection of the edge with a horizontal
                // line through the point
                double xIntersect = (point.ptY - p1.ptY) * (p2.ptX - p1.ptX) / (p2.ptY - p1.ptY) + p1.ptX;
                // If the edge is vertical or the point's
                // x-coordinate is less than or equal to the
                // intersection x-coordinate, increment count
                if (p1.ptX == p2.ptX || point.ptX <= xIntersect) {
                    count++;
                }
            }
        }
        // If the number of intersections is odd, the point is
        // inside the polygon
        return count % 2 == 1;
    }

    @Override
    public int getTimeStamp() {
        return timeStamp;
    }

}
