import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * DGraph represents a directed graph with appropriate functions
 *
 * @param <K> the datatype of the Key
 */
public class DGraph<K> {
	// used for the backtracking
	public LinkedList<K> finalPath = new LinkedList<>();
	public double finalCost = Double.MAX_VALUE;
	
	/*
	 * with tspBacktrack and tspHeuristic, those functions do not
	 * return a cost nor edit a path, but return those values through the 
	 * variables above as they track the best cost and path.
	 */
	
	/**
	 * Node represents a node for the graph
	 *
	 */
	private class Node {
		K key;
		List<Edge> adjList = new ArrayList<>();

		public Node(K key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "Node(" + key + ")";
		}

	}

	Map<K, Node> nodes = new HashMap<>();

	/**
	 * Edge contains the weight for each connection
	 *  and the node it goes to
	 *
	 */
	private class Edge {
		Node sink;
		double weight;

		Edge(Node v, double w) {
			sink = v;
			weight = w;
		}
		
		@Override
		public String toString() {
			return "Edge(" + sink + ")";
		}
	}

	/*
	 * addNode adds a node to the graph
	 * 
	 */
	void addNode(K key) {
		// only add a node if it already doesn't exist
		if (!nodes.containsKey(key)) {
			nodes.put(key, new Node(key));

		}
	}

	/**
	 * addEdge adds an edge to the graph 
	 * @param k1 the starting location
	 * @param k2 the ending location
	 * @param w the weight for the connection
	 */
	void addEdge(K k1, K k2, double w) {
		Node u = nodes.get(k1);
		Node v = nodes.get(k2);
		u.adjList.add(new Edge(v, w));
	}

	/**
	 * toString returns a string representation for the graph
	 */
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

	/**
	 * tspHeuristic runs a Heuristic approach for finding the lowest cost traversal
	 * @param startKey the key to start at
	 * @param path a list to return the path to
	 * @return the cost of the traversal
	 */
	public double tspHeuristic(K startKey, LinkedList<K> path) {
		// get the start node and create a stack to keep the nodes
		Node startNode = nodes.get(startKey);
		Stack<Node> discovered = new Stack<>();
		discovered.push(startNode);
		double tspCost = 0.0;
		// until the size is equal to the number of nodes, keep running
		while (discovered.size() != nodes.size()) {

			Node current = discovered.peek();
			Node minNode = null;
			
			double minEdgeWeight = Double.MAX_VALUE;
			
			// iterate through all edges
			for (Edge e : current.adjList) {
				// get the lowest weight and node
				if (e.weight < minEdgeWeight && !discovered.contains(e.sink)) {
					minEdgeWeight = e.weight;
					minNode = e.sink;
				}
			}
			
			if (minNode != null) {
				discovered.add(minNode);
				tspCost += minEdgeWeight;
			}
		}
		// add the nodes in discovered to the path
		while (discovered.size()!=0) {
			path.addFirst(discovered.pop().key);
		}
		// get the cost from going with the last node to the start
		for(Edge e: nodes.get(path.getLast()).adjList) {
			if(e.sink.key==path.getFirst()) {
				tspCost += e.weight;
			}
		}
		return tspCost;
	}

	/**
	 * starter method for tspBacktrack
	 * @param startKey the key to start with
	 */
	public void tspBacktrack(K startKey) {
		// reset these values since they are used after the method is called
		finalPath = new LinkedList<>();
		finalCost = Double.MAX_VALUE;
		
		// add a starter path for the helper function
		LinkedList<K> path = new LinkedList<>();
		path.add(startKey);
		tspBacktrack(path, 0);

	}
	
	/**
	 * tspBacktrack is a helper method for the backtrack function
	 * @param path the current path
	 * @param cost the current cost
	 * @return the cost upto that point
	 */
	public double tspBacktrack(LinkedList<K> path, double cost) {

		Node current = nodes.get(path.getLast());

		for (Edge e : current.adjList) {

			if (!path.contains(e.sink.key)) {
				path.add(e.sink.key);
				// recursivly call this
				double tempCost = tspBacktrack(path, cost + e.weight);
				
				// if the path is done, compare to the current best path
				if(path.size()==nodes.size()) {
					// get last connection weight
					double lastCost=0;
					for(Edge e2: nodes.get(path.getLast()).adjList) {
						if(e2.sink.key.equals(path.getFirst())) {
							lastCost=e2.weight;
						}
					}
					// copy the list to finalpath to preserve it
					if(tempCost+lastCost < finalCost) {
						finalPath=(LinkedList<K>) path.clone();
						finalCost=cost+e.weight+lastCost;
					}
				}
				path.removeLast();
			}

		}


		return cost;
	}
	
	/**
	 * tspCustom is a version of tspBacktrack with improvements
	 * @param startKey the key to start with 
	 */
	public void tspCustom(K startKey) {
		// reset these values since they are used after the method is called
		finalPath = new LinkedList<>();
		finalCost = Double.MAX_VALUE;
		LinkedList<K> path = new LinkedList<>();
		path.add(startKey);
		tspCustom(path, 0);

	}
	
	/**
	 * tspCustom is a helper method that is similar to tspBacktrack but has improvements
	 * @param path the current path
	 * @param cost the current cost
	 * @return the cost at that point
	 */
	public double tspCustom(LinkedList<K> path, double cost) {

		Node current = nodes.get(path.getLast());

		for (Edge e : current.adjList) {

			if (!path.contains(e.sink.key)) {
				path.add(e.sink.key);
				// only allow to run if the current cost is less than the best cost
				// if not, it could never be better so cut that branch out
				if(cost <= finalCost) {
					double tempCost = tspCustom(path, cost + e.weight);
					if(path.size()==nodes.size()) {
						// get last connection weight
						double lastCost=0;
						for(Edge e2: nodes.get(path.getLast()).adjList) {
							if(e2.sink.key.equals(path.getFirst())) {
								lastCost=e2.weight;
							}
						}
						
						if(tempCost+lastCost < finalCost) {
							finalPath=(LinkedList<K>) path.clone();
							finalCost=cost+e.weight+lastCost;
						}
					}
					
				}
				
				path.removeLast();
			}

		}


		return cost;
	}
	
	
}