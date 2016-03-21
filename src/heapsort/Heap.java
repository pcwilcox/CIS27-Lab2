package heapsort;

import java.util.ArrayList;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class Heap
{
    private Integer[] heap;
    private int       size;

    public Heap()
    {
        size = 0;
    }

    private void sink(int n)
    {
        while (n * 3 <= size)
        {
            int i = 3 * n;
            if (i < size && less(i, i + 1));
        }
    }

/*    private void swim(int i) {
        while ()
    }

    public void insert(Integer i)
    {
        if (++size == heap.length)
        {
            resize();
        }
        heap[size] = i;
        swim(i);
    }
*/
    public Integer remove()
    {
        exch(1, size);
        size--;
        sink(1);
        return heap[size + 1];

    }

    private void exch(int a, int b)
    {
        Integer temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    private boolean less(int i, int j)
    {
        return heap[i] < heap[j];
    }

    private void resize()
    {
        Integer[] newHeap = new Integer[size * 2];
        for (int i = 1; i <= size; i++)
        {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
}
