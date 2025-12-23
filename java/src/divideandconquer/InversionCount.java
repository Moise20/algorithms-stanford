package divideandconquer;

import java.util.Arrays;

public class InversionCount {

    public static long countInversions(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int[] copy = Arrays.copyOf(arr, arr.length);
        int[] temp = new int[arr.length];
        return sortAndCount(copy, temp, 0, arr.length - 1);
    }

    private static long sortAndCount(int[] a, int[] temp, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;

        long leftInv = sortAndCount(a, temp, left, mid);
        long rightInv = sortAndCount(a, temp, mid + 1, right);
        long splitInv = mergeAndCount(a, temp, left, mid, right);

        return leftInv + rightInv + splitInv;
    }

    private static long mergeAndCount(int[] a, int[] temp, int left, int mid, int right) {
        int i = left;     // pointer sur moitié gauche
        int j = mid + 1;  // pointer sur moitié droite
        int k = left;     // pointer dans temp

        long inversions = 0;

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                // a[i] > a[j] => a[i], a[i+1], ..., a[mid] font inversion avec a[j-1]
                inversions += (mid - i + 1);
            }
        }

        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        for (int idx = left; idx <= right; idx++) {
            a[idx] = temp[idx];
        }

        return inversions;
    }

    // Petit test
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5};
        long inv = countInversions(arr);
        System.out.println("Inversions: " + inv); // attendu: 3 (2,1) (4,1) (4,3)
    }
}

/*javac -d out src/divideandconquer/InversionCount.java
java -cp out divideandconquer.InversionCount
*/