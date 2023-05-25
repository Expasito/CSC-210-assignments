import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		PatientQueue p = new PatientQueue();

//		p.enqueue(new Patient("hello",5));
//		p.enqueue("hello2",8);

		

		System.out.println(p);
		p.enqueue(new Patient("Anat",4));
//		p.print();
		p.enqueue(new Patient("Ben",9));
//		p.print();
		p.enqueue(new Patient("Sasha",8));
//		p.print();
		p.enqueue(new Patient("Wu",7));
//		p.print();
		p.enqueue(new Patient("Rein",6));
//		p.print();
		p.enqueue(new Patient("Ford",2));
//		p.print();
		p.enqueue(new Patient("Eve",3));
		System.out.println(p);
		System.out.println("HERE");
		p.dequeue();
		System.out.println("HERE");
		System.out.println((p));
		System.out.println("HERERERER");
		p.enqueue("Geez34",-1);
		p.dequeue();
		p.enqueue("hello",5);
		p.dequeue();
		p.enqueue("hello",-1);
		p.enqueue("hello2",4);
		p.enqueue("Helo3",6);
		p.enqueue("45",8);
//		p.print();
//		p.dequeue();
//		p.print();
////		p.changePriority("Eve", 7);
//		p.print();
		System.out.println(p);
//		
		while(!p.isEmpty()) {
			System.out.println(p.dequeue());
		}
//		System.out.println(p);

		
	}
}
