//----------------------------------------------------------------------------------
//  Name:   Petersen, Jake
//  Project: 5
//  Due:    12/8/2023
//  Course: CS-2400-02-F23
//
//  Description: Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//----------------------------------------------------------------------------------

import java.util.EmptyStackException;

/** A class of stacks whose entries are stored in a chain of nodes. */
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain
	public LinkedStack()
	{
		topNode = null;
	} // end default constructor

	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	} // end push
		
	public T peek()
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
			return topNode.getData();
	} // end peek

	public T pop()
	{
		T top = peek(); // Might throw EmptyStackException
		// Assertion: topNode != null
		topNode = topNode.getNextNode();
		return top;
	} // end pop

	public boolean isEmpty()
	{
		return topNode == null;
	} // end isEmpty

	public void clear()
	{
		topNode = null;
	} // end clear

	private class Node 
	{
		private T data; //entry in bag
		private Node next; //link to next node
	
		private Node (T dataPortion)
		{
			this(dataPortion, null);
		}
	
		private Node (T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		} //end constructor

        public Node getNextNode()
        {
            return next;
        }

        public T getData()
        {
            return data;
        }
	} // end Node
} // end LinkedStack