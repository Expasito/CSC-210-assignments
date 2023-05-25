/**
 * ListStack represents a Stack data structure using a LinkedList
 *
 */
public class ListStack implements StackInterface {
	private LinkedList list;
	
	
	
	/**
	 * default constructor
	 */
	ListStack(){
		this.list = new LinkedList();
		this.list.reverse=true;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param ls The ListStack to copy
	 */
	ListStack(ListStack ls){
		this.list = new LinkedList(ls.list);
		this.list.reverse=true;
		
	}

	/**
	 * pushes an integer onto the Stack
	 * 
	 * @param value The value to put on the stack
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public void push(int value) {
		this.list.addFirst(value);
		
	}

	
	/**
	 * pops an integer off the stack
	 * 
	 * @return the value of the Node that was popped
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public int pop() {
		return this.list.removeFirst();
	}

	/**
	 * 
	 * peeks at the first item in the stack
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
	 * returns if the stack is empty
	 * 
	 * @return if the stack is empty
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
	 * clears the stack of all items
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public void clear() {
		this.list.clear();
		
	}

	/**
	 * 
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
	public String toString() {
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
		if(o instanceof StackInterface) {
			LinkedList ll = new LinkedList((StackInterface)o);
			return ll.equals(this.list);
			
			
			
		}
		return false;
		
	}

}
