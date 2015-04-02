package opentenek.game2048;

public class Tile 
{
    private Point dispLocation;
    private Point realLocation;
    
    private int realValue;
    
    private boolean canCombine = true;
    private boolean atRealLocation;
    
    public Tile(int x, int y, int value) 
    {
        dispLocation = new Point((double)x, (double)y);
        realLocation = new Point((double)x, (double)y);
        
        realValue = value;
        
        atRealLocation = true;
    }
    
    public int getValue() { return realValue; }
    public void setValue(int v) { realValue = v; }
    
    public Point getLocation() { return realLocation; }
    public void setLocation(Point p) 
    { 
        realLocation = p; 
        atRealLocation = false;
    }
    
    public boolean canCombine() { return canCombine; }
    public void setCanCombine(boolean b) { canCombine = b; }
    
    public Point getDisplayLocation() { return dispLocation; }
    
    public boolean atRealLocation() { return atRealLocation; }
    
    public void setToReal() 
    {
        dispLocation = realLocation;
        atRealLocation = true;
    }
    
    public void update() 
    {
        double move = 0.5;
        
        if(realLocation.x > dispLocation.x) 
            if(realLocation.x - dispLocation.x <= move) dispLocation.x = realLocation.x;
            else dispLocation.x += move;
        if(realLocation.x < dispLocation.x) 
            if(dispLocation.x - realLocation.x <= move) dispLocation.x = realLocation.x;
            else dispLocation.x -= move;
        if(realLocation.y > dispLocation.y) 
            if(realLocation.y - dispLocation.y <= move) dispLocation.y = realLocation.y;
            else dispLocation.y += move;
        if(realLocation.y < dispLocation.y) 
            if(dispLocation.y - realLocation.y <= move) dispLocation.y = realLocation.y;
            else dispLocation.y -= move;
        
        if(realLocation.equals(dispLocation)) 
        {
            atRealLocation = true;
        }
        
        canCombine = true;
    }
}
