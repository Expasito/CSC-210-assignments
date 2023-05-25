
/**
 * PatientQueue is a class that simulates a queue for a hospital where
 * 		 each Patient has a priority. The patients are added into a min-heap so
 * 		 so the first element out is the patient with the lowest priority.
 */

/**
 * 
 * DynamicArray is a version of an ArrayList where the
 * size can change when the capacity is maxed out
 * 
 *
 */
class DynamicArray<E> {

	private E[] array;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Basic constructor
	 */
	DynamicArray(){
		array = (E[]) new Object[DEFAULT_CAPACITY];
		size=0;
	}
	
	
	/**
	 * 
	 * add adds value to the end of the array
	 * 
	 * @param value The value to add to the array
	 */
	protected void add(E value) {
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
	protected E get(int index) {
		if(index<0) {
			return null;
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
	protected E remove(int index) {
		E x = get(index);
		if(index >= size) {
			return null;
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
		E[] temp = (E[]) new Object[capacity];
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
		this.array = (E[]) new Object[DEFAULT_CAPACITY];
		
		size=0;
	}
	
	public String out() {
//		return Arrays.deepToString(array);
		return "";
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


	/**
	 * sets the value at an index
	 * @param index the index to edit
	 * @param e the element to use
	 */
	public void set(int index, E e) {
		array[index]=e;
	}
	
}


/**
 * 
 * PatientQueue implements a min-heap to have the lowest priority patients 
 * dequeued first.
 *
 */
public class PatientQueue {
	private Heap heap;
	
	/**
	 * basic constructor
	 */
	PatientQueue() {
		heap = new Heap();
		
	}
	
	/**
	 * enqueue adds a name and priority to the heap
	 * @param name the name of the patient
	 * @param priority the priority of the patient
	 */
	void enqueue(String name, int priority){
		heap.add(new Patient(name, priority));
	}
	
	/**
	 * enqueue a patient object to the heap
	 * @param patient the patient object
	 */
	void enqueue(Patient patient){
		heap.add(patient);
		
	}
	
	/**
	 * dequeue removes the patient with the lowest priority
	 * @return the name of the patient
	 * @throws Exception if the queue is empty
	 */
	String dequeue() throws Exception {
		if(isEmpty())
			throw new Exception();
		return heap.remove().name;
	}
	
	/**
	 * peek returns the name of the first patient in the queue
	 * @return the name of the first patient in the queue
	 * @throws Exception if the queue is empty
	 */
	String peek() throws Exception {
		if(isEmpty())
			throw new Exception();
		return heap.peek().name;
	}
	
	
	/**
	 * peekPriority returns the priority of the first patient
	 * @return the priority of the first patient
	 * @throws Exception if the queue is empty
	 */
	int peekPriority() throws Exception {
		if(isEmpty())
			throw new Exception();
		return heap.peek().priority;
	}
	
	/**
	 * changePriority updates the priority of a patient in the queue
	 * @param name the name of the patient
	 * @param newPriority the new priority for the patient
	 */
	void changePriority(String name, int newPriority) {
		int index=-1;
		int oldPrio=0;
		for(int i=1;i<=heap.size();i++) {
			if(heap.get(i).name.equals(name)) {
				// keep the old priority for which direction to bubble
				oldPrio=heap.get(i).priority;
				heap.get(i).priority=newPriority;
				index=i;
				break;
			}
		}
		if(index!=-1) {
			if(newPriority>oldPrio) {
				heap.bubbleDown(index);
			}else {
				heap.bubbleUp(index);
			}
		}
	}
	
	/**
	 * isEmpty returns if the queue is empty
	 * @return if the queue is empty
	 */
	boolean isEmpty() {
		return heap.isEmpty();
	}
	
	/**
	 * size returns the size of the queue
	 * @return the size of the queue
	 */
	int size() {
		return heap.size();
	}
	
	/**
	 * clear clears all patients out of the queue
	 */
	void clear() {
		heap.clear();
	}

	/**
	 * toString returns a string representation of the queue
	 * 
	 * @param String the representation of the queue
	 */
	@Override
	public String toString() {
		return heap.toString();
	}
	
}


/**
 * Heap represents a min heap
 *
 */
class Heap {
	private DynamicArray<Patient> heap;
	private int size;

	/**
	 * basic constructor
	 */
	public Heap() {
		heap = new DynamicArray<>();
		heap.add(null); // the element at index 0
		size = 0;
	}
	
	/**
	 * compare compares two Patient objects
	 * @param a the first Patient object
	 * @param b the second Patient object
	 * @return which has higher priority
	 */
	private boolean compare(Patient a, Patient b) {
		if(a.priority==b.priority) {
			return a.name.compareTo(b.name)<0;
		}
		return a.priority<b.priority;
	}

	/**
	 * the index for the parent based on the current index
	 * @param i the starting index
	 * @return the parent index
	 */
	private int parent(int i) {
		return i / 2;
	}
	
	/**
	 * left returns the index of the left child
	 * @param i the starting index
	 * @return the left child's index
	 */
	private int left(int i) {
		return 2 * i;
	}

	/**
	 * right returns the index of the right child
	 * @param i the starting index
	 * @return the right child's idnex
	 */
	private int right(int i) {
		return 2 * i + 1;
	}
	
	/**
	 * prints out the heap for testing purposes
	 * @return the heap
	 */
	public DynamicArray out() {
		return this.heap;
	}
	
	/**
	 * gets the Patient at an index
	 * @param index the index to get from
	 * @return the Patient object
	 */
	public Patient get(int index) {
		return heap.get(index);
	}

	/**
	 * bubbleUp adjusts the positions in the heap of the Patients upwards in the heap
	 * @param i the starting index
	 */
	public void bubbleUp(int i) {
		if (i > 1 && compare(heap.get(i), heap.get(parent(i)))) {
			// swap with parent
			Patient e = heap.get(parent(i));
			heap.set(parent(i), heap.get(i));
			heap.set(i, e);
			// recurse
			bubbleUp(parent(i));
		}
	}

	/**
	 * bubbleDown adjusts the positions of the heap of the Patients downwards in the heap
	 * @param i the starting index
	 */
	public void bubbleDown(int i) {
		if (right(i) <= size) {
			
			// find lowest priority child
			int lowerpriorityChild = right(i);
			if (left(i) <= size && compare(heap.get(left(i)), heap.get(right(i))))
				lowerpriorityChild = left(i);
			
			// check if we need to swap
			if (compare(heap.get(lowerpriorityChild), heap.get(i))) {
				// swap with child of lower priority
				Patient e = heap.get(lowerpriorityChild);
				heap.set(lowerpriorityChild, heap.get(i));
				heap.set(i, e);
				// recurse
				bubbleDown(lowerpriorityChild);
			}
		}
	}
	


	/**
	 * adds a Patient object to the heap
	 * @param e the patient Object
	 */
	public void add(Patient e) {
		heap.add(e);
		size++;
		bubbleUp(size);
	}

	/**
	 * remove removes a patient from the queue and fixs the heap
	 * @return the Patient that was removed
	 */
	public Patient remove() {
		if (isEmpty())
			return null;
		Patient e = heap.get(1);
		heap.set(1, heap.get(size));
		heap.remove(size);
		size--;
		bubbleDown(1);
		return e;
	}
	
	/**
	 * peek returns the Patient at the first index
	 * @return the patient at the first index
	 */
	public Patient peek() {
		return heap.get(1);
	}

	/**
	 * returns if the heap is empty
	 * @return if the heap is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * returns the size of the heap
	 * @return the size of the heap
	 */
	public int size() {
		return size;
	}
	
	/*
	 * clear clears the values of the heap
	 */
	public void clear() {
		heap.clear();
		heap.add(null);
		size=0;
	}

	/**
	 * returns a string representation of the heap
	 */
	@Override
	public String toString() {
		String s = "{";
		for (int i = 1; i <= size; i++)
			s += (i == 1 ? "" : ", ") + heap.get(i);
		return s + "}";
	}
}
