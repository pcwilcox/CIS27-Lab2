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
            double[] arr1      = getArray(n);
            long     startTime = System.nanoTime();
            sort(arr1, 0, n - 1, i, -1);
            long elapsed = System.nanoTime() - startTime;
            System.out.println("Elapsed time: " + elapsed);
            startTime = System.nanoTime();
            System.out.println("Using getPivot(): ");
            double[] arr2 = getArray(n);
            startTime = System.nanoTime();
            sort(arr2, 0, n - 1, i, 1);
            elapsed = System.nanoTime() - startTime;
            System.out.println("Elapsed time: " + elapsed);
        }
    }

    public static double[] getArray(int n)
    {
        Random   rand = new Random();
        double[] arr  = new double[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = rand.nextDouble();
        }

        return arr;
    }

    public static int partition(double[] a, int lo, int hi)
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

    public static void sort(double[] a, int lo, int hi, int cutoff, int pivot)
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

    public static void getPivot(double[] a, int lo, int hi)
    {
        if (a[lo] <= a[hi])
        {
            if (a[hi] <= a[(hi + lo) / 2])
            {
                exch(a, lo, hi);
                return;
            }
            else
            {
                exch(a, lo, (hi + lo) / 2);
                return;
            }
        }
        else
        {
            if (a[lo] <= a[(hi + lo) / 2])
            {
                return;
            }
            else
            {
                exch(a, lo, (hi + lo) / 2);
                return;
            }
        }
    }

    public static void insertionSort(double[] a, int lo, int hi)
    {
        for (int i = lo + 1; i < hi - lo; i++)
        {
            for (int j = i; j > lo && a[j] > a[j - 1]; j--)
            {
                exch(a, j, j - 1);
            }
        }
    }

    public static boolean less(double a, double b)
    {
        return a < b;
    }

    public static void exch(double[] a, int i, int j)
    {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
