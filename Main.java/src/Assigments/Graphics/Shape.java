package Assigments.Graphics;

public interface Shape {

    /**
     * calculates area of shape
     * 
     * @return returns the area of a shape
     */
    double getArea();

    /**
     * calculates perimeter of shape
     * 
     * @return returns perimeter of a shape
     */
    double getPerimeter();

    /**
     * finds origin of shape
     * 
     * @return returns origin of a shape
     */
    Point getOrigin();

    /**
     * Tells if the point is enclosed by a shape
     * 
     * @return returns true if enclosed either returns false
     */
    boolean isPointEnclosed(Point givenPoint);

    ShapeType getShapeType();

    int getTimeStamp();

    public enum ShapeType {
        Circle,
        Square,
        Rectangle,
        Polygon,
        Triangle
    }

}
