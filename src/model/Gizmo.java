package model;

import java.awt.Color;
/**
 * @author Maciej Zajac GizmoBall 27/02/2015
 */
public class Gizmo implements IGizmo {

	protected double xpos1;
	protected double ypos1;
	protected Color color;
	protected String id;
	protected String opcode;
	private double DefaultX;
	private double DefaultY;
	private double height;
	private double width;
	
	public Color getColor() {
		return color;
	}

	public String getID() {
		return id;
	}
	
	public String getOpCode() {
		return opcode;
	}

	public double getX1() {

		return xpos1;
	}

	@Override
	public double getY1() {
		return ypos1;
	}
	
	
	
	public void setColor(Color c) {
		color = c;
	}

	public void setID(String s) {
		id = s;
	}
	
	public void setOpCode(String s) {
		opcode = s;
	}

	public void setX1(double x) {
		xpos1 = x;
		DefaultX = x;
	}

	public void setY1(double y) {
		ypos1 = y;
		DefaultY = y;
	}	
	
	public double getDefaultX(){
		return DefaultX;
	}
	
	public double getDefaultY(){
		return DefaultY;
	}
	
	public void setHeight(double h){
		height = h;
	}
	
	public void setWidth(double w){
		width = w;
	}
	
	public double getHeight(){
		return height;
	}
	
	public double getWidth(){
		return width;
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	