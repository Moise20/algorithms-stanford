package divideandconquer;

import java.util.Arrays;

public class MergeSort {

    public static int[] merge(int[] left, int[] right) {
        int n = left.length + right.length;
        int[] result = new int[n];

        int i = 0; // index left
        int j = 0; // index right
        int k = 0; // index result

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copie des restes (s'il en reste dans un des tableaux)
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 7, 4, 2, 6, 5, 1};
        int[] sorted = mergeSort(arr);

        System.out.println(Arrays.toString(sorted));
    }
}

/*
 * Merge procedure (from Stanford Algorithms Specialization)
 *
 * C = output array [length = n]
 * A = first sorted half [n/2]
 * B = second sorted half [n/2]
 * i = 1
 * j = 1
 * for k = 1 to n:
 *   if A(i) < B(j):
 *       C(k) = A(i)
 *       i++
 *   else:
 *       C(k) = B(j)
 *       j++
 */