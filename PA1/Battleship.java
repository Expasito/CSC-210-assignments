
/**
 * 
 * BattleShip.java simulates a simple version of the game battleship.
 * The player 1 is the user and player 2 is a simple computer AI.
 * 
 * @author georg
 *
 */

import java.util.Scanner;


public class Battleship {
	
	/**
	 * 
	 * startup initalizes an array with '_' to build the grid
	 * 
	 * @param arr The array to be initalized
	 * 
	 */
	public void startup(char[][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]='_';
			}
		}
	}
	
	/**
	 * 
	 * printGrid prints both grids to the console
	 * 
	 * @param playerTarget A grid for the attacks
	 * @param playerShips A grid for the ships
	 * 
	 */
	public void printGrid(char[][] playerTarget, char[][] playerShips) {
		
		// top grid
		System.out.println("Targets");
		System.out.println("  0 1 2 3 4");
		String[] c = {"A","B","C","D","E"};
		
		for(int i=0;i<playerTarget.length;i++) {
			System.out.print(c[i]+" ");
			
			for(int j=0;j<playerTarget[i].length;j++) {
				System.out.print(playerTarget[i][j] + " ");
			}
			
			System.out.println();
		}
		
		// bottom grid
		System.out.println("\nShips");
		System.out.println("  0 1 2 3 4");
		
		for(int i=0;i<playerShips.length;i++) {
			System.out.print(c[i]+" ");
			
			for(int j=0;j<playerShips[i].length;j++) {
				System.out.print(playerShips[i][j] + " ");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 
	 * getLoc converts a string of length 2 to its x and y coordinates
	 * 
	 * @param loc The string to be converted to the coordinates
	 * @return an array of the x and y coordinates from the string
	 * 
	 */
	public int[] getLoc(String loc) {
		String temp = loc.toLowerCase();;
		char[] let = temp.toCharArray();
		// convert char to int by subtracting chars by 97 and integers by 48
		int x = let[0]-97;
		int y = let[1]-48;
		return new int[]{x,y};
	}
	
	/**
	 * 
	 * aiShipPlacement generates locations for the ships and makes sure
	 * they are in bounds and not overlapping
	 * 
	 * @param computerShips the array for the computer's ships to be stored
	 * @param ships the number of ships to generate
	 */
	public void aiShipPlacement(char[][] computerShips, int ships) {
		boolean vert;
		
		// holds all of the ships on the boards coordinates 
		// to prevent overlap and set to null if there is no ship
		int[][][] allCoords = new int[ships][3][2];
		
		for(int i=0;i<ships;i++) {
			allCoords[i]=null;
		}
		
		/*
		 * random ship generation works by selecting either vertical or horizontal
		 * and then adding a delta x and delta y to move it over
		 * Then check if it is still in bounds and if it overlaps other ships.
		 */
		for(int ship=0;ship<ships;ship++) {
			vert = (Math.random()>.5) ? true:false;
			int[][] coords2Base = new int[3][2];
			int[][] coords2;
			
			// select which base location to start with
			if (vert){
				coords2= new int[][]{{0,0},{0,1},{0,2}};
			}else {
				coords2= new int[][]{{0,0},{1,0},{2,0}};
			}
			
			//copy array to reset if failure
			for(int h=0;h<3;h++) {
				coords2Base[h][0]=coords2[h][0];
				coords2Base[h][1]=coords2[h][1];
			}
			
			// delta x and delta y for the new placement
			int dx = 0;
			int dy = 0;
			boolean check=true;
			// until ship fits, keep trying
			while(check) {
				// reset coordinates each iteration
				for(int h=0;h<3;h++) {
					coords2[h][0]=coords2Base[h][0];
					coords2[h][1]=coords2Base[h][1];
				}
				
				dx = (int)(Math.random()*5);
				dy = (int)(Math.random()*5);
				
				for(int i=0;i <3;i++) {
					coords2[i][0]+=dx;
					coords2[i][1]+=dy;
				}
				
				// set to false, if fails below, set to true to keep testing
				check=false;
				for(int i=0;i<3;i++) {
					
					//check coords of other ships only if they exist
					for(int k=0;k<ships;k++) {
						
						if(allCoords[k]!=null) {
							
							for(int j=0;j<3;j++) {
								// check x and y for all ships and all of their individual coordinates
								if(coords2[i][0]==allCoords[k][j][0] && coords2[i][1]==allCoords[k][j][1]) {
									check=true;
									break;
								}
							}
						}
					}
					
					
					//make sure not out of bounds
					if(check==false && (coords2[i][0]<0 || coords2[i][1]<0 || coords2[i][0]>4 || coords2[i][1]>4)) {
						check=true;
						break;
					}
				}				
			}
			
			// after while loop, set coordinates and display on grid
			for(int[] arr:coords2) {
				computerShips[arr[0]][arr[1]]='C';
			}
			
			allCoords[ship]= new int[3][2];
			allCoords[ship][0] = new int[] {coords2[0][0],coords2[0][1]};
			allCoords[ship][1] = new int[] {coords2[1][0],coords2[1][1]};
			allCoords[ship][2] = new int[] {coords2[2][0],coords2[2][1]};
		}
	}
	
	/**
	 * 
	 * gameLoop is the game loop for the player and computer to interact
	 * 
	 * @param name Name of the player 
	 * @param sc Scanner for reading in console data
	 * @param battleship battleship object to access non static methods
	 * @param computerShips the array for the computer's ships
	 * @param playerShips the array for the player's ships
	 * @param computerTarget the array for the computer's attacks
	 * @param playerTarget the array for the player's attacks
	 * @param ships the number of ships for the AI
	 */
	public void gameLoop(String name, Scanner sc, Battleship battleship, 
			char[][] computerShips, char[][] playerShips, char[][] computerTarget, 
			char[][] playerTarget, int ships) {
		
		boolean winner=false;
		// once one of these hits 3*ships, all ships are sunk
		int playerHits=0;
		int compHits=0;
		while(!winner) {
			System.out.println("\nChoose a location "+name +"!");
			String loc = sc.nextLine();
			
			int[] loc2 = battleship.getLoc(loc);
			
			// update boards for player move
			if(computerShips[loc2[0]][loc2[1]]=='C') {
				playerTarget[loc2[0]][loc2[1]]='H';
				playerHits++;
				computerShips[loc2[0]][loc2[1]]='H';
				
			}else {
				playerTarget[loc2[0]][loc2[1]]='M';
				computerShips[loc2[0]][loc2[1]]='M';
				
			}
			System.out.println("\n");
			battleship.printGrid(playerTarget, playerShips);
			
			// computer's turn
			
			int x= (int)(Math.random()*5);
			int y = (int)(Math.random()*5);
			
			// keep looking for open spot
			while(computerTarget[x][y]!='_') {
				x= (int)(Math.random()*5);
				y = (int)(Math.random()*5);
			}
			
			String[] letters = {"A","B","C","D","E"};
			System.out.println("\nComputer plays "+letters[x] + " " + y + "!\n");
			
			// make C lowercase if hit so player knows where ships are
			if(playerShips[x][y]=='C') {
				computerTarget[x][y]='c';
				compHits++;
				playerShips[x][y]='c';
				
			}else {
				computerTarget[x][y]='M';
				playerShips[x][y]='M';
				
			}
			
			battleship.printGrid(playerTarget, playerShips);
			
			// hits required for a win is 3 per ship, then exit
			if(playerHits==3*ships || compHits==3*ships) {
				winner=true;
			}
			
			
		}
		
		//winner is found, end game
		
		System.out.println("\n\n");
		if(playerHits==3*ships) {
			System.out.println("Great gameplay "+ name + "\nYou won!!");
		}else {
			System.out.println("Must have not been the best strategy\nYou Lost...");
		}
	}
	
	/**
	 * 
	 * getInfo gathers the name and ship locations from the player before the
	 * game starts
	 * 
	 * @param sc Scanner for using console
	 * @param playerShips the grid for the player's ships
	 * @param battleship the Battleship object for accessing other methods
	 * @return name of the player
	 */
	public String getInfo(Scanner sc, char[][] playerShips, Battleship battleship) {
		System.out.println("What is your name?");
		String name = sc.nextLine();
		String[] coords = new String[6];
		
		// get positions for ships
		System.out.println("Alright, enter the positions for your ship"
				+ "(press enter after each location)");
		coords[0]=sc.nextLine();
		coords[1]=sc.nextLine();
		coords[2]=sc.nextLine();
		
		System.out.println("And your next ship");
		coords[3]=sc.nextLine();
		coords[4]=sc.nextLine();
		coords[5]=sc.nextLine();
		
		// add coordinates to the player's ships
		for(int i=0;i<6;i++) {
			int[] loc = battleship.getLoc(coords[i]);

			playerShips[loc[0]][loc[1]]='C';
		}
		return name;

	}
	
	
	public static void main(String[] args) {
		// size of the grids
		int size = 5;
		
		// number of ships to generate for the ai
		int ships=2;
		
		Scanner sc = new Scanner(System.in);
		
		Battleship battleship = new Battleship();
		
		// allocate memory for each grid
		char[][] playerTarget = new char[size][size];
		char[][] playerShips = new char[size][size];
		char[][] computerTarget = new char[size][size];
		char[][] computerShips = new char[size][size];
		
		// initalize all grids with '_'
		battleship.startup(playerTarget);
		battleship.startup(computerTarget);
		battleship.startup(playerShips);
		battleship.startup(computerShips);
		
		// Gather inputs from the user
		String name = battleship.getInfo(sc, playerShips,battleship);
		
		// display grid before gameplay
		battleship.printGrid(playerTarget, playerShips);
		
		//generate ships for ai
		battleship.aiShipPlacement(computerShips,ships);
		
		//activate the game loop
		battleship.gameLoop(name, sc, battleship, computerShips, playerShips, computerTarget,playerTarget,ships);
		
		
		sc.close();
		
	}

}
