package geometry;

import java.util.ArrayList;

public class SquareSymmetries implements Symmetries<Square>
{
    @Override
    public boolean areSymmetric(Square s1, Square s2)
    {
        ArrayList<Point> setOfS1 = new ArrayList<>();
        ArrayList<Point> setOfS2 = new ArrayList<>();

        setOfS1.add(s1.a);
        setOfS1.add(s1.b);
        setOfS1.add(s1.c);
        setOfS1.add(s1.d);

        setOfS2.add(s2.a);
        setOfS2.add(s2.b);
        setOfS2.add(s2.c);
        setOfS2.add(s2.d);


        boolean flag;
        for(int i = 0; i < setOfS1.size(); i++)
        {
            flag = false;
            for(int j = 0; j < setOfS2.size(); j++)
            {
                if((setOfS1.get(i).x == setOfS2.get(j).x) && (setOfS1.get(i).y == setOfS2.get(j).y))
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
    public ArrayList<Square> symmetriesOf(Square s)
    {
        ArrayList<Square> symmetries = new ArrayList<>();

        symmetries.add(s);
        symmetries.add(s.rotateBy(90));
        symmetries.add(s.rotateBy(180));
        symmetries.add(s.rotateBy(270));

        Square s1 = new Square(s.d, s.c, s.b, s.a);
        Square s2 = new Square(s.b, s.a, s.d, s.c);
        Square s3 = new Square(s.c, s.b, s.a, s.d);
        Square s4 = new Square(s.a, s.d, s.c, s.b);

        symmetries.add(s1);
        symmetries.add(s2);
        symmetries.add(s3);
        symmetries.add(s4);

        return symmetries;
        
    }
}

