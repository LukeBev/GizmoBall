package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

/**
 * @author Maciej Zajac GizmoBall 27/02/2015
 */
public class Ball extends Gizmo implements IGizmo {
	
	//private double xpos;
	//private double ypos;
	//private Color color;
	private Vect velocity;
	private double radius;
	//private String id;
	//private Circle circle;
	private boolean stopped;
	private double defaultX;
	private double defaultY;
	private double velo_x;
	private double velo_y;

	
	//constructor --set coordinates, velo, color and ID
	public Ball(String op, String ID,  double x, double y, double xv, double yv, Color c){
		xpos1 = x + 12.5;
		ypos1 = y + 12.5;
		velocity = new Vect(xv, yv);
		color =  c;
		radius  = 10;
		stopped = false;
		opcode = op;
		id = ID;
		defaultX = x;
		defaultY = y;
		velo_y = yv;
		velo_x = xv;
		
	}
	
	public void setStopped(Boolean b){
		stopped = b;
	}
	
	public boolean getStopped(){
		return stopped;
	}
	
	public Circle getCircle(){
		return new Circle(xpos1, ypos1, radius);
	}
	public double getRadius(){
		return radius;
	}
	
	
	public Vect getVelo(){
		return velocity;
	}
	public void setRadius(double r){
		radius = r;
	}
	public void setVelo(Vect v){
		velocity = v;
	}
	
	public double getDefaultX(){
		return defaultX;
	}
	
	public double getDefaultY(){
		return defaultY;
	}
	
	public Color getColour(){
		return color;
	}
	
	public void setColour(Color c){
		color = c;
	}
	
	public double getXV(){
		return velo_x;
	}
	public double getYV(){
		return velo_y;
	}
}
