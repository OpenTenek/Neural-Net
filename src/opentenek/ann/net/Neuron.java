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
}
