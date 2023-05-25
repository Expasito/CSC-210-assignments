package bst;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

/*********************************************************
 * Main
 * 
 */
public class Main 
{
	public static void main(String[] args)
	{
		Maze maze = new Maze();  
		MazeGUI mazeGUI = new MazeGUI(maze);
		maze.solveMaze(0,0);
	} 
}

/*********************************************************
 * Maze
 * 
 */
class Maze 
{
//	private int[][] maze = { 	
//			{ 1, 1, 1, 1 },
//			{ 1, 0, 1, 0 },
//			{ 1, 0, 1, 1 },
//			{ 1, 1, 0, 1 } };
	
	private int[][] maze = {
			{1,1,1,1,0,1},
			{1,0,1,1,1,1},
			{1,1,0,0,0,1},
			{1,1,1,1,0,1},
			{1,0,0,1,0,1},
			{1,1,1,1,0,1}
	};

	private int[][] solution = new int[maze.length][maze.length];
	private boolean solved = false;

	private MazeGUI gui;
	
	Maze(){} // todo: read from file 
	int[][] getMaze() {return maze;}
	int[][] getSolution() {return solution;}
	void resetSol() {for (int r = 0; r < maze.length; r++) for (int c = 0; c < maze.length; c++) solution[r][c] = 0;}
	void setGUI(MazeGUI gui) {this.gui = gui;}
	
	public void solveMaze(int r, int c)
	{	    	
		System.out.println(r + " " + c);
		if (r == maze.length - 1 && c == maze.length - 1 && maze[r][c] == 1)  // at goal
		{
			solved=true;
			solution[r][c]=1;
			gui.repaint(); 
			gui.pause(); 
		}
		else if ( r>=0 && r <maze.length && c >=0 && c<maze.length &&!solved) 
		{
			// if current cell is not already part of solution   
			if (solution[r][c] != 1)
			{    		
				// mark as part of solution
				solution[r][c]=1;

				gui.repaint(); 
				gui.pause(); 
				
				if(r+1 >=0 && r+1 < maze.length && maze[r+1][c]!=0) {
					solveMaze(r+1,c);
					
				}
				if(r-1 >=0 && r-1 < maze.length && maze[r-1][c]!=0) {
					solveMaze(r-1,c);
				}
				if(c+1 >=0 && c+1 < maze.length && maze[r][c+1]!=0) {
					solveMaze(r,c+1);
				}
				if(c-1 >=0 && c-1 < maze.length && maze[r][c-1]!=0) {
					solveMaze(r,c-1);
				}
				// try all possible moves


				if (!solved)
				{
					// backtrack by unmarking
					solution[r][c]=0;

					gui.repaint(); 
					gui.pause(); 
				}
			}
		}
	}			
} 

/*********************************************************
 * MazeGUI
 * 
 */
class MazeGUI 
{
	Maze maze;
	JFrame mainFrame;
	int delayTime = 50; //ms

	MazeGUI(Maze maze)
	{
		this.maze = maze;
		maze.setGUI(this);
		createAndShowGUI();       
	}

	void createAndShowGUI()
	{
		mainFrame = new JFrame("Maze-ing");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(maze.getMaze().length*100,maze.getMaze().length*100 + 25);

		JPanel mainPanel = new JPanel(null);
		JPanel graphicsPanel  = new GPanel();
		graphicsPanel.setLocation(0, 0);
		graphicsPanel.setSize(maze.getMaze().length*100,maze.getMaze().length*100);
		graphicsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(graphicsPanel);

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);  
	}

	void repaint() {mainFrame.repaint();} 
	void pause() {try {Thread.sleep(delayTime);} catch (InterruptedException e) {}}

	
	class GPanel extends JPanel 
	{
		@Override
		public void paintComponent(Graphics g) 
		{
			int[][] m = maze.getMaze();
			int[][] s = maze.getSolution();

			// draw the grid lines
			for (int r = 0; r <= m.length; r++) 
				g.drawLine(0, r*100, m.length * 100, r*100);
			for (int c = 0; c <= m.length; c++) 
				g.drawLine(c*100, 0, c*100, m.length * 100);

			// draw the filled rectangles and the path
			for (int r = 0; r < m.length; r++) 
				for (int c = 0; c < m.length; c++)
				{
					// draw cells with obstacles
					if (m[r][c] == 0)
						g.fillRect(c*100, r*100, 100, 100);
					// draw path in green
					g.setColor(new Color(0,150,0));
					if (s[r][c] == 1)
						g.fillRoundRect(c*100, r*100, 100, 100, 50, 50);
					g.setColor(Color.BLACK);
				}
		}
	}	
}












