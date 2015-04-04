package opentenek.ann.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import opentenek.ann.net.NeuralNetwork;

public class SimpleNNImage
{
    private BufferedImage img;
    
    private NeuralNetwork nn;
    
    public SimpleNNImage(NeuralNetwork nn, int width, int height) 
    {
        setNN(nn);
        
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    
    public void setSize(int width, int height) 
    {
        if(width != img.getWidth() || height != img.getHeight())
            img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    
    public void draw(double in[]) 
    {
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        double[][] fullInput = in == null ? null : nn.fireFull(in);
        
        // clear image
        g.setColor(Color.white);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        
        int size = 10;//img.getHeight() / (mostNeurons() * 4);
        {
            int hSize = mostNeurons() < 1 ? 10 : img.getHeight() / (mostNeurons() * 4);
            int wSize = nn.numLayers() < 1 ? 10 : img.getWidth() / (nn.numLayers() * 4);
            size = Math.min(hSize, wSize);
        }
        
        int dWidth = img.getWidth() / (nn.numLayers() + 2); // remember to include input layer
        
        // draw input layer
        g.setColor(Color.black);
        for(int input = 0; input < nn.numInputs(); input++) 
        {
            int dHeight = img.getHeight() / (nn.numInputs() + 1);
            int cx = dWidth * 1;
            int cy = dHeight * (1 + input);
            if(in != null) 
            {
                g.setColor(colorByInput(in[input]));
                g.fillRect(cx - size, cy - size, size * 2, size * 2);
            }
            g.setColor(Color.black);
            g.drawRect(cx - size, cy - size, size * 2, size * 2);
        }
        
        // draw rest of layers
        for(int layer = 0; layer < nn.numLayers(); layer++) 
        {
            int dHeight = img.getHeight() / (nn.numNeurons(layer) + 1);
            int iHeight = img.getHeight() / (nn.numInputs(layer) + 1);
            int cx = dWidth * (2 + layer);
            int cix = dWidth * (1 + layer);
            for(int neuron = 0; neuron < nn.numNeurons(layer); neuron++) 
            {
                g.setStroke(new BasicStroke(2));
                int cy = dHeight * (1 + neuron);
                
                if(in != null) 
                {
                    g.setColor(colorByValue(fullInput[layer][neuron]));
                    g.fillOval(cx - size, cy - size, size * 2, size * 2);
                }
                
                g.setColor(Color.black);
                g.drawOval(cx - size, cy - size, size * 2, size * 2);
                // connect to inputs
                for(int input = 0; input < nn.numInputs(layer); input++) 
                {
                    g.setColor(Color.black);
                    int ciy = iHeight * (1 + input);
                    int thick = thicknessByValue(Math.abs(nn.getWeight(layer, neuron, input)));
                    g.setColor(colorByValue(nn.getWeight(layer, neuron, input)));
                    g.setStroke(new BasicStroke(thick));
                    g.drawLine(cix + size, ciy, cx - size, cy);
                }
            }
        }
        
        g.dispose();
    }
    
    public BufferedImage getImage() 
    {
        return img;
    }
    
    public void setNN(NeuralNetwork nn) 
    {
        this.nn = nn;
    }
    
    private int mostNeurons() 
    {
        int most = 0;
        for(int layer = 0; layer < nn.numLayers(); layer++) 
        {
            if(nn.numNeurons(layer) > most) most = nn.numNeurons(layer);
        }
        return most;
    }
    
    private int thicknessByValue(double weight) 
    {
        return (int) (4 * 1f / (1 + (float)Math.exp(-weight)));
    }
    
    private Color colorByInput(double in) 
    {
        in = in * 2 - 1;
        float amt = 1f / (1 + (float)Math.exp(-in));
        int c = Color.HSBtoRGB((120f/360f)*amt, 0.5f, 0.85f);
        return new Color(c); 
    }
    
    private Color colorByValue(double val) 
    { 
        val = val * 2 - 1;
        float amt = 1f / (1 + (float)Math.exp(-val));
        int c = Color.HSBtoRGB((120f/360f)*amt, 0.5f, 0.85f);
        return new Color(c); 
    }
}
