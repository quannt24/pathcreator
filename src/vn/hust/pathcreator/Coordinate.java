package vn.hust.pathcreator;

/**
 * Real coordinate
 * 
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class Coordinate {

	private double x, y;
	
	public static double distance(Coordinate c1, Coordinate c2) {
		return Math.sqrt((c1.getX() - c2.getX()) * (c1.getX() - c2.getX()) + (c1.getY() - c2.getY()) * (c1.getY() - c2.getY()));
	}
	
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
