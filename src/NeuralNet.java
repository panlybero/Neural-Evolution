package com.game.test;

import java.util.LinkedList;

public class NeuralNet 
{
	private int Ninputs;
	private int Noutputs;
	private int NhiddenLayers;
	private int Nperhidden;
	private LinkedList<NeuronLayer> layers=new LinkedList<NeuronLayer>();
	public double bias=-1;
	
	public NeuralNet(int Ninputs, int Noutputs, int NhiddenLayers, int Nperhidden)
	{
		this.Ninputs=Ninputs;
		this.Noutputs=Noutputs;
		this.NhiddenLayers=NhiddenLayers;
		this.Nperhidden=Nperhidden;
		CreateNet();
	}
	public void CreateNet()
	{
		
		if(NhiddenLayers>0)
		{
			
			
			this.layers.add(new NeuronLayer(Nperhidden,Ninputs));
			for(int i=0;i<NhiddenLayers-1;i++)
			this.layers.add(new NeuronLayer(Nperhidden,Nperhidden));
			
			this.layers.add(new NeuronLayer(Noutputs,Nperhidden));
			
		}
		
	}
	public LinkedList<Double> getWeights()
	{
		LinkedList<Double> weights=new LinkedList<Double>();
		for(int i=0;i<NhiddenLayers+1;i++)
		{
			for(int j=0;j<layers.get(i).Nneurons;j++)
			{
				for(int k=0;k<layers.get(i).layer.get(j).Ninputs;k++)
				{
					
					weights.add(layers.get(i).layer.get(j).weights.get(k));
					
				}
			}
		}
		return weights;
	}
	public void putWeights(LinkedList<Double> weights)
	{
		int w=0;
		for(int i=0;i<NhiddenLayers+1;i++)
		{
			for(int j=0;j<layers.get(i).Nneurons;j++)
			{
				
					layers.get(i).layer.get(j).weights.clear();
				
			}
		}
		
		
		for(int i=0;i<NhiddenLayers+1;i++)
		{
			for(int j=0;j<layers.get(i).Nneurons;j++)
			{
				for(int k=0;k<layers.get(i).layer.get(j).Ninputs;k++)
				{
					
					layers.get(i).layer.get(j).weights.add(weights.get(w++)); 

				}
				
			}
		}
		

	}
	public double[] Update(double[] inputs)
	{
		double[] outputs=new double[6];
		LinkedList<Double> weights=new LinkedList<Double>();
		weights=getWeights();
		int w=0;
		
		
		for(int i=0;i<NhiddenLayers+1;i++)
		{
			
			
			if(i>0)
			{
				
				for(int p=0;p<6;p++)
				{
					inputs[p]=outputs[p];
				}
				
			}
			
			for(int j=0;j<layers.get(i).Nneurons;j++)
				
			{
				double netinput=0;
				int NumInp=layers.get(i).layer.get(j).Ninputs-1;
				
		
				for(int k=0;k<NumInp;k++)
				{
					netinput+=layers.get(i).layer.get(j).weights.get(k)*inputs[k];	
				}
				netinput+=layers.get(i).layer.get(j).weights.get(NumInp)*bias;
				outputs[j]=layers.get(i).layer.get(j).sigmoid(netinput,1);
				
			}
			
			
		}
		
		
		 
		
		
		return outputs;
	}
}
