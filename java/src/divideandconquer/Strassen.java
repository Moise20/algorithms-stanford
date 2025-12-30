package divideandconquer;

public class Strassen {

    // Addition de matrices
    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[j][j];
        return C;
    }

    // Soustraction
    private static int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    public static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;

        // cas de base
        if (n == 1) {
            return new int[][] { { A[0][0] * B[0][0] } };
        }

        int newSize = n / 2;

        int[][] a11 = new int[newSize][newSize];
        int[][] a12 = new int[newSize][newSize];
        int[][] a21 = new int[newSize][newSize];
        int[][] a22 = new int[newSize][newSize];

        int[][] b11 = new int[newSize][newSize];
        int[][] b12 = new int[newSize][newSize];
        int[][] b21 = new int[newSize][newSize];
        int[][] b22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                a11[i][j] = A[i][j];
                a12[i][j] = A[i][j + newSize];
                a21[i][j] = A[i + newSize][j];
                a22[i][j] = A[i + newSize][j + newSize];

                b11[i][j] = B[i][j];
                b12[i][j] = B[i][j + newSize];
                b21[i][j] = B[i + newSize][j];
                b22[i][j] = B[i + newSize][j + newSize];
            }
        }

        int[][] m1 = strassen(add(a11, a22), add(b11, b22));
        int[][] m2 = strassen(add(a21, a22), b11);
        int[][] m3 = strassen(a11, sub(b12, b22));
        int[][] m4 = strassen(a22, sub(b21, b11));
        int[][] m5 = strassen(add(a11, a12), b22);
        int[][] m6 = strassen(sub(a21, a11), add(b11, b12));
        int[][] m7 = strassen(sub(a12, a22), add(b21, b22));

        int[][] C = new int[n][n];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                C[i][j] = m1[i][j] + m4[i][j] - m5[i][j] + m7[i][j];
                C[i][j + newSize] = m3[i][j] + m5[i][j];
                C[i + newSize][j] = m2[i][j] + m4[i][j];
                C[i + newSize][j + newSize] = m1[i][j] - m2[i][j] + m3[i][j] + m6[i][j];
            }
        }

        return C;
    }

    public static void main(String[] args) {
        int[][] A = {{1,2},{3,4}};
        int[][] B = {{5,6},{7,8}};

        int[][] C = strassen(A,B);

        for (int[] row : C) {
            for (int v : row) System.out.print(v + " ");
            System.out.println();
        }
    }
}
