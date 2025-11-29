package divideandconquer;

public class Karatsuba {

    public static long karatsuba(long x, long y) {
        // Cas de base : nombres petits → multiplication normale
        if (x < 10 || y < 10) {
            return x * y;
        }

        int n = Math.max(numDigits(x), numDigits(y));
        int m = n / 2;

        long power = (long) Math.pow(10, m);

        long a = x / power;
        long b = x % power;
        long c = y / power;
        long d = y % power;

        long ac = karatsuba(a, c);
        long bd = karatsuba(b, d);
        long abcd = karatsuba(a + b, c + d);
        long adPlusBc = abcd - ac - bd;

        return ac * (long) Math.pow(10, 2 * m)
                + adPlusBc * power
                + bd;
    }

    private static int numDigits(long x) {
        return Long.toString(Math.abs(x)).length();
    }

    public static void main(String[] args) {
        long x = 1234L;
        long y = 5678L;

        long resKaratsuba = karatsuba(x, y);
        long resNormal = x * y;

        System.out.println("Karatsuba : " + resKaratsuba);
        System.out.println("Normal    : " + resNormal);
    }
}


/*
 * Stanford Algorithms Specialization — Karatsuba Multiplication
 *
 * Pseudocode:
 * 1. Compute ac recursively
 * 2. Compute bd recursively
 * 3. Compute (a+b)(c+d) = ac + bd + ad + bc
 * 4. Gauss trick: (3) - (1) - (2) = ad + bc
 *
 * Final result:
 * x * y = ac * 10^(2m) + (ad + bc)*10^m + bd
 *
 */
/*
Compiler : javac src/divideandconquer/*.java
java -cp src divideandconquer.Karatsuba
java -cp src divideandconquer.MergeSort 

ou 
javac -d out src/divideandconquer/*.java 
java -cp out divideandconquer.Karatsuba
java -cp out divideandconquer.MergeSort
*/