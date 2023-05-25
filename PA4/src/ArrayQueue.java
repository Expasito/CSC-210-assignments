
/**
 * ArrayQueue implements a Queue by using a DynamicArray as the base
 *
 *
 */
public class ArrayQueue implements QueueInterface{
	private DynamicArray list;
	
	/**
	 * Default constructor
	 */
	ArrayQueue(){
		this.list=new DynamicArray();
	}
	
	/**
	 * Copy Constructor
	 * 
	 * @param aq The ArrayQueue object to copy
	 */
	ArrayQueue(ArrayQueue aq){
		this.list = new DynamicArray();
		for(int i=0;i<aq.size();i++) {
			this.enqueue(aq.list.get(i));
		}
		
	}

	/**
	 * 
	 * add an integer the the back of the queue
	 * 
	 * @param value the value to enqueue
	 * 
	 * Complexity: O(1)
	 * 
	 */
	@Override
	public void enqueue(int value) {
		this.list.add(value);
		
	}

	/**
	 * 
	 * remove an integer from the front of the queue
	 * 
	 * @return the value that was dequeued
	 * 
	 * Complexity: O(n)
	 */
	@Override
	public int dequeue() {
		return this.list.remove(0);
	}

	/**
	 * 
	 * peeks at the first item in the queue
	 * 
	 * @return the value that was peeked
	 * Complexity: O(1)
	 * 
	 */
	@Override
	public int peek() {
		if(this.size()==0) {
			return -1;
		}
		return this.list.get(0);
	}

	/**
	 * 
	 * checks if the queue is empty
	 * 
	 * @return if the queue is empty
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public boolean isEmpty() {
		if(this.size()==0) {
			return true;
		}
		return false;
	}

	/**
	 * clears the queue of all items
	 * 
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public void clear() {
		this.list.clear();
		
	}

	/**
	 * 
	 * returns the size of the queue
	 * 
	 * @return the size of the queue
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public int size() {
		return this.list.size();
	}
	
	@Override
	public String toString(){
		return this.list.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==this) {
			return true;
		}
		if(o==null) {
			return false;
		}
		if(o instanceof QueueInterface) {
			DynamicArray da = new DynamicArray((QueueInterface)o);
			return da.equals(this.list);
			
			
			
		}
		return false;
		
	}

}
