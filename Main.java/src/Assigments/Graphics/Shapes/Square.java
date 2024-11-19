package Assigments.Graphics.Shapes;

import java.util.ArrayList;

import Assigments.Misc;
import Assigments.Graphics.Point;
import Assigments.Graphics.Shape;

/**
 * Square class implements shape interface and override abstract functions of
 * shape
 */
public class Square implements Shape {
    private int side;
    private Point origin;
    private Point miniumCordinate;
    private Point maximumCordinate;
    public ShapeType shapeType = ShapeType.Square;
    private int timeStamp;

    /**
     * 
     * @param originPoint takes origin point from user
     * @param properties  takes list of dimensions
     * @param timeStamp   takes timestamp when shape is added to screen
     */
    public Square(Point originPoint, ArrayList<Integer> properties, int timeStamp) {
        this.side = properties.get(0);
        this.origin = originPoint;
        miniumCordinate = originPoint;
        Point newPoint = new Point(miniumCordinate.ptX + side, miniumCordinate.ptY + side);
        maximumCordinate = newPoint;
        this.timeStamp = timeStamp;
    }

    /**
     * returns area of square
     */
    @Override
    public double getArea() {
        return Misc.roundOff((double) side * side);
    }

    /**
     * Returns type of shape
     */
    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public double getPerimeter() {
        return Misc.roundOff((double) 4 * side);
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
