package model;

import java.awt.Color;
import java.util.ArrayList;

import view.BuildModeGUI;
import controller.BuildListener;

public class BuildShapes {

	private static Model m;
	private int x;
	private int y;
	public static int square_id = 1;
	public static int tri_id = 1;
	public static int cir_id = 1;
	public static int ab_id = 1;
	public static int lf_id = 1;
	public static int rf_id = 1;
	public static int b_id = 1;
	private static int absorberx = 0;
	private static int absorbery = 0;
	public static boolean draw_shape = true;
	//public static ArrayList<Gizmo> gizmos = Model.gizmos;
	public static BoardLocations bl = new BoardLocations(m);
	
	public BuildShapes(int xpos, int ypos, Model model) {
		x = xpos;
		y = ypos;
		m = model;
		//addShape(x, y, m);
	}

	public void addShape(int xpos, int ypos, Model m) {
		//boolean draw_shape = true;
		int height = 25;
			
		/*for (int i = 0; i < Model.gizmos.size(); i++) {
			System.out.println("Gizmo " + i + ":");
			System.out.println("X = " + Model.gizmos.get(i).getX1());
			System.out.println("Y = " + Model.gizmos.get(i).getY1());
			System.out.println("X2 = " + ((Model.gizmos.get(i).getX1()) + (Model.gizmos.get(i).getWidth())));
			System.out.println("Y2 = " + ((Model.gizmos.get(i).getY1()) + (Model.gizmos.get(i).getHeight())));*/
			
			/*if(!Model.gizmos.get(i).getOpCode().equals("Circle") &&
					!Model.gizmos.get(i).getOpCode().equals("Ball"))
			if (Model.gizmos.get(i).getX1() <= xpos 
					&& xpos <= Model.gizmos.get(i).getX1() + Model.gizmos.get(i).getWidth()
					&& Model.gizmos.get(i).getY1() <= ypos
					&& ypos <= Model.gizmos.get(i).getY1() + Model.gizmos.get(i).getHeight()) {
				draw_shape = false;
			}
			else{
				if(Model.gizmos.get(i).getX1()-12.5 <= xpos 
						&& xpos < ((Model.gizmos.get(i).getX1()-12.5) + Model.gizmos.get(i).getWidth()) 
						&& Model.gizmos.get(i).getY1()-12.5 <= ypos
						&& y < ((Model.gizmos.get(i).getY1()-12.5) + Model.gizmos.get(i).getHeight())){
					draw_shape = false;
				}
			}*/
			System.out.println(xpos + " " + ypos);
			draw_shape = bl.isAvailable(xpos, ypos);
			
		//}
		// draw shape is false when there is all ready a gizmo in this position
		if (draw_shape) {
			 if (BuildListener.type.equals("Square")) {
				String square_name = "S" + square_id;
				System.out.println("Square Name: " + square_name);
				System.out
						.println("Grid position: (" + xpos + "," + ypos + ")");
				BuildModeGUI.info_bar.setForeground(Color.BLUE);
				BuildModeGUI.info_bar.setText("Square Gizmo added to board");
				//SquareGizmo s = new SquareGizmo("Square", square_name, xpos,
					//	ypos, height);
				//gizmos.add(s);
				//model.addSquare(s);
				m.addGizmo("Square", square_name, xpos, ypos, height,
						absorberx, absorbery, "red");
				square_id++;
				// might not need this next line
				// BuildListener.type = "none";	
				bl.setInvalid(xpos, ypos);
				 draw_shape = true;

			} else if (BuildListener.type.equals("Triangle")) {
				String tri_name = "T" + tri_id;
				System.out
						.println("Grid position: (" + xpos + "," + ypos + ")");
				// Triangle t = new Triangle(xpos, ypos, 3, width);
				// model.addTriangle(t);
				BuildModeGUI.info_bar.setForeground(Color.BLUE);
				BuildModeGUI.info_bar.setText("Triangle Gizmo added to board");
				m.addGizmo("Triangle", tri_name, xpos, ypos, height,
						absorberx, absorbery, "pink");
				// set the value to false once a gizmo is in that location
				// BuildListener.type = "none";
				tri_id++;
				bl.setInvalid(xpos, ypos);
				draw_shape = true;
			}
			// if circles are in wrong place, add 12.5 to xpos and ypos
			else if (BuildListener.type.equals("Circle")) {
				String cir_name = "C" + cir_id;
				// System.out.println("Grid position: (" + xpos + "," + ypos +
				// ")");
				// Circle c = new Circle((double) xpos, (double) ypos, 10);
				BuildModeGUI.info_bar.setForeground(Color.BLUE);
				BuildModeGUI.info_bar.setText("Circle Gizmo added to board");
				m.addGizmo("Circle", cir_name, xpos, ypos, height,
						absorberx, absorbery, "yellow");
				cir_id++;
				bl.setInvalid(xpos, ypos);
				draw_shape = true;
				// might not need this next line
				// BuildListener.type = "none";
			} else if (BuildListener.type.equals("Absorber")) {
				String ab_name = "A" + ab_id;
				
				System.out
						.println("Grid position: (" + xpos + "," + ypos + ")");
				// need to change the values in here, just an example
				// Absorber a = new Absorber(xpos, ypos, xpos + 500, ypos + 50
				// );
				// model.addAbsorber(a);
				BuildModeGUI.info_bar.setForeground(Color.BLUE);
				BuildModeGUI.info_bar.setText("Absorber added to board");
				
				// need to change absorberx and absorbery values in this. 
				m.addGizmo("Absorber", ab_name, xpos, ypos, 500, 50,0, "green");
				
				ab_id++;
				for(AbsorberGizmo a: m.getAbs()){
					for(double i = a.getX1(); i < a.getWidth(); i+=25){
						for(double j = a.getY1(); j < (a.getY1() + a.getHeight()); j+=25){
					    	int x = (int) i;
							int y = (int) j;
							bl.setInvalid(x, y);
						}
					}
				}
				
				draw_shape = true;
				// might not need this next line
				// BuildListener.type = "none";
			} else if (BuildListener.type.equals("LeftFlipper")) {
				String lf_name = "LF" + lf_id;
				
				System.out
						.println("Grid position: (" + xpos + "," + ypos + ")");
				// LeftFlipper lf = new LeftFlipper(xpos, ypos);
				// model.addLeftFlipper(lf);
				BuildModeGUI.info_bar.setForeground(Color.BLUE);
				BuildModeGUI.info_bar.setText("Left Flipper added to board");
				m.addGizmo("LeftFlipper", lf_name, xpos, ypos, height,
						absorberx, absorbery, "orange");
				lf_id++;				
				
				
				for(Flipper f : m.getFlipper()){
					for(double i = f.getX1(); i < (f.getX1() +f.getWidth()); i+=25){
						for(double j = f.getY1(); j < (f.getY1() + f.getHeight()); j+=25){
							int x = (int) i;
							int y = (int) j;
							bl.setInvalid(x, y);
						}
					}
				}
				
				draw_shape = true;
				// might not need this next line
				// BuildListener.type = "none";
			} else if (BuildListener.type.equals("RightFlipper")) {
				String rf_name = "RF" + rf_id;
				
				System.out
						.println("Grid position: (" + xpos + "," + ypos + ")");
				// RightFlipper rf = new RightFlipper(xpos, ypos);
				// model.addRightFlipper(rf);
				BuildModeGUI.info_bar.setForeground(Color.BLUE);
				BuildModeGUI.info_bar.setText("Right Flipper added to board");
				m.addGizmo("RightFlipper", rf_name, xpos, ypos, height,
						absorberx, absorbery, "red");
				
				for(Flipper f : m.getFlipper()){
					for(double i = f.getX1(); i < (f.getX1() +f.getWidth()); i+=25){
						for(double j = f.getY1(); j < (f.getY1() + f.getHeight()); j+=25){
							int x = (int) i;
							int y = (int) j;
							bl.setInvalid(x, y);
						}
					}
				}
				
				rf_id++;
				draw_shape = true;
				// might not need this next line
				// BuildListener.type = "none";
				
				
			} else if (BuildListener.type.equals("ball")) {
				String b_name = "B" + b_id;
				
				System.out
						.println("Grid position: (" + xpos + "," + ypos + ")");
				// need to change the 0's, need to decided how to do this
				// Ball b = new Ball((double) xpos, (double) ypos, 100, 100);
				// model.addBall(b);
				BuildModeGUI.info_bar.setForeground(Color.BLUE);
				BuildModeGUI.info_bar.setText("Ball added to board");
				m.addGizmo("Ball", b_name, xpos, ypos, height, absorberx,
						absorbery, "blue");
				bl.setInvalid(xpos, ypos);
				draw_shape = true;
			}
			 b_id++;			
		}
		else {
			BuildModeGUI.info_bar.setForeground(Color.RED);
			BuildModeGUI.info_bar.setText("Gizmo not added, there is a gizmo all ready in the position selected");
			System.out.println("There is a gizmo all ready in this position");
		}		 
	}	 
}
