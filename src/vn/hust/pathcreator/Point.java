package vn.hust.pathcreator;

/**
 * Point in pixel
 * 
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class Point {

	int x, y;

	public Point() {
		this(0, 0);
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void change(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
