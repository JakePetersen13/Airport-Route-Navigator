//---------------------------------------------------------------------------------------------------------------------------------
// Name: Petersen, Jake
// Description:
//      Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//--------------------------------------------------------------------------------------------------------------------------------- 

/** An interface for the ADT maxheap. */
public interface MinheapInterface<T extends Comparable<? super T>> extends QueueInterface<T>
{
    /** Adds a new entry to this heap.
    @param newEntry An object to be added. */
    public void add(T newEntry);

    /** Removes and returns the largest item in this heap.
    @return Either the largest object in the heap or,
    if the heap is empty before the operation, null. */
    public T removeMin();

    /** Retrieves the largest item in this heap.
    @return Either the largest object in the heap or,
    if the heap is empty, null. */
    public T getMin();

    /** Detects whether this heap is empty.
    @return True if the heap is empty, or false otherwise. */
    public boolean isEmpty();

    /** Gets the size of this heap.
    @return The number of entries currently in the heap. */
    public int getSize();

    /** Removes all entries from this heap. */
    public void clear();

} // end MinheapInterface
