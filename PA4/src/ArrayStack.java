
/**
 * 
 * ArrayStack is a stack with a DynamicArray as the base
 * design
 *
 */
public class ArrayStack implements StackInterface {
	private DynamicArray list;
	
	/**
	 * default constructor
	 */
	ArrayStack() {
		this.list = new DynamicArray();
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param as The ArrayStack object to copy
	 */
	ArrayStack(ArrayStack as){
		this.list = new DynamicArray();
		for(int i=0;i<as.size();i++) {
			this.push(as.list.get(i));
		}
		
	}
	
	/** 
	 * 
	 * push an integer onto the stack
	 * 
	 * @param int value The value to push onto the stack
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public void push(int value) {
		this.list.add(value);
		
	}

	/**
	 * pop an integer off of the stack
	 * 
	 * @return the integer the was popped
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public int pop() {
		if(this.size()==0) {
			return -1;
		}
		return this.list.remove(this.size()-1);
	}
	
	/**
	 * 
	 * peek at the top value of the stack
	 * 
	 * @return the peeked value
	 * 
	 * Complexity: O(1)
	 * 
	 */
	
	@Override
	public int peek() {
		if(this.size()==0) {
			return -1;
		}
		return this.list.get(this.size()-1);
	}

	/**
	 * 
	 * check if the stack is empty
	 * 
	 * @return if the stack is empty
	 * 
	 * Complexity: O(1)
	 * 
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
	 * clear the stack of all items
	 * 
	 * Complexity: O(1)
	 * 
	 */
	@Override
	public void clear() {
		this.list.clear();
		
	}

	/**
	 * 
	 * return the size of the stack
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
		if(o instanceof StackInterface) {
			DynamicArray da = new DynamicArray((StackInterface)o);
			if(o instanceof ListStack) {
				// if is a ListStack, we need to reverse the elements since it is design
				DynamicArray da2 = new DynamicArray();
				int size=da.size();
				for(int i=size-1;i>=0;i--) {
					da2.add(da.remove(i));
				}
				da=da2;
			}

			
			return da.equals(this.list);
			
			
			
		}
		return false;
		
	}
	
	

}
