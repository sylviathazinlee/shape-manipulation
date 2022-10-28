package geometry;

public class Square extends Shape {
    Point center;
    Point a; Point b; Point c; Point d;

    @Override
    public Point center() 
    {
        return center;
    }

    @Override
    public Square rotateBy(int degrees) 
    {
        return null; 
    }

    @Override
    public Shape translateBy(double x, double y) 
    {
        return null; 
    }

    @Override
    public String toString() 
    {
        return null; 
    }

    public Square(Point a, Point b, Point c, Point d) 
    {
       this.a=a;
       this.b=b;
       this.c=c;
       this.d=d;
    }

    //Driver Method
    public static void main(String... args) 
    {
        Point one = new Point("one", 3, 3);
        Point two = new Point("two", 2, 3);
        Point three = new Point("three", 2, 2);
        Point four = new Point("four", 3, 2);

        Square firstSquare = new Square(one, two, three, four);


      
    }

}
