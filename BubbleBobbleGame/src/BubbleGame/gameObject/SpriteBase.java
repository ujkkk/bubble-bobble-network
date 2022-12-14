package BubbleGame.gameObject;

import java.awt.Graphics;

import javax.swing.JLabel;

/**
 * This class will load the sprites (image).
 */
public class SpriteBase {
	private String dirPath;
    private Coordinates coordinates;
    private double w;
    private double h;
    private boolean canMove;
    private int frameCount = 0;
    private int timeCount = 0;
    private int operationTime = 5;

    private String [] imagePaths;
    /**
     * The constructor. It needs all the parameters and creates the image where planned.
     *
     * @param imagePath The path to the image to load.
     * @param  coordinates The coordinates of the Sprite.
     */
    public SpriteBase(String dirPath, Coordinates coordinates)  {

    	this.dirPath = dirPath;
        this.coordinates = coordinates;
        this.h = 0;
        this.w = 0;
        this.canMove = true;
        //this.spriteChanged = false;
    }
    
    public SpriteBase(String dirPath)  {

    	this.dirPath = dirPath;
        this.canMove = true;
        //this.spriteChanged = false;
    }

    /**
     * The move function that moves the image if possible.
     */
    public void move() {

        if (!canMove) {
            return;
        }

        this.coordinates.apply();
    }
    
    public String getImage() {
    	String [] fileNames = this.getImagePaths();
    	String fileName;
    	timeCount++;
    	if((timeCount%operationTime)==0) {
    		frameCount++;      	
    	}
    	fileName = fileNames[frameCount%(fileNames.length)];
    	String imagePath = this.getDirPath()+"/"+ fileName;
    	//System.out.println("getImage : " + imagePath);
    	return imagePath;
    }
    
    public String getImage(int i) {
    	String [] fileNames = this.getImagePaths();
    	String fileName;
    	fileName = fileNames[i];
    	String imagePath = this.getDirPath()+"/"+ fileName;
    	//System.out.println("getImage : " + imagePath);
    	return imagePath;
    }
    
    public void setOperationTime(int operationTime) {
    	this.operationTime = operationTime;
    }

    /**
     * This method gets the image of the level.
     *
     * @return the image.
     */
    
    public String getDirPath() {
    	return this.dirPath;
    }


    /**
     * This method returns the X coordinate.
     *
     * @return x coordinate
     */
    public double getXCoordinate() {
        return coordinates.getXCoordinate();
    }

    /**
     * This method return the Y coordinate.
     *
     * @return y coordinate.
     */
    public double getYCoordinate() {
        return coordinates.getYCoordinate();
    }

    /**
     * This method returns the rotation degree.
     *
     * @return The rotation degree.
     */
    public double getRotation() {
        return coordinates.getRotation();
    }

    /**
     * This function returns the height of the SpriteBase.
     * @return The height of the SpriteBase.i
     */
    public double getHeight() {
        return h;
    }

    /**
     * This method sets the height of the SpriteBase.
     *
     * @param height The height to be set
     */
    public void setHeight(double height) {
        this.h = height;
    }

    /**
     * This function returns the width of the SpriteBase.
     * @return The width of the SpriteBase.
     */
    public double getWidth() {
        return w;
    }

    /**
     * This method sets the width of the SpriteBase.
     *
     * @param width The width to be set
     */
    public void setWidth(double width) {
        this.w = width;
    }



    public void setDirPath( String dirPath) {
        this.dirPath = dirPath;
      //  spriteChanged = true;
    }

    /**
     * This function checks if there is a collision with a set of coordinates.
     * @param minX The minimal X.
     * @param maxX The maximal X.
     * @param minY The minimal Y.
     * @param maxY The maximal Y.
     * @return True if there is a collision.
     */
    public boolean causesCollision(double minX, double maxX, double minY, double maxY) {
        double minX2 = coordinates.getXCoordinate();
        double maxX2 = minX2 + getWidth();
        double minY2 = coordinates.getYCoordinate();
        double maxY2 = minY2 + getHeight();
        return ((minX > minX2 && minX < maxX2)
                || (maxX > minX2 && maxX < maxX2)
                || (minX2 > minX && minX2 < maxX)
                || (maxX2 > minX && maxX2 < maxX))
                && ((minY > minY2 && minY < maxY2)
                || (maxY > minY2 && maxY < maxY2)
                || (minY2 > minY && minY2 < maxY)
                || (maxY2 > minY && maxY2 < maxY));
    }

    /**
     * Sets the X coordinate.
     * @param x The X coordinate.
     */
    public void setXCoordinate(double x) {
        coordinates.setXCoordinate(x);
    }

    /**
     * Sets the Y coordinate.
     * @param y The Y coordinate.
     */
    public void setYCoordinate(double y) {
    	coordinates.setYCoordinate(y);
    }

    /**
     * Sets the R coordinate.
     * @param r The R coordinate.
     */
    public void setRotation(double r) {
    	coordinates.setRotation(r);
    }

    /**
     * Sets the Dx.
     * @param dx The Dx.
     */
    public void setDxCoordinate(double dx) {
        coordinates.setDXCoordinate(dx);
    }

    /**
     * Sets the Dy.
     * @param dy The Dy.
     */
    public void setDyCoordinate(double dy) {
        coordinates.setDYCoordinate(dy);
    }

    /**
     * Sets the Dr.
     * @param dr The Dr.
     */
    public void setDRotation(double dr) {
       coordinates.setDRotation(dr);
    }

    /**
     * Gets the Dx.
     * @return The Dx.
     */
    public double getDxCoordinate() {
        return coordinates.getDXCoordinate();
    }

    /**
     * Gets the Dy.
     * @return The Dy.
     */
    public double getDyCoordinate() {
        return coordinates.getDYCoordinate();
    }

    /**
     * Gets the Dr.
     * @return The Dr.
     */
    public double getDRotation() {
        return coordinates.getDRotation();
    }
    
    /**
     * Sets the canMove.
     * @param canMove The canMove.
     */
    public void setCanMove(Boolean canMove) {
    	this.canMove = canMove;
    }
    
    public String[] getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String[] imagePaths) {
		this.imagePaths = imagePaths;
	}

	

}