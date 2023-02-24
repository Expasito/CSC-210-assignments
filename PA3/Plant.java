
/**
 * Plant is the base class for all plant objects in the simulation
 *
 */
public abstract class Plant {
	
	protected int x;
	protected int y;
	protected String symb;
	protected String[][] plot = new String[5][5];
	
	public Plant(int x, int y, String symb) {
		this.x=x;
		this.y=y;
		this.symb=symb;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				this.plot[i][j]=".";
			}
		}
	}
	
	/**
	 * the general grow method all subclasses have
	 */
	
	abstract void grow();
	/**
	 * getx returns the x location of the plant
	 * @return the x location of the plant
	 */
	public int getx() {
		return this.x;
	}
	
	/**
	 * gety returns the y location of the plant
	 * @return the y location of the plant
	 */
	public int gety() {
		return this.y;
	}

}
