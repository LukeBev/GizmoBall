package view;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.SaveListener;
import model.Model;


public class SaveWindow {
	
	public static JFrame window;
	private static JPanel sp;
	private static JButton Save, Cancel;
	private static JLabel efn;
	public static JTextArea fileName;
	private static ActionListener l;
	
	public SaveWindow(Model m){
		l = new SaveListener(m);
		saveGUI();
	}
	
	public static void saveGUI(){
		window = new JFrame();
		window.setTitle("Save Window");
		window.setSize(700, 500);
		window.setLocationRelativeTo(null);	
		window.setVisible(true);
		
		sp = new JPanel();
		
		Save = new JButton("Save");
		Save.setMaximumSize(new Dimension(100, 50));
		Save.addActionListener(l);
		
		Cancel = new JButton("Cancel");
		Cancel.setMaximumSize(new Dimension(100, 50));
		Cancel.addActionListener(l);
		
		efn = new JLabel("Enter File Name");
		
		fileName = new JTextArea(2, 10);
		fileName.setEditable(true);
		
		sp.add(Save);
		sp.add(Cancel);
		sp.add(efn);
		sp.add(fileName);
		window.add(sp);
		
		
	}
	
}