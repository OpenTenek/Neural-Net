package opentenek.ann.net;

/* 
 * File Name : NeuronLayer.java
 * Author(s) : Ike, Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.1.0
 * 
 * Basic layer of net, contains neurons.
 */

public class NeuronLayer
{
    private Neuron layer[];
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
        if(inBounds(index)) return layer[index];
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
        if (numInputs == 1 && input.length == layer.length)
        {
            //it�s an input layer
            for (int i = 0; i < size; i++) 
            {
                out[i] = layer[i].fire(input[i]);
            }
        } else if (input.length == numInputs)
        {
            for(int i = 0; i < size; i++) 
            {
                out[i] = layer[i].fire(input);
            }
        }
        // if neither are true, it will return all 0�s

        return out;
    }
    
    private boolean inBounds(int index) 
    {
        return index >= 0 && index < size;
    }

    private void initNeurons() 
    {
        layer = new Neuron[size];        
        for(int i = 0; i < layer.length; i++) 
        {
            layer[i] = new Neuron(numInputs);
        }
    }
}
