package opentenek.ann.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import opentenek.ann.net.NeuralNetwork;

public class SimpleNNDisplay
{
    private JFrame frame;
    private SimpleNNImage image;
    private double input[];
    @SuppressWarnings("unused")
    private NeuralNetwork nn;
    
    public SimpleNNDisplay(NeuralNetwork nn, int width, int height) 
    {
        frame = new JFrame("SimpleNNDisplay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new NNPanel(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
        
        this.nn = nn;
        image = new SimpleNNImage(nn, width, height);
    }
    
    public void setNN(NeuralNetwork nn) 
    {
        this.nn = nn;
        image.setNN(nn);
    }
    
    public void draw() 
    {
        input = null;
        frame.repaint();
    }
    
    public void draw(double i[]) 
    {
        input = i;
        frame.repaint();
    }
    
    @SuppressWarnings("serial")
    class NNPanel extends JPanel 
    {
        NNPanel(int width, int height) 
        {
            setPreferredSize(new Dimension(width, height));
        }
        
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            image.setSize(getWidth(), getHeight());
            
            image.draw(input);
            Image img = image.getImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
    }
    
    public static void main(String args[]) 
    {
        NeuralNetwork nn = new NeuralNetwork(1);
        
        SimpleNNDisplay display = new SimpleNNDisplay(nn, 1200, 900);
        
        for(int i = 0; i < 3; i++) 
        {
            nn.addLayer((int)(Math.random()*7)+4);
            int layer = nn.numLayers() - 1;
            for(int neuron = 0; neuron < nn.numNeurons(layer); neuron++) 
            {
                nn.setBias(layer, neuron, Math.random()*20 - 10);
                for(int weight = 0; weight < nn.numInputs(layer); weight++) 
                {
                    nn.setWeight(layer, neuron, weight, Math.random() * 20 - 10);
                }
            }
        }
        
        nn.addLayer(3);
        int layer = nn.numLayers() - 1;
        for(int neuron = 0; neuron < nn.numNeurons(layer); neuron++) 
        {
            nn.setBias(layer, neuron, Math.random()*20 - 10);
            for(int weight = 0; weight < nn.numInputs(layer); weight++) 
            {
                nn.setWeight(layer, neuron, weight, Math.random() * 20 - 10);
            }
        }
        
        double in = 0;
        double dIn = 0.05;
        
        double limit = 10;
        
        while(true) 
        {
            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            in += dIn;
            
            if(in > limit) 
            {
                dIn *= -1;
                in = limit;
            }
            if(in < -limit) 
            {
                dIn *= -1;
                in = -limit;
            }
            
            display.draw(new double[]{ in });
        }
    }
}
