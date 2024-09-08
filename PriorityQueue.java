//---------------------------------------------------------------------------------------------------------------------------------
// Name: Petersen, Jake
// Description:
//      Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//--------------------------------------------------------------------------------------------------------------------------------- 

public class PriorityQueue<T extends Comparable<? super T>> implements MinheapInterface<T>
{
    private T[] heap; // Array of heap entries; ignore heap[0]
    private int lastIndex; // Index of last entry and number of entries
    private static final int DEFAULT_CAPACITY = 25;

    public PriorityQueue(int initialCapacity)
    {
        initialCapacity = DEFAULT_CAPACITY;
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
    } // end constructor

    public PriorityQueue()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public T getMin() //getFront()
    {
        T root = null;
        if (!isEmpty())
        root = heap[1];
        return root;
    } // end getMax

    public boolean isEmpty()
    {
        return lastIndex < 1;
    } // end isEmpty

    public int getSize()
    {
        return lastIndex;
    } // end getSize

    public void add(T newEntry) //enqueue()
    {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
        
    } // end add

    public T removeMin()
    {
        T root = null;
        if (!isEmpty())
        {
            root = heap[1]; // Return value
            heap[1] = heap[lastIndex]; // Form a semiheap
            lastIndex--; // Decrease size
            reheap(1); // Transform to a heap
        } // end if
        return root;
    } // end removeMax

    public void clear()
    {
        
        while (lastIndex > -1)
        {
        heap[lastIndex] = null;
        lastIndex--;
        } // end while
        lastIndex = 0;
    } // end clear

    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex) )
        {
            int smallerChildIndex = leftChildIndex; // Assume smaller
            int rightChildIndex = leftChildIndex + 1;
            if ( (rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[smallerChildIndex]) < 0)
            {
                smallerChildIndex = rightChildIndex;
            } // end if
            if (orphan.compareTo(heap[smallerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[smallerChildIndex];
                rootIndex = smallerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } // end while
        heap[rootIndex] = orphan;
    } // end reheap

    //same as add
    public void enqueue(T newEntry)
    {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) < 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
    } //end enqueue

    //same as removeMin
    public T dequeue()
    {
         T root = null;
        if (!isEmpty())
        {
            root = heap[1]; // Return value
            heap[1] = heap[lastIndex]; // Form a semiheap
            lastIndex--; // Decrease size
            reheap(1); // Transform to a heap
        } // end if
        return root;
    } //end dequeue

    //same as getMin
    public T getFront()
    {
        T root = null;
        if (!isEmpty())
        root = heap[1];
        return root;
    }
}
