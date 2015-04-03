package opentenek.sim;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display
{
    private JFrame frame;
    private Canvas canvas;
    private BufferedImage img;
    private Graphics gfx;
    
    private Keyboard keyboard;
    
    public Display(String title, int width, int height) 
    {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new Canvas();
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        gfx = img.getGraphics();
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        keyboard = new Keyboard();
        canvas.addKeyListener(keyboard);
        
        canvas.requestFocus();
    }
    
    public Keyboard getKeyboard() 
    {
        return keyboard;
    }
    
    public void setColor(int col) 
    {
        gfx.setColor(new Color(col));
    }
    
    public void fillRect(int x, int y, int w, int h) 
    {
        gfx.fillRect(x, y, w, h);
    }
    
    public void roundRect(double x, double y, double w, double h, double a) 
    {
        gfx.fillRoundRect((int)x, (int)y, (int)w, (int)h, (int)a, (int)a);
    }
    
    public void render(World world) 
    {
        int size = 16;
        
        int width = world.getWidth();
        int height = world.getHeight();
        
        setColor(0xdddddd);
        fillRect(0, 0, getWidth(), getHeight());
        
        setColor(0x000000);
        for(int x = 0; x < width; x++) 
            fillRect(x * size, 0, 1, height * size);
        for(int y = 0; y < height; y++) 
            fillRect(0, y * size, width * size, 1);
        
        for(int x = 0; x < width; x++) 
        {
            for(int y = 0; y < height; y++) 
            {
                if(world.containsPlant(x, y)) 
                {
                    setColor(0x007f00);
                    fillRect(x * size + 2, y * size + 2, size - 3, size - 3);
                }
                Entity e = world.getEntity(x, y);
                if(e != null) 
                {
                    setColor(0x7f0000);
                    switch(e.direction) 
                    {
                    case Up: 
                        fillRect(x * size + size / 2 - 1, y * size + 3, 3, size - 5);
                        fillRect(x * size + 3, y * size + size - 5, size - 5, 3);
                        break;
                    case Down:
                        fillRect(x * size + size / 2 - 1, y * size + 3, 3, size - 5);
                        fillRect(x * size + 3, y * size + 3, size - 5, 3);
                        break;
                    case Left:
                        fillRect(x * size + 3, y * size + size / 2 - 1, size - 5, 3);
                        fillRect(x * size + size - 5, y * size + 3, 3, size - 5);
                        break;
                    case Right: 
                        fillRect(x * size + 3, y * size + size / 2 - 1, size - 5, 3);
                        fillRect(x * size + 3, y * size + 3, 3, size - 5);
                        break;
                    }
                }
            }
        }
    }
    
    public void show() 
    {
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) 
        {
            canvas.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight(), canvas);
        
        g.dispose();
        bs.show();
    }
    
    public int getWidth() 
    {
        return canvas.getWidth();
    }
    
    public int getHeight() 
    {
        return canvas.getHeight();
    }
}
