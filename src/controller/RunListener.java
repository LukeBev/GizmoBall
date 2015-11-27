package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import physics.Vect;
import view.BuildModeGUI;
import view.RunModeGUI;
import model.GizmoFunctions;
import model.Model;

public class RunListener implements ActionListener, KeyListener {

	private Timer timer;
	private Model model;

	public RunListener(Model m) {
		model = m;
		timer = new Timer(50, this);
		//timer.start();	
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
			
		} else
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				for(int i = 0; i < model.balls.size(); i++){
					model.getBall().get(i).setStopped(false);
				}
				break;
			case "Stop":
				timer.stop();
				break;
			case "Tick":
				model.moveBall();
			timer.stop();
				break;
			case "Quit":
				timer.stop();
				System.exit(0);
				break;
			case "BUILD MODE":
				System.out.println("Build mode has been selected");
				timer.stop();
				//GizmoFunctions gf = new GizmoFunctions(model);
				// Need to reset the gizmos to their default colour
				RunModeGUI.window.dispose();
				BuildModeGUI bmg = new BuildModeGUI(model);				
				break;
			case "Restart":
				System.out.println("Restart has been selected");
				timer.stop();
				GizmoFunctions.restartGame();
				timer.start();
			}
	}

	
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed"+e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_X){
			for(int i = 0; i < model.flippers.size(); i++){
				if(model.flippers.get(i).getOpCode().equals("RightFlipper")){
				model.flippers.get(i).keyPressed = true;
				model.moveFlipper();
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_Z){
			for(int i = 0; i < model.flippers.size(); i++){
				if(model.flippers.get(i).getOpCode().equals("LeftFlipper")){
					model.flippers.get(i).keyPressed = true;
					model.moveFlipper();
					}

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		System.out.println("here"+e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_X){
			for(int i = 0; i < model.flippers.size(); i++){
				if(model.flippers.get(i).getOpCode().equals("RightFlipper")){
				model.flippers.get(i).keyPressed = false;
				model.moveFlipper();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_Z){
			for(int i = 0; i < model.flippers.size(); i++){
				if(model.flippers.get(i).getOpCode().equals("LeftFlipper")){
				model.flippers.get(i).keyPressed = false;
				model.moveFlipper();
				}
			}
		}
	}
	public void keyTyped(KeyEvent e) {
	}
}
