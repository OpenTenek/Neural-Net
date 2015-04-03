package opentenek.sim;

public class Game extends GameLoop
{
    public Display display;
    public Keyboard keyboard;
    
    public World world;
    
    public Game() 
    {
        super(1000, 60);
        
        display = new Display("Sim", 600, 400);
        keyboard = display.getKeyboard();
        
        world = new World(30, 20);
        world.addPlant(2, 3);
        world.addPlant(3, 3);
        world.addEntity(3, 5, new Entity(3, 5, null));
    }
    
    public void update() 
    {
        keyboard.update();
    }
    
    public void render() 
    {
        display.render(world);
        
        display.show();
    }
}
