/**
 * Garden is the general class for the 'garden'of the simulation.
 * Aside from instantiating an object to create the array, all methods are
 * static so an object is not required to be passed around
 *
 */

public class Garden {
	private static Plant[][] garden;
	private static String[][] gardenPrint;
	public Garden(int x,int y) {
		garden = new Plant[x][y];
		gardenPrint = new String[x*5][y*5];
		for(int i=0;i<garden.length;i++) {
			for(int j=0;j<garden[0].length;j++) {
				garden[i][j]=null;
			}
		}
	}
	
	/**
	 * addPlant adds a plant to the garden if it is in the bounds.
	 * @param p p is a plant object
	 */
	public static void addPlant(Plant p) {
		if(p.getx()>=garden.length || p.gety() >= garden[0].length) {
//			System.out.println("Can't plant there.\n");
		}else {
			garden[p.getx()][p.gety()]=p;
			
		}
	}
	
	/**
	 * GROW calls the grow method for all plants in the garden
	 */
	public static void GROW() {
		for(Plant[] p: garden) {
			for(Plant p2:p) {
				if(p2!=null) {p2.grow();}
			}
		}
	}
	
	/**
	 * GROW selects a plant in position x,y and grows it num times
	 * @param num num is the number of times to grow the plant
	 * @param x x is the x coordinate of the plant
	 * @param y y is the y coordinate of the plant
	 */
	public static void GROW(int num, int x, int y) {
		// make sure in bounds and not null
		if(x<garden.length && y <garden[0].length && garden[x][y]!=null) {
			for(int i=0;i<num;i++) {
				garden[x][y].grow();
			}
		}
		else {
			System.out.println("Can't grow there.\n");
		}
	}
	
	/**
	 * GROW grows all non-null objects of subclass plant num times
	 * @param num num is the number of times to grow the plant
	 * @param plant plant is the string version of a subclass of Plant
	 */
	public static void GROW(int num, String plant) {
		
		for(int i=0;i<garden.length;i++) {
			for(int j=0;j<garden[0].length;j++) {
				// for all flowers
				if(plant.toLowerCase().equals("flower")) {
					if(garden[i][j] instanceof Flower) {
						for(int k=0;k<num;k++) {
							garden[i][j].grow();
						}
					}
				}
				// all trees
				if(plant.toLowerCase().equals("tree")) {
					if(garden[i][j] instanceof Tree) {
						for(int k=0;k<num;k++) {
							garden[i][j].grow();
						}
					}
				}
				// all vegetables
				if(plant.toLowerCase().equals("vegetable")) {
					if(garden[i][j] instanceof Vegetable) {
						for(int k=0;k<num;k++) {
							garden[i][j].grow();
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * REMOVE removes all plants from the garden that are of subclass type
	 * 
	 * @param type type is the subclass of plant as a string that is to be removed.
	 * 
	 */
	public static void REMOVE(String type) {
		for(int i=0;i<garden.length;i++) {
			for(int j=0;j<garden[0].length;j++) {
				if(type.equals("vegetable") && garden[i][j] instanceof Vegetable) {
					garden[i][j]=null;
				}
				if(type.equals("tree") && garden[i][j] instanceof Tree) {
					garden[i][j]=null;
				}
				if(type.equals("flower") && garden[i][j] instanceof Flower) {
					garden[i][j]=null;
				}
			}
		}
	}
	
	/**
	 * REMOVE removes the plant at x,y if of type 'type'
	 * @param type type is the string form of a subclass of plant
	 * @param x x is the x location of the plant
	 * @param y y is the y location of the plant
	 */
	public static void REMOVE(String type, int x, int y) {
		if(x<garden.length && y <garden[0].length) {
			if(type.equals("vegetable") && garden[x][y] instanceof Vegetable) {
				garden[x][y]=null;
				return;
			}
			else if(type.equals("tree") && garden[x][y] instanceof Tree) {
				garden[x][y]=null;
				return;
			}
			else if(type.equals("flower") && garden[x][y] instanceof Flower) {
				garden[x][y]=null;
				return;
			}
		}
		{
			// no early return, print error
			switch(type) {
			case "flower":
				System.out.println("Can't pick there.\n");
				break;
			case "vegetable":
				System.out.println("Can't harvest there.\n");
				break;
			case "tree":
				System.out.println("Can't cut there.\n");
				break;
			}
			
		}
	}
	
	/**
	 * REMOVE removes all plants based on their name
	 * @param type type is the string version of a subclass of Plant
	 * @param type2 type2 is the single letter representation of the plant to be removed
	 */
	public static void REMOVE(String type, String type2) {
		for(int i=0;i<garden.length;i++) {
			for(int j=0;j<garden[0].length;j++) {
				if(type.equals("vegetable") && garden[i][j] instanceof Vegetable && garden[i][j].symb.equals(type2)) {
					garden[i][j]=null;
				}
				if(type.equals("tree") && garden[i][j] instanceof Tree && garden[i][j].symb.equals(type2)) {
					garden[i][j]=null;
				}
				if(type.equals("flower") && garden[i][j] instanceof Flower && garden[i][j].symb.equals(type2)) {
					garden[i][j]=null;
				}
			}
		}
	}
	
	/**
	 * print is the general print method for displaying the garden
	 * to the console
	 */
	public static void print() {
		// fill the array before printing
		for(int i=0;i<garden.length;i++) {
			for(int j=0;j<garden[0].length;j++) {
				if(garden[i][j]!=null) {
					// plant exists so add
					for(int k=0;k<5;k++) {
						for(int l=0;l<5;l++) {
							gardenPrint[i*5+k][j*5+l]=garden[i][j].plot[k][l];
						}
					}
				}else {
					// plant does not exist so fill with empty
					for(int k=0;k<5;k++) {
						for(int l=0;l<5;l++) {
							gardenPrint[i*5+k][j*5+l]=".";
						}
					}
				}
			}
		}
		
		// now print the array
		for(int i=0;i<gardenPrint.length;i++) {
			for(int j=0;j<gardenPrint[0].length;j++) {
				System.out.print(gardenPrint[i][j]);
			}
			System.out.println("");
		}
	}
	
}
