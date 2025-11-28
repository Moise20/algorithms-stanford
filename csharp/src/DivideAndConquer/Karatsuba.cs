using System;
using System.Numerics;

namespace DivideAndConquer
{
    public static class Karatsuba
    {
        public static BigInteger KaratsubaMul(BigInteger x, BigInteger y)
        {
            // Cas de base : nombres petits
            if (x < 10 && y < 10)
                return x * y;

            int n = Math.Max(x.ToString().Length, y.ToString().Length);
            int m = n / 2;

            BigInteger pow10m = BigInteger.Pow(10, m);

            BigInteger a = x / pow10m;
            BigInteger b = x % pow10m;
            BigInteger c = y / pow10m;
            BigInteger d = y % pow10m;

            BigInteger ac = KaratsubaMul(a, c);
            BigInteger bd = KaratsubaMul(b, d);
            BigInteger abcd = KaratsubaMul(a + b, c + d);
            BigInteger adPlusBc = abcd - ac - bd;

            return ac * BigInteger.Pow(10, 2 * m)
                   + adPlusBc * pow10m
                   + bd;
        }
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