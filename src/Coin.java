package com.game.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

public class Coin extends GameObject
{
	boolean c;
	public Coin(int x, int y, ID id, double r, boolean c) {
		super(x, y, id, r );
		// TODO Auto-generated constructor stub
		this.c=c;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y, 5, 5);
	}
	
	@Override
	public void render(Graphics g) 
	{
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		Graphics2D g2d=(Graphics2D)g;
		g2d.draw(getBounds());
		if(c)
		g.setColor(Color.WHITE);
		else g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, 7, 7);
		
	}
	
	
}
