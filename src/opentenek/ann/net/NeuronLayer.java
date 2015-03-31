package opentenek.ann.net;

/* 
 * File Name : NeuronLayer.java
 * Author(s) : Ike, Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.0.1
 * 
 * Basic layer of net, contains neurons.
 */

public class NeuronLayer
{
    private Neuron layer[];
    private int size, inputSize;
    
    public NeuronLayer(int numNeurons, int inputs) 
    {
        size = numNeurons;
        layer = new Neuron[size];
        inputSize = inputs;
        initNeurons();
    }

public void changeInputAmount(int inputs) 
{
inputSize = inputs;
initNeurons();
}

/*public double[] fire(double input[][])
{

}*/

    private void initNeurons() 
    {
        for(int i = 0; i < layer.length; i++) 
        {
            layer[i] = new Neuron(inputSize);
        }
    }

    

}

