package arithmetic;

import core.Group;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne x, PlusOrMinusOne y) 
    {
        int z = x.value * y.value;
        return PlusOrMinusOne.getValue(z);
    }

    @Override
    public PlusOrMinusOne identity() 
    {
        return PlusOrMinusOne.ONE;
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne x) 
    {
        int num= 1/(x.value);
        return PlusOrMinusOne.getValue(num);
    }

}
