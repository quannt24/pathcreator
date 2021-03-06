package vn.hust.pathcreator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class DrawHandler implements MouseListener, MouseMotionListener {

	private DrawingPanel plDraw;
	private JLabel lbCoordinate;
	private JTextArea taResult;
	private JLabel lbResult;
	
	private long prevTimeStamp;
	
	public DrawHandler(DrawingPanel plDraw, JLabel lbCoordinate, JTextArea taResult, JLabel lbResult) {
		this.plDraw = plDraw;
		this.lbCoordinate = lbCoordinate;
		this.taResult = taResult;
		this.lbResult = lbResult;
		prevTimeStamp = 0;
	}
	
	// MouseListener
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		plDraw.repaint();
		
		Coordinate[] realPath = plDraw.getRealPath();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < plDraw.getPathLen(); i++) {
			sb.append(realPath[i].getX());
			sb.append(' ');
			sb.append(realPath[i].getY());
			sb.append('\n');
		}
		taResult.setText(sb.toString());
		
		lbResult.setText("Result: " + plDraw.getPathLen() + " points");
	}

	// MouseMotionListener
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		long timeStamp = System.currentTimeMillis();
		if (timeStamp - prevTimeStamp < plDraw.getTimeStep()) return;

		if (plDraw.getPathLen() > 0) {
			Point lastOffseted = plDraw.getPath()[plDraw.getPathLen() - 1];
			Point last = new Point(lastOffseted.getX() + plDraw.getBaseOffsetX(),
					lastOffseted.getY() + plDraw.getBaseOffsetY());
			double d = Point.distance(last, new Point(arg0.getX(), arg0.getY()));

			if (d < plDraw.getMinDistance()) {
				return;
			} else if (d <= plDraw.getMaxDistance()) {
				plDraw.recordPoint(arg0.getX(), arg0.getY());
				plDraw.repaint();
				prevTimeStamp = timeStamp;
			} else {
				// Add more than one point
				int n = (int) (d / plDraw.getMaxDistance()) + 1;
				int subx = (arg0.getX() - last.getX()) / n;
				int suby = (arg0.getY() - last.getY()) / n;

				for (int i = 0; i < n; i++) {
					plDraw.recordPoint(last.getX() + (i + 1) * subx,
										last.getY() + (i + 1) * suby);
				}
				plDraw.repaint();
				prevTimeStamp = timeStamp;
			}
		} else {
			plDraw.recordPoint(arg0.getX(), arg0.getY());
			plDraw.repaint();
			prevTimeStamp = timeStamp;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		lbCoordinate.setText("x = " + (arg0.getX() - plDraw.getBaseOffsetX())
				+ " ; y = " + (arg0.getY() - plDraw.getBaseOffsetY()));
	}
	
}
