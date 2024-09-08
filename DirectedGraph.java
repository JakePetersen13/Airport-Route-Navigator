//---------------------------------------------------------------------------------------------------------------------------------
// Name: Petersen, Jake
// Description:
//      Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//--------------------------------------------------------------------------------------------------------------------------------- 

import java.util.*;

/** A class that implements the ADT directed graph. */
public class DirectedGraph<T> implements GraphInterface<T>
{
    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;
    public DirectedGraph()
    {
        vertices = new HashedDictionary<>();
        edgeCount = 0;
    } // end default constructor

    public boolean addVertex(T vertexLabel)
    {
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null; // Was addition to dictionary successful?
    } // end addVertex

    public boolean addEdge(T begin, T end)
    {
        return addEdge(begin, end, 0);
    } // end addEdge

    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ( (beginVertex != null) && (endVertex != null) )
            result = beginVertex.connect(endVertex, edgeWeight);
        if (result)
            edgeCount++;
        return result;
    } // end addEdge

    public boolean hasEdge(T begin, T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ( (beginVertex != null) && (endVertex != null) )
        {
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                found = true;
            } // end while
        } // end if
        return found;
    } // end hasEdge

    public boolean isEmpty()
    {
        return vertices.isEmpty();
    } // end isEmpty

    public void clear()
    {
        vertices.clear();
        edgeCount = 0;
    } // end clear

    public int getNumberOfVertices()
    {
        return vertices.getSize();
    } // end getNumberOfVertices

    public int getNumberOfEdges()
    {
        return edgeCount;
    } // end getNumberOfEdges

    protected void resetVertices()
    {
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } // end while
    } // end resetVertices

    public int getShortestPath(T begin, T end, StackInterface<T> path)
    {
        throw new RuntimeException("getShortestPath not implemented.");
    } // end getShortestPath

    public QueueInterface<T> getDepthFirstTraversal(T origin)
    {
        throw new RuntimeException("getDepthFirstTraversal not implemented.");
    }

    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        throw new RuntimeException("getBreadthFirstTraversal not implemented.");
    }

    public StackInterface<T> getTopologicalOrder()
    {
        throw new RuntimeException("getTopologicalOrder not implemented.");
    }

    public double getCheapestPath(T begin, T end, StackInterface<T> path)
    {
        resetVertices();
		boolean done = false;
        int counter = 0;

		// use EntryPQ instead of Vertex because multiple entries contain 
		// the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
		MinheapInterface<EntryPQ> priorityQueue = new PriorityQueue<EntryPQ>();
		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		priorityQueue.add(new EntryPQ(originVertex, 0, null));
	
		while (!done && !priorityQueue.isEmpty())
		{
            counter++;
			EntryPQ frontEntry = priorityQueue.removeMin();
			VertexInterface<T> frontVertex = frontEntry.getVertex();
			
			if (!frontVertex.isVisited())
			{
				frontVertex.visit();
				frontVertex.setCost(frontEntry.getCost());
				frontVertex.setPredecessor(frontEntry.getPredecessor());
				
				if (frontVertex.equals(endVertex))
					done = true;
				else 
				{
					Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
					Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
					while (neighbors.hasNext())
					{
						VertexInterface<T> nextNeighbor = neighbors.next();
						Double weightOfEdgeToNeighbor = edgeWeights.next();
						
						if (!nextNeighbor.isVisited())
						{
							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
						} // end if
					} // end while
				} // end if
			} // end if
		} // end while

		// traversal ends, construct cheapest path
		double pathCost = endVertex.getCost();
		path.push(endVertex.getLabel());
		
		VertexInterface<T> vertex = endVertex;
		while (vertex.hasPredecessor())
		{
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
		} // end while

        if (counter < 1000)
		    return pathCost;  
        else
            return -1.0;
    }

    private class EntryPQ implements Comparable<EntryPQ>
    {
		private VertexInterface<T> vertex; 	
		private VertexInterface<T> previousVertex; 
		private double cost; // cost to nextVertex
		
		private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex)
		{
			this.vertex = vertex;
			this.previousVertex = previousVertex;
			this.cost = cost;
		} // end constructor
		
		public VertexInterface<T> getVertex()
		{
			return vertex;
		} // end getVertex
		
		public VertexInterface<T> getPredecessor()
		{
			return previousVertex;
		} // end getPredecessor

		public double getCost()
		{
			return cost;
		} // end getCost
		
		public int compareTo(EntryPQ otherEntry)
		{
			// using opposite of reality since our priority queue uses a maxHeap;
			// could revise using a minheap
			return (int)Math.signum(otherEntry.cost - cost);
		} // end compareTo
		
		public String toString()
		{
			return vertex.toString() + " " + cost;
		} // end toString 
	} // end EntryPQ

    //GET CHEAPEST PATH

} // end DirectedGraph
