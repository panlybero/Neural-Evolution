package com.game.test;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Handler 
{
	static LinkedList<GameObject> object = new LinkedList<GameObject>();
	int maxCoins=40;
	public boolean bounds=false;
	public void tick()
	{
		if(getNcoins()<maxCoins)
		{
				Random rd=new Random();
				if(maxCoins-getNcoins()>=0)
				for(int i=0;i<maxCoins-getNcoins();i++)
				addObject(new Coin(rd.nextInt(Game.WIDTH),rd.nextInt(Game.HEIGHT) , ID.Enemy,0,false));
		}
		for(int i=0; i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.tick();
			
		}
		
			if(object.get(0).getID()==ID.Player)
			{
				Player best=(Player) object.get(0);
			
				for(int i=0;i<object.size();i++)
				{
					
					if(object.get(i).getID()==ID.Player)
					{	
						((Player) object.get(i)).c=false;
						if( ((Player) object.get(i)).getScore()>best.getScore())
						{
							best=(Player) object.get(i);
							((Player)object.get(i)).c=false;
						}
						
					}
				}
				
			
				best.c=true;
			}
		
	}
	public void render (Graphics g)
	{
		for(int i=0; i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		
	}
	public static LinkedList<Coin> getCoins()
	{
		LinkedList<Coin> coins=new LinkedList<Coin>();
		for(int i=0;i<object.size();i++)
		{
			if(object.get(i).getID()==ID.Enemy)
			{
				coins.add((Coin)object.get(i));
			}
		}
		return coins;
	}
	public static Coin getClosestCoin(Player p)
	{
	
		LinkedList<Coin> coin=getCoins();
		Coin c=coin.get(0);
		for(int i=0;i<coin.size();i++)
		{
			if(Math.sqrt(Math.pow(c.getX()-p.getX(),2)+Math.pow(c.getY()-p.getY(), 2))>Math.sqrt(Math.pow(coin.get(i).getX()-p.getX(),2)+Math.pow(coin.get(i).getY()-p.getY(), 2)))
			{
				
				c=coin.get(i);
			}
		}
		
		return c;
	}
	public int getNcoins()
	{
		int c=0;
		for(int i=0;i<object.size();i++)
			if(object.get(i).getID()==ID.Enemy)
				c++;
		return c;
				
				
	}
	public void addObject (GameObject object)
	{
		this.object.add(object);
	}
	public static void removeObject( GameObject objec)
	{
		object.remove(objec);
	}
	
}
