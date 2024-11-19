package Assigments.Graphics.Shapes;

import java.util.ArrayList;

import Assigments.Graphics.Point;
import Assigments.Graphics.Shape;

/**
 * Triangle class implements shape interface and its various methods like
 * getArea() getPerimeter() getOrigin and so on
 */
public class Triangle implements Shape {
    private int base;
    private int height;
    private Point origin;
    /* stores cordinates of all vertices */
    ArrayList<Point> vertices = new ArrayList<>();
    public ShapeType shapeType = ShapeType.Triangle;
    private int timeStamp;

    /**
     * 
     * @param originPoint left bottom point of triangle
     * @param properties  list of properties like height and base of triangle
     * @param timeStamp   takes timestamp from when object is added on screen
     */
    public Triangle(Point originPoint, ArrayList<Integer> properties, int timeStamp) {
        this.base = properties.get(0);
        this.height = properties.get(1);
        origin = originPoint;
        vertices.add(originPoint);
        Point point2 = new Point(originPoint.ptX + base, originPoint.ptY);
        Point point3 = new Point(originPoint.ptX + base / 2, originPoint.ptY + height);
        vertices.add(point2);
        vertices.add(point3);
        this.timeStamp = timeStamp;
    }

    /**
     * Calculates distance between two points by pythogorus theorem
     * 
     * @param p1 point one
     * @param p2 point two
     * @return distance between two points
     */
    double distance(Point p1, Point p2) {

        return Math.sqrt((p1.ptX - p2.ptX) * (p1.ptX - p2.ptX)
                + (p1.ptY - p2.ptY) * (p1.ptY - p2.ptY));
    }

    @Override
    public double getArea() {
        return (double) (0.5) * base * height;
    }

    @Override
    public double getPerimeter() {

        return 3 * base;
    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point givenPoint) {

        double givenPointDist = 0;
        for (Point vertex : vertices) {
            givenPointDist += distance(vertex, givenPoint);
        }
        double vertexDist = 0;
        vertexDist += distance(vertices.get(0), vertices.get(1));
        vertexDist += distance(vertices.get(0), vertices.get(2));
        vertexDist += distance(vertices.get(1), vertices.get(2));

        if (givenPointDist >= vertexDist) {
            return false;
        }
        return true;
    }

    @Override
    public int getTimeStamp() {
        return timeStamp;
    }

}
