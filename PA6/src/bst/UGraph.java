package bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UGraph<K, V> {
	private class Node {
		K key;
		V value;
		boolean visited;
		List<Node> adjList = new ArrayList<>();

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.visited = false;
			
		}
		public String toString() {
			return "Node("+ key + " " + value + " )";
		}
	}

	Map<K, Node> nodes = new HashMap<>();

	void addNode(K key, V value) {
		nodes.put(key, new Node(key, value));
	}

	void addEdge(K k1, K k2) {
		Node u = nodes.get(k1);
		Node v = nodes.get(k2);
		u.adjList.add(v);
		v.adjList.add(u);
	}

	public String toString() {
		String str = "";
		for (K key : nodes.keySet()) {
			str += key + ": [";
			Node u = nodes.get(key);
			for (Node adj : u.adjList)
				str += adj.key + ", ";
			str += "]\n";
		}
		return str;
	}

	void depthFirstTraversal(K key) {
		Node startNode = nodes.get(key);
		if (startNode == null)
			return;
		Set<Node> visited = new HashSet<>();
		depthFirstTraversal(startNode, visited);
	}

	void depthFirstTraversal(Node current, Set<Node> visited) {
		System.out.print("" + current.key + ' ');
		visited.add(current);
		for (Node adj : current.adjList)
			if (!visited.contains(adj))
				depthFirstTraversal(adj, visited);
	}

	void breadthFirstTraversal(K key) {
		Node startNode = nodes.get(key);
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(startNode);
		Set<Node> discovered = new HashSet<>();
		discovered.add(startNode);
		while (queue.size() != 0) {
			Node current = queue.pollFirst();
			System.out.print("" + current.key + ' ');
			for (Node adj : current.adjList)
				if (!discovered.contains(adj)) {
					discovered.add(adj);
					queue.add(adj);
				}
		}
	}

	void depthFirstTraversalI(K key) {
		Node startNode = nodes.get(key);
		LinkedList<Node> stack = new LinkedList<>();
		stack.add(startNode);
		Set<Node> discovered = new HashSet<>();
		discovered.add(startNode);
		while (stack.size() != 0) {
			System.out.println(stack);
			Node current = stack.pollLast();
			System.out.print("" + current.key + ' ');
			for (Node adj : current.adjList)
				if (!discovered.contains(adj)) {
					discovered.add(adj);
					stack.add(adj);
				}
		}
	}
}