//package bst;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//class ChessBoard {
//	private int numRows, numCols;
//	private boolean[][] board;
//	int numQueenSolns = 0;
//
//	ChessBoard(int numRowsCols) {
//		numRows = numCols = numRowsCols;
//		board = new boolean[numRows][numCols];
//	}
//
//	public void add(int row, int col) {
//		board[row][col] = true;
//	}
//
//	public void remove(int row, int col) {
//		board[row][col] = false;
//	}
//	
//	public boolean isSafe(int row, int col)
//	{
//	for (int r = row; r < numRows; r++ )
//	if (board[r][col]) return false;
//	for (int r = row; r >= 0; r-- )
//	if (board[r][col]) return false;
//	for (int c = col; c < numCols; c++ )
//	if (board[row][c]) return false;
//	for (int c = col; c >= 0; c-- )
//	if (board[row][c]) return false;
//	for (int r = row, c = col; r < numRows && c < numCols; r++, c++ )
//	if (board[r][c]) return false;
//	for (int r = row, c = col; r >= 0 && c >= 0; r--, c-- )
//	if (board[r][c]) return false;
//	for (int r = row, c = col; r < numRows && c >= 0; r++, c-- )
//	if (board[r][c]) return false;
//	for (int r = row, c = col; r >= 0 && c < numCols; r--, c++ )
//	if (board[r][c]) return false;
//	return true;
//	}
//	
//	public void print()
//	{
//	for (int i = 0; i < numRows; i++)
//	{
//	for (int j = 0; j < numCols; j++)
//	if (board[i][j] == false)
//	System.out.print("_");
//	else
//	System.out.print("X");
//	System.out.println();
//	}
//	System.out.println();
//	}
//	
//	public void solveQueens()
//	{
//	solveQueens(0,0,0);
//	}
//	private void solveQueens(int x, int y,int numQueens)
//	{
////		print();
//		System.out.println(x + " " + y + " " + numQueens);
//		if(numQueens==numRows) {
//			print();
//		}
//		if(x==numRows-1 && y==numRows-1) {
//			System.out.println("HERERE");
//			return;
//		}
//		add(x,y);
//		print();
//		remove(x,y);
//		
//		
//		if(isSafe(x,y)) {
//			add(x,y);
//			if(y+1==numRows) {
//				solveQueens(x+1,0,numQueens+1);
//			}else {
//				solveQueens(x,y+1,numQueens+1);
//				
//			}
//		}else {
//			remove(x,y);
//			if(y+1==numRows) {
//				solveQueens(x+1,0,numQueens);
//				
//			}else {
//				solveQueens(x,y+1,numQueens);
//			}
//		}
//		remove(x,y);
//		
//	}
//
//}
//
//public class Main {
//	static BST<Integer, String> bst2 = new BST<>();
//
//	public static void main(String[] args) {
//		ChessBoard b = new ChessBoard(4);
//		b.solveQueens();
////		DGraph<Integer, Integer> dg = new DGraph<>();
////		dg.addNode(1, 1);
////		dg.addNode(2, 2);
////		dg.addNode(3, 3);
////		dg.addEdge(1, 3, 3.0);
////		dg.addEdge(3, 1, 4.0);
////		dg.addEdge(1,2,1);
////		dg.addEdge(2, 1, 2.0);
////		dg.addEdge(2, 3, 5.0);
////		dg.addEdge(3, 2, 6.0);
////		System.out.println(dg);
////		dg.hamiltonianCycles(1);
////		diceRoll(3,7);
//	}
//
//	public static void diceRoll(int numDice, int sum) {
//		diceRoll(numDice, sum, new ArrayList<Integer>(),0);
//	}
//
//	private static void diceRoll(int numDice, int desired_sum, List<Integer> rolls, int cur_sum) {
//		if (numDice == 0) {
//			if(desired_sum==cur_sum) {
//				System.out.println(rolls);
//				
//			}
//		}
//		else if(cur_sum +numDice*1 <= desired_sum && cur_sum+numDice*6 >=desired_sum) {
//				for (int i = 1; i <= 6; i++) {
//					rolls.add(i);
//					diceRoll(numDice - 1, desired_sum,rolls,cur_sum+i);
//					rolls.remove(rolls.size() - 1);
//				}
//				
//			}
//		}
//	}
