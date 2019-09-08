package com.game.test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	public KeyInput( Handler handler)
	{
		this.handler = handler;
	}
	public void keyPressed (KeyEvent e)
	{
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject temp=handler.object.get(i);
			if(e.getKeyCode()==KeyEvent.VK_D)
			{
				if(temp.id==ID.Player)
				{
					Game.amountOfTicks+=5;

				}
			}
			if(e.getKeyCode()==KeyEvent.VK_A)
			{
				if(temp.id==ID.Player)
				{
					
					Game.amountOfTicks-=5;
					
					
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_W)
			{
				if(temp.id==ID.Player)
				{
					temp.sety1(1);
					temp.sety2(1);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_S)
			{
				if(temp.id==ID.Player)
				{
					temp.sety1(0);
					temp.sety2(0);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				if(temp.id==ID.Player)
				{
					temp.setM(1);
					temp.sety1(temp.gety1()+0.05);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				if(temp.id==ID.Player)
				{
					temp.setM(1);
					temp.sety2(temp.gety2()+0.05);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_SPACE)
			{
				Game.st=true;
			}
			if(e.getKeyCode()==KeyEvent.VK_0)
			{
				
				handler.bounds=true;
				System.out.println(handler.bounds);
			}
		}
	}
	public void keyReleased(KeyEvent e)
	{
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject temp=handler.object.get(i);
			if(e.getKeyCode()==KeyEvent.VK_D)
			{
				if(temp.id==ID.Player)
				{
					temp.setvR(0);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_W)
			{
				if(temp.id==ID.Player)
				{
					temp.setM(0);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_A)
			{
				if(temp.id==ID.Player)
				{
					temp.setvR(0);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_S)
			{
				if(temp.id==ID.Player)
				{
					temp.setM(0);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				if(temp.id==ID.Player)
				{
					temp.setM(0);
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				if(temp.id==ID.Player)
				{
					temp.setM(0);
					
				}
			}
		}
	}
}
