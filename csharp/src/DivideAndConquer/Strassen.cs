using System;

namespace DivideAndConquer
{
    public class Strassen
    {
        static int[,] Add(int[,] A, int[,] B)
        {
            int n = A.GetLength(0);
            var C = new int[n, n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    C[i, j] = A[i, j] + B[i, j];
            return C;
        }

        static int[,] Sub(int[,] A, int[,] B)
        {
            int n = A.GetLength(0);
            var C = new int[n, n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    C[i, j] = A[i, j] - B[i, j];
            return C;
        }

        public static int[,] StrassenMul(int[,] A, int[,] B)
        {
            int n = A.GetLength(0);

            if (n == 1)
                return new int[,] { { A[0, 0] * B[0, 0] } };

            int m = n / 2;
            var a11 = new int[m, m]; var a12 = new int[m, m];
            var a21 = new int[m, m]; var a22 = new int[m, m];
            var b11 = new int[m, m]; var b12 = new int[m, m];
            var b21 = new int[m, m]; var b22 = new int[m, m];

            for (int i = 0; i < m; i++)
                for (int j = 0; j < m; j++)
                {
                    a11[i, j] = A[i, j];
                    a12[i, j] = A[i, j + m];
                    a21[i, j] = A[i + m, j];
                    a22[i, j] = A[i + m, j + m];

                    b11[i, j] = B[i, j];
                    b12[i, j] = B[i, j + m];
                    b21[i, j] = B[i + m, j];
                    b22[i, j] = B[i + m, j + m];
                }

            var m1 = StrassenMul(Add(a11, a22), Add(b11, b22));
            var m2 = StrassenMul(Add(a21, a22), b11);
            var m3 = StrassenMul(a11, Sub(b12, b22));
            var m4 = StrassenMul(a22, Sub(b21, b11));
            var m5 = StrassenMul(Add(a11, a12), b22);
            var m6 = StrassenMul(Sub(a21, a11), Add(b11, b12));
            var m7 = StrassenMul(Sub(a12, a22), Add(b21, b22));

            var C = new int[n, n];

            for (int i = 0; i < m; i++)
                for (int j = 0; j < m; j++)
                {
                    C[i, j] = m1[i, j] + m4[i, j] - m5[i, j] + m7[i, j];
                    C[i, j + m] = m3[i, j] + m5[i, j];
                    C[i + m, j] = m2[i, j] + m4[i, j];
                    C[i + m, j + m] = m1[i, j] - m2[i, j] + m3[i, j] + m6[i, j];
                }

            return C;
        }
    }
}
