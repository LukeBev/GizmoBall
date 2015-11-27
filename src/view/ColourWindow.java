package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BuildListener;
import model.Model;

public class ColourWindow {
	public static JFrame window;
	private static JButton red, blue, green, pink, orange, yellow;
	private static JPanel panel;
	private Model m;
	private static ActionListener listener;
	
	public ColourWindow(Model model){
		this.m = model;
		listener = new BuildListener(model);
		
	}
	
	public static void ColourWindowGUI(){
		window = new JFrame();
		window.setSize(300, 300);
		window.setTitle("Colour Selection");
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		red = new JButton("red");
		red.setMaximumSize(new Dimension(30,30));
		red.setBackground(Color.RED);
		red.setForeground(Color.black);
		red.addActionListener(listener);
		
		blue = new JButton("blue");
		blue.setMaximumSize(new Dimension(30,30));
		blue.setBackground(Color.blue);
		blue.setForeground(Color.black);
		blue.addActionListener(listener);
		
		green = new JButton("green");
		green.setMaximumSize(new Dimension(30,30));
		green.setBackground(Color.green);
		green.setForeground(Color.black);
		green.addActionListener(listener);
		
		pink = new JButton("pink");
		pink.setMaximumSize(new Dimension(30,30));
		pink.setBackground(Color.magenta);
		pink.setForeground(Color.black);
		pink.addActionListener(listener);
		
		yellow = new JButton("yellow");
		yellow.setMaximumSize(new Dimension(30,30));
		yellow.setBackground(Color.yellow);
		yellow.setForeground(Color.black);
		yellow.addActionListener(listener);
		
		orange = new JButton("orange");
		orange.setMaximumSize(new Dimension(30,30));
		orange.setBackground(Color.orange);
		orange.setForeground(Color.black);
		orange.addActionListener(listener);
		
		panel = new JPanel();
		panel.add(red);
		panel.add(blue);
		panel.add(green);
		panel.add(pink);
		panel.add(yellow);
		panel.add(orange);
		window.add(panel);
		
	}
	
}
