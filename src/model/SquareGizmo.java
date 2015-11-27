package model;

import java.awt.Color;
import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;
/**
 * @author Maciej Zajac GizmoBall 27/02/2015
 */
public class SquareGizmo extends Gizmo implements IGizmo {

	//private double xpos;
	//private double ypos;
	//private Color color;
	private double height;
	//private String id;
	private ArrayList<Circle> cs;
	private ArrayList<LineSegment> ls;
	private Color defaultColour;
	//private int L = 25;
	
	//constructor
	public SquareGizmo(String op, String ID, double x, double y, double h, Color c){
		xpos1 = x;
		ypos1 = y;
		height = h;
		color  = c;
		defaultColour = c;
		opcode = op;
		id =ID;
	}
	
	public ArrayList<Circle> getCircles(){
		cs = new ArrayList<Circle>();
		cs.add(new Circle(xpos1, ypos1, 0));
		cs.add(new Circle(xpos1+height, ypos1, 0));
		cs.add(new Circle(xpos1+height, ypos1+height, 0));
		cs.add(new Circle(xpos1, ypos1+height, 0));
		return cs;
	}
	
	public double getH(){
		return height;
	}
	
	public ArrayList<LineSegment> getLSegments(){
		ls = new ArrayList<LineSegment>();
		ls.add(new LineSegment(xpos1, ypos1, xpos1+height, ypos1));
		ls.add(new LineSegment(xpos1, ypos1, xpos1, ypos1+height));
		ls.add(new LineSegment(xpos1+height, ypos1, xpos1+height, ypos1+height));
		ls.add(new LineSegment(xpos1, ypos1+height, xpos1+height, ypos1+height));
		return ls;
	}
	
	public Color getColour(){
		return color;
	}
	
	public void setColour(Color c){
		color = c;
	}
	
	public Color changeColour(){
		 return Color.WHITE;
	}
	
	public Color getDefaultColour(){
		return defaultColour;
	}
	
	public void setDefaultColour(Color c){
		defaultColour = c;
	}
}
