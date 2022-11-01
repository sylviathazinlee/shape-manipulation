package geometry;

import java.util.*;

public class RadialGraph extends Shape {

    Point center;
    List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) 
    {
        this.center=center;
        this.neighbors=neighbors;

        if(neighbors.size() > 1)
        {
            double distance = Math.round(Math.hypot(neighbors.get(0).x, neighbors.get(0).y));
            for(int i = 1; i < neighbors.size(); i++)
            {
                double nextDistance = Math.round(Math.hypot(neighbors.get(i).x, neighbors.get(i).y));
                if( distance != nextDistance)
                {
                    throw new IllegalArgumentException("Error: edges are not of the same length.");
                }
            }
        }

    }

    public RadialGraph(Point center) 
    {
        this.center=center;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public RadialGraph rotateBy(int degrees) 
    {
        Point tempcen = this.center;
        List<Point> tempneigh = new ArrayList<Point>(this.neighbors); 
        RadialGraph rotatedGraph = new RadialGraph(tempcen, tempneigh);

        if(tempcen.x == 0.0 && tempcen.y == 0.0)
        {
            for(int i = 0; i < tempneigh.size(); i++)
            {
                double x = tempneigh.get(i).x;
                double y = tempneigh.get(i).y;
                double deg = Math.toRadians(degrees);
            
                double new_POINTX = Math.round((x * Math.cos(deg)) - (y * Math.sin(deg)));
                double new_POINTY = Math.round((x * Math.sin(deg)) + (y * Math.cos(deg)));

                Point new_coord = new Point(tempneigh.get(i).name, new_POINTX, new_POINTY);
                tempneigh.set(i, new_coord);
            }
        }
        else if(rotatedGraph.center.x < 0.0 && rotatedGraph.center.y < 0.0)
        {
            translateBy(Math.abs(tempcen.x), Math.abs(tempcen.y));
            for(int i = 0; i < tempneigh.size(); i++)
            {
                double x = tempneigh.get(i).x;
                double y = tempneigh.get(i).y;
                double deg = Math.toRadians(degrees);
            
                double new_POINTX = Math.round((x * Math.cos(deg)) - (y * Math.sin(deg)));
                double new_POINTY = Math.round((x * Math.sin(deg)) + (y * Math.cos(deg)));

                Point new_coord = new Point(tempneigh.get(i).name, new_POINTX, new_POINTY);
                tempneigh.set(i, new_coord);
            }
            translateBy(-1 * tempcen.x, -1 * tempcen.y);
        }
        else if(rotatedGraph.center.x < 0.0 && rotatedGraph.center.y > 0.0)
        {
            translateBy(Math.abs(tempcen.x), -1 * tempcen.y);
            for(int i = 0; i < tempneigh.size(); i++)
            {
                double x = tempneigh.get(i).x;
                double y = tempneigh.get(i).y;
                double deg = Math.toRadians(degrees);
            
                double new_POINTX = Math.round((x * Math.cos(deg)) - (y * Math.sin(deg)));
                double new_POINTY = Math.round((x * Math.sin(deg)) + (y * Math.cos(deg)));

                Point new_coord = new Point(tempneigh.get(i).name, new_POINTX, new_POINTY);
                tempneigh.set(i, new_coord);
            }
            translateBy(-1 * tempcen.x, Math.abs(tempcen.y));
        }
        else if(rotatedGraph.center.x > 0.0 && rotatedGraph.center.y < 0.0)
        {
            translateBy(-1 * tempcen.x, Math.abs(tempcen.y));
            for(int i = 0; i < tempneigh.size(); i++)
            {
                double x = tempneigh.get(i).x;
                double y = tempneigh.get(i).y;
                double deg = Math.toRadians(degrees);
            
                double new_POINTX = Math.round((x * Math.cos(deg)) - (y * Math.sin(deg)));
                double new_POINTY = Math.round((x * Math.sin(deg)) + (y * Math.cos(deg)));

                Point new_coord = new Point(tempneigh.get(i).name, new_POINTX, new_POINTY);
                tempneigh.set(i, new_coord);
            }
            translateBy(Math.abs(tempcen.x), -1 * tempcen.y);
        }
        else if(rotatedGraph.center.x > 0.0 && rotatedGraph.center.y >0.0)
        {
            translateBy(-1 * tempcen.x, -1 * tempcen.y);
            for(int i = 0; i < tempneigh.size(); i++)
            {
                double x = tempneigh.get(i).x;
                double y = tempneigh.get(i).y;
                double deg = Math.toRadians(degrees);
            
                double new_POINTX = Math.round((x * Math.cos(deg)) - (y * Math.sin(deg)));
                double new_POINTY = Math.round((x * Math.sin(deg)) + (y * Math.cos(deg)));

                Point new_coord = new Point(tempneigh.get(i).name, new_POINTX, new_POINTY);
                tempneigh.set(i, new_coord);
            }
            translateBy(Math.abs(tempcen.x), Math.abs(tempcen.y));
        }
        return rotatedGraph;
    }

    @Override
    public RadialGraph translateBy(double x, double y) 
    {
        RadialGraph translatedGraph= new RadialGraph(this.center, this.neighbors);
        Point tempcen = this.center;
        List<Point> tempneigh = new ArrayList<Point>(this.neighbors); 

        Point new_cen= new Point(tempcen.name, tempcen.x + x, tempcen.y + y);
        tempcen=new_cen;

        for(int i = 0; i < tempneigh.size(); i++)
        {
            double new_pointX = tempneigh.get(i).x + x;
            double new_pointY = tempneigh.get(i).y + y;
            Point new_coord= new Point(tempneigh.get(i).name,new_pointX,new_pointY);
            tempneigh.set(i,new_coord);
        }

        translatedGraph.center= tempcen;
        translatedGraph.neighbors= tempneigh;
        return translatedGraph;
    }

    @Override
    public String toString() 
    {

        String line = "[" + this.center;
        List<Point> neighCopy = new ArrayList<Point>(this.neighbors);

        
        while(neighCopy.size() > 0)
        {
            double lastAng = Double.MAX_VALUE;
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
        //Point toofarsouth = new Point("south", 0, -2);

        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);

        // Must print: [(center, 0.0, 0.0)]
        System.out.println(lonely);


        // This line must throw IllegalArgumentException, since the edges will not be of the same length
        //RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));

        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

        // Must follow the documentation in the Shape abstract class, and print the following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        System.out.println("Original :" + g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1, 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
        g = g.rotateBy(90);
        System.out.println("Rotated: " + g);

        // you should similarly add tests for the translateBy(x, y) method
        g = g.translateBy(2, -1);
        System.out.println("Translated: " + g);
    }
}
