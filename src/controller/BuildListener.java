package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import view.BallPropertiesWindow;
import view.BuildModeGUI;
import view.ColourWindow;
import view.RunModeGUI;
import view.SaveWindow;
import model.ClearBoard;
import model.GizmoFunctions;
import model.Model;
import model.Square;
import model.Praser;

public class BuildListener implements ActionListener {
	
	private Model model;
	public static String type = "none";
	// use this to determine what function to perform
	public static String function = "none";
	public static Color colour = null;

	public BuildListener(Model m) {
		model = m;		
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {
		switch (e.getActionCommand()){
		case "Square":
			System.out.println("Square has been selected");
			type = "Square";
			function = "none";
			break;
		case "Triangle":
			System.out.println("Triangle has been selected");
			type = "Triangle";
			function = "none";
			break;
		case "Circle":
			System.out.println("Circle has been selected");
			type = "Circle";
			function = "none";
			break;
		case "Absorber":
			System.out.println("Absorber has been selected");
			type = "Absorber";
			function = "none";
			break;
		case "Left Flipper":
			System.out.println("Left Flipper has been selected");
			type = "LeftFlipper";
			function = "none";
			break;
		case "Right Flipper":
			System.out.println("Right Flipper has been selected");
			type = "RightFlipper";
			function = "none";
			break;
		case "Ball":
			System.out.println("Ball has been selected");
			type = "ball";
			function = "none";
			break;
		case "Ball Properties":
			System.out.println("Ball Properties has been selected");
			BallPropertiesWindow b = new BallPropertiesWindow(model);
			b.BPWGUI();
			break;
		case "RUN MODE":
			System.out.println("Run Mode has been selected");
			RunModeGUI rmg = new RunModeGUI(model);
			BuildModeGUI.window.dispose();
			break;
			
			
			
		/**************************************************/	
		case "Move Gizmo":	
			System.out.println("Move has been selected");
			if(Model.gizmos.isEmpty()){
				BuildModeGUI.info_bar.setForeground(Color.red);
				BuildModeGUI.info_bar.setText("There are no gizmos to move");
			}
			else {
				BuildModeGUI.info_bar.setForeground(Color.green);
				BuildModeGUI.info_bar
						.setText("Please select the gizmo you wish to move");
				type = "none";
				function = "move";
			}
			break;
		case "Delete Gizmo":
			System.out.println("Delete has been selected");
			BuildModeGUI.info_bar.setForeground(Color.green);
			BuildModeGUI.info_bar.setText("Please select the gizmo you wish to delete");
			type = "none";
			function = "delete";
			break;
		case "Connect Gizmo":
			System.out.println("Connect Gizmo has been selected");
			BuildModeGUI.info_bar.setForeground(Color.green);
			BuildModeGUI.info_bar.setText("Please select a gizmo");
			type = "none";
			function = "connect";
			break;
		case "Disconnect Gizmo":
			System.out.println("Disconnect Gizmo has been selected");
			BuildModeGUI.info_bar.setForeground(Color.green);
			BuildModeGUI.info_bar.setText("Please select a gizmo");
			type = "none";
			function = "disconnect";
			break;
		case "Rotate Gizmo":
			System.out.println("Rotate has been selected");
			BuildModeGUI.info_bar.setForeground(Color.green);
			BuildModeGUI.info_bar.setText("Please select the gizmo you wish to rotate");
			type = "none";
			function = "rotate";
			break;
		case "Colour":
			System.out.println("Colour has been selected");
			BuildModeGUI.info_bar.setForeground(Color.green);
			BuildModeGUI.info_bar.setText("Please select the colour you want");
			ColourWindow cw = new ColourWindow(model);
			cw.ColourWindowGUI();
			break;
		case "Load...":
			System.out.println("Load has been selected");
			ClearBoard cb = new ClearBoard(model);
			cb.clear();			
			Praser p = new Praser();
			p.fileReader(model);
			break;
		case "Save...":
			System.out.println("Save has been selected");
			//new SaveWindow(model);
			try {
				GizmoFunctions.saveBoard();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Clear board...":
			System.out.println("Clear has been selected");
			ClearBoard c = new ClearBoard(model);
			c.clear();
			break;
		/**************************************************/
		
		/*
		 * Listeners for the color window buttons	
		 */
		case "red":
			System.out.println("Red has been selected");
			ColourWindow.window.dispose();
			function = "colour";
			colour = Color.red;
			break;
		case "blue":
			System.out.println("Blue has been selected");
			ColourWindow.window.dispose();
			colour = Color.blue;
			function = "colour";
			break;
		case "green":
			System.out.println("Green has been selected");
			ColourWindow.window.dispose();
			colour = Color.green;
			function = "colour";
			break;
		case "pink":
			System.out.println("Pink has been selected");
			ColourWindow.window.dispose();
			colour = Color.magenta;
			function = "colour";
			break;
		case "yellow":
			System.out.println("Yellow has been selected");
			ColourWindow.window.dispose();
			colour = Color.yellow;
			function = "colour";
			break;
		case "orange":
			System.out.println("Orange has been selected");
			ColourWindow.window.dispose();
			colour = Color.orange;
			function = "colour";
			break;
		case "Ok":
			System.out.println("Ok has been selected");
			GizmoFunctions.ballProperties();
			BallPropertiesWindow.window.dispose();
			break;
		case "Cancel":
			System.out.println("Cancel has been selected");
			BallPropertiesWindow.window.dispose();
			break;
		}		
	}
}
