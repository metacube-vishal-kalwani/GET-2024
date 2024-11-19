package Assigments.Graphics.Shapes;

import java.util.ArrayList;

import Assigments.Misc;
import Assigments.Graphics.Point;
import Assigments.Graphics.Shape;

public class Circle implements Shape {

    private int radius;
    private Point center;
    static final double PI = 3.14;
    public ShapeType shapeType = ShapeType.Circle;
    private int timeStamp;

    public Circle(Point centerPoint, ArrayList<Integer> properties, int timeStamp) {
        radius = properties.get(0);
        center = centerPoint;
        this.timeStamp = timeStamp;

    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public double getArea() {
        return Misc.roundOff((double) PI * radius * radius);
    }

    @Override
    public double getPerimeter() {
        return Misc.roundOff((double) 2 * PI * radius);
    }

    @Override
    public Point getOrigin() {
        double h = center.ptX;
        double k = center.ptY;
        double d = Math.sqrt(h * h + k * k);
        double x = (h * radius) / d;
        double y = (k * radius) / d;
        Point origin = new Point((int) x, (int) y);
        return origin;

    }

    @Override
    public boolean isPointEnclosed(Point givenPoint) {

        /* Distance of point from center */
        double pointDistance = Math.pow((givenPoint.ptX - center.ptX), 2)
                + Math.pow((givenPoint.ptY - center.ptY), 2);

        /* Condition for point to be outside of circle */
        if (pointDistance > (double) (radius * radius)) {
            return false;
        }
        return true;
    }

    @Override
    public int getTimeStamp() {
        return timeStamp;
    }

}
