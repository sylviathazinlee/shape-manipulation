package arithmetic;

public enum PlusOrMinusOne {
    ONE(1),NEG_ONE(-1);

    public int value;

    PlusOrMinusOne(int value)
    {
        this.value=value;
    }

    public PlusOrMinusOne getVal()
    {
        return PlusOrMinusOne.ONE;
    }
}

