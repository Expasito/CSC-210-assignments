
/**
 * ListQueue implements a Queue data structure using a LinkedList
 *
 */
public class ListQueue implements QueueInterface {
	private LinkedList list;
	
	/** 
	 * default constructor
	 */
	ListQueue() {
		this.list=new LinkedList();
	}
	
	/**
	 * copy constructor
	 * 
	 * @param lq The ListQueue object to copy
	 */
	ListQueue(ListQueue lq) {
		this.list = new LinkedList(lq.list);
	}

	/**
	 * 
	 * enqueues an integer onto the queue
	 * 
	 * @param value The value to be added on the queue
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public void enqueue(int value) {
		this.list.addLast(value);
		
	}

	/**
	 * dequeues an integer off the stack
	 * 
	 * @return the value value that was dequeued
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public int dequeue() {
		return this.list.removeFirst();
		
	}

	/**
	 * 
	 * peeks at the first item on the queue
	 * 
	 * @return the value that was peeked
	 * 
	 * Complexity: O(1)
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
	 * 
	 * clears the queue of all elements
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public void clear() {
		this.list.clear();
		
	}

	/**
	 * returns the size of the stack
	 * 
	 * @return the size of the stack
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
			LinkedList ll = new LinkedList((QueueInterface)o);
			return ll.equals(this.list);
			
			
			
		}
		return false;
		
	}

}
