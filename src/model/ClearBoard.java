package model;

import java.util.ArrayList;

import view.BuildModeGUI;

/*
 * Full class for this seems unnecessary
 */
public class ClearBoard {
	
	private Model m;
	
	public ClearBoard(Model model){
		this.m = model;
	}
	
	public static void clear(){
		//System.out.println("Gizmos ArrayList size: " + Model.gizmos);
		Model.gizmos.clear();
		//System.out.println("Gizmos ArrayList size: " + Model.gizmos);
		BuildModeGUI.window.repaint();
		
	}
}
