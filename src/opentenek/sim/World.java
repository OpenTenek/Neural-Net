package opentenek.sim;

public class World
{
    private int width, height;
    private Tile tiles[][];
    
    public World(int width, int height) 
    {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++)
                tiles[x][y] = new Tile();
    }
    
    public void clear() 
    {
        for(int x = 0; x < width; x++) 
            for(int y = 0; y < height; y++)
                tiles[x][y] = new Tile();
    }
    
    public void update() {}
    
    public void addPlant(int x, int y) { tiles[x][y].plant = true; }
    public void removePlant(int x, int y) { tiles[x][y].plant = false; }
    
    public void addEntity(int x, int y, Entity entity) { tiles[x][y].entity = entity; }
    public void removeEntity(int x, int y) { tiles[x][y].entity = null; }
    
    public boolean containsPlant(int x, int y) { return tiles[x][y].plant; }
    public Entity getEntity(int x, int y) { return tiles[x][y].entity; }
    
    public boolean inBounds(int x, int y) 
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
