package com.ColorPick;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ColorRectangle extends FillableShape{

	private double length;
	private Color color2;
	private boolean isBorder = false;
	
	 
	public ColorRectangle(double x, double y, Color color, double length, boolean isBorder){
		super(x, y, color);
		this.length = length;
		this.color2 = color;
		this.isBorder = isBorder;
	}
 
	public double getLength(){
		return length;
	}
 
	public void paint(Graphics g){
		g.setColor(this.color2);
		
		g.drawRect((int)getX(),(int)getY(),(int)length, (int)length);
		g.fillRect((int)getX(),(int)getY(),(int)length, (int)length);
		if(isBorder){
			g.setColor(Color.black);
			g.drawRect((int)getX(), (int)getY(), (int)length, (int)length);
		}
	}
	
	public void setColor(Color c){
		this.color2 = c;
	}
	

	public Color getColor() { return this.color2; }
	
}
