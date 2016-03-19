package quicksort;

/**
 * Created by Pete on 3/19/2016.
 */
public class Quicksort
{
    public static void main(String[] args)
    {
        int arr[] = {10, 15, 138, 47, 1847, 387, 147, 1745, 174, 5857, 578, 98, 9, 11, 6};
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(" " + arr[i]);
        }
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(" " + arr[i]);
        }
    }

    public static int partition(int[] a, int lo, int hi)
    {
        int i = lo + 1;
        int j = hi + 1;
        while (true)
        {
            while (less(a[i++], a[lo]))
            {
                if (i == hi) break;
            }
            while (less(a[lo], a[j--]))
            {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        return i;
    }

    public static void sort(int[] a, int lo, int hi)
    {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
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
