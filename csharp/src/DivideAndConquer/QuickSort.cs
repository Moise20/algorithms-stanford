using System;

namespace DivideAndConquer
{
    public static class QuickSort
    {
        private static readonly Random Rand = new Random();

        public static void Sort(int[] arr)
        {
            Sort(arr, 0, arr.Length - 1);
        }

        private static void Sort(int[] arr, int left, int right)
        {
            if (left >= right) return;

            // Pivot aléatoire pour éviter le pire cas sur tableau trié
            int pivotIndex = Rand.Next(left, right + 1);
            Swap(arr, left, pivotIndex);

            int p = Partition(arr, left, right);

            Sort(arr, left, p - 1);
            Sort(arr, p + 1, right);
        }

        private static int Partition(int[] arr, int left, int right)
        {
            int pivot = arr[left];
            int i = left + 1;

            for (int j = left + 1; j <= right; j++)
            {
                if (arr[j] < pivot)
                {
                    Swap(arr, i, j);
                    i++;
                }
            }

            Swap(arr, left, i - 1);
            return i - 1;
        }

        private static void Swap(int[] arr, int i, int j)
        {
            if (i == j) return;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
