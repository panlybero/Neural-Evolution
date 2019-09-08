package com.game.test;

import java.util.LinkedList;
import java.util.Random;

public class Neuron 
{
	int Ninputs;
	
	LinkedList<Double> weights=new LinkedList<Double>();
	Random rd = new Random();
	public Neuron(int Ninputs)
	{
		this.Ninputs=Ninputs;
		for(int i=0;i<Ninputs;i++)
		{
			double d=rd.nextDouble();
			weights.add(d);
			
		}
	}
	public double sigmoid(double input, double response)
	{
		
		return 1/(1+Math.exp(-input/response));
		
	}
}
