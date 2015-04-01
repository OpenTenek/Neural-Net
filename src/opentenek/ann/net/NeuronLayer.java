package opentenek.ann.net;

/* 
 * File Name : NeuronLayer.java
 * Author(s) : Ike, Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.1.2
 * 
 * Basic layer of net, contains neurons.
 */

public class NeuronLayer
{
    private Neuron neuron[];
    private int size, numInputs;
    
    public NeuronLayer() 
    {
        this(1, 1);
    }
    
    //ctor
    public NeuronLayer(int numNeurons, int inputs) 
    {
        size = numNeurons;
        numInputs = inputs;
        initNeurons();
    }
    
    public int size() { return size; }
    
    public int numInputs() { return numInputs; }
    
    public Neuron getNeuron(int index) 
    {
        if(inBounds(index)) return neuron[index];
        return null;
    }
    
    public void changeNeuronAmount(int amt) 
    {
        size = amt;
    }

    public void changeInputAmount(int inputs) 
    {
        numInputs = inputs;
        initNeurons();
    }

    public double[] fire(double input[])
    {
        double out[] = new double[size];
        
        if (input.length == numInputs)
        {
            for(int i = 0; i < size; i++) 
            {
                out[i] = neuron[i].fire(input);
            }
        }

        return out;
    }
    
    private boolean inBounds(int index) 
    {
        return index >= 0 && index < size;
    }

    private void initNeurons() 
    {
        neuron = new Neuron[size];        
        for(int i = 0; i < neuron.length; i++) 
        {
            neuron[i] = new Neuron(numInputs);
        }
    }
}
