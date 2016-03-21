package heapsort;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class MaxHeap
{
    // Max-value heap set up with 3 child branches per leaf
    private int[] heap;
    private int   size;

    // Sink an element to its proper place in the heap
    private void sink(int i, int n)
    {
        while (3 * i - 1 < n)
        {
            int j = 3 * i - 1;

            // These two blocks set j = the largest child branch
            if (j + 2 < n)
            {
                j = greatest(j, j + 1, j + 2);
            }
            else if (j + 1 < n && less(j, j + 1))
            {
                j++;
            }

            if (!less(i, j))
            {
                break;
            }
            exch(i, j);
            i = j;
        }
    }

    // Swim an element upwards to its correct place in the heap
    private void swim(int i)
    {
        while (i > 1 && less((i + 1) / 3, i))
        {
            exch((i + 1) / 3, i);
            i = (i + 1) / 3;
        }
    }

    public MaxHeap()
    {
        heap = new int[1];
        size = 0;
    }

    // Adds an element to the end of the array, then swims it up. Resize() if necessary
    public void insert(int i)
    {
        if (++size == heap.length)
        {
            resize();
        }

        heap[size] = i;
        swim(size);

    }

    // Swaps the first and last elements, removes the last one, then sinks the first one
    public int remove()
    {
        exch(1, size);
        int temp = heap[size--];
        sink(1, size);
        if (size <= heap.length / 2)
        {
            resize();
        }
        return temp;
    }

    // Compares two elements in the heap
    private boolean less(int a, int b)
    {
        return heap[a] < heap[b];
    }

    // Returns the largest of the three elements in the heap - helps the sink() function a lot
    private int greatest(int a, int b, int c)
    {
        if (heap[a] >= heap[b] && heap[a] >= heap[c])
        {
            return a;
        }
        if (heap[b] >= heap[a] && heap[b] >= heap[c])
        {
            return b;
        }
        return c;
    }

    // Swap two elements in the heap
    private void exch(int a, int b)
    {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    // Simple helper to concatenate every element into a string
    public String toString()
    {
        String output = "";

        for (int i = 1; i <= size; i++)
        {
            output = output + heap[i] + " ";
        }
        return output;
    }

    // Helper function to resize the array as necessary
    private void resize()
    {
        int[] newHeap = new int[2 * size];
        for (int i = 1; i <= size && i < heap.length && i < newHeap.length; i++)
        {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    // Sort it!
    public void heapSort()
    {
        int n = size;
        while (n > 1)
        {
            exch(1, n);
            sink(1, n--);
        }
    }
}
