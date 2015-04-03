package opentenek.ann.net.ga;

import opentenek.ann.net.NeuralNetworkData;

public class AIData
{
    private NeuralNetworkData data;
    private double score = 0;
    
    public AIData(int layers, int neurons[]) 
    {
        data = new NeuralNetworkData(layers, neurons);
        data.randomize(-10, 10);
    }
    
    public void setFitness(double s) 
    {
        score = s;
    }
    
    public double getFitness() 
    {
        return score;
    }
    
    public NeuralNetworkData getData() 
    {
        return data;
    }
}
