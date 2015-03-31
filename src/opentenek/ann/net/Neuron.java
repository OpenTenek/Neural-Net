package opentenek.ann.net;

/* 
 * File Name : Neuron.java
 * Author(s) : Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.1.0
 * 
 * Basic unit for neural net.
 */

public class Neuron
{
    // amount of inputs and weights
    private int size;
    // list of inputs
    private double input[];
    // list of weights
    private double weight[];
    // bias(threshold) is held separately for now
    private double bias;
    
    // ctor
    public Neuron(int size) 
    {
        this.size = size;
        input = new double[size];
        weight = new double[size];
        bias = 0.0;
    }
    
    // returns number of inputs available
    public int getSize() { return size; }
    
    // add a single input
    public boolean setInput(int index, double i) 
    {
        if(!inBounds(index)) return false;
        
        input[index] = i;
        
        return true;
    }
    
    // add all inputs
    // must be the size of inputs array
    public boolean setInputs(double i[]) 
    {
        if(input.length != i.length) return false; 
        
        input = i;
        
        return true;
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
    
    // get input at [index]
    public double getInput(int index) 
    { 
    	//ternary operator
        return inBounds(index) ? input[index] : 0.0; 
    }
    
    // get weight at [index]
    public double getWeight(int index) 
    { 
        return inBounds(index) ? weight[index] : 0.0; 
    }
    
    // get bias weight
    public double getBias() { return bias; }
    
    private boolean inBounds(int i) { return i >= 0 && i < size; }
}
