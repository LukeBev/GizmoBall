package model;

import java.awt.Color;

/**
 * @author Maciej Zajac GizmoBall 27/02/2015
 */
public interface IGizmo {
	
	public Color getColor();
	public String getID();
	public double getX1();
	public double getY1();
	
	public void setColor (Color c);
	public void setID(String s);
	public void setX1(double x);
	public void setY1(double y);
	

}
