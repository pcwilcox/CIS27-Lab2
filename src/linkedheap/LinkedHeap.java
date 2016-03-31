package linkedheap;

/**
 * Created by Pete Wilcox on 3/21/2016.
 * petercwilcox@gmail.com
 */
public class LinkedHeap<Item>
{
    private Node head;
    private Node tail;
    private int  size;

    private class Node
            implements Comparable<Node>
    {
        Node root;
        Node left;
        Node right;
        Item item;
        int  size;

        // I wasn't sure how to go about displaying the finalized heap
        public String toString()
        {
            if (left == null)
            {
                return this.toStringHelper();
            }
            else if (right == null)
            {
                return this.toStringHelper() + ", " + left.toString();
            }
            else
            {
                return this.toStringHelper() + ", " + left.toString() + ", " + right.toString();
            }
        }

        // So I made this helper to just display the info of each node
        private String toStringHelper() {
            String output, root;
            if (this.root == null) {
                root = "null";
            } else {
                root = this.root.item.toString();
            }

            output = "[Item: " + this.item.toString() + ", Size: " + this.size + ", Root: " + root + "]";
            return output;
        }

        public int compareTo(Node other)
        {
            if (other == null)
            {
                throw new NullPointerException();
            }
            else if (other.item instanceof Comparable)
            {
                Comparable a = (Comparable) item;
                Comparable b = (Comparable) other.item;
                return a.compareTo(b);
            }
            else
            {
                throw new ClassCastException();
            }
        }
    }

    public LinkedHeap()
    {
        size = 0;
    }

    // Insert an item.
    public void insert(Item i)
    {
        if (head == null)
        {
            // If the list is empty we save some time
            head = new Node();
            head.size = 1;
            tail = head;
        }
        else
        {
            // Otherwise we need to create a new tail
            tail = newTail(head);
        }

        // Then insert the key into the tail
        tail.item = i;

        // And swim it into position
        swim(tail);
        size++;
    }

    // Remove the root item
    public Item remove()
    {
        // Swap the
        Node temp = head;
        exch(head, tail);
        shrink(tail);
        if (temp.root.right == temp)
        {
            temp.root.right = null;
        }
        else
        {
            temp.root.left = null;
        }

        tail = findTail(head);
        sink(head);
        size--;
        return temp.item;
    }

    private void sink(Node n)
    {
        while (n.left != null && n.right != null)
        {
            Node next = greatest(n.left, n.right);
            if (less(n, next))
            {
                exch(n, next);
            }
            else
            {
                break;
            }
        }
        if (less(n, n.left))
        {
            exch(n, n.left);
        }
    }

    private void swim(Node n)
    {
        while (n.root != null && less(n.root, n))
        {
            exch(n, n.root);
        }
    }

    private void shrink(Node n)
    {
        n.size--;
        if (n.root != null)
        {
            shrink(n.root);
        }
    }

    private void grow(Node n)
    {
        n.size++;
        if (n.root != null)
        {
            grow(n.root);
        }
    }

    // Creates a new node at the end of the list and returns it
    private Node newTail(Node n)
    {
        if (n.left != null && n.right != null)
        {
            // We aren't at the end of the list yet
            return newTail(smaller(n));
        }

        // We're at the end of the list, so create a new node
        Node newTail = new Node();
        newTail.root = n;
        newTail.size = 0;
        grow(newTail);

        // Assign it to the correct branch of n
        if (n.left == null)
        {
            n.left = newTail;
        }
        else
        {
            n.right = newTail;
        }

        return newTail;
    }

    // Returns the most-recently inserted node
    private Node findTail(Node n)
    {
        if (n.left == null)
        {
            return n;
        }
        else if (n.right == null)
        {
            return n.left;
        }
        else
        {
            return findTail(smaller(n));
        }
    }

    private Node smaller(Node n)
    {
        if (n.right == null)
        {
            return n.left;
        }
        if (n.right.size < n.left.size)
        {
            return n.right;
        }
        return n.left;
    }

    private Node greatest(Node n, Node m)
    {
        if (less(n, m))
        {
            return m;
        }
        return n;
    }

    private void exch(Node n, Node m)
    {
        Node temp = new Node();
        temp.item = n.item;
        n.item = m.item;
        m.item = temp.item;
    }

    private boolean less(Node n, Node m)
    {
        return n.compareTo(m) < 1;
    }

    public String toString()
    {
        return head.toString();
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }
}
