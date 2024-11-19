package Assigments.Graphics;

import java.util.ArrayList;

import Assigments.Graphics.Shape.ShapeType;
import Assigments.Graphics.Shapes.Circle;
import Assigments.Graphics.Shapes.Polygon;
import Assigments.Graphics.Shapes.Rectangle;
import Assigments.Graphics.Shapes.Square;
import Assigments.Graphics.Shapes.Triangle;

public class Factory {

    public Shape createShape(String shapeType, Point shapeOrigin, ArrayList<Integer> properties, int timeStamp) {

        Shape newShape = null;
        if (ShapeType.Circle.toString().equals(shapeType)) {
            newShape = new Circle(shapeOrigin, properties, timeStamp);

        } else if (ShapeType.Triangle.toString().equals(shapeType)) {
            newShape = new Triangle(shapeOrigin, properties, timeStamp);

        } else if (ShapeType.Rectangle.toString().equals(shapeType)) {
            newShape = new Rectangle(shapeOrigin, properties, timeStamp);

        } else if (ShapeType.Polygon.toString().equals(shapeType)) {
            newShape = new Polygon(shapeOrigin, properties, timeStamp);

        } else if (ShapeType.Square.toString().equals(shapeType)) {
            newShape = new Square(shapeOrigin, properties, timeStamp);
        } else {
            throw new AssertionError("Invalid Shape!!!");
        }

        return newShape;

    }
}
