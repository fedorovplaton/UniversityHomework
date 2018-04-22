import java.math.BigInteger;

public class PublicKey {
    public BigInteger e;
    public BigInteger n;

    public PublicKey(BigInteger e, BigInteger n) {
        this.e = e;
        this.n = n;
    }
}
