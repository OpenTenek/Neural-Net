package opentenek.game2048;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter
{
    public static final int UP = KeyEvent.VK_UP;
    public static final int DOWN = KeyEvent.VK_DOWN;
    public static final int LEFT = KeyEvent.VK_LEFT;
    public static final int RIGHT = KeyEvent.VK_RIGHT;
    public static final int ESCAPE = KeyEvent.VK_ESCAPE;
    
    private static final int MAX_KEYS = 256;
    private static final int WAIT_TIME = 15;
    
    private boolean keys[];
    private int keyTimes[];
    
    public Keyboard() 
    {
        keys = new boolean[MAX_KEYS];
        keyTimes = new int[MAX_KEYS];
    }
    
    public void update() 
    {
        for(int i = 0; i < MAX_KEYS; i++) 
            if(keys[i]) keyTimes[i]++;
            else keyTimes[i] = 0;
    }
    
    public boolean isKeyDown(int code) 
    {
        if(inBounds(code))
            return keyTimes[code] > 0;
        return false;
    }
    
    public boolean isKeyTyped(int code) 
    {
        if(inBounds(code)) 
            return keyTimes[code] == 1 || keyTimes[code] > WAIT_TIME;
        return false;
    }
    
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if(inBounds(code))
            keys[code] = true;
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if(inBounds(code))
            keys[code] = false;
    }
    
    private boolean inBounds(int code) 
    {
        return code >= 0 && code < MAX_KEYS;
    }
}
