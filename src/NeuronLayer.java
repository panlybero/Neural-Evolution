package com.game.test;

import java.util.LinkedList;

public class NeuronLayer 
{
	int Nneurons;
	
	LinkedList<Neuron> layer=new LinkedList<Neuron>();
	
	public NeuronLayer(int Nneurons, int InputsPerNeuron)
	{
		this.Nneurons=Nneurons;
		
		for(int i=0;i<Nneurons;i++)
		{
			
			layer.add(new Neuron(InputsPerNeuron+1));
			
		}
		//System.out.println(layer.size());
	}
}
