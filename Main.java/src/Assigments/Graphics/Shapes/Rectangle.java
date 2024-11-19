package Assigments.Graphics.Shapes;

import java.util.ArrayList;

import Assigments.Graphics.Point;
import Assigments.Graphics.Shape;

public class Rectangle implements Shape {

    private int length;
    private int breadth;
    private Point origin;
    private Point miniumCordinate;
    private Point maximumCordinate;
    public ShapeType shapeType = ShapeType.Rectangle;
    private int timeStamp;

    public Rectangle(Point originPoint, ArrayList<Integer> properties, int timeStamp) {
        this.length = properties.get(0);
        this.breadth = properties.get(1);
        this.origin = originPoint;
        miniumCordinate = originPoint;
        Point newPoint = new Point(miniumCordinate.ptX + length, miniumCordinate.ptY + breadth);
        maximumCordinate = newPoint;
        this.timeStamp = timeStamp;
    }

    @Override
    public double getArea() {
        return length * breadth;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + breadth);
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

        if ((givenPoint.ptX > miniumCordinate.ptX && givenPoint.ptY > miniumCordinate.ptY) &&
                (givenPoint.ptX < maximumCordinate.ptX && givenPoint.ptY < maximumCordinate.ptY)) {
            return true;
        }
        return false;
    }

    @Override
    public int getTimeStamp() {
        return timeStamp;
    }
}
