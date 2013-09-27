package vn.hust.pathcreator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class ConfigHandler implements ActionListener {

	private JTextField tfCanvasWidth, tfCanvasHeight;
	private JTextField tfBaseOffsetX, tfBaseOffsetY;
	private JTextField tfUnitScale;
	private JTextField tfMaxPathLen;
	private JTextField tfTimeStep;
	
	private DrawingPanel plDraw;
	private JTextArea taResult;
	private JLabel lbResult;
	
	public ConfigHandler(JTextField tfCanvasWidth, JTextField tfCanvasHeight,
			JTextField tfBaseOffsetX, JTextField tfBaseOffsetY, JTextField tfUnitScale,
			JTextField tfMaxPathLen, JTextField tfTimeStep, DrawingPanel plDraw,
			JTextArea taResult, JLabel lbResult) {
		this.tfCanvasWidth = tfCanvasWidth;
		this.tfCanvasHeight = tfCanvasHeight;
		this.tfBaseOffsetX = tfBaseOffsetX;
		this.tfBaseOffsetY = tfBaseOffsetY;
		this.tfUnitScale = tfUnitScale;
		this.tfMaxPathLen = tfMaxPathLen;
		this.tfTimeStep = tfTimeStep;
		
		this.plDraw = plDraw;
		this.taResult = taResult;
		this.lbResult = lbResult;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		plDraw.setPreferredSize(new Dimension(Integer.valueOf(tfCanvasWidth.getText()),
				Integer.valueOf(tfCanvasHeight.getText())));
		plDraw.revalidate();
		
		plDraw.setBaseOffset(Integer.valueOf(tfBaseOffsetX.getText()),
				Integer.valueOf(tfBaseOffsetY.getText()));
		plDraw.setUnitScale(Integer.valueOf(tfUnitScale.getText()));
		plDraw.setMaxPathLen(Integer.valueOf(tfMaxPathLen.getText()));
		plDraw.setTimeStep(Integer.valueOf(tfTimeStep.getText()));
		
		// Clear result
		plDraw.clear();
		taResult.setText(null);
		lbResult.setText("Result");
	}

}
