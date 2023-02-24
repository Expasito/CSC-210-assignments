
/**
 * Tree is a subclass of Plant where the trees grow
 * upwards from the ground
 *
 */
public class Tree extends Plant {
	public Tree(int x, int y, String symb) {
		super(x, y, symb);
		this.plot[4][2]=symb;
	}
	
	/**
	 * grow has the tree grow upwards
	 */
	@Override
	public void grow() {
		for(int i=0;i<5;i++) {
			if(this.plot[i][2].equals(symb) && i!=0) {
				this.plot[i-1][2]=symb;
			}
		}
		
	}

}
