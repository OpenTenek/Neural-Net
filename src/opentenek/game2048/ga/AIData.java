package opentenek.game2048.ga;

import opentenek.ann.net.NeuralNetworkData;

public class AIData
{
    private NeuralNetworkData data;
    private int score = 0;
    
    public AIData(int layers, int neurons[]) 
    {
        data = new NeuralNetworkData(layers, neurons);
        data.randomize(-10, 10);
    }
    
    public void setScore(int s) 
    {
        score = s;
    }
    
    public int getScore() 
    {
        return score;
    }
    
    public NeuralNetworkData getData() 
    {
        return data;
    }
}
