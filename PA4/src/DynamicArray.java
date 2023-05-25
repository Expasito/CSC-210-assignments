
/**
 * 
 * DynamicArray is a version of an ArrayList where the
 * size can change when the capacity is maxed out
 * 
 *
 */
public class DynamicArray {

	private int[] array;
	private int size;
	private static final int DEFAULT_CAPACITY = 3;
	
	/**
	 * Basic constructor
	 */
	DynamicArray(){
		array = new int[DEFAULT_CAPACITY];
		size=0;
	}
	
	/**
	 * 
	 * Copy Constructor
	 * 
	 * @param da The DynamicArray to copy
	 */
	DynamicArray(DynamicArray da){
		array = new int[da.size()];
		size=0;
		for(int i=0;i<size;i++) {
			this.add(da.get(i));
		}
	}
	
	/**
	 * 
	 * Constructor for a StackInterface. Converts the StackInterface
	 * reference object into a DynamicArray Object
	 * 
	 * @param si The StackInterface reference to replicate
	 */
	DynamicArray(StackInterface si){
		array = new int[si.size()];
		size=0;
		int temp = si.size();
		// remove the object 
		for(int i=0;i<temp;i++) {
			this.add(si.pop());
		}
		//list stacks are different order
		if(si instanceof ListStack) {
			for(int i=temp-1;i>=0;i--) {
				si.push(this.array[i]);
			}
		}else {
		// put back onto the origional stack
		for(int i=temp-1;i>=0;i--) {
			si.push(this.array[i]);
		}

		if(si instanceof ArrayStack) {
			//need to reverse the array since it is a stack
			for(int i=0;i<this.size()/2;i++) {
				int temp2 = this.array[i];
				this.array[i]=this.array[this.size-1-i];
				this.array[this.size-1-i]=temp2;
			}
		}

		}

	}
	
	/**
	 * 
	 * Constructor for a QueueInterface. Converts the QueueInterface 
	 * reference object into a DynamicArray Object.
	 * 
	 * @param qi The QueueInterface reference to replicate
	 */
	DynamicArray(QueueInterface qi){
		array = new int[qi.size()];
		size=0;
		int temp = qi.size();
		for(int i=0;i<temp;i++) {
			this.add(qi.dequeue());
			qi.enqueue(this.get(i));
		}
	}
	
	/**
	 * 
	 * add adds value to the end of the array
	 * 
	 * @param value The value to add to the array
	 */
	protected void add(int value) {
		if(size>=array.length)
			resize(2*array.length);
		array[size]=value;
		size++;
	}
	
	/**
	 *  gets the element at index
	 * 
	 * @param index The index to search at
	 * @return the integer at index
	 */
	protected int get(int index) {
		if(index<0) {
			return -1;
		}
		return array[index];
	}
	
	/**
	 * 
	 * remove removes the integer at index
	 * 
	 * @param index The index to remove at
	 * @return The integer at index that was removed
	 */
	protected int remove(int index) {
		int x = get(index);
		if(index >= size) {
			return -1;
		}
		for(int i=index;i<size-1;i++) {
			array[i] = array[i+1];
		}
		size--;
		return x;
	}
	
	/**
	 * 
	 * resize increases the size of the private array
	 * 
	 * @param capacity The new capactiy for the array
	 */
	private void resize(int capacity) {
		int[] temp = new int[capacity];
		size = capacity < size ? capacity : size;
		for(int i=0;i<size;i++) {
			temp[i]= array[i];
		}
		array=temp;
	}
	
	/**
	 * 
	 * size returns the size of the array
	 * 
	 * @return the size of the array
	 */
	public int size() {
		return size;
	}
	
	/**
	 * removes all items in the array
	 */
	public void clear() {
		this.array = new int[DEFAULT_CAPACITY];
		size=0;
	}
	

	@Override
	/**
	 * gives a String representation of the object
	 */
	public String toString() {
		String str="{";
		for(int i=0;i<size-1;i++) {
			str+=array[i];
			str+=",";
		}
		if(size>=1) {
			str += array[size-1];
			
		}
		str += "}";
		return str;
	}
	
	@Override
	/**
	 * basic equals method for the class
	 */
	public boolean equals(Object o) {
		if(o==this) {
			return true;
		}
		if(o==null) {
			return false;
		}
		if(o.getClass()!=this.getClass()) {
			return false;
		}
		DynamicArray da = (DynamicArray)o;
		if(this.size()==da.size()) {
			for(int i=0;i<this.size();i++) {
				if(this.array[i]!=da.array[i]) {
					return false;
				}
			}
			return true;
		}
		return false;

	}
	
	/**
	 * 
	 * checks equality with a LinkedList object
	 * 
	 * @param ll The linkedList object to check
	 * @return if the objects are equal
	 */
	public boolean equals(LinkedList ll) {
		if(ll ==null) {
			return false;
		}
		if(ll.size()!=this.size()) {
			return false;
		}
		for(int i=0;i<this.size();i++) {
			if(this.array[i]!=ll.get(i)) {
				return false;
			}
		}
		return true;
	}

}
