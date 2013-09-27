/**
 * 
 */
package vn.hust.pathcreator;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class AboutDialog extends JDialog {

	/**
	 * Version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame parent;
	
	public AboutDialog(JFrame parent) {
		super(parent);
		this.parent = parent;
		initUI();
	}

	/**
	 * Initialize UI
	 */
	private void initUI() {
		setTitle("About");
		setSize(new Dimension(400, 300));
		setResizable(false);
		setLocationRelativeTo(parent);
		setModal(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lbName = new JLabel("PathCreator");
		JLabel lbVer = new JLabel("Version 1.0.0");
		JLabel lbAuthor = new JLabel("Author: Quan T. Nguyen");
		JLabel lbContact = new JLabel("Contact: quannt24@gmail.com");
		
		lbName.setFont(new Font(null, Font.BOLD, 20));
		lbName.setAlignmentX(CENTER_ALIGNMENT);
		lbVer.setAlignmentX(CENTER_ALIGNMENT);
		lbAuthor.setAlignmentX(CENTER_ALIGNMENT);
		lbContact.setAlignmentX(CENTER_ALIGNMENT);
	
		Box vBox = Box.createVerticalBox();
		
		vBox.add(Box.createVerticalGlue());
		vBox.add(lbName);
		vBox.add(lbVer);
		vBox.add(Box.createVerticalGlue());
		vBox.add(lbAuthor);
		vBox.add(lbContact);
		vBox.add(Box.createVerticalGlue());
		
		this.add(vBox);
	}

}
