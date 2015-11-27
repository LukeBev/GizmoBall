package model;

import java.awt.Graphics;

public class Square  {
	
	private int x;
	private int y;
	private int height;
	private int width;
		
	public Square(int x, int y, int height, int width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	/*
	 * This might not be needed any more, doing it a different way
	 * 
	public void drawSquare(){
		ReadFile.model.addLine(new VerticalLine(x, y, width));
		ReadFile.model.addLine(new VerticalLine(x, y+height, width));
		ReadFile.model.addHorizontalLine(new HorizontalLine(x, y, height));
		ReadFile.model.addHorizontalLine(new HorizontalLine(x+width, y, height));			
	}	
	*/
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
}
