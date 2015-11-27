package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import controller.BuildListener;
import controller.MouseEventListener;
import controller.RunListener;
import model.Model;

public class BuildModeGUI {

	public static JFrame window;
	private JMenu menu;
	private JMenuBar menuBar;
	private JButton move, rotate, connect, disconnect, keyConnect,
			keyDisconnect, delete, colour, runMode, square, triangle, circle,
			absorber, leftFlipper, rightFlipper, ball, ballO;
	private JPanel mainPanel, leftButtons, rightButtons, info_panel;
	private static JPanel buildPanel;
	// private CellPane buildCell;
	private JPanel runPanel;
	
	private ActionListener listener;
	private MouseListener ml;
	public static JTextArea info_bar;
	private static Model model;
	public static Board board;
	
	public static void main(String[] args) {
		model = new Model();
		new BuildModeGUI(model);
		
	}

	public BuildModeGUI(Model m) {	
		model = m;
		listener = new BuildListener(model);
		ml = new MouseEventListener(model);
				
		buildGUI();
		}

	public void buildGUI() {
		
		window = new JFrame();		
		
		menu = new JMenu();
		menuBar = new JMenuBar();
		
		// Images for the buttons
		ImageIcon square_img = new ImageIcon("square.png");
		ImageIcon tri_img = new ImageIcon("triangle.png");
		ImageIcon cir_img = new ImageIcon("circle.png");
		ImageIcon ab_img = new ImageIcon("ab.png");
		
		
		runMode = new JButton("RUN MODE");
		runMode.setFocusable(false);
		square = new JButton();
		square.setFocusable(false);
		square.setMaximumSize(new Dimension(150, 58));
		triangle = new JButton();
		triangle.setFocusable(false);
		triangle.setMaximumSize(new Dimension(150, 58));
		circle = new JButton();
		circle.setFocusable(false);
		circle.setMaximumSize(new Dimension(150, 58));
		absorber = new JButton();
		absorber.setFocusable(false);
		absorber.setMaximumSize(new Dimension(150, 58));
		leftFlipper = new JButton("Left Flipper");
		leftFlipper.setFocusable(false);
		leftFlipper.setMaximumSize(new Dimension(150, 58));
		rightFlipper = new JButton("Right Flipper");
		rightFlipper.setFocusable(false);
		rightFlipper.setMaximumSize(new Dimension(150, 58));
		ball = new JButton("Ball");
		ball.setFocusable(false);
		ball.setMaximumSize(new Dimension(150, 58));
		ballO = new JButton("Ball Properties");
		ballO.setFocusable(false);
		ballO.setMaximumSize(new Dimension(150, 58));

		move = new JButton();
		move.setFocusable(false);
		move.setMaximumSize(new Dimension(150, 50));
		rotate = new JButton();
		rotate.setFocusable(false);
		rotate.setMaximumSize(new Dimension(150, 50));
		connect = new JButton();
		connect.setFocusable(false);
		connect.setMaximumSize(new Dimension(150, 50));
		disconnect = new JButton();
		disconnect.setFocusable(false);
		disconnect.setMaximumSize(new Dimension(150, 50));
		keyConnect = new JButton();
		keyConnect.setFocusable(false);
		keyConnect.setMaximumSize(new Dimension(150, 50));
		keyDisconnect = new JButton();
		keyDisconnect.setFocusable(false);
		keyDisconnect.setMaximumSize(new Dimension(150, 50));
		delete = new JButton();
		delete.setFocusable(false);
		delete.setMaximumSize(new Dimension(150, 50));
		colour = new JButton();
		colour.setFocusable(false);
		colour.setMaximumSize(new Dimension(150, 50));

		leftButtons = new JPanel();
		rightButtons = new JPanel();
		buildPanel = new JPanel();
		mainPanel = new JPanel();
		runPanel = new JPanel();
		info_panel = new JPanel();

		JMenuItem save = new JMenuItem("Save...");
		JMenuItem load = new JMenuItem("Load...");
		JMenuItem clear = new JMenuItem();
		JMenuItem quit = new JMenuItem();

		//save.setText("Save...");
		//load.setText("Load...");
		clear.setText("Clear board...");
		quit.setText("Quit...");

		menu.add(save);
		menu.add(load);
		menu.add(clear);
		menu.add(quit);
		menu.setText("File");
		menuBar.add(menu);

		square.setText("Square");
		triangle.setText("Triangle");
		circle.setText("Circle");
		absorber.setText("Absorber");
		//leftFlipper.setText("Left Flipper");
		//rightFlipper.setText("Right Flipper");

		move.setText("Move Gizmo");
		rotate.setText("Rotate Gizmo");
		connect.setText("Connect Gizmo");
		disconnect.setText("Disconnect Gizmo");
		keyConnect.setText("Key Connect");
		keyDisconnect.setText("Key Disconnect");
		delete.setText("Delete Gizmo");
		colour.setText("Colour");

		leftButtons.setLayout(new BoxLayout(leftButtons, BoxLayout.Y_AXIS));
		leftButtons.add(square);
		leftButtons.add(triangle);
		leftButtons.add(circle);
		leftButtons.add(absorber);
		leftButtons.add(leftFlipper);
		leftButtons.add(rightFlipper);
		leftButtons.add(ball);
		leftButtons.add(ballO);

		rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.Y_AXIS));
		rightButtons.add(move);
		rightButtons.add(rotate);
		rightButtons.add(connect);
		rightButtons.add(disconnect);
		rightButtons.add(keyConnect);
		rightButtons.add(keyDisconnect);
		rightButtons.add(keyDisconnect);
		rightButtons.add(delete);
		rightButtons.add(colour);

		runMode.setPreferredSize(new Dimension(150, 75));
		runMode.setText("RUN MODE");
		runPanel.add(runMode);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(leftButtons, BorderLayout.WEST);
		mainPanel.add(rightButtons, BorderLayout.EAST);
		mainPanel.add(runPanel, BorderLayout.SOUTH);
		
		//buildPanel.addMouseListener(ml);
		
		board = new Board(500,500, model);
		board.setBackground(Color.white);
		mainPanel.add(board, BorderLayout.CENTER);
		board.addMouseListener(ml);
		
		/*buildPanel = new JPanel(new GridLayout(20,20, -1, -1));
		buildPanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		for (int i = 0; i < 400; i++){
		    JLabel label = new JLabel("");
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    buildPanel.add(label);
		}*/
				
		mainPanel.add(board);
		window.add(mainPanel);

		menu.setEnabled(true);
		menu.setVisible(true);
		menuBar.setEnabled(true);
		menuBar.setVisible(true);
		mainPanel.setVisible(true);

		info_bar = new JTextArea(2,40);
		Font font = new Font("Arial", Font.BOLD, 17);
		info_bar.setFont(font);
		info_bar.setForeground(Color.green);
		info_bar.setText("In Build mode you can build your own gizmoball board");
		runPanel.add(info_bar);
		runPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		window.setTitle("Gizmoball: Build Mode");
		// Display the window
		window.setVisible(true);
		window.setSize(778, 672);
		// Exit program on close
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// This will centre the window
		window.setLocationRelativeTo(null);		
		
		runMode.addActionListener(listener);
		square.addActionListener(listener);
		triangle.addActionListener(listener);
		circle.addActionListener(listener);
		absorber.addActionListener(listener);		
		leftFlipper.addActionListener(listener);
		rightFlipper.addActionListener(listener);
		ball.addActionListener(listener);
		ballO.addActionListener(listener);
		load.addActionListener(listener);
		save.addActionListener(listener);
		clear.addActionListener(listener);
		move.addActionListener(listener);
		delete.addActionListener(listener);
		rotate.addActionListener(listener);
		colour.addActionListener(listener);
		connect.addActionListener(listener);
		disconnect.addActionListener(listener);
				
	}
}