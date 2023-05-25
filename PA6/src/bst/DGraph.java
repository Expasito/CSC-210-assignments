package bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DGraph<K, V> {
	private class Node {
		K key;
		V value;
		List<Edge> adjList = new ArrayList<>();

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	Map<K, Node> nodes = new HashMap<>();

	private class Edge {
		Node sink;
		double weight;

		Edge(Node v, double w) {
			sink = v;
			weight = w;
		}
	}

	void addNode(K key, V value) {
		nodes.put(key, new Node(key, value));
	}

	void addEdge(K k1, K k2, double w) {
		Node u = nodes.get(k1);
		Node v = nodes.get(k2);
		u.adjList.add(new Edge(v, w));
	}

	public String toString() {
		String str = "";
		for (K key : nodes.keySet()) {
			str += key + ": [";
			Node u = nodes.get(key);
			for (Edge adj : u.adjList)
				str += adj.sink.key + ", ";
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
		for (Edge e : current.adjList)
			if (!visited.contains(e.sink))
				depthFirstTraversal(e.sink, visited);
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
			for (Edge e : current.adjList)
				if (!discovered.contains(e.sink)) {
					discovered.add(e.sink);
					queue.add(e.sink);
				}
		}
	}

	public void hamiltonianCycles(K startKey) {
		Node startNode = nodes.get(startKey);
		if (startNode == null)
			return;
		List<K> path = new ArrayList<>();
		path.add(startKey);
		Set<Node> visited = new HashSet<>();
		hamiltonianCycles(startNode, visited, path);
	}

	public void hamiltonianCycles(Node current, Set<Node> visited, List<K> path) {
		visited.add(current);
		System.out.print(current.key + " ");
		// if all the nodes are visited, then the Hamiltonian cycle exists
		if (nodes.size()==path.size()) {
			System.out.println("path = " + path);
			return;
		}
		// Check if every edge starting from current node leads to a cycle or not
		for (Edge e : current.adjList) {
			Node v = e.sink;
			double w = e.weight;
			if (!visited.contains(e.sink)) {
				path.add(v.key);
				hamiltonianCycles(v, visited, path);
				visited.remove(v);
				path.remove(path.size()-1);
			}
		}
	}
}