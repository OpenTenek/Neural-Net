package opentenek.ann.net;

/* 
 * File Name : NeuralNetworkData.java
 * Author(s) : Thinic
 * Created   : 2015 Apr 1
 * Updated   : 2015 Apr 1
 * Version   : 0.0.1
 * 
 * Contains all the data for the neural network.
 * 
 * This can be used to create a neural network
 * without having to create and populate layers
 * manually.
 * 
 * This will also be useful for genetic algorithms
 * since it contains all the weights in a single
 * array.
 */

public class NeuralNetworkData
{
    // number of layers, INCLUDING input layer
    private int layers;
    // amount of neurons in each layer, the amount
    // of inputs for each neuron is determined by
    // the previous amount of neurons.
    private int neurons[];
    // amount of weights for each neuron in a 
    // layer, this DOES NOT include the bias
    private int weights[];
    // all the weights and bias in the neural 
    // network. To get a specific weight, you must
    // use this equation: 
    //      a
    //     sum(N[i]W[i]) + n(W[a]+1) + w 
    //     i=0
    // where N is the neurons array, 
    // W is the weights array,
    // a is the layer you are accessing,
    // n is the neuron within that layer,
    // and w is weight(or bias) for that neuron.
    // To get the bias, treat it as though it were
    // at the weights.length position.
    private double data[];
    
    private boolean isValid;
    
    public NeuralNetworkData()
    {
        layers = 1;
        neurons = new int[]{ 1 };
        weights = new int[]{ 1 };
        
        isValid = initData();
    }
    
    private boolean initData() 
    {
        if(neurons.length != layers || weights.length != layers) 
            return false;
        
        int size = 0;
        for(int i = 0; i < layers; i++) 
        {
            
        }
        
        return true;
    }
    
    
}
