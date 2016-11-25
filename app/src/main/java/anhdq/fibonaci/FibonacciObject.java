package anhdq.fibonaci;

import java.math.BigInteger;

/**
 * Created by Admin on 25-Nov-16.
 */

public class FibonacciObject {
    private BigInteger number;
    private long time;

    public FibonacciObject(BigInteger number, long time) {
        this.number = number;
        this.time = time;
    }

    public BigInteger getNumber() {
        return number;
    }

    public long getTime() {
        return time;
    }
}
