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
 * since it contains all the weights(and biases) 
 * in a single array.
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
    //     a-1
    //      ∑ N[i](W[i]+1) + n(W[a]+1) + w 
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
        this(1, new int[]{1}, new int[]{1});
    }
    
    public NeuralNetworkData(int layers, int neurons[], int weights[]) 
    {
        this.layers = layers;
        this.neurons = neurons;
        this.weights = weights;
        
        isValid = initData();
    }
    
    public NeuralNetworkData(int layers, int neurons[], int weights[], double data[]) 
    {
        this.layers = layers;
        this.neurons = neurons;
        this.weights = weights;
        
        isValid = initData(data);
    }
    
    public boolean isValid() { return isValid; }
    
    public double getWeight(int layer, int neuron, int weight) 
    {
        if(!valid(layer, neuron, weight)) return 0.0;
        
        return data[getIndex(layer, neuron, weight)];
    }
    
    public boolean setWeight(int layer, int neuron, int weight, double value) 
    {
        if(!valid(layer, neuron, weight)) return false;
        
        data[getIndex(layer, neuron, weight)] = value;
        
        return true;
    }
    
    private int getIndex(int layer, int neuron, int weight) 
    {
        int index = 0;
        for(int i = 0; i < layer; i++) 
            index += neurons[i] * (weights[i] + 1);
        index += neuron * (weights[layer] + 1);
        index += weight;
        
        return index;
    }
    
    // checks if in bounds, weights can include bias
    private boolean inBounds(int layer, int neuron, int weight) 
    {
        if(layer < 0 || layer >= layers) return false;
        if(neuron < 0 || neuron >= neurons[layer]) return false;
        // only greater than, since bias is weights.length
        // position
        if(weight < 0 || weight > weights[layer]) return false;
        
        return true;
    }
    
    private boolean valid(int layer, int neuron, int weight) 
    {
        return isValid && inBounds(layer, neuron, weight);
    }
    
    private boolean initData() 
    {
        if(neurons.length != layers || weights.length != layers) 
            return false;
        
        int size = 0;
        for(int i = 0; i < layers; i++) 
        {
            size += neurons[i] * (weights[i]+1);
        }
        data = new double[size];
        
        return true;
    }
    
    private boolean initData(double data[]) 
    {
        if(neurons.length != layers || weights.length != layers) 
            return false;
        
        int size = 0;
        for(int i = 0; i < layers; i++) 
        {
            size += neurons[i] * (weights[i]+1);
        }
        if(data.length != size) return false;
        this.data = data;
        
        return true;
    }
    
    public String toString() 
    {
        String str = "";
        
        for(int layer = 0; layer < layers; layer++) 
        {
            str += "|";
            
            for(int neuron = 0; neuron < neurons[layer]; neuron++) 
            {
                for(int weight = 0; weight < weights[layer]; weight++) 
                {
                    str += getWeight(layer, neuron, weight) + ",";
                }
                str += "(" + getWeight(layer, neuron, weights[layer]) + ")";
                str += "|";
            }
            str += "\n";
        }
        
        return str;
    }
}
