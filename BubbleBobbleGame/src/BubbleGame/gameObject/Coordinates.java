package BubbleGame.gameObject;

/**
 * This class is in charge of the coordinates.
 *
 */
public class Coordinates {

	private double x;
	private double y;
	private double r;
	private double dx;
	private double dy;
	private double dr;

	/**
	 * This is the constructor of the coordinates.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @param r the r coordinate.
	 * @param dx the dx coordinate.
	 * @param dy the dy coordinate.
	 * @param dr the dr coordinate.
	 */
	public Coordinates(double x, double y, double r,
			double dx, double dy, double dr) {
		this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;
	}

    /**
     * Add the derivative to the actual value.
     */
	public void apply() {
		this.setXCoordinate(x + dx);
		this.setYCoordinate(y + dy);
		this.setRotation(r + dr);
	}
	
	/**
	 * This method gets the x of the coordinates. 
	 * @return x the x coordinate.
	 */
	public double getXCoordinate() {
		return x;
	}
	
	/**
	 * This method sets the x of the coordinates. 
	 * @param x the x coordinate.
	 */
	public void setXCoordinate(double x) {
		this.x = x;
	}
	
	/**
	 * This method gets the y of the coordinates. 
	 * @return y the y coordinate.
	 */
	public double getYCoordinate() {
		return y;
	}
	
	/**
	 * This method sets the y of the coordinates. 
	 * @param y the y coordinate.
	 */
	public void setYCoordinate(double y) {
		this.y = y;
	}
	
	/**
	 * This method gets the r of the coordinates. 
	 * @return r the r coordinate.
	 */
	public double getRotation() {
		return r;
	}
	
	/**
	 * This method sets the r of the coordinates. 
	 * @param r the r coordinate.
	 */
	public void setRotation(double r) {
		this.r = r;
	}
	
	/**
	 * This method gets the dx of the coordinates. 
	 * @return dx the dx coordinate.
	 */
	public double getDXCoordinate() {
		return dx;
	}
	
	/**
	 * This method sets the dx of the coordinates. 
	 * @param dx the dx coordinate.
	 */
	public void setDXCoordinate(double dx) {
		this.dx = dx;
	}
	
	/**
	 * This method gets the dy of the coordinates. 
	 * @return dy the dy coordinate.
	 */
	public double getDYCoordinate() {
		return dy;
	}
	
	/**
	 * This method sets the dy of the coordinates. 
	 * @param dy the dy coordinate.
	 */
	public void setDYCoordinate(double dy) {
		this.dy = dy;
	}
	
	/**
	 * This method gets the dr of the coordinates. 
	 * @return dr the dr coordinate.
	 */
	public double getDRotation() {
		return dr;
	}
	
	/**
	 * This method sets the dr of the coordinates. 
	 * @param dr the dr.
	 */
	public void setDRotation(double dr) {
		this.dr = dr;
	}
}
