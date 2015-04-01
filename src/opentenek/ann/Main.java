package opentenek.ann;

import opentenek.ann.net.NeuralNetwork;

/* 
 * File Name : Main.java
 * Author(s) : Thinic
 * Created   : 2015 Mar 31
 * Updated   : 2015 Mar 31
 * Version   : 0.1.2
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
        
        printArray(nn.fire(new double[]{ 0.0, 0.0 }));
        printArray(nn.fire(new double[]{ 0.0, 1.0 }));
        printArray(nn.fire(new double[]{ 1.0, 0.0 }));
        printArray(nn.fire(new double[]{ 1.0, 1.0 }));
        
        System.out.println(nn);
    }
    
    private static void printArray(double array[]) 
    {
        for(double d : array) 
        {
            System.out.print(d + " ");
        }
        System.out.println();
    }
}
