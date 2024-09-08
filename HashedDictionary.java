//---------------------------------------------------------------------------------------------------------------------------------
// Name: Petersen, Jake
// Description:
//      Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//--------------------------------------------------------------------------------------------------------------------------------- 

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

public class HashedDictionary<K, V> implements DictionaryInterface<K, V>
{


    // The dictionary:
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 2111; // Must be prime
    private int collisionCount;

    // The hash table:
    private Entry<K, V>[] hashTable;
    @SuppressWarnings("unchecked")
    private K[] keyArray = (K[])new Object[2111];
    private int tableSize; // Must be prime
    protected final Entry<K, V> AVAILABLE = new Entry<>(null, null);

    public HashedDictionary()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public HashedDictionary(int capacity)
    {
        numberOfEntries = 0; // Dictionary is empty
        // Set up hash table:
        // Initial size of hash table is same as initialCapacity if it is prime;
        // otherwise increase it until it is prime size
        tableSize = getNextPrime(capacity);
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        Entry<K, V>[] temp = (Entry<K, V>[])new Entry[tableSize];
        hashTable = temp;
    } // end constructor

    //--------------------------------------------------
    // Implementations of DictionaryInterface methods
    //--------------------------------------------------

    /** Adds a new entry to this dictionary. If the given search key already
	exists in the dictionary, replaces the corresponding value.
		@param key An object search key of the new entry.
		@param value An object associated with the search key.
		@return Either null if the new entry was added to the dictionary
			or the value that was associated with key if that value
			was replaced. */
	public V add(K key, V value)
    {
        V oldValue;
        K oldKey;

        if ((key == null) || (value == null))
            throw new RuntimeException("Value and or Key is null!");
        
        int index = getHashIndex(key);

        if (hashTable[index] == null)
        {
            hashTable[index] = new Entry(key, value);
            numberOfEntries++;
            oldValue = null;
        }
        else
        {
            oldValue = hashTable[index].getValue();
            oldKey = hashTable[index].getKey();

            if (hashTable[index] != AVAILABLE && !oldKey.equals(key))
                collisionCount++;

            hashTable[index].setValue(value);
        }

        return oldValue;
    }

	//Not implemented
	public V remove(K key)
    {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

	/** Retrieves from this dictionary the value associated with a given
	search key.
		@param key An object search key of the entry to be retrieved.
		@return Either the value that is associated with the search key
			or null if no such object exists. */
    public V getValue(K key)
    {
        V result = null;
        int index = getHashIndex(key);
        if ((hashTable[index] != null) && (hashTable[index] != AVAILABLE))
            result = hashTable[index].getValue(); // Key found; get value
        // Else key not found; return null
        return result;
    } // end getValue

	//Not implemented
	public boolean contains(K key)
    {
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

	/**Creates an iterator that traverses all search keys in this dictionary.
		@return An iterator that provides sequential access to the search keys in dictionary*/
	public Iterator<K> getKeyIterator()
    {
        return new KeyIterator();
    }

	//Not implemented
	public Iterator<V> getValueIterator()
    {
        return new ValueIterator();
    }

	//Not implemented
	public boolean isEmpty()
    {
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

	//Not implemented
	public int getSize()
    {
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

	//Not implemented
	public void clear()
    {
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    public int getCollisionCount()
    {
        return collisionCount;
    }

    //--------------------------------------------------
    // Implementations of private methods
    //--------------------------------------------------

    private int getNextPrime(int n)
    {
        // Base case 
        if (n <= 1) 
            return 2; 
     
        int prime = n; 
        boolean isPrime = false; 
     
        // Loop until found
        while (!isPrime) 
        { 
            prime++; 
     
            if (isPrime(prime)) 
                isPrime = true; 
        } 
     
        return prime; 
    }

    private boolean isPrime(int n)
    {
        // Corner cases 
        if (n <= 1) 
            return false; 

        if (n <= 3) 
            return true; 
        
        // check if even or divisible by 3
        if (n % 2 == 0 || n % 3 == 0) 
            return false; 
         
        for (int i = 5; i * i <= n; i = i + 6) 
        {
            if (n % i == 0 || n % (i + 2) == 0) 
                return false; 
        }
         
        return true; 
    }

    private int getHashIndex(K key)
    {
        int hashIndex = key.hashCode() % hashTable.length;
        if (hashIndex < 0)
            hashIndex = hashIndex + hashTable.length;

        return hashIndex;
    }//end getHashIndex


    protected final class Entry<K, V>
    {
        private K key;
        private V value;
        private Entry(K searchKey, V dataValue)
        {
            key = searchKey;
            value = dataValue;
        } // end constructor

        private K getKey()
        {
            return key;
        } // end getKey

        private V getValue()
        {
         return value;
        } // end getValue

        private void setValue(V dataValue)
        {
         value = dataValue;
        } // end setValue
    } // end Entry

    protected class KeyIterator implements Iterator<K>
    {
        private int currentIndex; // Current position in hash table
        private int numberLeft; // Number of entries left in iteration
        private KeyIterator()
        {
        currentIndex = 0;
        numberLeft = numberOfEntries;
        } // end default constructor
        public boolean hasNext()
        {
            return numberLeft > 0;
        } // end hasNext
        public void remove()
        {
        throw new UnsupportedOperationException();
        } // end remove
        public K next()
        {
            K result = null;
            if (hasNext())
            {
                // Skip table locations that do not contain a current entry
                while ( (hashTable[currentIndex] == null) || (hashTable[currentIndex] == AVAILABLE) )
                {
                 currentIndex++;
                } // end while
                result = hashTable[currentIndex].getKey();
                numberLeft--;
                currentIndex++;
            }
            else
              throw new NoSuchElementException();
            return result;
        } // end next
    } // end KeyIterator

    private class ValueIterator implements Iterator<V>
	{
		private int currentIndex; // Current position in hash table
        private int numberLeft; // Number of entries left in iteration
        private ValueIterator()
        {
        currentIndex = 0;
        numberLeft = numberOfEntries;
        } // end default constructor
        public boolean hasNext()
        {
            return numberLeft > 0;
        } // end hasNext
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
        public V next()
        {
            V result = null;
            if (hasNext())
            {
                // Skip table locations that do not contain a current entry
                while ( (hashTable[currentIndex] == null) || (hashTable[currentIndex] == AVAILABLE) )
                {
                 currentIndex++;
                } // end while
                result = hashTable[currentIndex].getValue();
                numberLeft--;
                currentIndex++;
            }
            else
              throw new NoSuchElementException();
            return result;
        } // end next
	} // end getValueIterator
} // end HashedDictionary
