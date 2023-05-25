
/**
 * LinkedList is a list that uses Nodes to represent an array
 *
 */
public class LinkedList {
	
	/**
	 * The base Node class for the LinkedList
	 *
	 */
	private class Node {
		private int data;
		private Node next;
		/**
		 * Default constructor
		 * 
		 * @param data The value at that node
		 * 
		 */
		Node(int data){
			this.data=data;
			this.next=null;
		}
	}
	private Node head=null;
	private Node tail=null;
	private int size=0;
	protected boolean reverse=false;
	
	/**
	 * Default constructor
	 */
	LinkedList(){
		
	}
	
	/**
	 * Copy constructor for another LinkedList object
	 * 
	 * @param ll The LinkedList to copy
	 */
	LinkedList(LinkedList ll) {
		for(int i=0;i<ll.size();i++) {
			this.addLast(ll.get(i));
		}
	}
	
	/**
	 * Constructor for replicating the elements on a Stack based
	 * on a StackInterface reference object
	 * 
	 * @param si The StackInterface reference object to copy
	 */
	LinkedList(StackInterface si){

		size=0;;
		int temp=si.size();
		for(int i=0;i<temp;i++) {
			this.addLast(si.pop());
		}

		for(int i=temp-1;i>=0;i--) {
			si.push(this.get(i));
		}

	}
	
	/**
	 * Constructor for replicating the elements on a Queue based
	 * on a StackInterface object
	 * 
	 * @param qi The QueueInterface reference object to copy
	 */
	LinkedList(QueueInterface qi){
		size=0;
		for(int i=0;i<qi.size();i++) {
			this.addLast(qi.dequeue());
			qi.enqueue(this.get(i));
		}
	}
	
	/**
	 * addLast adds a node to the end of the LinkedList
	 * 
	 * @param data The value of the node to be added
	 */
	protected void addLast(int data) {
		Node newNode = new Node(data);
		size++;
		if(head==null) {
			head=newNode;
			tail=newNode;
		}else {
			tail.next=newNode;
			tail=newNode;
		}
	}
	
	/**
	 * addFirst adds a node to the front of the LinkedList
	 * 
	 * @param data The value of the node to be added
	 */
	public void addFirst(int data) {
		Node newNode = new Node(data);
		Node temp=head;
		if(head==null) {
			head=newNode;
			newNode.next=temp;
			tail=newNode;
		}else {
			head=newNode;
			newNode.next=temp;
			
		}
		size++;
	}
	/**
	 * gets the value at the end of the LinkedList
	 * 
	 * @return the value at the tail
	 */
	public int getLast() {
		return tail.data;
	}
	
	/**
	 * gets a value at index
	 * 
	 * @param index The index to get from
	 * @return The value at index
	 */
	public int get(int index) {
		// in case of last element, return tail
		if(index==size) {
			return tail.data;
		}
		Node p = head;
		for(int i=0;i<index;i++) {
			p=p.next;
		}
		return p.data;
	}
	/**
	 * removes the node at the end of the LinkedList
	 * 
	 * @return The value at the node
	 */
	public int removeLast() {
		if(head!=null) {
			if(head.next==null) {
				int temp=head.data;
				head=tail=null;
				size--;
				return temp;
			}
			Node current=head;
			Node prev=current;
			while(current.next!=null) {
				prev=current;
				current=current.next;
			}
			int temp = current.data;
			prev.next=null;
			tail=prev;
			size--;
			return temp;
		}
		return -1;
	}
	
	/**
	 * removeFirst removes the first node on the LinkedList
	 * 
	 * @return The value at the first node that was removed
	 */
	public int removeFirst() {
		if(head!=null) {
			if(head.next==null) {
				int temp = head.data;
				head=tail=null;
				size--;
				return temp;
			}
			Node current=head;
			Node prev=current.next;
			int temp = head.data;
			head=prev;
			size--;
			return temp;
		} 
		return -1;
	}
	
	/**
	 * returns the size of the LinkedList
	 * @return the size of the LinkedList
	 */
	public int size() {
		return size;
	}
	
	/**
	 * clears the LinkedList of all nodes
	 */
	public void clear() {
		this.head=null;
		this.tail=null;
		this.size=0;
	}
	
	@Override
	public String toString() {
		String str = "{";
		if(head==null) {
			str+="}";
		}else {
			Node current=head;
			while(current.next!=null) {
				str += current.data;
				str += ",";
				current=current.next;
			}
			str+=current.data;
			str += "}";
		}
		if(reverse) {
			String[] str2 = str.substring(1,str.length()-1).split(",");
			
			String newStr="{";
			for(int i=str2.length-1;i>=1;i--) {
				newStr+=str2[i] +",";
			}
			newStr+=str2[0];
			return newStr+"}";
		}
		return str;
	}
	
	@Override
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
		
		LinkedList ll = (LinkedList)o;
		if(this.size()==ll.size()) {
			for(int i=0;i<this.size();i++) {
				if(this.get(i)!=ll.get(i)) {
					return false;
				}
			}
			return true;
		}
		return false;

	}
	
	/**
	 * equals checks equality against a DynamicArray
	 * 
	 * @return if the DynamicArray object is equal in values
	 */
	public boolean equals(DynamicArray da) {
		if(da ==null) {
			return false;
		}
		if(da.size()!=this.size()) {
			return false;
		}
		for(int i=0;i<this.size();i++) {
			if(da.get(i)!=this.get(i)) {
				return false;
			}
		}
		return true;
	}
	
}
