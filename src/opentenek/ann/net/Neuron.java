package opentenek.ann.net;

/* 
 * File Name : Neuron.java
 * Author(s) : Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.0.1
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
    
    //TODO: finish code beyond this point
    
    // add a single input
    public void setInput(int index, double i) {}
    
    // add all inputs
    public void setInputs(double i[]) {}
    
    // add a sinle weight
    public void setWeight(int index, double w) {}
    
    // add all weights
    public void setWeights(double w[]) {}
    
    // get input at [index]
    public double getInput(int index) { return 0.0; }
    
    // get weight at [index]
    public double getWeight(int index) { return 0.0; }
}
