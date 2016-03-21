package pq;

import java.util.ArrayList;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class MinHeap
{
    private int[] heap;
    private int   size;

    private void sink(int i)
    {
        while (2 * i < size)
        {
            int j = 2 * i;
            if (j < size && less(j + 1, j))
            {
                j++;
            }
            if (!less(j, i))
            {
                break;
            }
            exch(i, j);
            i = j;
        }
    }

    private void swim(int i)
    {
        while (i > 1 && less(i, i / 2))
        {
            exch(i / 2, i);
            i /= 2;
        }
    }

    public MinHeap()
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
        sink(1);
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
}
