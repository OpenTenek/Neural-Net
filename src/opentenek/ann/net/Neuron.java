package opentenek.ann.net;

/* 
 * File Name : Neuron.java
 * Author(s) : Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.1.1
 * 
 * Basic unit for neural net.
 */

public class Neuron
{
    // amount of inputs and weights
    private int size;
    // list of weights
    private double weight[];
    // bias(threshold) is held separately for now
    private double bias;
    
    // ctor
    public Neuron(int size) 
    {
        this.size = size;
        weight = new double[size];
        bias = 0.0;
        
        initWeights();
    }
    
    // returns number of inputs available
    public int size() { return size; }
    
    public double fire(double input[]) 
    {
        if(input.length != weight.length) return 0.0;
        double out = 0.0;
        for(int i = 0; i < size(); i++)
            out += weight[i] * input[i];
        out -= bias;
        
        return bipolar(out);
    }
    
    // add a single weight
    public boolean setWeight(int index, double w) 
    {
        if(!inBounds(index)) return false;
        
        weight[index] = w;
        
        return true;
    }
    
    // add all weights
    public boolean setWeights(double w[]) 
    {
        if(weight.length != w.length) return false;
        
        weight = w;
        
        return true;
    }
    
    // set the bias
    public void setBias(double b) 
    {
        bias = b;
    }
    
    // get weight at [index]
    public double getWeight(int index) 
    { 
        return inBounds(index) ? weight[index] : 0.0; 
    }
    
    // get bias weight
    public double getBias() { return bias; }
    
    private boolean inBounds(int i) { return i >= 0 && i < size; }
    
    private void initWeights() 
    {
        for(int i = 0; i < weight.length; i++) 
            weight[i] = 1.0;
    }
    
    @SuppressWarnings("unused")
    private double sigmoid(double x) 
    {
        return 1.0 / (1.0 + Math.exp(-x));
    }
    
    private double bipolar(double x) 
    {
        return x < 0 ? -1.0 : 1.0;
    }
}
