package com.game.test;

import java.util.LinkedList;
import java.util.Random;



public class GeneticAlg 
{
	public static Player BestofGen;
	
	public static LinkedList<Player> newpool;
	public static LinkedList<Player> genAlg(LinkedList<Player> pool, Handler h)
	{
		
		
		newpool=new LinkedList<Player>();
		Player mom,dad,kappa;
		/*
		for(int i=0;i<pool.size();i++)
		{
			for(int j=0;j<pool.size()-1;j++)
			{
				if(pool.get(j).getScore()<pool.get(j+1).getScore())
				{
					kappa=pool.get(j);
					pool.set(j, pool.get(j+1));
					pool.set(j+1,kappa);
				}
			}
			

			
		}
		*/
		BestofGen=pool.get(0);
		for(int i=0;i<pool.size();i++)
		{
			if(pool.get(i).getScore()>=BestofGen.getScore())
				BestofGen=pool.get(i);
		}
		for(int i=0;i<pool.size();i++)
		{
			Game.avg+=(double)pool.get(i).getScore()/10;
		}
		
		
		//Game.total=best.getScore();
		
		Random rd=new Random();
		
		LinkedList<LinkedList<Double>> temp=new LinkedList<LinkedList<Double>>();


		Player newP,newP2;
		
		addBest(BestofGen,2,h);
		while(newpool.size()<pool.size()-1)
		{
			dad=geneRoulette(pool,h);
			mom=geneRoulette(pool,h);
			//dad=pool.get(0);
			//mom=pool.get(1);
			temp=cross(dad,mom);
			newP=new Player(rd.nextInt(Game.WIDTH),rd.nextInt(Game.HEIGHT) , ID.Player,rd.nextDouble(),h);
			newP.brain.putWeights(temp.getFirst()); 
			newpool.add(newP);
			newP2=new Player(rd.nextInt(Game.WIDTH),rd.nextInt(Game.HEIGHT) , ID.Player,rd.nextDouble(),h);
			newP2.brain.putWeights(temp.getLast()); 
			newpool.add(newP2);
			
		}
		return newpool;
		
		
	}
	public static LinkedList<LinkedList<Double>> cross(Player a, Player b)
	{
		LinkedList<Double> w1=a.brain.getWeights();
		LinkedList<Double> w2=b.brain.getWeights();
		LinkedList<Double> b1=new LinkedList<Double>();
		LinkedList<Double> b2=new LinkedList<Double>();
		LinkedList<LinkedList<Double>> ls=new LinkedList<LinkedList<Double>>();

		Random rd=new Random();
		if(a==b || rd.nextDouble()>0.7)
		{
			ls.add(w1);
			ls.add(w2);
			return ls;
		}
		int brkp=rd.nextInt(w1.size()-1);
		
			for(int i=0;i<brkp;i++)
			{
				
				b1.add(w1.get(i));
				b2.add(w2.get(i));
			}
			
		
			for(int i=brkp;i<w1.size();i++)
			{
				b1.add(w2.get(i));
				b2.add(w1.get(i));
			}
			b1=mutate(b1);
			b2=mutate(b2);
			ls.add(b1);
			ls.add(b2);
			return ls;
	}
	public static LinkedList<Double> mutate(LinkedList<Double> w)
	{
		Random rd=new Random();
		for(int i=0;i<w.size();i++)
		{
			
			if(rd.nextDouble()>0.9)
			{
				if(rd.nextDouble()>0.5)
				{
					w.set(i, w.get(i)+rd.nextDouble()*0.3);
				}else
				w.set(i, w.get(i)-rd.nextDouble()*0.3);		
			}
		}
		return w;
	}
	public static Player getBest()
	{
		return BestofGen;
	}
	public static Player geneRoulette(LinkedList<Player> players, Handler h)
	{
		Player a=new Player(0,0,ID.Player,0,h);
		double[] chance =new double[players.size()];
		int tot=0;
		for(int i=0;i<players.size();i++)
		{
			tot+=players.get(i).getScore();
			
		}
		for(int i=0;i<chance.length;i++)
		{
			if(tot!=0)
			chance[i]=100* players.get(i).getScore()/tot;
		}
		double fitnesssofar=0;
		Random rd=new Random();
		double slice=rd.nextDouble() * tot;
		
		for(int i=0;i<chance.length;i++)
		{	
			fitnesssofar+=players.get(i).getScore();
			
			if(fitnesssofar>=slice)
			{
				a=players.get(i);
				break;
			}
		}
		
			
		
	return a;
	}
	public static void addBest(Player p, int times, Handler h) 
	{
		Random rd=new Random();
		Player temp;
		for(int i=0;i<times;i++)
		{
			temp=new Player(rd.nextInt(Game.WIDTH),rd.nextInt(Game.HEIGHT),ID.Player,rd.nextDouble(),h);
			temp.brain.putWeights(p.brain.getWeights());
			newpool.add(temp);

		}
	}
	
}
