//---------------------------------------------------------------------------------------------------------------------------------
// Name: Petersen, Jake
// Description:
//      Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//--------------------------------------------------------------------------------------------------------------------------------- 

import java.util.Iterator;

/** An interface for a dictionary with distinct search keys.
Search keys and associated values are not null. */
public interface DictionaryInterface<K, V>
{

	/** Adds a new entry to this dictionary. If the given search key already
	exists in the dictionary, replaces the corresponding value.
		@param key An object search key of the new entry.
		@param value An object associated with the search key.
		@return Either null if the new entry was added to the dictionary
			or the value that was associated with key if that value
			was replaced. */
	public V add(K key, V value);

	//Not implemented
	public V remove(K key);

	/** Retrieves from this dictionary the value associated with a given
	search key.
		@param key An object search key of the entry to be retrieved.
		@return Either the value that is associated with the search key
			or null if no such object exists. */
	public V getValue(K key);

	//Not implemented
	public boolean contains(K key);

	/**Creates an iterator that traverses all search keys in this dictionary.
		@return An iterator that provides sequential access to the search keys in dictionary*/
	public Iterator<K> getKeyIterator();

	//Not implemented
	public Iterator<V> getValueIterator();

	//Not implemented
	public boolean isEmpty();

	//Not implemented
	public int getSize();

	//Not implemented
	public void clear();

} // end DictionaryInterface
