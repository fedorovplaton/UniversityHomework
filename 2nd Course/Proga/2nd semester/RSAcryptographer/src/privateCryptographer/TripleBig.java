package privateCryptographer;

import java.math.BigInteger;

public class TripleBig {
    public BigInteger d;
    public BigInteger x;
    public BigInteger y;

    public TripleBig() {
    }

    public TripleBig(BigInteger d,
                     BigInteger x,
                     BigInteger y) {
        this.d = d;
        this.x = x;
        this.y = y;
    }
}
