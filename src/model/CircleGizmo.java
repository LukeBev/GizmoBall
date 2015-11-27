package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

/**
 * @author Maciej Zajac GizmoBall 27/02/2015
 */
public class CircleGizmo extends Gizmo implements IGizmo {
	
	private double radius;
	private Color defaultColour;
	

	//constructor -- setting up coordinates, radius and color
	public CircleGizmo(String op, String ID, double x, double y, Color c){
		xpos1 = x + 12.5;
		ypos1 = y+ 12.5;
		radius = 10;
		color = c;
		opcode = op;
		id = ID;
		defaultColour = c;
	}
	//setters
	
	public Circle getCircle(){
		return new Circle(xpos1, ypos1, radius);
	}
	
	
	//getters
	
	public double getRadius(){
		return radius;
	}
	public void setRadius(double r){
		radius = r;
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
