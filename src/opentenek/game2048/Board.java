package opentenek.game2048;

public class Board
{
    private Tile tiles[];
    private int width, height;
    
    private int score = 0;
    
    public Board(int w, int h) 
    {
        width = w;
        height = h;
        tiles = new Tile[w*h];
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public int getScore() { return score; }
    public void resetScore() { score = 0; }
    
    public void clearBoard() 
    {
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++) 
                setTile(x, y, null);
    }
    
    public void spawnTile(int x, int y, int val) 
    {
        if(!inBounds(x, y)) return;
        
        tiles[x + y*width] = new Tile(x, y, val);
    }
    
    public void spawnRandomTile() 
    {
        int x, y;
        do 
        {
            x = (int)(Math.random() * width);
            y = (int)(Math.random() * height);
        } while(tiles[x + y*width] != null);
        
        int val = Math.random() < .9 ? 2 : 4;
        
        spawnTile(x, y, val);
    }
    
    public Tile getTile(int x, int y) 
    {
        if(inBounds(x, y)) return tiles[x + y*width];
        return null;
    }
    
    private void setTile(int x, int y, Tile t) 
    {
        if(inBounds(x, y)) tiles[x + y*width] = t;
    }
    
    public boolean canMove() 
    {
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++) 
            {
                if(getTile(x, y) != null) 
                {
                    int xx = x-1, yy = y;
                    if(inBounds(xx, yy) && (getTile(xx, yy) == null || getTile(xx, yy).getValue() == getTile(x, y).getValue())) return true;
                    xx = x+1; yy = y;
                    if(inBounds(xx, yy) && (getTile(xx, yy) == null || getTile(xx, yy).getValue() == getTile(x, y).getValue())) return true;
                    xx = x; yy = y-1;
                    if(inBounds(xx, yy) && (getTile(xx, yy) == null || getTile(xx, yy).getValue() == getTile(x, y).getValue())) return true;
                    xx = x; yy = y+1;
                    if(inBounds(xx, yy) && (getTile(xx, yy) == null || getTile(xx, yy).getValue() == getTile(x, y).getValue())) return true;
                }
            }
        
        return false;
    }
    
    public boolean moveUp() 
    {
        boolean moved = false;
        
        for(int y = 0; y < height; y++) 
            for(int x = 0; x < width; x++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                moved = move(x, y, 0, -1) ? true : moved;
            }
        
        return moved;
    }
    
    public boolean moveDown() 
    {
        boolean moved = false;
        
        for(int y = height - 1; y >= 0; y--) 
            for(int x = 0; x < width; x++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                moved = move(x, y, 0, 1) ? true : moved;
            }
        
        return moved;
    }
    
    public boolean moveLeft() 
    {
        boolean moved = false;
        
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                moved = move(x, y, -1, 0) ? true : moved;
            }
        
        return moved;
    }
    
    public boolean moveRight() 
    {
        boolean moved = false;
        
        for(int x = width - 1; x >= 0; x--)
            for(int y = 0; y < height; y++) 
            {
                Tile t = getTile(x, y);
                if(t == null) continue;
                moved = move(x, y, 1, 0) ? true : moved;
            }
        
        return moved;
    }
    
    private boolean move(int x, int y, int dx, int dy) 
    {
        boolean moved = false;
        
        int xx = x, yy = y;
        if(getTile(x, y) == null) return false;
        while(inBounds(xx + dx, yy + dy)) 
        {
            Tile t = getTile(xx, yy);
            if(getTile(xx + dx, yy + dy) == null) 
            {
                setTile(xx, yy, null);
                setTile(xx + dx, yy + dy, t);
                t.setLocation(new Point(xx + dx, yy + dy));
                moved = true;
            } else if(getTile(xx + dx, yy + dy).getValue() == t.getValue() && t.canCombine() && getTile(xx + dx, yy + dy).canCombine()) 
            {
                t.setValue(t.getValue() * 2);
                setTile(xx + dx, yy + dy, t);
                setTile(xx, yy, null);
                t.setLocation(new Point(xx + dx, yy + dy));
                t.setToReal();
                t.setCanCombine(false);
                moved = true;
                score += t.getValue();
            }
            
            xx += dx;
            yy += dy;
        }
        
        return moved;
    }
    
    public void update() 
    {
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++) 
                if(tiles[x + y*width] != null) 
                {
                    tiles[x + y*width].update();
                }
    }
    
    public boolean inBounds(int x, int y) 
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
