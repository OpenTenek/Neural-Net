package opentenek.game2048;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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

    public void render(Board board)
    {
        setColor(0x77777a);
        fillRect(0, 0, img.getWidth(), img.getHeight());
        
        gfx.setFont(new Font("Courier New", Font.BOLD, 20));
        
        for(int x = 0; x < board.getWidth(); x++) 
        {
            for(int y = 0; y < board.getHeight(); y++) 
            {
                setColor(0x99999a); 
                
                roundRect(x*100 + 5, 100 + y*100 + 5, 90, 90, 20);
            }
        }
        
        for(int x = 0; x < board.getWidth(); x++) 
        {
            for(int y = 0; y < board.getHeight(); y++) 
            {
                Tile t = board.getTile(x, y);
                if(t == null) continue;
                
                int val = t.getValue();
                
                double xx = t.getDisplayLocation().x;
                double yy = t.getDisplayLocation().y;
                
                setColor(getColorCode(val));
                
                roundRect(xx*100 + 5, 100 + yy*100 + 5, 90, 90, 20);
                setColor(0x000000);
                gfx.drawString("" + val, (int)(xx*100 + 45), 100 + (int)(yy*100 + 57));
            }
        }
        
        gfx.drawString("Score: " + board.getScore(), 200, 50);
        
        if(!board.canMove()) 
        {
            gfx.drawString("You Lose!", 20, 50);
        }
    }
    
    private int getColorCode(int val) 
    {
        switch(val) 
        {
        case 2: return 0xeeeeee;
        case 4: return 0xeeddaa;
        case 8: return 0xffaa33;
        case 16: return 0xff8800;
        case 32: return 0xff6644;
        case 64: return 0xff0000;
        case 128: return 0xeedd88;
        case 256: return 0xffee77;
        case 512: return 0xffee55;
        case 1024: return 0xffee22;
        case 2048: return 0xffee00;
        default: return 0x0000ff;
        }
    }
}
