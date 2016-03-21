package pq;

import java.util.ArrayList;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class MaxHeap<Item extends Comparable>
{
    private ArrayList<Item> heap;
    private int             size;

    private void sink(int i)
    {
        while (2 * i < size)
        {
            int j = 2 * i;
            if (j < size && less(j, j + 1))
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
        while (i > 1 && less(i / 2, i))
        {
            exch(i / 2, i);
            i /= 2;
        }
    }

    public MaxHeap()
    {
        heap = new ArrayList<>();
        size = 0;
    }

    public void insert(Item i)
    {
        heap.add(i);
        swim(size);
        size++;
    }

    public Item remove()
    {
        exch(1, size--);
        Item temp = heap.remove(size);
        sink(1);
        return temp;
    }

    private boolean less(int a, int b)
    {
        return heap.get(a - 1).compareTo(heap.get(b - 1)) < 0;
    }

    private void exch(int a, int b)
    {
        Item temp = heap.get(a - 1);
        heap.set(a - 1, heap.get(b - 1));
        heap.set(b - 1, temp);
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

        for (Item i : heap)
        {
            output = output + i + " ";
        }
        return output;
    }
}
