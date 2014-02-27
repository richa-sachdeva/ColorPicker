package com.ColorPick;

import java.awt.Color;

abstract class FillableShape extends Shape {

private boolean filled;
	
	public FillableShape(double x, double y, Color color)
	{
		super(x, y, color);
	}
	
	public void setFilled(boolean v)
	{
		filled = v;
	}
	
	public boolean getFilled()
	{
		return filled;
	}
	
	public void deleteObject(){
	//	this. = null;
	}

}
