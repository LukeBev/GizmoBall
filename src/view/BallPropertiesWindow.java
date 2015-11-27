package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import controller.BuildListener;
import model.Model;


public class BallPropertiesWindow {

	public static JFrame window;
	private static JLabel bf1, bf2, bv;
	private static JButton ok, cancel;
	private static JPanel panel1, panel2, panel3;
	private Model model;
	private static ActionListener listener;
	public static JSlider f1slider, gslider, f2slider;
	
	public BallPropertiesWindow(Model m){
		this.listener = new BuildListener(model);
		this.model = m;
	}
	
	public static void BPWGUI(){
		
		window = new JFrame();
		window.setSize(360,300);
		window.setTitle("Ball Properties");
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		bf1 = new JLabel("Ball X Friction:");
		bf2 = new JLabel("Ball Y Friction:");
		bv = new JLabel("Ball Gravity:");
		
		f1slider = new JSlider(JSlider.HORIZONTAL,0,1000,500);
		f1slider.setMinorTickSpacing(50);
		f1slider.setMajorTickSpacing(200);
		gslider = new JSlider(JSlider.HORIZONTAL,0,1000,500);
		gslider.setMinorTickSpacing(50);
		gslider.setMajorTickSpacing(200);
		f2slider = new JSlider(JSlider.HORIZONTAL,0,1000,500);
		f2slider.setMinorTickSpacing(50);
		f2slider.setMajorTickSpacing(200);
		
		f1slider.setPaintTicks(true);  
		f1slider.setPaintLabels(true);  
		f2slider.setPaintTicks(true);  
		f2slider.setPaintLabels(true); 
		gslider.setPaintTicks(true);  
		gslider.setPaintLabels(true);  
		
		ok = new JButton("Ok");
		ok.setMaximumSize(new Dimension(100, 50));
		cancel = new JButton("Cancel");
		cancel.setMaximumSize(new Dimension(100, 50));
		BoxLayout boxLayout = new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS);
		window.setLayout(boxLayout);
		panel1.add(bf1);
		panel1.add(f1slider);
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel2.add(bf2);
		panel2.add(f2slider);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel3.add(bv);
		panel3.add(gslider);
		panel3.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ok.setText("Ok");
		cancel.setText("Cancel");
		ok.addActionListener(listener);
		cancel.addActionListener(listener);
	
		window.add(panel1);
		window.add(panel2);
		window.add(panel3);
		window.add(ok);
		window.add(cancel);
	}
}