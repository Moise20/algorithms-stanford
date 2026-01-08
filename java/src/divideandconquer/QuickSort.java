package divideandconquer;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private static final Random RAND = new Random();

    // Tri en place : modifie le tableau directement
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        // Pivot aléatoire (meilleure pratique pour éviter le pire cas sur tableau trié)
        int pivotIndex = left + RAND.nextInt(right - left + 1);
        swap(arr, left, pivotIndex); // on met le pivot au début (à left)

        int p = partition(arr, left, right);

        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    // Partition de Lomuto (pivot au début). Retourne la position finale du pivot.
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left + 1;

        for (int j = left + 1; j <= right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        // placer le pivot à sa bonne place
        swap(arr, left, i - 1);
        return i - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Test 
    public static void main(String[] args) {
        int[] arr = {8, 3, 7, 4, 2, 6, 5, 1};
        System.out.println("Avant: " + Arrays.toString(arr));
        quickSort(arr);
        System.out.println("Après: " + Arrays.toString(arr));
    }
}
