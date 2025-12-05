package divideandconquer;

import java.math.BigInteger;

public class TestKaratsubaBigInt {
    public static void main(String[] args) {

        BigInteger x = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger y = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");

        BigInteger res1 = KaratsubaBigInt.karatsuba(x, y);
        BigInteger res2 = x.multiply(y);

        System.out.println("Karatsuba BigInt : " + res1);
        System.out.println("Normal BigInt    : " + res2);
        System.out.println("Correct ? " + res1.equals(res2));
    }
}
