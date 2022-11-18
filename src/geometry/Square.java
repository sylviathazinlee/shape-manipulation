package geometry;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Square extends Shape {
    Point center;
    Point a;
    Point b;
    Point c;
    Point d;

    @Override
    public Point center() 
    {

        double midX = ((a.x + c.x) / 2);
        double midY = ((a.y + c.y) / 2);
        this.center = new Point("Center", midX, midY);

        return this.center;
    }

    @Override
    public Square rotateBy(int degrees) 
    {
        Square centeredSquare = (Square) translateBy(-center().x, -center().y);

        Square rotatedSquare = new Square(rotatedAngle(centeredSquare.a, degrees),
                rotatedAngle(centeredSquare.b, degrees),
                rotatedAngle(centeredSquare.c, degrees),
                rotatedAngle(centeredSquare.d, degrees));

        return (Square) rotatedSquare.translateBy(center().x, center().y);

    }

    @Override
    public Shape translateBy(double x, double y) 
    {

        Point one = this.a;
        Point two = this.b;
        Point three = this.c;
        Point four = this.d;

        Shape translatedShape = new Square(one, two, three, four);

        ArrayList<Point> squarePoints = new ArrayList<>();
        squarePoints.add(one);
        squarePoints.add(two);
        squarePoints.add(three);
        squarePoints.add(four);

        for (int i = 0; i < squarePoints.size(); i++) {
            double new_POINTX = squarePoints.get(i).x + x;
            double new_POINTY = squarePoints.get(i).y + y;

            Point new_coord = new Point(squarePoints.get(i).name, new_POINTX, new_POINTY);
            squarePoints.set(i, new_coord);
        }
        translatedShape = new Square(squarePoints.get(0), squarePoints.get(1), squarePoints.get(2),
                squarePoints.get(3));
        return translatedShape;

    }

    @Override
    public String toString() 
    {
        Point A = this.a;
        Point B = this.b;
        Point C = this.c;
        Point D = this.d;
        ArrayList<Point> squarePoints = new ArrayList<>();
        squarePoints.add(A);
        squarePoints.add(B);
        squarePoints.add(C);
        squarePoints.add(D);

        String line = "[";
        while (squarePoints.size() > 0) {
            double lastAng = Double.MAX_VALUE;
            int tempNum = 0;
            for (int j = 0; j < squarePoints.size(); j++) {
                double ang = Math.atan2(squarePoints.get(j).y, squarePoints.get(j).x);
                if (ang < 0.0) {
                    ang = (ang * -1) + 3.14;
                }
                if (ang <= lastAng) {
                    lastAng = ang;
                    tempNum = j;
                }
            }
            line += squarePoints.get(tempNum) + "; ";
            squarePoints.remove(tempNum);
        }
        String finishedPrint = line.substring(0, line.length() - 2);
        return finishedPrint + "]";
    }

    public Square(Point a, Point b, Point c, Point d) 
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;


        // check if all the sides are equal and the two diagonals are equal to each
        if (getDistance(d, a) != getDistance(a, b) || getDistance(c, d) != getDistance(a, b) || getDistance(b, c) != getDistance(a, b)
            || getDistance(a, c) != getDistance(b, d)) 
        {
            throw new IllegalArgumentException("Error: Invalid points for a square.");
        }

    }

    protected double getDistance(Point one, Point two) 
    {
        double distx = Math.pow(one.x - two.x, 2);
        double disty = Math.pow(one.y - two.y, 2);

        return Math.sqrt(distx + disty);
    }

    protected Point rotatedAngle(Point anyPoint, int degree) 
    {
        double deg = Math.toRadians(degree);
        double cos = roundNum(Math.cos(deg));
        double sin = roundNum(Math.sin(deg));

        double new_XVal = (anyPoint.x * cos) - (anyPoint.y * sin);
        double new_YVal = (anyPoint.x * sin) + (anyPoint.y * cos);

        return new Point(anyPoint.name, new_XVal, new_YVal);
    }

    //sets rounding to two decimal places
    protected double roundNum(double randomNum) 
    {
        DecimalFormat decFormat = new DecimalFormat();

        decFormat.setMaximumFractionDigits(2);

        return Double.parseDouble(decFormat.format(randomNum));
    }

    /*
    //driver method to test this file
    public static void main(String... args) 
    {
        Point one = new Point("A", 10, 20);
        Point two = new Point("B", 20, 20);
        Point three = new Point("C", 20, 10);
        Point four = new Point("D", 10, 10);



        Shape firstSquare = new Square(one, two, three, four);

        System.out.println(firstSquare.center());
        System.out.println("Original: " + firstSquare);

        firstSquare = firstSquare.rotateBy(90);
        System.out.println("Rotated: " + firstSquare);

        firstSquare = firstSquare.translateBy(1, 1);
        System.out.println("Translated: " + firstSquare);

    }
    */
}
