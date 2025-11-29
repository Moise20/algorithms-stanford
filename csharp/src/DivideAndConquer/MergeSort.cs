using System;

namespace DivideAndConquer
{
    public static class MergeSort
    {
        public static int[] Merge(int[] left, int[] right)
        {
            int n = left.Length + right.Length;
            int[] result = new int[n];

            int i = 0, j = 0, k = 0;

            while (i < left.Length && j < right.Length)
            {
                if (left[i] <= right[j])
                    result[k++] = left[i++];
                else
                    result[k++] = right[j++];
            }

            while (i < left.Length)
                result[k++] = left[i++];

            while (j < right.Length)
                result[k++] = right[j++];

            return result;
        }

        public static int[] MergeSortAlgo(int[] arr)
        {
            if (arr.Length <= 1) return arr;

            int mid = arr.Length / 2;
            int[] left = new int[mid];
            int[] right = new int[arr.Length - mid];

            Array.Copy(arr, 0, left, 0, mid);
            Array.Copy(arr, mid, right, 0, arr.Length - mid);

            left = MergeSortAlgo(left);
            right = MergeSortAlgo(right);

            return Merge(left, right);
        }
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