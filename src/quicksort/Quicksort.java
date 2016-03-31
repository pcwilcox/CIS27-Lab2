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

        // Run the test on a given value for n
        testArrays(1000);
        testArrays(10000);
        testArrays(100000);
        testArrays(1000000);
    }

    // This method runs a process to empirically test
    // quicksort on different values of n and m
    public static void testArrays(int n)
    {
        // Data for testing runtime
        long partition;
        long pivot;
        long startTime;

        for (int i = 0; i <= 30; i++)
        {
            partition = 0;
            pivot = 0;

            // Get an unsorted list of size n
            double[] unsorted = getArray(n);

            // We'll test each optimization 10 times
            for (int j = 0; j < 10; j++)
            {
                // Create two arrays as a copy
                // of the original unsorted list
                double[] arr1 = new double[n];
                double[] arr2 = new double[n];
                for (int k = 0; k < unsorted.length; k++)
                {
                    arr1[k] = unsorted[k];
                    arr2[k] = unsorted[k];
                }

                // Use a stopwatch on nanoTime() to test
                // runtimes for both pivot methods
                startTime = System.nanoTime();
                sort(arr1, 0, n - 1, i, 1);
                partition = (partition + System.nanoTime()
                             - startTime) / (j + 1);

                startTime = System.nanoTime();
                sort(arr2, 0, n - 1, i, -1);
                pivot = (pivot + System.nanoTime()
                         - startTime) / (j + 1);
            }
            System.out.println(n + "," + i + "," +
                               partition + "," + pivot);
        }
    }

    // Returns an array of random doubles
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

    // Partitions the array based on first element
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

    // Recursive sort method with cutoff to insertion
    // option and option for pivot vs partition
    public static void sort(double[] a, int lo,
                            int hi, int cutoff, int pivot)
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

    // Puts the median of lo, hi, and
    // (hi+lo)/2 into the first index
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

    // Simple insertion method
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

    // Simple method used all over the place for sorting
    public static boolean less(double a, double b)
    {
        return a < b;
    }

    // Simple helper to exchange elements
    public static void exch(double[] a, int i, int j)
    {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

/* Program output:
Testing quicksort optimizations.
1000,0,5844,6412
... // trimmed output here
1000000,30,8102923,8322123


See spreadsheet for data. I get a lot of statistical
noise but a strong correlation between higher values
of m bringing lower runtimes. The 3-way pivot
selection doesn't seem to be an improvement - my
guess is, since the data set is already a selection
of random doubles, you're not likely to get really
bad samples out of it, and thus the improvement on
pivot selection isn't generally worth it (though
there are a few cases where a substantial
improvement is made).
 */