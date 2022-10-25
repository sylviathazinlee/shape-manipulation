package arithmetic;

import core.Group;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne x, PlusOrMinusOne y) {
        return x.ONE + y.NEG_ONE;
    }

    @Override
    public PlusOrMinusOne identity() {
        return PlusOrMinusOne.ONE;
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne x) {
        return x.NEG_ONE;
    }

    @Override
    public PlusOrMinusOne exponent(PlusOrMinusOne x, int k) {
        return PlusOrMinusOne;
    }
}
