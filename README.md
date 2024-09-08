The final project for Thanh Nguyen's CS2400 Data Structures and Advanced Programming course, this program functions as an app that holds airports and their discriptions utilizing a hashed dictionary,
and an airports connections and distances to other airports utilizing a weighted graph.

airports.csv holds the airport names and their descriptions, distances.csv holds the connections and their distances.

The files are both read, and airports.csv is stored inside of a hashed dictionary, and the names of the airports in distances.csv are stored as Verticies and the Edges are the distances between the two listed airports.

To see the description of an airport, the requested airport's three letter abbrevaition would be taken as its hash code, which would be the index of that value should it exist in the dictionary. If its found,
it will return the description of the airport.

To find the cheapest path between two airports, a minheap priority queue was utilized to hold verticies of said cheapest path and output them once confirmed.
