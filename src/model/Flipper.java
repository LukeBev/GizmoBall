package model;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
/**
 * @author Maciej Zajac GizmoBall 03/03/2015
 */

public class Flipper extends Gizmo implements IGizmo {
	
	public int direction = 0;
	private double tx1,tx2,ty1,ty2;
	private double sx1,sx2,sy1,sy2;
	public boolean keyPressed = false;
	private AffineTransform at = new AffineTransform();
	private int deg = 0;
	private double xpos2;
	private double ypos2;
	private ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
	private ArrayList<Circle> statC = new ArrayList<Circle>();
	private ArrayList<Circle> pivotC = new ArrayList<Circle>();
	
	
	
	public Flipper(String opc, String iD, double x1, double y1, Color c){
		opcode = opc;
		id = iD;
		xpos1 = x1;
		ypos1 = y1;
		 
		 tx1=0;
		 ty1=0;
		if(opcode.equals("LeftFlipper")){
			System.out.println("Setting up LF");
			xpos2 = xpos1;	
			//ypos2 = ypos1+50;
			this.setColor(c);
			 tx2 = xpos1-15;
			  ty2= ypos1+40;
			  tx1= xpos1;
			  ty1 = ypos1;
			  sx1 = xpos1;
			  sy1 = ypos1+5;
			  sx2 =sx1+15;
			  sy2 = sy1+40;
			
		}else if(opcode.equals("RightFlipper")){
			System.out.println("Setting up RF");
			this.setColor(c);
			xpos2 = xpos1 +35;	
			  tx2 = xpos1+20;
			  ty2= ypos1+40;
			  tx1 = xpos1 + 35;
			  ty1 = ypos1;
			  sx1 = xpos1+35;
			  sy1 = ypos1 +5;
			  sx2 =sx1+15;
			  sy2 = sy1+40;
			  
		}
		ypos2 = ypos1+50;
		
		
		  tx2 = tx1 +15;
		  ty2 = ty1+25;
		ls.add(new LineSegment(sx1, sy1, sx1, sy2));
		ls.add(new LineSegment(sx1, sy1, sx2, sy1));
		ls.add(new LineSegment(sx2, sy2, sx1, sy2));
		ls.add(new LineSegment(sx2, sy2, sx2, sy1));
		//stationary circle
		statC.add(new Circle(tx1+8, ty1+8, 8));
		//pivoted circle
		pivotC.add(new Circle(tx2, ty2, 8));
		
		
	
		
	}
	
	public double gettx1(){
		return tx1;
	}
	public double gettx2(){
		return tx2;
	}
	public double getty1(){
		return ty1;
	}
	public double getty2(){
		return ty2;
	}
	
	
	
	public AffineTransform getTrans(){
		return at;
	}
	//degrees - flipper rotation limit 
	public void degrees() throws InterruptedException{

		if(keyPressed){
			while(deg < 90){
				deg += 15;
				System.out.println("Rotating UP " + opcode);
			}
		}
		else if(!keyPressed){
			while(deg > 0){
				deg -= 15;
				System.out.println("Rotating Down " + opcode);
			}
		}
	}//end degrees
	public double getX2(){
		return xpos2;
	}
	public double getY2(){
		return xpos2;
	}
	//direction of rotation
	public void rotateDirection() {
        if (direction == 0 ) {
                if (deg == 0)
                        direction = 1;
                if (deg == 90)
                        direction = -1;
        }
        else
                direction = -direction;       
	}
	
	//rotate flipper
	public void rotateFlipper(){
		System.out.println("rf "+ this );
		try {
			degrees();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(opcode.equals("RightFlipper")){
			System.out.println(ls);
			for(LineSegment l : ls){
				l = Geometry.rotateAround(l, new Vect(xpos1+30, ypos1-5), Angle.DEG_90);
				System.out.println("rotating up rF");
				
			}
				System.out.println(ls);
			at.setToRotation(Math.toRadians(deg), (xpos1+30), (ypos1-5));
			if(deg == 90){
			at.translate(0, -20);
			}
			System.out.println("after rot" +ls);
		}else if(opcode.equals("LeftFlipper")){
			System.out.println("rotating up LF");
			at.setToRotation(Math.toRadians(-deg), (xpos1+16), (ypos1));
			if(deg == 90){
			at.translate(0, -10);
	}	}	}
	/*Vect centre = new Vect(xpos1+L/2, ypos1+L/2);
	for (int i=0 ; i < line.length ; i++)
		line[i] = Geometry.rotateAround(line[i], centre, Angle.DEG_90);
	for (int i=0 ; i < circle.length ; i++)
		circle[i] = Geometry.rotateAround(circle[i], centre, Angle.DEG_90);			
	
	public ArrayList<LineSegment> getLS(){
		return ls;*/
	
	public ArrayList<Circle> getSCirc(){
		
		return statC;
	}
public ArrayList<Circle> getPCirc(){
		
		return pivotC;
	}
public ArrayList<LineSegment> getLs(){
	
	return ls;
}
	

	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}
	

}
