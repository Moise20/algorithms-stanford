package divideandconquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 Sur le sous-tableau arr[l..r], on regarde :

first = arr[l]

middle = arr[mid]

last = arr[r]

On choisit la médiane de ces 3 valeurs (celle entre les deux autres).

Avant d’appeler partition(arr, l, r) (pivot = premier élément), on swap l’élément médian choisi avec arr[l].

On compte les comparaisons uniquement via m-1 par appel récursif.
 */
public class QuickSortMedianOfThreeCount {

    public static long quickSortAndCount(int[] arr) {
        return quickSortAndCount(arr, 0, arr.length - 1);
    }

    private static long quickSortAndCount(int[] arr, int l, int r) {
        if (l >= r) return 0;

        int m = r - l + 1;
        long comparisons = m - 1;

       
        int pivotIndex = medianOfThreeIndex(arr, l, r);
        swap(arr, l, pivotIndex); 

        int pivotFinalIndex = partition(arr, l, r);

        comparisons += quickSortAndCount(arr, l, pivotFinalIndex - 1);
        comparisons += quickSortAndCount(arr, pivotFinalIndex + 1, r);

        return comparisons;
    }

    private static int medianOfThreeIndex(int[] arr, int l, int r) {
        int m = r - l + 1;
        int mid = l + (m - 1) / 2; 

        int a = arr[l];
        int b = arr[mid];
        int c = arr[r];

       
        if ((a <= b && b <= c) || (c <= b && b <= a)) return mid;
        if ((b <= a && a <= c) || (c <= a && a <= b)) return l;
        return r;
    }

    
    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = l + 1;

        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, l, i - 1);
        return i - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] readNumbers(String filePath) throws IOException {
        ArrayList<Integer> list = new ArrayList<>(10000);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) list.add(Integer.parseInt(line));
            }
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
        return arr;
    }

    public static void main(String[] args) throws Exception {
       /* into java
       javac -d out src/divideandconquer/QuickSortMedianOfThreeCount.java
java -cp out divideandconquer.QuickSortMedianOfThreeCount QuickSort.txt
       */
        String filePath = (args.length > 0) ? args[0] : "QuickSort.txt";

        int[] arr = readNumbers(filePath);
        long comparisons = quickSortAndCount(arr);

        System.out.println("Total comparisons (pivot = median-of-three): " + comparisons);
    }
}
