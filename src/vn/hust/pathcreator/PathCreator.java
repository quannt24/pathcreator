package vn.hust.pathcreator;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Quan T. Nguyen <br>
 * Hanoi University of Science and Technology
 */
public class PathCreator extends JFrame implements ComponentListener {

	/**
	 * Version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WIDTH = 1000;
	public static final int DEFAULT_HEIGHT = 600;
	
	private GridBagLayout mainLayout;
	
	private JPanel plTools;
	private JScrollPane spDraw;
	private Box bxConfig;
	private JPanel plStatus;
	
	private JButton btSave;
	private JButton btClear;
	private JButton btAbout;
	
	private DrawingPanel plDraw;
	
	private Box bxConfigCanvasSize;
	private Box bxConfigBaseX;
	private Box bxConfigBaseY;
	private Box bxConfigUnitScale;
	private Box bxConfigMaxLen;
	private Box bxConfigTimeStep;
	private JTextField tfCanvasWidth;
	private JTextField tfCanvasHeight;
	private JTextField tfBaseOffsetX;
	private JTextField tfBaseOffsetY;
	private JTextField tfUnitScale;
	private JTextField tfMaxPathLen;
	private JTextField tfTimeStep;
	private JButton btApply;
	private JLabel lbResult;
	private JTextArea taResult;
	
	private JLabel lbCoordinate;
	
	// Event handlers
	private ToolHandler toolHandler;
	private DrawHandler drawHandler;
	private ConfigHandler configHandler;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PathCreator app = new PathCreator();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		app.setVisible(true);
	}

	public PathCreator() throws HeadlessException {
		super("Path Creator");
		initUI();
		this.addComponentListener(this);
	}

	private void initUI() {
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		// Main layout
		mainLayout = new GridBagLayout();
		
		// Get content pane
		Container container = getContentPane();
		container.setLayout(mainLayout);
		
		// Tool bar
		plTools = new JPanel();
		
		btSave = new JButton("Save");
		btClear = new JButton("Clear");
		btAbout = new JButton("About");
		
		plTools.add(btSave);
		plTools.add(btClear);
		plTools.add(btAbout);

		// Drawing scroll pane
		plDraw = new DrawingPanel();
		spDraw = new JScrollPane(plDraw,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Config box
		bxConfig = Box.createVerticalBox();
		bxConfigCanvasSize = Box.createHorizontalBox();
		bxConfigBaseX = Box.createHorizontalBox();
		bxConfigBaseY = Box.createHorizontalBox();
		bxConfigUnitScale = Box.createHorizontalBox();
		bxConfigMaxLen = Box.createHorizontalBox();
		bxConfigTimeStep = Box.createHorizontalBox();
		
		tfCanvasWidth = new JTextField(String.valueOf(DrawingPanel.DEFAULT_WIDTH), 4);
		tfCanvasHeight = new JTextField(String.valueOf(DrawingPanel.DEFAULT_HEIGHT), 4);
		tfBaseOffsetX = new JTextField(String.valueOf(DrawingPanel.DEFAULT_BASE_OFFSET_X), 4);
		tfBaseOffsetY = new JTextField(String.valueOf(DrawingPanel.DEFAULT_BASE_OFFSET_Y), 4);
		tfUnitScale = new JTextField(String.valueOf(DrawingPanel.DEFAULT_UNIT_SCALE), 4);
		tfMaxPathLen = new JTextField(String.valueOf(DrawingPanel.DEFAULT_MAX_PATH_LEN), 4);
		tfTimeStep = new JTextField(String.valueOf(DrawingPanel.DEFAULT_TIMESTEP), 4);
		btApply = new JButton("Apply");
		btApply.setAlignmentX(CENTER_ALIGNMENT);
		lbResult = new JLabel("Result:");
		lbResult.setAlignmentX(CENTER_ALIGNMENT);
		taResult = new JTextArea(10, 10);
		
		bxConfigCanvasSize.add(new JLabel("Canvas size (px):"));
		bxConfigCanvasSize.add(tfCanvasWidth);
		bxConfigCanvasSize.add(new JLabel("x"));
		bxConfigCanvasSize.add(tfCanvasHeight);
		bxConfigBaseX.add(new JLabel("Base offset X (px):"));
		bxConfigBaseX.add(tfBaseOffsetX);
		bxConfigBaseY.add(new JLabel("Base offset Y (px):"));
		bxConfigBaseY.add(tfBaseOffsetY);
		bxConfigUnitScale.add(new JLabel("Unit scale (px):"));
		bxConfigUnitScale.add(tfUnitScale);
		bxConfigMaxLen.add(new JLabel("Max path length:"));
		bxConfigMaxLen.add(tfMaxPathLen);
		bxConfigTimeStep.add(new JLabel("Time step (ms):"));
		bxConfigTimeStep.add(tfTimeStep);
		
		bxConfig.add(bxConfigCanvasSize);
		bxConfig.add(bxConfigBaseX);
		bxConfig.add(bxConfigBaseY);
		bxConfig.add(bxConfigUnitScale);
		bxConfig.add(bxConfigMaxLen);
		bxConfig.add(bxConfigTimeStep);
		bxConfig.add(btApply);
		bxConfig.add(lbResult);
		bxConfig.add(new JScrollPane(taResult,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		// Status bar
		lbCoordinate = new JLabel("x = 0 ; y = 0");
		plStatus = new JPanel();
		plStatus.add(lbCoordinate);

		// Event handlers
		toolHandler = new ToolHandler(this, btSave, btClear, btAbout, plDraw, taResult, lbResult);
		btSave.addActionListener(toolHandler);
		btClear.addActionListener(toolHandler);
		btAbout.addActionListener(toolHandler);
		
		drawHandler = new DrawHandler(plDraw, lbCoordinate, taResult, lbResult);
		plDraw.addMouseListener(drawHandler);
		plDraw.addMouseMotionListener(drawHandler);
		
		configHandler = new ConfigHandler(tfCanvasWidth, tfCanvasHeight, tfBaseOffsetX,
				tfBaseOffsetY, tfUnitScale, tfMaxPathLen, tfTimeStep, plDraw, taResult, lbResult);
		btApply.addActionListener(configHandler);
		
		// Add panels
		addComponent(plTools, 0, 0, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		addComponent(spDraw, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);
		addComponent(bxConfig, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.NORTH);
		addComponent(plStatus, 2, 0, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		
		pack();
	}
	
	/**
	 * Add component to layout with constraints
	 * 
	 * @param component
	 *            Reference to added component
	 * @param row
	 *            Row position of added component in GridBagLayout, starting
	 *            from 0
	 * @param col
	 *            Column position of added component in GridBagLayout, starting
	 *            from 0
	 * @param width
	 *            Number of columns that component will occupies horizontally
	 * @param height
	 *            Number of rows that component will occupies vertically
	 * @param fill
	 *            Value for GridBagConstrain.fill
	 */
	private void addComponent(Component component, int row, int col, int width,
			int height, int fill, int anchor) {
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = col;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.fill = fill;
		constraints.anchor = anchor;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.ipadx = 1;
		constraints.ipady = 1;

		mainLayout.setConstraints(component, constraints);
		this.add(component);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		spDraw.setPreferredSize(new Dimension(this.getWidth() - 250, this.getHeight() - 100));
		spDraw.revalidate();
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
	
	// End class PathCreator
}
