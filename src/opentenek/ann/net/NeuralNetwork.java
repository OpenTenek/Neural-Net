package opentenek.ann.net;

import java.util.ArrayList;

/* 
 * File Name : NeuronNetwork.java
 * Author(s) : Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.0.1
 * 
 * Neural network.
 */

public class NeuralNetwork 
{
    // list of neuron layers
    private ArrayList<NeuronLayer> layers;
    
    public NeuralNetwork() 
    {
        layers = new ArrayList<NeuronLayer>();
        
        layers.add(new NeuronLayer());
    }
    
    public double[] fire(double input[]) 
    {
        if(input.length != layers.get(0).size()) return new double[]{ 0.0 };
        
        double out[] = input;
        
        for(NeuronLayer layer : layers) 
        {
            out = layer.fire(out);
        }
        
        return out;
    }
    
    public double getWeight(int layer, int neuron, int weightIndex) 
    {
        if(layer >= layers.size()) return 0.0;
        if(neuron >= layers.get(layer).size()) return 0.0;
        
        return layers.get(layer).getNeuron(neuron).getWeight(weightIndex);
    }
    
    public int numLayers() { return layers.size(); }
    
    public int numNeurons(int index) 
    {
        if(inBounds(index)) return layers.get(index).size();
        return 0;
    }
    
    // numInputs is same as numWeights
    public int numInputs(int index) 
    {
        if(inBounds(index)) return layers.get(index).numInputs();
        return 0;
    }
    
    private boolean inBounds(int index) 
    {
        return index >= 0 && index < layers.size();
    }
}