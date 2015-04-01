package opentenek.ann.net;

/* 
 * File Name : Neuron.java
 * Author(s) : Thinic, Ike
 * Created   : 2015 Mar 31
 * Updated   : 2015 Apr 1
 * Version   : 0.1.3
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
    
    // set the amount of weights, and copy over the existing values
    public void setNumWeights(int amt) 
    {
        int oldSize = size;
        size = amt;
        double newWeights[] = new double[size];
        // if new size < old size, only copy over up to new size
        // else copy all of the old array
        for(int i = 0; i < (oldSize < amt ? oldSize : size); i++) 
            newWeights[i] = weight[i];
    }
    
    public double fire(double input[]) 
    {
        double out = 0.0;
        if(input.length == weight.length)
            for(int i = 0; i < size(); i++)
                out += weight[i] * input[i];
        else
            System.out.println("invalid inputs");
        
        out -= bias;
        
        // temp return type
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
