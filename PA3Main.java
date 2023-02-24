import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * PA3Main is the starting class for the garden simulation
 *
 */
public class PA3Main {

	/**
	 * main is the starting method for the program
	 * @param args args[0] is the file path for the inputs
	 */
	public static void main(String[] args) {
		// read in data for the simulation
		Scanner sc = new Scanner(System.in);
//		String filename = "src/input.txt";
		String filename = args[0];
		sc.close();

		File file = new File(filename);
		try {
			Scanner scFile = new Scanner(file);
			
			// get the size of the garden
			String x = scFile.nextLine();
			String y = scFile.nextLine();
			
			// create the garden object after parsing the data
			Garden garden = new Garden(
					Integer.valueOf((x.split(":")[1]).substring(1)),
					Integer.valueOf((y.split(":")[1]).substring(1))
					);
			
			// make sure there are not too many columns
			if(Integer.valueOf((y.split(":")[1]).substring(1))>16) {
				System.out.println("Too many plot columns.\n");
				System.exit(0);
			}
			
			// now for reading in inputs
			while(scFile.hasNextLine()) {
				String input = scFile.nextLine().toLowerCase();
				// run the processing function
				PA3Main.process_inputs(input);
				
			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
		
	}
	
	/**
	 * process_inputs breaks down the string input and runs the command
	 * @param input is the string from the input file
	 */
	public static void process_inputs(String input) {
		// this is the next line to analyze
		
		
		// start checking the possible commans
		if(input.toLowerCase().equals("print")) {
			System.out.println("> PRINT");
			Garden.print();
			System.out.println("");
			
		}
		// '(' means there is a coordinate pair
		else if(input.contains("(")) {
			
			// handle grow since it is different
			if(input.substring(0,4).equals("grow")) {
				// split at whitespace
				String[] input2 = input.split(" ");
				
				int itter = Integer.valueOf(input2[1]);
				
				// remove the exta stuff and split
				String[] input3 = input2[2].replace("(","").replace(")", "").split(",");
				String xLoc = String.valueOf(input3[0]);
				String yLoc = String.valueOf(input3[1]);
				
				System.out.println("> GROW "+itter + " ("+xLoc +","+yLoc+")\n");
				Garden.GROW(itter,Integer.valueOf(xLoc),Integer.valueOf(yLoc));
			}
			else {
				
				// split at whitespace
				String[] input2 = input.split(" ");
				
				// this is the command for the coordinate pair
				String command = input2[0].toLowerCase();
				
				// get x and y locations
				String[] input3 = input2[1].replace("(","").replace(")","").split(",");
				String xLoc = String.valueOf(input3[0]);
				String yLoc = String.valueOf(input3[1]);
				
				// if there is a third element, assign type
				String type="";
				if(input2.length==3) {
					type = input2[2].toLowerCase();
				}
				
				//Check cases for planting
				if(command.equals("plant")) {
					if(type.equals("banana") || 
							type.equals("coconut") ||
							type.equals("pine") ||
							type.equals("oak") ||
							type.equals("willow")
						) {
						Garden.addPlant(new Tree(Integer.valueOf(xLoc),Integer.valueOf(yLoc),String.valueOf(type.charAt(0))));
						
					}
				}
				if(command.equals("plant")) {
					if(type.equals("iris") || 
							type.equals("lily") ||
							type.equals("rose") ||
							type.equals("daisy") ||
							type.equals("tulip") ||
							type.equals("sunflower")
						) {
						Garden.addPlant(new Flower(Integer.valueOf(xLoc),Integer.valueOf(yLoc),String.valueOf(type.charAt(0))));
						
					}
				}
				if(command.equals("plant")) {
					if(type.equals("garlic") || 
							type.equals("zucchini") ||
							type.equals("tomato") ||
							type.equals("yam") ||
							type.equals("lettuce")
						) {
						Garden.addPlant(new Vegetable(Integer.valueOf(xLoc),Integer.valueOf(yLoc),String.valueOf(type.charAt(0))));
						
					}
				}
				
				// check for xxx (z,z) format
				if(command.equals("pick")) {
					System.out.println("> PICK ("+ xLoc + ","+yLoc+")\n");
					Garden.REMOVE("flower",Integer.valueOf(xLoc),Integer.valueOf(yLoc));
				}
				if(command.equals("harvest")) {
					System.out.println("> HARVEST ("+ xLoc + ","+yLoc+")\n");
					Garden.REMOVE("vegetable",Integer.valueOf(xLoc),Integer.valueOf(yLoc));
				}
				if(command.equals("cut")) {
					System.out.println("> CUT ("+ xLoc + ","+yLoc+")\n");
					Garden.REMOVE("tree",Integer.valueOf(xLoc),Integer.valueOf(yLoc));
				}
				
			}
		}
		// if length 2, check those possible commands
		else if(input.split(" ").length==2) {
			
			// split at whitespace for analysis
			String[] input2 = input.split(" ");
			
			// check for each of the 2 part commands
			if(input2[0].toLowerCase().equals("grow")) {
				System.out.println("> GROW "+input2[1]+ "\n");
				for(int i=0;i<Integer.valueOf(input2[1]);i++) {
					Garden.GROW();
				}
				
			}
			else if(input2[0].equals("harvest")) {
				System.out.println("> HARVEST "+input2[1].toLowerCase()+ "\n");
				Garden.REMOVE("vegetable",input2[1].toLowerCase().substring(0,1));
			}
			else if(input2[0].equals("pick")) {
				System.out.println("> PICK "+input2[1].toLowerCase()+ "\n");
				Garden.REMOVE("flower",input2[1].toLowerCase().substring(0,1));
			}
			else if(input2[0].equals("cut")) {
				System.out.println("> CUT "+input2[1].toLowerCase()+ "\n");
				Garden.REMOVE("tree",input2[1].toLowerCase().substring(0,1));
			}
			
		}
		// almost last are single word commands
		else if(input.equals("harvest")) {
			System.out.println("> HARVEST\n");
			Garden.REMOVE("vegetable");
		}
		else if(input.equals("cut")) {
			System.out.println("> CUT\n");
			Garden.REMOVE("tree");
		}
		else if(input.equals("pick")) {
			System.out.println("> PICK\n");
			Garden.REMOVE("flower");
		}
		
		// last option is 3 length and is grow
		else if(input.split(" ").length==3){
			String[] input2 = input.split(" ");
			System.out.println("> GROW "+ input2[1] + " " + input2[2]+"\n");
			Garden.GROW(Integer.valueOf(input2[1]),input2[2].toLowerCase());
			
		} else {
			// not a command
		}
	}

}
