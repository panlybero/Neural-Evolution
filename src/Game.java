package com.game.test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 560040030776037832L;
	
	public static final int WIDTH=3000, HEIGHT= WIDTH/12 *9;
	private Thread thread;
	private boolean running = false;
	public static int total=0;
	public static double avg;
	private static Handler handler;
	public Player bestofall;
	public static boolean st=false;
	public Game()
	{
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH,HEIGHT, "Test", this);
		init(30,40);
			
	}
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running=false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static double amountOfTicks = 60.0; //updates per second --> PC MASTER RACE
	public void run()
	{
		long lastTime = System.nanoTime();
		
		double ns = 1000000000/ amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running)
		{
			long now = System.nanoTime();
			ns = 1000000000/ amountOfTicks;
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>= 1)
			{
				tick();
				delta--;
			}
			if (running)
			{
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	int counter=0;
	int times=0;
	int tot10=0;
	boolean stopped=false;
	private void tick()
	{
		handler.tick();
		counter++;
		

		if(counter==6000 && !stopped)
		{
			LinkedList<Player> pool=new LinkedList<Player>();
			for(int i=0;i<Handler.object.size();i++)
			{
				if(Handler.object.get(i).getID()==ID.Player)
				{
					pool.add((Player)Handler.object.get(i));
					tot10+=((Player)Handler.object.get(i)).getScore();
				}
			}
			times++;
			//System.out.println(pool.get(0).brain.getWeights().get(0));
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			Player BestofGen=pool.get(0);
			for(int i=0;i<pool.size();i++)
			{
				if(BestofGen.getScore()<=pool.get(i).getScore())
				{
					BestofGen=pool.get(i);
				}
			}
			System.out.println(BestofGen.getScore());
			//BestofGen.setScore(0);
			/*
			for(int i=0;i<pool.size();i++)
			{
				
					LinkedList<Double> w=pool.get(i).brain.getWeights();
					for(int j=0;j<w.size();j++)
					{
						System.out.println(w.get(j));
					}
					System.out.println("///////////");
				
				
			}
			System.out.println("////////........./////////");
			*/
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
			pool=GeneticAlg.genAlg(pool, handler);
			for(int i=0;i<pool.size();i++)
			{
				pool.get(i).setScore(0);
			}
			//System.out.println(pool.get(0).brain.getWeights().get(0));

			int k=0;
			handler.object.clear();
			for(int i=0;i<pool.size();i++)
			{
				
					handler.object.add(pool.get(i));
					
			}
			
			
			tot10=0;
			total=0;
			avg=0;
			counter=0;
			//if(handler.maxCoins>2)
			//handler.maxCoins-=1;
		}
		
		if(st && !stopped)
		{
			
			int e=handler.object.size();
			for(int i=0;i<e;i++)
			{
				handler.removeObject(handler.object.getLast());
				System.out.println("aaa");
			}
			
			 Random rd=new Random();
			 
			handler.addObject(GeneticAlg.getBest());
			
			
			
			stopped=true;
			handler.maxCoins=10;
			//times++;
		}
		
	}
	private void render()
	{
		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		

		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[])
	{
		new Game ();
	}
	public static void init(int p, int c)
	{
		Random rd=new Random();
		for(int i=0;i<p;i++)
		{
			handler.addObject(new Player(rd.nextInt(WIDTH),rd.nextInt(HEIGHT) , ID.Player,rd.nextDouble(),handler));
			
		}
		for(int i=0;i<40;i++)
		{
			handler.addObject(new Coin(rd.nextInt(WIDTH),rd.nextInt(HEIGHT) , ID.Enemy,0,true));
			
		}
	}
}
