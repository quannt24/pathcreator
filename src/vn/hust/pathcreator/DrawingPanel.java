package vn.hust.pathcreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class DrawingPanel extends JPanel {
	
	/**
	 * Release 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	// Default size
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 1200;
	// Default base offsets of the origin
	public static final int DEFAULT_BASE_OFFSET_X = 100;
	public static final int DEFAULT_BASE_OFFSET_Y = 100;
	// Default number of pixels representing 1 in drawing unit
	public static final int DEFAULT_UNIT_SCALE = 10;
	// Default max number of anchor points in a path
	public static final int DEFAULT_MAX_PATH_LEN = 1000;
	// Default time step for recording anchor points (ms)
	public static final int DEFAULT_TIMESTEP = 500;
	
	// Base offsets (Offsets of origin of coordinate axes, relative to top left corner of drawing pane)
	private int baseOffsetX;
	private int baseOffsetY;
	private int unitScale;
	private int maxPathLen;
	private int timeStep; // Time step between two continual recording times
	
	private int pathLen;
	private Point[] path; // Path recorded in pixels
	private Coordinate[] realPath; // Path scaled to unit
	
	public DrawingPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		baseOffsetX = DEFAULT_BASE_OFFSET_X;
		baseOffsetY = DEFAULT_BASE_OFFSET_Y;
		unitScale = DEFAULT_UNIT_SCALE;
		maxPathLen = DEFAULT_MAX_PATH_LEN;
		timeStep = DEFAULT_TIMESTEP;
		
		pathLen = 0;
		path = new Point[maxPathLen];
		realPath = new Coordinate[maxPathLen];
	}
	
	public void setBaseOffset(int baseOffsetX, int baseOffsetY) {
		this.baseOffsetX = baseOffsetX;
		this.baseOffsetY = baseOffsetY;
		clear();
	}
	
	public int getBaseOffsetX() {
		return baseOffsetX;
	}
	
	public int getBaseOffsetY() {
		return baseOffsetY;
	}
	
	public void setUnitScale(int unitScale) {
		this.unitScale = unitScale;
		
		for (int i = 0; i < pathLen; i++) {
			realPath[i].change((double) path[i].getX() / (double) unitScale,
					(double) path[i].getY() / (double) unitScale);
		}
	}
	
	public int getMaxPathLen() {
		return maxPathLen;
	}
	
	public void setMaxPathLen(int n) {
		this.maxPathLen = n;
		clear();
		path = new Point[n];
		realPath = new Coordinate[n];
	}
	
	public int getTimeStep() {
		return timeStep;
	}

	public void setTimeStep(int timeStep) {
		this.timeStep = timeStep;
	}

	/**
	 * Get current path length
	 */
	public int getPathLen() {
		return pathLen;
	}
	
	public Coordinate[] getRealPath() {
		return realPath;
	}
	
	/**
	 * Record point into path, use pixel coordinate
	 */
	public void recordPoint(int x, int y) {
		if (pathLen < maxPathLen) {
			if (path[pathLen] != null)
				path[pathLen].change(x - baseOffsetX, y - baseOffsetY);
			else
				path[pathLen] = new Point(x - baseOffsetX, y - baseOffsetY);
			
			if (realPath[pathLen] != null) {
			realPath[pathLen].change((double) path[pathLen].getX() / (double) unitScale,
					(double) path[pathLen].getY() / (double) unitScale);
			} else {
				realPath[pathLen] = new Coordinate((double) path[pathLen].getX() / (double) unitScale,
						(double) path[pathLen].getY() / (double) unitScale);
			}
			pathLen++;
		}
	}
	
	public void clear() {
		pathLen = 0;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Clear
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw coordinate axes
		g.setColor(Color.GREEN);
		g.drawLine(0, baseOffsetY, getWidth(), baseOffsetY);
		g.drawLine(baseOffsetX, 0, baseOffsetX, getHeight());
		
		// Draw path
		g.setColor(Color.BLACK);
		for (int i = 1; i < pathLen; i++) {
			g.drawLine(path[i - 1].getX() + baseOffsetX, path[i - 1].getY() + baseOffsetY,
					path[i].getX() + baseOffsetX, path[i].getY() + baseOffsetY);
		}
	}
	
}
