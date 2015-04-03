package opentenek.sim;

public class Entity
{
    public enum Direction 
    {
        Up, Down, Left, Right;
    }
    
    public int x, y;
    public Controller controller;
    public Direction direction;
    
    public Entity(int x, int y, Controller controller) 
    {
        this.x = x;
        this.y = y;
        this.controller = controller;
        
        double r = Math.random();
        if(r < .25) direction = Direction.Up;
        else if(r < .50) direction = Direction.Down;
        else if(r < .75) direction = Direction.Left;
        else direction = Direction.Right;
    }
    
    public void update(World world) 
    {
        SensorData data = new SensorData();
        data.entityInFront = entity(world);
        data.plantInFront = plant(world);
        
        if(controller == null) return;
        
        controller.update(data);
        
        switch(controller.getRotation()) 
        {
        case Left:
            break;
        case Right:
            break;
        case None:
            break;
        }
        
        switch(controller.getMovement()) 
        {
        case Forward:
            move(world);
            break;
        case None:
            break;
        }
    }
    
    private void move(World world) 
    {
        switch(direction) 
        {
        case Up:
            if(!world.inBounds(x, y-1) || world.getEntity(x, y-1) != null) return;
            world.removeEntity(x, y);
            world.addEntity(x, --y, this);
            return;
        case Down:
            if(!world.inBounds(x, y+1) || world.getEntity(x, y+1) != null) return;
            world.removeEntity(x, y);
            world.addEntity(x, ++y, this);
            return;
        case Left:
            if(!world.inBounds(x-1, y) || world.getEntity(x-1, y) != null) return;
            world.removeEntity(x, y);
            world.addEntity(--x, y, this);
            return;
        case Right:
            if(!world.inBounds(x+1, y) || world.getEntity(x+1, y) != null) return;
            world.removeEntity(x, y);
            world.addEntity(++x, y, this);
            return;
        }
    }
    
    private boolean plant(World world) 
    {
        switch(direction) 
        {
        case Up:
            if(!world.inBounds(x, y-1)) return false;
            return world.containsPlant(x, y-1);
        case Down:
            if(!world.inBounds(x, y+1)) return false;
            return world.containsPlant(x, y+1);
        case Left:
            if(!world.inBounds(x-1, y)) return false;
            return world.containsPlant(x-1, y);
        case Right:
            if(!world.inBounds(x+1, y)) return false;
            return world.containsPlant(x+1, y);
        }
        
        return false;
    }
    
    private boolean entity(World world) 
    {
        switch(direction) 
        {
        case Up:
            if(!world.inBounds(x, y-1)) return false;
            return world.getEntity(x, y-1) != null;
        case Down:
            if(!world.inBounds(x, y+1)) return false;
            return world.getEntity(x, y+1) != null;
        case Left:
            if(!world.inBounds(x-1, y)) return false;
            return world.getEntity(x-1, y) != null;
        case Right:
            if(!world.inBounds(x+1, y)) return false;
            return world.getEntity(x+1, y) != null;
        }
        
        return false;
    }
}
