
/**
 * 
 * Flower is a subclass of Plant where they grow
 * from the center out and stop at the borders
 *
 */
public class Flower extends Plant {
	private int counter=0;
	public Flower(int x, int y, String symb) {
		super(x, y, symb);
		this.plot[2][2]=symb;
		this.counter=1;
	}

	/**
	 * grow here has the flower 'bloom' outwards upto the borders
	 */
	@Override
	public void grow() {
		String[][] newArr = new String[5][5];
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				newArr[i][j]=".";
			}
		}
		// only go upto the edges of the border
		for(int i=1;i<4;i++) {
			for(int j=1;j<4;j++) {
				if(this.plot[i][j].equals(symb)) {
					// grow in all directions 
					newArr[i][j]=symb;
					newArr[i-1][j]=symb;
					newArr[i+1][j]=symb;
					newArr[i][j+1]=symb;
					newArr[i][j-1]=symb;
				}
			}
		}
		this.counter++;
		// if >=5, fill in edges
		if(this.counter>=5) {
			newArr[0][0]=symb;
			newArr[0][4]=symb;
			newArr[4][0]=symb;
			newArr[4][4]=symb;
			this.counter=5;
		}
		// replace the old plot with the new one
		this.plot=newArr;
	}
	
}
