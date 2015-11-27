package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import physics.Circle;
import view.BuildModeGUI;
import model.AbsorberGizmo;
import model.BuildShapes;
import model.Gizmo;
import model.GizmoFunctions;
import model.Model;
import model.SquareGizmo;
import model.TriangleGizmo;

public class MouseEventListener implements MouseListener{
	
	private Model model;
	boolean move_gizmo = false;
	public static boolean gizmo_found = false;
	private int x;
	private int y;
	//public static ArrayList<Gizmo> g = Model.gizmos;
	public static String gizmo_name = "none";
	public static int gizmo_index;
	private static String connect_id_1;
	private static String connect_id_2;
		
	public MouseEventListener(Model m){
		model = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {			
		x = e.getX();
		y = e.getY();
		
		// divide it by 25 to get the posotion to draw it on board
		double exact_x = x / 25;
		// cast to int to remove the decimal places
		int xpos = ((int) exact_x) * 25;
		// divide it by 25 to get the posotion to draw it on board
		double exact_y = y / 25;
		// cast to int to remove the decimal places
		int ypos = ((int) exact_y) * 25;
		
		String function = BuildListener.function;
		Color colour = BuildListener.colour;
		System.out.println("Mouse Pressed. X = " + x + " Y = " + y);
				
		if(gizmo_found){			
			if(function.equals("move")){
				System.out.println("WHEN IS THIS HAPPENING");
				System.out.println("Moving Gizmo");
				BuildListener.function = "none";
				function = "none";
				gizmo_found = false;
				GizmoFunctions g = new GizmoFunctions(model);
				g.moveGizmo(xpos, ypos, gizmo_index);
			}
			if(function.equals("connect")){
				GizmoFunctions g = new GizmoFunctions(model);
				g.findConnectGizmos(xpos, ypos);
				g.connectGizmos();
				/*for(int i = 0; i < Model.gizmos.size(); i++){
					if(!Model.gizmos.get(i).getOpCode().equals("Circle")){
						if(!Model.gizmos.get(i).getOpCode().equals("Circle")){
							if (Model.gizmos.get(i).getX1() <= xpos && 
									xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
									&& Model.gizmos.get(i).getY1() <= ypos 
									&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {						
						System.out.println("Connecting Gizmos");
						BuildListener.function = "none";
						function = "none";
						connect_id_2 = model.gizmos.get(i).getID();
						GizmoFunctions g = new GizmoFunctions(model);
						g.connectGizmos(connect_id_1, connect_id_2);
						gizmo_found = false;
					}
					}
					else{
						if(Model.gizmos.get(i).getX1()-12.5 <= xpos 
								&& xpos < ((Model.gizmos.get(i).getX1()-12.5) + Model.gizmos.get(i).getWidth()) 
								&& Model.gizmos.get(i).getY1()-12.5 <= ypos
								&& y < ((Model.gizmos.get(i).getY1()-12.5) + Model.gizmos.get(i).getHeight())){
							System.out.println("Connecting Gizmos");
							BuildListener.function = "none";
							function = "none";
							connect_id_2 = model.gizmos.get(i).getID();
							GizmoFunctions g = new GizmoFunctions(model);
							g.connectGizmos(connect_id_1, connect_id_2);
							gizmo_found = false;
						}
					}
					}
				}*/				
			}
			else if(function.equals("disconnect")){
				GizmoFunctions g = new GizmoFunctions(model);
				g.findConnectGizmos(xpos, ypos);
				g.disconnectGizmos();
			}
			
			/*else if(function.equals("delete")){
				System.out.println("Deleting Gizmo");
				GizmoFunctions g = new GizmoFunctions(model);
				g.deletedGizmo(gizmo_index);
				//GizmoFunctions.deletedGizmo(gizmo_index);
			}*/
			//g.get(gizmo_index).setX1(xpos);
			//g.get(gizmo_index).setY1(ypos);
		}
		
		// if function is set we want to perform the correct function
		if (!function.equals("none")) {
			if (function.equals("move")) {
				System.out.println("Function equals move");
				GizmoFunctions g = new GizmoFunctions(model);
				g.findGizmo(xpos, ypos);
				
				/*for (int i = 0; i < Model.gizmos.size(); i++) {
					
					if(!Model.gizmos.get(i).getOpCode().equals("Circle")){
					if (Model.gizmos.get(i).getX1() <= xpos && 
							xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
							&& Model.gizmos.get(i).getY1() <= ypos 
							&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
						System.out.println("There is a gizmo in this location");
						BuildModeGUI.info_bar.setForeground(Color.green);
						BuildModeGUI.info_bar.setText("Please select where this gizmo should be moved to");
						gizmo_found = true;
						gizmo_index = i;
					}
					}
					else{
						if(Model.gizmos.get(i).getX1()-12.5 <= xpos 
								&& xpos < ((Model.gizmos.get(i).getX1()-12.5) + Model.gizmos.get(i).getWidth()) 
								&& Model.gizmos.get(i).getY1()-12.5 <= ypos
								&& y < ((Model.gizmos.get(i).getY1()-12.5) + Model.gizmos.get(i).getHeight())){
							System.out.println("There is a gizmo in this location");
							BuildModeGUI.info_bar.setForeground(Color.green);
							BuildModeGUI.info_bar.setText("Please select where this gizmo should be moved to");
							gizmo_found = true;
							gizmo_index = i;
						}
					}
				}*/
			}

			else if (function.equals("delete")) {
				System.out.println("Function equals delete");
				GizmoFunctions g = new GizmoFunctions(model);
				g.findGizmo(xpos, ypos);
				g.deletedGizmo(gizmo_index);
			}
				/*for (int i = 0; i < Model.gizmos.size(); i++) {
				//GizmoFunctions g = new GizmoFunctions(model);
				//g.findGizmo(xpos, ypos);
					if(!Model.gizmos.get(i).getOpCode().equals("Circle")){
						if (Model.gizmos.get(i).getX1() <= xpos && 
								xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
								&& Model.gizmos.get(i).getY1() <= ypos 
								&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
						System.out.println("There is a gizmo in this location");
						gizmo_found = true;
						gizmo_index = i;
					}
					}
					else {
						if (Model.gizmos.get(i).getX1() <= xpos && 
								xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
								&& Model.gizmos.get(i).getY1() <= ypos 
								&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
							System.out.println("There is a gizmo in this location");							
							gizmo_found = true;
							gizmo_index = i;
						}
					}
					
					if(gizmo_found){
						//GizmoFunctions g = new GizmoFunctions(model);
						System.out.println("Gizmo Index = " + gizmo_index);
						GizmoFunctions g = new GizmoFunctions(model);
						g.deletedGizmo(gizmo_index);
						function = "none";
						BuildListener.function = "none";
					}
					if(!gizmo_found){
						BuildModeGUI.info_bar.setForeground(Color.red);
						BuildModeGUI.info_bar.setText("There is no gizmo in this location to delete");
					}
				}
				gizmo_found = false;
			}*/
		
		else if(function.equals("rotate")){
			System.out.println("Function equals rotate");
			GizmoFunctions g = new GizmoFunctions(model);
			g.findGizmo(xpos, ypos);
			g.rotateGizmo(gizmo_index, xpos, ypos);
		}
			/*for (int i = 0; i < Model.gizmos.size(); i++) {
				if(!Model.gizmos.get(i).getOpCode().equals("Circle")){
					if (Model.gizmos.get(i).getX1() <= xpos && 
							xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
							&& Model.gizmos.get(i).getY1() <= ypos 
							&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
					System.out.println("There is a gizmo in this location");
					gizmo_found = true;
					gizmo_index = i;
				}
				}
				else {
					if (Model.gizmos.get(i).getX1() <= xpos && 
							xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
							&& Model.gizmos.get(i).getY1() <= ypos 
							&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
						System.out.println("There is a gizmo in this location");							
						gizmo_found = true;
						gizmo_index = i;
					}
				}
				if(gizmo_found){
					//GizmoFunctions g = new GizmoFunctions(model);
					BuildListener.function = "none";
					function = "none";
					g.rotateGizmo(gizmo_index);
				}
				if(!gizmo_found){
					BuildModeGUI.info_bar.setForeground(Color.red);
					BuildModeGUI.info_bar.setText("There is no gizmo in this location to rotate.");
				}
			}
			gizmo_found = false;
		}*/			
		else if(function.equals("colour")){
			System.out.println("Function equals colour");
			BuildModeGUI.info_bar.setForeground(Color.green);
			BuildModeGUI.info_bar.setText("Please select the gizmo you wish to change the colour of.");
			
			GizmoFunctions g = new GizmoFunctions(model);
			g.findGizmo(xpos, ypos);
			g.changeGizmoColour(gizmo_index, xpos, ypos, colour);
		}
			/*for (int i = 0; i < Model.gizmos.size(); i++) {
				if(!Model.gizmos.get(i).getOpCode().equals("Circle")){
					if (Model.gizmos.get(i).getX1() <= xpos && 
							xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
							&& Model.gizmos.get(i).getY1() <= ypos 
							&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
					System.out.println("There is a gizmo in this location");
					gizmo_found = true;
					gizmo_index = i;
				}
				}
				else {
					if (Model.gizmos.get(i).getX1() <= xpos && 
							xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
							&& Model.gizmos.get(i).getY1() <= ypos 
							&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
						System.out.println("There is a gizmo in this location");							
						gizmo_found = true;
						gizmo_index = i;
					}
				}
				if(gizmo_found){
					BuildListener.function = "none";
					function = "none";
					//GizmoFunctions g = new GizmoFunctions(model);
					g.changeGizmoColour(gizmo_index, xpos, ypos, colour);
				}
				if(!gizmo_found){
					BuildModeGUI.info_bar.setForeground(Color.red);
					BuildModeGUI.info_bar.setText("There is no gizmo in this location to change the colour of.");
				}
			}
			gizmo_found = false;
		}*/
		else if(function.equals("connect")){
			System.out.println("Function Equals Connect");
			GizmoFunctions g = new GizmoFunctions(model);
			g.findConnectGizmos(xpos, ypos);
			/*for(int i = 0; i < Model.gizmos.size(); i++){
				if(!Model.gizmos.get(i).getOpCode().equals("Circle")){
					if (Model.gizmos.get(i).getX1() <= xpos && 
							xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
							&& Model.gizmos.get(i).getY1() <= ypos 
							&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
					System.out.println("There is a gizmo in this location");
					BuildModeGUI.info_bar.setForeground(Color.green);
					BuildModeGUI.info_bar.setText("Please select the gizmo you want to connect to");
					gizmo_found = true;
					gizmo_index = i;
				}
				}
				else {
					if (Model.gizmos.get(i).getX1() <= xpos && 
							xpos < (Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()) 
							&& Model.gizmos.get(i).getY1() <= ypos 
							&& ypos < (Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight())) {
						System.out.println("There is a gizmo in this location");							
						gizmo_found = true;
						gizmo_index = i;
					}
				}
				if(gizmo_found)
					connect_id_1 = model.gizmos.get(i).getID();
					break;				
			}*/			
		}
		else if (function.equals("disconnect")){
			System.out.println("Function equals disconnect");
			GizmoFunctions g = new GizmoFunctions(model);
			g.findConnectGizmos(xpos, ypos);
		}
	}
		
		else{		
			BuildShapes b = new BuildShapes(xpos, ypos, model);
			b.addShape(xpos, ypos, model);
			
		}
	
		
		BuildModeGUI.window.repaint();
	}

	
	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {		
		
	}
}
