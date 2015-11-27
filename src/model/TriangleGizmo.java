package model;

import java.awt.Color;
import java.util.ArrayList;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * @author Maciej Zajac GizmoBall 27/02/2015
 */
public class TriangleGizmo extends Gizmo implements IGizmo {
	//private double xpos;
	//private double ypos;
	//private Color color;
	private double height;
	//private String id;
	private ArrayList<Circle> cs;
	private ArrayList<LineSegment> ls;
	private int L = 25;
	LineSegment[] line;
	Circle[] circle;
	private Color defaultColour;

	//constructor -- setting up coordinates and height
	public TriangleGizmo(String op, String ID, double x, double y, double s, Color c){
		xpos1 = x;
		ypos1 = y;
		height = s;
		color = c;
		opcode = op;
		id =ID;
		defaultColour = c;
		
		line = new LineSegment[3];
		circle = new Circle[3];
		line = new LineSegment[3];
		line[0] = new LineSegment( (int)x, (int)y, (int)x+(int)s, (int)y);
		line[1] = new LineSegment( (int)x+(int)s, (int)y, (int)x, (int)y+(int)s);
		line[2] = new LineSegment( (int)x, (int)y+(int)s, (int)x, (int)y);
		
		
		circle = new Circle[3];
		circle[0] = new Circle( (int)x, y, 0);
		circle[1] = new Circle( x+s, y, 0);
		circle[2] = new Circle( x, y+s, 0);		
		
		ls = new ArrayList<LineSegment>();		
		
		cs = new ArrayList<Circle>();
		cs.add(new Circle(xpos1, ypos1, 0));
		cs.add(new Circle(xpos1+height, ypos1, 0));
		cs.add(new Circle(xpos1, ypos1+height, 0));
	}
	
	public ArrayList<Circle> getCircles(){
		
		return cs;
	}
	
	//Getters
	public double getH(){
		return height;
	}
	
	public ArrayList<LineSegment> getLSegments(){
		for (int i=0 ; i < line.length ; i++){
			ls.add(line[i]);
		}
		return ls;
	}
	public void rotate() {
		Vect centre = new Vect(xpos1+L/2, ypos1+L/2);
		for (int i=0 ; i < line.length ; i++){
			System.out.println("			before	"+line[i]);
		line[i] = Geometry.rotateAround(line[i], centre, Angle.DEG_90);
		System.out.println("				after "+line[i]);
		}
		for (int i=0 ; i < circle.length ; i++)
			circle[i] = Geometry.rotateAround(circle[i], centre, Angle.DEG_90);
	}
	
	/*
	public void rotate(){
		System.out.println("rot");
		Vect centre = new Vect(xpos1+L/2, ypos1+L/2);
		System.out.println("vect = " + centre);
		for (LineSegment l : ls){
			System.out.println("ls = " + l);
			l = Geometry.rotateAround(l, centre, Angle.DEG_90);
			
		}
		for (Circle c : cs)
			c = Geometry.rotateAround(c, centre, Angle.DEG_90);	
	}*/
	
	public Color getColour(){
		return color;
	}
	
	public void setColour(Color c){
		color = c;
	}
	
	public Color getDefaultColour(){
		return defaultColour;
	}
	
	public void setDefaultColour(Color c){
		defaultColour = c;
	}
	
	public Color changeColour(){
		return Color.white;
	}
	
}
