package opentenek.ann.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GraphImage
{
    private BufferedImage image;
    private ArrayList<Double> values;
    
    public GraphImage(int width, int height) 
    {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        values = new ArrayList<Double>();
        redraw();
    }
    
    public void setSize(int width, int height) 
    {
        if(image.getWidth() == width && image.getHeight() == height) return;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        redraw();
    }
    
    public void addValue(double val) 
    {
        values.add(val);
        redraw();
    }
    
    public BufferedImage getImage() 
    {
        return image;
    }
    
    private void redraw() 
    {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(2));
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        System.out.println("range: " + range());
        
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        
        double range = range();
        double highest = highestValue();
        double lowest = lowestValue();
        double dWidth = range <= 0 ? 0 : (double)width / values.size();
        // to get height: (value / highest) * height
        g.setColor(new Color(0x7f7f7f));
        for(int i = 1; i < values.size(); i++) 
        {
            int x1 = (int)(dWidth * (i - 1));
            int y1 = height - (int)((values.get(i - 1) - lowest) / (range) * height);
            int x2 = (int)(dWidth * i);
            int y2 = height - (int)((values.get(i) - lowest) / (range) * height);
            g.drawLine(x1, y1, x2, y2);
        }
        
        g.dispose();
    }
    
    private double highestValue() 
    {
        if(values.size() == 0) return 0.0;
        double high = values.get(0);
        for(double d : values) 
            if(d > high) high = d;
        return high;
    }
    
    private double lowestValue() 
    {
        if(values.size() == 0) return 0.0;
        double low = values.get(0);
        for(double d : values) 
            if(d < low) low = d;
        return low;
    }
    
    private double range() 
    {
        return highestValue() - lowestValue();
    }
}
