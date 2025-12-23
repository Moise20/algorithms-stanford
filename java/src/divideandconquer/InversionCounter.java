package divideandconquer;

import java.io.*;
import java.util.*;

public class InversionCounter {

    // Fonction principale : retourne le nombre d'inversions
    public static long countInversions(int[] arr) {
        int[] temp = new int[arr.length];
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    // Tri fusion + comptage sur arr[left..right]
    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;
        long count = 0;

        count += mergeSortAndCount(arr, temp, left, mid);       // inversions à gauche
        count += mergeSortAndCount(arr, temp, mid + 1, right);  // inversions à droite
        count += mergeAndCount(arr, temp, left, mid, right);    // inversions croisées

        return count;
    }

    // Fusion de deux parties triées + comptage des inversions croisées
    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;      // pointeur sur la moitié gauche
        int j = mid + 1;   // pointeur sur la moitié droite
        int k = left;      // position dans temp
        long inversions = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // arr[i] > arr[j] => arr[j] est plus petit que tous les arr[i..mid]
                inversions += (mid - i + 1);
            }
        }

        // copier le reste
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // recopier temp -> arr
        for (int p = left; p <= right; p++) arr[p] = temp[p];

        return inversions;
    }

    // Lecture fichier : 1 entier par ligne
    public static int[] readFile(String path) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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
        if (args.length != 1) {
            System.out.println("Usage: java divideandconquer.InversionCounter <path_to_file>");
            return;
        }

        int[] arr = readFile(args[0]);
        long inv = countInversions(arr);
        System.out.println("Inversions: " + inv);
    }
}

/*cd java
javac -d out src/divideandconquer/InversionCounter.java
java -cp out divideandconquer.InversionCounter 

ou mettre le fichier fichier.txt contenent les chiffres a inverser 
executer depuis le dossier java:
java -cp out divideandconquer.InversionCounter C:\ALL_MOSES_DATA_ON_ASUS\algorithms-stanford\java\fichier.txt

 */