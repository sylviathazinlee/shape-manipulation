package geometry;

import java.util.*;

public class RadialGraph extends Shape {

    Point center;
    List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) 
    {
        this.center=center;
        this.neighbors=neighbors;
    }

    public RadialGraph(Point center) 
    {
        this.center=center;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        double new_POINTX;
        double new_POINTY;
        RadialGraph rotatedGraph = new RadialGraph(this.center, this.neighbors);

        for(int i = 0; i < rotatedGraph.neighbors.size(); i++)
        {
            double x = rotatedGraph.neighbors.get(i).x;
            double y = rotatedGraph.neighbors.get(i).y;
            double deg = Math.toRadians(degrees);

            new_POINTX = (x * Math.cos(deg)) - (y * Math.sin(deg));
            new_POINTY = (x * Math.sin(deg)) + (y * Math.cos(deg));

            Point new_coord = new Point(rotatedGraph.neighbors.get(i).name, new_POINTX, new_POINTY);
            rotatedGraph.neighbors.set(i, new_coord);
        }
        return rotatedGraph;
    }

    @Override
    public RadialGraph translateBy(double x, double y) 
    {
        double new_pointX;
        double new_pointY;
        Point tempcen = this.center;
        List<Point> tempneigh = this.neighbors; 

        Point new_cen= new Point(tempcen.name, tempcen.x + x, tempcen.y + y);
        tempcen=new_cen;

        for(int i = 0; i < tempneigh.size(); i++)
        {
            new_pointX = tempneigh.get(i).x + x;
            new_pointY = tempneigh.get(i).y + y;

            Point new_coord= new Point(tempneigh.get(i).name,new_pointX,new_pointY);
            tempneigh.set(i,new_coord);
        }

        RadialGraph translatedGraph= new RadialGraph(tempcen, tempneigh);
        return translatedGraph;
    }

    @Override
    public String toString() {

        String line = "[" + this.center;
        List<Point> neighCopy = new ArrayList<Point>(this.neighbors);

        
        while(neighCopy.size() > 0)
        {
            double lastAng = 99;
            int tempNum = 0;
            for(int j = 0; j < neighCopy.size(); j++)
            {
                double ang = Math.atan2(neighCopy.get(j).y-this.center.y, neighCopy.get(j).x-this.center.x);
                if( ang < 0.0)
                {
                    ang = (ang*-1)+3.14;
                }
                if(ang <= lastAng)
                {
                    lastAng = ang;
                    tempNum = j;
                }
            }
            line += "; " + neighCopy.get(tempNum);
            neighCopy.remove(tempNum);
        }

        return line + "]";
    }

    @Override
    public Point center() 
    {
        return this.center; 
    }

    /* Driver method given to you as an outline for testing your code. You can modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as they are (some lines have additional
     * explanation of what is expected) */
    public static void main(String... args) 
    {
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
