package vn.hust.pathcreator;

/**
 * Real coordinate
 * 
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class Coordinate {

	private double x, y;
	
	public Coordinate() {
		this(0, 0);
	}
	
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void change(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
