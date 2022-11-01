package geometry;

import java.util.ArrayList;

public class RadialGraphSymmetries implements Symmetries<RadialGraph>
{
    @Override
    public boolean areSymmetric(RadialGraph s1, RadialGraph s2)
    {
        boolean flag;

        for(int i = 0 ; i < s1.neighbors.size(); i++)
        {
            flag = false;
            for(int j = 0 ; j < s2.neighbors.size(); j++)
            {
                if((s1.neighbors.get(i).x == s2.neighbors.get(j).x) && (s1.neighbors.get(i).y == s2.neighbors.get(j).y))
                {
                    flag = true;
                }
            }

            if(flag == false)
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public ArrayList<RadialGraph> symmetriesOf(RadialGraph s)
    {
        ArrayList<RadialGraph> symmetries = new ArrayList<>();
        ArrayList<Integer> edgedeg = edgeDegrees(s);

        for(int i = 0; i < edgedeg.size(); i++)
        {
            RadialGraph rotatedGraph = s.rotateBy(edgedeg.get(i));
            
            if(areSymmetric(s, rotatedGraph) == true)
            {
                symmetries.add(rotatedGraph);
            }
        }
        return symmetries;
    }
    
    protected ArrayList<Integer> edgeDegrees(RadialGraph g)
    {
        RadialGraph centeredGraph = g.translateBy(-g.center.x,-g.center.y);
        ArrayList<Integer> lstAngles = new ArrayList<>();


        for(int i = 0; i < centeredGraph.neighbors.size(); i++)
        {
            lstAngles.add((int)Math.atan2(centeredGraph.neighbors.get(i).y, centeredGraph.neighbors.get(i).x));
        }
        for(int i = 0; i < lstAngles.size(); i++)
        {
            lstAngles.set(i, lstAngles.get(i) - lstAngles.get(0));
        }
        return lstAngles;
    }
}

