package bst;

public class BST<K extends Comparable<K>, V> {
	private class Node {
		K key;
		V value;
		Node left = null;
		Node right = null;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public String toString() {
			return "Node(" + this.key + ", " + this.value + ")";
		}
	}

	private Node root = null;

	private int compare(K a, K b) {
		return a.compareTo(b);
	}

	public void add(K key, V value) {
		root = addRecur(root, key, value);
	}

	private Node addRecur(Node curr, K key, V value) {
		if (curr == null)
			return new Node(key, value);
		if (compare(key, curr.key) < 0)
			curr.left = addRecur(curr.left, key, value);
		else if (compare(key, curr.key) > 0)
			curr.right = addRecur(curr.right, key, value);
		return curr;
	}

	public V get(K key) {
		return getRecur(root, key);
	}

	private V getRecur(Node curr, K key) {
		System.out.println(curr+" " + key);
		if(curr==null) {
			return null;
		}
		System.out.println("HERE"+curr);
		if(compare(curr.key,key)<0) {
			getRecur(curr.left,key);
		}else if(compare(curr.key,key)>0){
			getRecur(curr.right,key);
		}
		return curr.value;
	}
	
	public void traversePreOrder(Node node)
	{
	if (node != null)
	{
	System.out.print(" " + node.value);
	traversePreOrder(node.left);
	traversePreOrder(node.right);
	}
	}
	public void traverseInOrder(Node node)
	{
	if (node != null)
	{
	traverseInOrder(node.left);
	System.out.print(" " + node.value);
	traverseInOrder(node.right);
	}
	}
	public void traversePostOrder(Node node)
	{
	if (node != null)
	{
	traversePostOrder(node.left);
	traversePostOrder(node.right);
	System.out.print(" " + node.value);
	}
	}

	
	public void print()
	{
	System.out.print("\nPreOrder: ");
	traversePreOrder(root);
	System.out.print("\nInOrder: ");
	traverseInOrder(root);
	System.out.print("\nPostOrder: ");
	traversePostOrder(root);
	}
	
	public K min() {return min(root);}
	public K min(Node root) {
		if(root.left==null) {return root.key;}else {return min(root.left);}
	}
	public K max() {return max(root);}
	public K max(Node root) {
		if(root.right==null) {return root.key;}else {return max(root.right);}
	}

}