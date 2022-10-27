package arithmetic;

public enum PlusOrMinusOne {
    ONE(1),NEG_ONE(-1);

    int value;

    private PlusOrMinusOne(int value)
    {
        this.value=value;

    }

    protected static PlusOrMinusOne getValue(int z)
    {
        if(z == -1)
        {
            return PlusOrMinusOne.NEG_ONE;
        }
        else
        {
            return PlusOrMinusOne.ONE;
        }
    }

     @Override
     public String toString()
     {
        return String.valueOf(value);
     }

}

