package vn.hust.pathcreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class ToolHandler implements ActionListener {

	private JButton btSave, btClear, btAbout;
	private DrawingPanel plDraw;
	private JTextArea taResult;
	private JLabel lbResult;
	
	private JFileChooser fileChooser;
	private int fcRet; // Return value of file chooser
	
	private AboutDialog dlAbout;
	
	public ToolHandler(JFrame frame, JButton btSave, JButton btClear, JButton btAbout,
			DrawingPanel plDraw, JTextArea taResult, JLabel lbResult) {
		this.btSave = btSave;
		this.btClear = btClear;
		this.btAbout = btAbout;
		
		this.plDraw = plDraw;
		this.taResult = taResult;
		this.lbResult = lbResult;
		
		// Init save dialog
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		// Init about frame
		dlAbout = new AboutDialog(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btSave) {
			save();
		} else if (e.getSource() == btClear) {
			plDraw.clear();
			taResult.setText(null);
			lbResult.setText("Result:");
		} else if (e.getSource() == btAbout) {
			dlAbout.setVisible(true);
		}
	}
	
	private void save() {
		int dlgRet;
		File file;
		File fileCheck;
		String fileName = "path.txt";
		FileOutputStream foStream;
		BufferedWriter bw;

		fcRet = fileChooser.showSaveDialog(btSave);
		if (fcRet == JFileChooser.APPROVE_OPTION) {
			// Get file name with full path
			file = fileChooser.getSelectedFile();
			fileName = file.getPath();

			// Check if file exists
			fileCheck = new File(fileName);
			if (fileCheck.exists()) {
				dlgRet = JOptionPane.showConfirmDialog(btSave, "The file "
						+ fileName
						+ " has already existed. Do you want to overwrite it?",
						"File exists", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null);
				if (dlgRet == JOptionPane.NO_OPTION)
					return; // Don't do anything
			}

			// Write to file
			try {
				int n = plDraw.getPathLen();
				if (n > 0) {
					foStream = new FileOutputStream(fileName);
					bw = new BufferedWriter(new OutputStreamWriter(foStream));
					
					Coordinate[] realPath = plDraw.getRealPath();
					for (int i = 0; i < n; i++) {
						bw.write(realPath[i].getX() + " " + realPath[i].getY());
						bw.newLine();
					}
					
					// Flush buffer and close stream
					bw.flush();
					foStream.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Just don't save
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Just don't save
				e.printStackTrace();
			} finally {
				foStream = null;
			}
		}
	}
	
	// End class ToolHandler
}
