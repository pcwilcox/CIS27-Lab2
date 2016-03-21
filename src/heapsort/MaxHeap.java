package heapsort;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class MaxHeap
{
    private int[] heap;
    private int   size;

    private void sink(int i, int n)
    {
        while (3 * i - 1 < n)
        {
            int j = 3 * i - 1;
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

    public void insert(int i)
    {
        if (++size == heap.length)
        {
            resize();
        }

        heap[size] = i;
        swim(size);

    }

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

    private boolean less(int a, int b)
    {
        return heap[a] < heap[b];
    }

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

    public String toString()
    {
        String output = "";

        for (int i = 1; i <= size; i++)
        {
            output = output + heap[i] + " ";
        }
        return output;
    }

    private void resize()
    {
        int[] newHeap = new int[2 * size];
        for (int i = 1; i <= size && i < heap.length && i < newHeap.length; i++)
        {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

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
