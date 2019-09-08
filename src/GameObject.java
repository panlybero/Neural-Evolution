package com.game.test;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject 
{
	protected double x,y;
	protected ID id;
	protected double vX, vY;
	protected double r, vR;
	protected double m;
	protected double y2,y1;
	
	public GameObject(int x, int y, ID id, double r)
	{
		this.x=x;
		this.y=y;
		this.id=id;
		this.vX=0;
		this.vY=0;
		this.r=r;
		this.vR=0;
		m=0;
		this.y1=0;
		this.y2=0;
	}
	public abstract void tick();
	public abstract void render (Graphics g);
	public abstract Rectangle getBounds();
	
	public void sety2(double y2)
	{
		this.y2=y2;
	}
	public void sety1(double y1)
	{
		this.y1=y1;
	}
	public double gety2()
	{
		return y2;
	}
	public double gety1()
	{
		return y1;
	}
	public void setM(double m)
	{
		this.m=m;
	}
	public void setR(double r)
	{
		this.r=r;
	}
	public double getr()
	{
		return r;
	}
	public void setvR(double r)
	{
		this.vR=r;
	}
	public double getvR()
	{
		return vR;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public void setID(ID id)
	{
		this.id=id;
	}
	public ID getID()
	{
		return id;
	}
	public void setvY(double d)
	{
		this.vY=d;
	}
	public double getvY()
	{
		return vY;
	}
	public double getvX()
	{
		return vX;
	}
	public void setvX(double d)
	{
		this.vX=d;
	}

}
