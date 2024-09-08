//---------------------------------------------------------------------------------------------------------------------------------
// Name: Petersen, Jake
// Project: 5
// Due: 12/8/2023
// Course: cs-2400-02-f23
//
// Description:
//      Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//--------------------------------------------------------------------------------------------------------------------------------- 

/** An interface for the ADT stack. */
public interface StackInterface<T>
{
    /** Adds a new entry to the top of this stack.
    @param newEntry An object to be added to the stack. */
    public void push(T newEntry);
    
    /** Removes and returns this stack's top entry.
    @return The object at the top of the stack.
    @throws EmptyStackException if the stack is empty before the operation. */
    public T pop();
    
    /** Retrieves this stack's top entry.
    @return The object at the top of the stack.
    @throws EmptyStackException if the stack is empty. */
    public T peek();
    
    /** Detects whether this stack is empty.
    @return True if the stack is empty. */
    public boolean isEmpty();

    /** Removes all entries from this stack. */
    public void clear();
    
} // end StackInterface
