package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.RunListener;
import model.Model;


public class RunModeGUI extends JFrame {
	
	public static JFrame window;
	private JMenu menu;
	private JMenuBar menuBar;
	private JPanel mainPanel, runPanel, buttons, buildPanel;
	private JButton buildMode, start, stop, restart, tick;
	
	private Model model;
	private ActionListener listener;
	
	public RunModeGUI(Model m) {
		model = m;
		listener = new RunListener(model);
		
		buildGUI();
	}
		
	public void buildGUI() {
		
		//model = new Model();
		
		window = new JFrame();
		mainPanel = new JPanel();
		runPanel = new JPanel();
		menu = new JMenu();
		menuBar = new JMenuBar();
		buttons = new JPanel();
		buildMode = new JButton();
		buildMode.setFocusable(false);
		buildMode.setMaximumSize(new Dimension(150, 50));
		start = new JButton("Start");
		start.setFocusable(true);
		start.setMaximumSize(new Dimension(150, 50));
		stop = new JButton("Stop");
		stop.setFocusable(false);
		stop.setMaximumSize(new Dimension(150, 50));
		restart = new JButton("Restart");
		restart.setFocusable(false);
		restart.setMaximumSize(new Dimension(150, 50));
		tick = new JButton("Tick");
		tick.setFocusable(false);
		tick.setMaximumSize(new Dimension(150, 50));	
		
		JMenuItem quit = new JMenuItem();
		JMenuItem load = new JMenuItem();
		
		quit.setText("Quit...");
		load.setText("Load game...");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		});
		
		menu.add(quit);
		menu.add(load);
		menu.setText("File");
		menuBar.add(menu);
		
		//buildMode.setText("Build Mode");
		start.setText("Start");
		stop.setText("Stop");
		tick.setText("Tick");
		restart.setText("Restart");
		
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		buttons.add(start);
		buttons.add(stop);
		buttons.add(tick);
		buttons.add(restart);
		buttons.add(buildMode);
		
		buildMode.setText("BUILD MODE");
		//buildPanel.add(buildMode);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuBar, BorderLayout.NORTH);
		//mainPanel.add(runPanel, BorderLayout.CENTER);
		mainPanel.add(buttons, BorderLayout.WEST);
		//mainPanel.add(buildMode, BorderLayout.EAST);
		//runPanel.setBackground(Color.black);
		
		Board board = new Board(500, 500, model);
		board.setBackground(Color.BLACK);
		mainPanel.add(board, BorderLayout.CENTER);
		
		window.add(mainPanel);
		
		window.setTitle("Gizmoball: Run Mode");
		//Display the window
		window.setVisible(true);
		window.setSize(629,580);
		//Exit program on close
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//This will centre the window
		window.setLocationRelativeTo(null);
		
		start.addActionListener(listener);
		stop.addActionListener(listener);
		tick.addActionListener(listener);
		restart.addActionListener(listener);
		
		buildMode.addActionListener(listener);		
	}
}
