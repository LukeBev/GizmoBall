package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Model;
import controller.RunListener;


public class RunGui {

	private Model model;
	public static JFrame frame;
	private ActionListener listener;
	private KeyListener klist;
	private Board board;
	public RunGui(Model m) {
		model = m;

		// RunListener catches all GUI events. In reality might have many listeners.
		listener = new RunListener(m);
		klist = new RunListener(m);
		
	}

	public void createAndShowGUI() {

		frame = new JFrame("Gizmoballs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		//frame.addKeyListener(new RunListener(model));
		frame.addKeyListener(klist);
		
	//	frame.setFocusable(true);

		// Board is passed the Model so it can act as Observer
		board = new Board(500, 500, model);
		//frame.addKeyListener(new RunListener(model));
		

		Container cp = frame.getContentPane();

		Font gf = new Font("Arial", Font.BOLD, 12);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 1));

		JButton button1 = new JButton("Start");
		button1.setFont(gf);
		button1.setFocusable(false);
		button1.addActionListener(listener);
		button1.setMaximumSize(new Dimension(100, 100));
		buttons.add(button1);

		JButton button2 = new JButton("Stop");
		button2.setFocusable(false);
		button2.setFont(gf);
		button2.addActionListener(listener);
		button2.setMaximumSize(new Dimension(100, 100));
		buttons.add(button2);

		JButton button4 = new JButton("Tick");
		button4.setFocusable(false);
		button4.setFont(gf);
		button4.addActionListener(listener);
		button4.setMaximumSize(new Dimension(100, 100));
		buttons.add(button4);

		JButton button3 = new JButton("Quit");
		button3.setFocusable(false);
		button3.setFont(gf);
		button3.addActionListener(listener);
		button3.setMaximumSize(new Dimension(100, 100));
		buttons.add(button3);

		cp.add(buttons, BorderLayout.LINE_START);
		cp.add(board, BorderLayout.CENTER);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
