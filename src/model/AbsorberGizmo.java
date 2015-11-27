package model;

import java.awt.Color;
import java.util.ArrayList;

import physics.LineSegment;

/**
 * @author Maciej Zajac GizmoBall 03/03/2015
 */

public class AbsorberGizmo extends Gizmo implements IGizmo {

	private double xpos2;
	private double ypos2;
	//private Color color;
	private double height;
	private double widht;
	//private String id;
	private ArrayList<LineSegment> ls;
	LineSegment l ;
	
	public AbsorberGizmo(String op, String ID ,double x1, double y1, double x2, double y2, Color c){
		xpos1 = x1;
		ypos1 = y1;
		xpos2 = x2;
		ypos2 = y2;	
		color = c;
		opcode = op;
		id = ID;
		ls = new ArrayList<LineSegment>();
		l = new LineSegment(xpos1, ypos1, xpos2, ypos1);
		ls.add(l);
		
	}
	
	public ArrayList<LineSegment> getLs(){
		
		return ls;
	}
	public double getX2(){
		return xpos2;
	}
	public double getY2(){
		return ypos2;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}

	

}
