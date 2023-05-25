import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PA11Main {
	

	
	public static void main(String[] args) throws FileNotFoundException {
		DGraph<String> graph = new DGraph<>();
		// get the arguments from the commandline and run the two functions
		String filepath = args[0];
		String function = args[1];
		processInput(filepath, graph);
		
		runTest(function, graph);
		
		
	}
	
	/**
	 * runTest runs the appropriate tsp function and prints the results
	 * @param test the function to run
	 * @param graph the graph to run the functions on
	 */
	public static void runTest(String test, DGraph<String> graph) {
		LinkedList<String> path = new LinkedList<>();
		graph.finalPath = new LinkedList<>();
		graph.finalCost = Double.MAX_VALUE;
		double cost;
		// run the correct test based on the name of the function
		if(test.equals("HEURISTIC")) {
			cost = graph.tspHeuristic("1", path);
			System.out.printf("cost = %.1f, visitOrder = ",cost);
			System.out.println(path);

		}
		if(test.equals("BACKTRACK")) {
			graph.tspBacktrack("1");
			System.out.printf("cost = %.1f, visitOrder = ",graph.finalCost);
			System.out.println(graph.finalPath);
		}
		if(test.equals("MINE")) {
			graph.tspCustom("1");
			System.out.printf("cost = %.1f, visitOrder = ",graph.finalCost);
			System.out.println(graph.finalPath);
			
		}
		if(test.equals("TIME")) {
			long startTime;
			long endTime;
			// reset the linkedlist
			path = new LinkedList<>();
			
			startTime = System.nanoTime();
			cost= graph.tspHeuristic("1", path);
			endTime = System.nanoTime();
			System.out.printf("heuristic: cost = %f, %f milliseconds\n",cost,(endTime-startTime)/100000.0);

			path = new LinkedList<>();
			startTime = System.nanoTime();
			graph.tspBacktrack("1");
			endTime = System.nanoTime();
			System.out.printf("backtrack: cost = %f, %f milliseconds\n",graph.finalCost,(endTime-startTime)/100000.0);
			
			path = new LinkedList<>();
			startTime = System.nanoTime();
			graph.tspCustom("1");
			endTime = System.nanoTime();
			System.out.printf("mine: cost = %f, %f milliseconds",graph.finalCost,(endTime-startTime)/100000.0);
			
			
		}
	}
	
	/**
	 * processInput gets the data from the mtx files and adds the nodes and edges to the graph
	 * @param filepath the filepath to the file to open
	 * @param graph the graph to add the edges and nodes to
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static void processInput(String filepath, DGraph<String> graph) throws FileNotFoundException {
		File file = new File(filepath);
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.charAt(0)=='%') {
				continue;
			}

			// remove all whitespace
			String[] values = line.split("\\s+");
			// purely because there are formatting issues in the files

			String startNode=values[0];
			String endNode=values[1];
			String weight=values[2];
			
			graph.addNode(startNode);
			graph.addNode(endNode);
			graph.addEdge(startNode, endNode, Double.valueOf(weight));
		}
		sc.close();
	}
}
