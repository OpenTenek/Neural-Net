package opentenek.ann.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphDisplay
{
    private JFrame frame;
    private GraphImage image;
    
    public GraphDisplay(int width, int height) 
    {
        frame = new JFrame("GraphDisplay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new NNPanel(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
        
        image = new GraphImage(width, height);
    }
    
    public void addValue(double value) 
    {
        image.addValue(value);
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
            
            Image img = image.getImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
