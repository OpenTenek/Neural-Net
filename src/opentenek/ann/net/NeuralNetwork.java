package opentenek.ann.net;

import java.util.ArrayList;

/* 
 * File Name : NeuralNetwork.java
 * Author(s) : Thinic, Ike
 * Created   : 2015 Mar 31
 * Updated   : 2015 Apr 1
 * Version   : 0.1.4.1
 * 
 * Neural network
 * 
 * Starts off with 0 layers.
 * 
 * You must edit each layer incrementally,
 * you cannot change number of neurons or 
 * inputs to previously add layers. You 
 * must remove and reset that layer if you
 * wish to do so. 
 * 
 * You can edit specific weights and biases
 * without removing layers.
 */

public class NeuralNetwork 
{
    // list of neuron layers
    private ArrayList<NeuronLayer> layers;
    private int numInputs;
    
    public NeuralNetwork(int numInputs) 
    {
        layers = new ArrayList<NeuronLayer>();
        
        this.numInputs = numInputs;
    }
    
    public double[] fire(double input[]) 
    {
        if(layers.size() < 1 || input.length != numInputs) return null;
        
        double out[] = input;
        
        for(NeuronLayer layer : layers) 
        {
            out = layer.fire(out);
        }
        
        return out;
    }
    
    public int getNumInputs() { return numInputs; }
    public void setNumInputs(int num) 
    { 
        numInputs = num; 
        if(numLayers() > 0) 
            layers.get(0).changeInputAmount(numInputs);
    }
    
    public double getWeight(int layer, int neuron, int weightIndex) 
    {
        if(numLayers() < 1 || !inBounds(layer)) return 0.0;
        if(neuron >= layers.get(layer).size()) return 0.0;
        
        return layers.get(layer).getNeuron(neuron).getWeight(weightIndex);
    }
    
    public double getBias(int layer, int neuron) 
    {
        if(numLayers() < 1 || !inBounds(layer)) return 0.0;

        return layers.get(layer).getNeuron(neuron).getBias();
    }
    
    public void setWeight(int layer, int neuron, int weightIndex, double value) 
    {
        if(numLayers() < 1 || !inBounds(layer)) return;
        if(neuron >= layers.get(layer).size()) return;
        
        layers.get(layer).getNeuron(neuron).setWeight(weightIndex, value);
    }
    
    public void setBias(int layer, int neuron, double value) 
    {
        if(numLayers() < 1 || !inBounds(layer)) return;

        layers.get(layer).getNeuron(neuron).setBias(value);
    }
    
    public int numLayers() { return layers.size(); }
    
    public void addLayer(int numNeurons) 
    {
        if(numLayers() < 1) 
        {
            layers.add(new NeuronLayer(numNeurons, numInputs));
        } else 
        {
            int inputs = layers.get(layers.size() - 1).size();
            layers.add(new NeuronLayer(numNeurons, inputs));
        }
    }
    
    public NeuronLayer removeLayer() 
    {
        if(layers.size() > 0) return layers.remove(layers.size() - 1);
        return null;
    }
    
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
    
    public String toString() 
    {
        String str = "";
        
        for(NeuronLayer layer : layers) 
        {
            int layerSize = layer.size();
            int inputs = layer.numInputs();
            for(int neuron = 0; neuron < layerSize; neuron++) 
            {
                Neuron n = layer.getNeuron(neuron);
                str += "| ";
                for(int i = 0; i < inputs; i++) 
                {
                    str += n.getWeight(i) + " ";
                }
                str += "(" + n.getBias() + ") ";
            }
            str += "| \n";
        }
        
        return str;
    }
    
    private boolean inBounds(int index) 
    {
        return index >= 0 && index < layers.size();
    }
}