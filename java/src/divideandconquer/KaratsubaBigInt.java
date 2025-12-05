package divideandconquer;

import java.math.BigInteger;

public class KaratsubaBigInt {

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {

        // Cas de base : si x ou y tient dans un int on multiplie directement
        if (x.bitLength() < 32 || y.bitLength() < 32) {
            return x.multiply(y);
        }

        int n = Math.max(x.toString().length(), y.toString().length());
        int m = n / 2;

        BigInteger power = BigInteger.TEN.pow(m);

        BigInteger a = x.divide(power);
        BigInteger b = x.mod(power);
        BigInteger c = y.divide(power);
        BigInteger d = y.mod(power);

        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        BigInteger abcd = karatsuba(a.add(b), c.add(d));

        BigInteger adPlusBc = abcd.subtract(ac).subtract(bd);

        return ac.multiply(BigInteger.TEN.pow(2 * m))
                .add(adPlusBc.multiply(power))
                .add(bd);
    }
}
