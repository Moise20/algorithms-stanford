using System;
using System.Numerics;
using DivideAndConquer;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Choose algorithm:");
        Console.WriteLine("1 - Karatsuba multiplication");
        Console.WriteLine("2 - Merge Sort");
        Console.Write("Your choice: ");
        var choice = Console.ReadLine();

        switch (choice)
        {
            case "1":
                RunKaratsuba();
                break;

            case "2":
                RunMergeSort();
                break;

            default:
                Console.WriteLine("Unknown choice");
                break;
        }
    }

    static void RunKaratsuba()
    {
        BigInteger x = BigInteger.Parse("1234");
        BigInteger y = BigInteger.Parse("5678");

        var result = Karatsuba.KaratsubaMul(x, y);

        Console.WriteLine($"Result = {result}");
    }

    static void RunMergeSort()
    {
        int[] arr = {8,3,7,4,2,6,5,1};
        int[] sorted = MergeSort.MergeSortAlgo(arr);

        Console.WriteLine(string.Join(", ", sorted));
    }
}
