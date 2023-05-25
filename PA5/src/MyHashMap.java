import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


/**
 * 
 * MyHashMap implements a basic generic-typed HashMap
 *
 * @param <K> The data type of the Key
 * @param <V> The data type of the Value
 */
public class MyHashMap<K, V> {
	private final static int TABLE_SIZE = 8;
	private ArrayList<LinkedList<Node<K, V>>> hashTable = new ArrayList<>(TABLE_SIZE);

	/**
	 * This is the Node class for the LinkedList which contains the
	 * key and value
	 *
	 * @param <K> The data type of the Key
	 * @param <V> The data type of the Value
	 */
	private class Node<K, V> {
		K key;
		V value;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
	}

	/**
	 * 
	 * Basic constructor
	 * 
	 */
	public MyHashMap() {
		for (int i = 0; i < TABLE_SIZE; i++)
			hashTable.add(new LinkedList<Node<K, V>>());
	}

	
	/**
	 * Hashing method for getting the index for which LinkedList to
	 * add to
	 * 
	 * @param key The Key to hash
	 * @return An integer 'representation' for the key
	 */
	private int h(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % TABLE_SIZE;
		return Math.abs(index);
	}

	/**
	 * Put puts a key and value into the hashTable or updates the current
	 * key's value if the key already exists
	 * @param key The key to add or update
	 * @param value The value
	 * @return The old value if present or null
	 */
	public V put(K key, V value) {
		V out=null;
		if(key==null) {
			return null;
		}
		int index = h(key);
		// if the key already exists, update instead
		if(containsKey(key)) {
			LinkedList<Node<K,V>> ll = hashTable.get(index);
			// iterate through the linkedlist
			for(Node<K,V> n: ll) {
				if(n.key.equals(key)) {
					out=n.value;
					n.value=value;
					return out;
				}
			}
		}
		// else create new node
		Node<K, V> node = new Node<>(key, value);
		hashTable.get(index).addFirst(node);
		return out;
	}
	
	/**
	 * clears the hashTable of all key value pairs
	 */
	public void clear() {
		for(LinkedList<Node<K,V>> ll : hashTable) {
			ll.clear();
		}
	}
	
	/**
	 * containsKey returns true if the Key is in the hashTable already
	 * @param key The key to check
	 * @return if the key already exists
	 */
	public boolean containsKey(K key) {
		// make sure the key exists
		if(key==null) {
			return false;
		}
		int index = h(key);
		// only look at one linkedlist due to hashing
		for(Node<K,V> n:hashTable.get(index)) {
			if(n.key.equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * containsValue checks if the value exists in the hashTable
	 * @param val The value to check
	 * @return true if the key exists
	 */
	public boolean containsValue(V val) {
		if(val==null) {
			return false;
		}
		// iterate through all linkedlists to check
		for(LinkedList<Node<K,V>> ll : hashTable) {
			for(Node<K,V> n: ll) {
				if(n.value.equals(val)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * remove removes a key from the hashTable
	 * @param key The key to remove
	 * @return The value of that key
	 */
	public V remove(K key) {
		int index= h(key);
		LinkedList<Node<K,V>> ll = hashTable.get(index);
		// get the node to remove
		Node <K,V> rem=null;
		for(Node<K,V> n : ll) {
			if(n.key==key) {
				rem=n;
			}
		}
		// remove the node
		ll.remove(rem);
		if(rem==null) {
			return null;
		}
		return rem.value;
	}
	
	/**
	 *  returns the value of the key
	 * @param key The key to get the value from
	 * @return The value if it exists or null
	 */
	public V get(K key) {
		int index = h(key);
		// just iterate until find or return null
		for(Node<K,V> n:hashTable.get(index)) {
			if(n.key==key) {
				return n.value;
			}
		}
		return null;
	}
	
	/**
	 * returns true if the hashTable is empty
	 * @return true if the table is empty
	 */
	public boolean isEmpty() {
		for(LinkedList<Node<K,V>> ll : hashTable) {
			if(ll.isEmpty()==false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * returns the number of keys in the hashtable
	 * @return the number of keys
	 */
	public int size() {
		int counter=0;
		for(LinkedList<Node<K,V>> ll : hashTable) {
			counter+=ll.size();
		}
		return counter;
	}
	
	/**
	 * prints the table's keys and the number of conflicts to the
	 * console
	 */
	public void printTable() {
		int total_conflicts = 0;
		for (int i = 0; i < TABLE_SIZE; i++) {
			var list = hashTable.get(i);
			// if conflicts is greater than 2, subract one else return 0 
			// because only 1 or 0 elements
			int conflicts = list.size()>=2 ? list.size()-1:0;
			System.out.print("Index "+i +": ("+ conflicts + " conflicts), [");
			total_conflicts+=conflicts;
			// print all of the node keys
			for (Node<K, V> node : list)
				System.out.print(node.key + ", ");
			System.out.print("]");
			System.out.println();
		}
		// and the number of conflicts
		System.out.println("Total # of conflicts: "+total_conflicts);
	}
	
	/**
	 * returns all of the keys as a Set interface reference
	 * @return
	 */
	public Set<K> keySet(){
		Set<K> out = new HashSet<>();
		for(LinkedList<Node<K,V>> ll : hashTable) {
			for(Node<K,V> n: ll) {
				out.add(n.key);
			}
		}
		return out;
		
	}
}