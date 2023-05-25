import java.util.PriorityQueue;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
//		MyHashMap<Integer, Integer> hm = new MyHashMap<>();
//		hm.put(8, 1);
//		hm.put(0, 1);
//		hm.put(9, 1);
//		hm.put(1, 1);
//		hm.put(10, 2);
//		hm.put(2, 10);
//		hm.put(3, 1);
//		hm.put(4, 1);
//		hm.put(5, 1);
//		hm.put(6, 6);
//		hm.put(7, 900);
//		hm.put(15, 3);
//		hm.put(23, 2);
//		hm.put(null, 1);
//		hm.printTable();
//		System.out.println(hm.put(7, 4));
//		hm.printTable();

		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
		pQueue.add(10);
		pQueue.add(20);
		pQueue.add(15);
		// Print the top element of PriorityQueue
		System.out.println(pQueue.peek());
		// Print the top element and remove it
		System.out.println(pQueue.poll());
		// Print the top element again
		System.out.println(pQueue.peek());
		PriorityQueue<String> namePriorityQueue = new PriorityQueue<>();
		// Add items to a Priority Queue (enqueue)
		namePriorityQueue.add("Liz");
		namePriorityQueue.add("Robert");
		namePriorityQueue.add("John");
		// Remove items from the Priority Queue (dequeue)
		while (!namePriorityQueue.isEmpty())
		System.out.println(namePriorityQueue.remove()); //same as poll
		}
		
		
	}
	

	

