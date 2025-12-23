using System;

namespace DivideAndConquer
{
    public static class InversionCount
    {
        public static long CountInversions(int[] arr)
        {
            if (arr == null || arr.Length <= 1) return 0;

            int[] copy = (int[])arr.Clone();
            int[] temp = new int[arr.Length];
            return SortAndCount(copy, temp, 0, arr.Length - 1);
        }

        private static long SortAndCount(int[] a, int[] temp, int left, int right)
        {
            if (left >= right) return 0;

            int mid = left + (right - left) / 2;

            long leftInv = SortAndCount(a, temp, left, mid);
            long rightInv = SortAndCount(a, temp, mid + 1, right);
            long splitInv = MergeAndCount(a, temp, left, mid, right);

            return leftInv + rightInv + splitInv;
        }

        private static long MergeAndCount(int[] a, int[] temp, int left, int mid, int right)
        {
            int i = left;
            int j = mid + 1;
            int k = left;

            long inversions = 0;

            while (i <= mid && j <= right)
            {
                if (a[i] <= a[j])
                {
                    temp[k++] = a[i++];
                }
                else
                {
                    temp[k++] = a[j++];
                    inversions += (mid - i + 1);
                }
            }

            while (i <= mid) temp[k++] = a[i++];
            while (j <= right) temp[k++] = a[j++];

            for (int idx = left; idx <= right; idx++)
                a[idx] = temp[idx];

            return inversions;
        }
    }
}
