package com.game.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.NoninvertibleTransformException;
import java.util.LinkedList;

public class Player extends GameObject{
Handler handler;
	public Player(int x, int y, ID id, double r,Handler handler) {
		super(x, y, id,r);
		// TODO Auto-generated constructor stub
		this.handler=handler;
		
		
	}
	private double[] input=new double[6];
	public NeuralNet brain=new NeuralNet(4,2,1,6);
	private int score=0;
	

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		//input.add(Math.sin(r));
		//input.add(Math.cos(r));
		
		input[0]= -Math.sin(r);
		input[1]=Math.cos(r);
		//System.out.println(Math.cos(r)+"  "+Math.sin(r));
		
		Coin c= Handler.getClosestCoin(this);
	
		input[2] = c.getX()-this.getX();
		input[3] = c.getY()-this.getY();
		
		/*
		for(int i=0;i<4;i++)
		{
			System.out.println(input.get(i));
		}
		*/
		
		double[]out=new double[6];
		out=brain.Update(input);
		int m=0;
		
		y1=out[0];
		y2=out[1];
		//System.out.println(y1+"  "+y2);
		vR=y1-y2;
		vR=Math.max(-0.3,Math.min(0.3,vR));
		
		if(y1!=0 || y2!=0) m=1;
		r+=vR;
		vY=2*Math.sin(r);
		
		vX=2*Math.cos(r);
		y+=vY;
		
		x+=vX;
		
		collision();
		
		if(x>Game.WIDTH)
		{
			x-=Game.WIDTH;
			if(handler.bounds)score-=1;
		}
		if(x<0)
		{
			x+=Game.WIDTH;
			if(handler.bounds)score-=1;
		}
		if(y>Game.HEIGHT)
		{
			y-=Game.HEIGHT;
			if(handler.bounds)score-=1;
		}
		if(y<0)
		{
			y+=Game.HEIGHT;
			if(handler.bounds)score-=1;
		}
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y, 7, 7);
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int s)
	{
		score=s;
	}
	public void collision()
	{
		GameObject temp=handler.object.get(0);
		
			temp=handler.getClosestCoin(this);
			int d=(int) Math.sqrt(Math.pow(temp.getX()-getBounds().getCenterX(),2)+Math.pow(temp.getY()-getBounds().getCenterY(), 2));
			//System.out.println(d);
			
				if(d<10)
				{
					handler.removeObject(temp);
					score++;
				}
			
					
		
	}
public boolean c=false;
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d=(Graphics2D) g;
		
		AffineTransform t = new AffineTransform();        
	    t.rotate(r,x,y); 
	    g2d.transform(t);
	    g2d.setColor(Color.BLUE);
	    g2d.draw(getBounds());
	    if(c)
	    g2d.setColor(Color.GREEN);
	    else
	    g2d.setColor(Color.RED);	
	    
	   
	    g2d.fillRect((int)x, (int)y, 20, 20);
	    try{
	        g2d.transform(t.createInverse());
	    }catch(NoninvertibleTransformException e){
	        
	    }
		
	}

}
