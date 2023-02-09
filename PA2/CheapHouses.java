/**
 * 
 * CheapHouses.java uses a GUI to display a map of houses from a csv file
 * for analysis and also saves houses under a certain price to a text file.
 * 
 * @author Joshua Baus
 *
 */
import javax.swing.*;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;



class GPanel extends JPanel {
	/**
	 * GPanel represents a graphics panel with a paintComponent method
	 * to draw the locations of the houses.
	 */
	// initalize with really low values that should not appear in long/lat coords
	public static double xmin=100000, xmax=-100000,ymin=100000,ymax=-100000;
	public GPanel() {
		setLocation(0, 0);
		setSize(500,500);
	}
	
	/**
	 * 
	 * paintComponent draws the houses on the GPanel object and scales the data 
	 * to fit within the bounds
	 * 
	 * @param g The Graphics object used for drawing on GPanel
	 */
	public void paintComponent(Graphics g) {
		// clear the panel for new draw
		int width = getSize().width;
		int height = getSize().height;
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		
		// first get the min and max for each for scaling purposes
		for(String addr: CheapHouses.data.keySet()) {
			double[] dat = CheapHouses.data.get(addr);
			if(dat[2]<xmin) {
				xmin=dat[2];
			}
			if(dat[2]>xmax) {
				xmax=dat[2];
			}
			if(dat[1]<ymin) {
				ymin=dat[1];
			}
			if(dat[1]>ymax) {
				ymax=dat[1];
			}
		}

		int price = Integer.valueOf(CheapHouses.startingPrice);
		for(String addr: CheapHouses.data.keySet()) {
			double[] vals = CheapHouses.data.get(addr);
			// note the use of 475 to have a small border for the data to fit on the panel
			if(vals[0]<price) {
				// uses a scaling formula to resize based on minimums and maximums
				double x = (vals[2]-xmin)/(-xmin+xmax)*475;

				double y = (vals[1]-ymin)/(ymax-ymin)*475;

				g.fillOval((int)x, 500-(int)(y), 5, 5);
			}
		}


	}
}


public class CheapHouses {

	public static HashMap<String, double[]> data = new HashMap<>();
	public static JPanel graphicsPanel = new GPanel();
	public static String filePath = "houses.csv";
	public static String startingPrice = "225000";
	
	/**
	 * getHouses gathers data from a csv file and saves it in the data Hash map
	 * while also managing exceptions.
	 */
	public static void getHouses() {
		
		try {
			File file = new File("src/"+filePath);
			Scanner sc = new Scanner(file);
			
			// remove the first line(has column names) and gather data
			sc.nextLine();
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] lines = line.split(",");
				
				// select certain lines that have the data
				data.put(lines[0]+", " + lines[1] + " "+lines[2], new double[] {
						Double.parseDouble(lines[9]),
						Double.parseDouble(lines[10]),
						Double.parseDouble(lines[11])
								});

			}
			sc.close();
			
		} 
		catch(Exception e){
			System.out.println("File not Found");
		}
		
	}
	
	/**
	 * 
	 * createAndShowGUI creates the main GUI and adds all of the components
	 * to make it functional with buttons and text fields.
	 * 
	 */
	public static void createAndShowGUI() {
		// Create the frame
		JFrame mainFrame = new JFrame("Cheap Houses");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set the width since it is always the same for each panel
		int width = 500;
		mainFrame.setSize(width,570);
		
		// Create panels
		JPanel mainPanel = new JPanel(null);
		JPanel widgetsPanel = new JPanel(new GridLayout(1,5));
		
		
		// create the text fields
		JTextField path = new JTextField(filePath);
		JTextField price = new JTextField(startingPrice);
		
		// Creat plot button
		JButton plotButton = new JButton("Plot");
		
		plotButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				filePath=path.getText();
				startingPrice = price.getText();
				
				try {
					// run getHouses and try casting, if there is an error
					// the program will fail and not run repaint or save
					// the text file
					getHouses();
					int temp = Integer.valueOf(startingPrice);
					graphicsPanel.repaint();
					
					PrintWriter writer = new PrintWriter("src/cheaphouses.txt");
					// clear last output
					writer.flush();
					for(String addr: CheapHouses.data.keySet()) {
						if(CheapHouses.data.get(addr)[0]<temp) {
							writer.print(addr + " " + CheapHouses.data.get(addr)[0]+ "\n");
						}
					}
					writer.close();
					
				} catch(Exception ex){
					System.out.println("Price is not an integer");
				}

				
			}
		});
		
		// adjust the widgetsPanel size/location
		widgetsPanel.setLocation(0, 500);
		widgetsPanel.setSize(width,25);
		
		// add all of the components to the widgetsPanel
		widgetsPanel.add(new JLabel("File:",JLabel.CENTER));
		widgetsPanel.add(path);
		widgetsPanel.add(new JLabel("Price:",JLabel.CENTER));
		widgetsPanel.add(price);
		widgetsPanel.add(plotButton);
		
		// add the two panels to the main panel
		mainPanel.add(widgetsPanel);
		mainPanel.add(graphicsPanel);
		
		// last add and visible!
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}
	

	public static void main(String[] args) {
		// run get houses once to gather the data for the inital plot
		getHouses();
		createAndShowGUI();
	}

}


