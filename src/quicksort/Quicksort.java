package quicksort;

import java.util.Random;

/**
 * Created by Pete on 3/19/2016.
 */
public class Quicksort
{
    public static void main(String[] args)
    {

        System.out.println("Testing quicksort optimizations.");

        testArrays(1000);
        testArrays(10000);
        testArrays(100000);
        testArrays(1000000);
    }

    public static void testArrays(int n)
    {
        for (int i = 0; i <= 30; i++)
        {
            System.out.println("Testing " + n + " integers, " + i + " cutoff to insertion sort.");
            System.out.println("First element pivot: ");
            long  startTime = System.currentTimeMillis();
            int[] arr1      = getArray(n);
            sort(arr1, 0, n - 1, i, -1);
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + elapsed);

            startTime = System.currentTimeMillis();
            System.out.println("Using getPivot(): ");
            int[] arr2 = getArray(n);
            sort(arr2, 0, n - 1, i, 1);
            elapsed = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + elapsed);
        }
    }

    public static int[] getArray(int n)
    {
        Random rand = new Random();
        int[]  arr  = new int[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = rand.nextInt(n);
        }

        return arr;
    }

    public static int partition(int[] a, int lo, int hi)
    {
        int i = lo;
        int j = hi + 1;
        while (true)
        {
            while (less(a[++i], a[lo]))
            {
                if (i == hi)
                {
                    break;
                }
            }
            while (less(a[lo], a[--j]))
            {
                if (j == lo)
                {
                    break;
                }
            }
            if (i >= j)
            {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(int[] a, int lo, int hi, int cutoff, int pivot)
    {
        if (lo >= hi)
        {
            return;
        }
        if (hi - lo > cutoff)
        {
            if (pivot < 0)
            {
                getPivot(a, lo, hi);
            }
            int j = partition(a, lo, hi);
            sort(a, lo, j - 1, cutoff, pivot);
            sort(a, j + 1, hi, cutoff, pivot);
        }
        else
        {
            insertionSort(a, lo, hi);
        }
    }

    public static void getPivot(int[] a, int lo, int hi)
    {
        int i = a[lo];
        int j = a[hi];
        int k = a[(hi + lo) / 2];
        if (i <= j)
        {
            if (j <= k)
            {
                exch(a, a[lo], a[hi]);
                return;
            }
            else
            {
                exch(a, a[lo], a[(hi + lo) / 2]);
                return;
            }
        }
        else
        {
            if (i <= k)
            {
                return;
            }
            else
            {
                exch(a, a[lo], a[(hi + lo) / 2]);
                return;
            }
        }
    }

    public static void insertionSort(int[] a, int lo, int hi)
    {
        for (int i = lo + 1; i < hi - lo; i++)
        {
            for (int j = i; j > lo && a[j] > a[j - 1]; j--)
            {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }

    public static boolean less(int a, int b)
    {
        return a < b;
    }

    public static void exch(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
