/**
 * 
 * Vegetable is a subclass of the Plant class where
 * the plants grow from top to bottom
 *
 */
public class Vegetable extends Plant {
	public Vegetable(int x, int y, String symb) {
		super(x, y, symb);
		this.plot[0][2]=symb;
	}
	
	/**
	 * grow simulates a vegetable growing
	 */
	@Override
	public void grow() {
		for(int i=4;i>=0;i--) {
			if(this.plot[i][2].equals(symb) && i!=4) {
				this.plot[i+1][2]=symb;
			}
		}
		
	}

}
