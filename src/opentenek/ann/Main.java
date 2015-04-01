package opentenek.ann;

import opentenek.ann.net.NeuralNetwork;
import opentenek.ann.net.NeuralNetworkData;

/* 
 * File Name : Main.java
 * Author(s) : Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Apr 1
 * Version   : 0.1.4
 * 
 * This is where the application will be launched.
 * Contains a demo XOR NN.
 */

public class Main
{
    public static void main(String args[]) 
    {
        NeuralNetwork nn = new NeuralNetwork(2);
        
        nn.addLayer(2);
        nn.addLayer(1);
        
        nn.setWeight(0, 0, 0, 1.0);
        nn.setWeight(0, 0, 1, 1.0);
        nn.setBias  (0, 0, 0.5);
        
        nn.setWeight(0, 1, 0, 1.0);
        nn.setWeight(0, 1, 1, 1.0);
        nn.setBias  (0, 1, 1.5);
        
        nn.setWeight(1, 0, 0,  1.0);
        nn.setWeight(1, 0, 1, -1.0);
        nn.setBias  (1, 0, 0.5);
        
        System.out.println("XOR");
        printArray(nn.fire(new double[]{ 0.0, 0.0 }));
        printArray(nn.fire(new double[]{ 0.0, 1.0 }));
        printArray(nn.fire(new double[]{ 1.0, 0.0 }));
        printArray(nn.fire(new double[]{ 1.0, 1.0 }));
        System.out.println();
        
        System.out.println("NN for XOR");
        System.out.println(nn);
        
        System.out.println("Simple NN Data");
        int n[] = new int[]{2, 3, 1};
        NeuralNetworkData data = new NeuralNetworkData(2, n);
        data.setWeight(1, 0, 2, 3.14);
        
        System.out.println(data);
        printArray(data.getDataArray());
    }
    
    private static void printArray(double array[]) 
    {
        for(double d : array) 
        {
            System.out.print(d + ", ");
        }
        System.out.println();
    }
}
