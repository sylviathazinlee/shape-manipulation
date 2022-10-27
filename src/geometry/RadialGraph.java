package geometry;

import java.util.*;

public class RadialGraph extends Shape {

    Point center;
    List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) {
        this.center=center;
        this.neighbors=neighbors;
    }

    public RadialGraph(Point center) {
        this.center=center;
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        double new_pointX;
        double new_pointY;
    

        if(this.center.x == 0.0 && this.center.y == 0.0)
        {
            for(int i = 0; i < neighbors.size(); i++)
            {
               new_pointX = ((neighbors.get(i).x)*Math.cos(Math.toRadians(degrees))) - ((neighbors.get(i).y)*Math.sin(Math.toRadians(degrees)));
               new_pointY = ((neighbors.get(i).x)*Math.sin(Math.toRadians(degrees))) + ((neighbors.get(i).y)*Math.cos(Math.toRadians(degrees)));

               Point new_coord= new Point(this.neighbors.get(i).name,new_pointX,new_pointY);
               this.neighbors.set(i,new_coord);
            }
        }
        else
        {
            if(center.x < 0.0 && center.y < 0.0)
            {
                translateBy(Math.abs(center.x), Math.abs(center.y));
            }
            else if(center.x > 0.0 && center.y > 0.0)
            {
                translateBy(-1*center.x, -1*center.y);
            }
            else if(center.x < 0.0 && center.y > 0.0)
            {
                translateBy(Math.abs(center.x), -1*center.y);
            }
            else if(center.x > 0.0 && center.y < 0.0)
            {
                translateBy(-1*center.x, Math.abs(center.y));
            }
        }

        RadialGraph rotatedGraph= new RadialGraph(this.center, this.neighbors);
        return rotatedGraph;
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        double new_pointX;
        double new_pointY;

        Point new_cen= new Point(this.center.name, this.center.x + x, this.center.y + y);
        this.center=new_cen;

        for(int i = 0; i < neighbors.size(); i++)
        {
            new_pointX = this.neighbors.get(i).x + x;
            new_pointY = this.neighbors.get(i).y + y;

            Point new_coord= new Point(this.neighbors.get(i).name,new_pointX,new_pointY);
            this.neighbors.set(i,new_coord);
        }

        RadialGraph translatedGraph= new RadialGraph(this.center, this.neighbors);
        return translatedGraph;
    }

    @Override
    public String toString() {
        return null; 
    }

    @Override
    public Point center() {
        return this.center; 
    }

    /* Driver method given to you as an outline for testing your code. You can modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as they are (some lines have additional
     * explanation of what is expected) */
    public static void main(String... args) {
        Point center = new Point("center", 0, 0);
        Point east = new Point("east", 1, 0);
        Point west = new Point("west", -1, 0);
        Point north = new Point("north", 0, 1);
        Point south = new Point("south", 0, -1);
        Point toofarsouth = new Point("south", 0, -2);

        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);

        // Must print: [(center, 0.0, 0.0)]
        System.out.println(lonely);


        // This line must throw IllegalArgumentException, since the edges will not be of the same length
        RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));

        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

        // Must follow the documentation in the Shape abstract class, and print the following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        System.out.println(g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1, 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
        g = g.rotateBy(90);

        // you should similarly add tests for the translateBy(x, y) method
    }
}
