package opentenek.game2048;

public class Game extends GameLoop
{
    public Board board;
    public Display display;
    public Keyboard keyboard;
    
    public Game() 
    {
        super(1000, 60);
        
        display = new Display("2048", 400, 500);
        keyboard = display.getKeyboard();
        
        board = new Board(4, 4);
        
        board.spawnRandomTile();
        board.spawnRandomTile();
    }
    
    public void update() 
    {
        keyboard.update();
        
        board.update();
        
        boolean moved = false;
        
        if(keyboard.isKeyTyped(Keyboard.UP))         moved = board.moveUp();
        else if(keyboard.isKeyTyped(Keyboard.DOWN))  moved = board.moveDown();
        else if(keyboard.isKeyTyped(Keyboard.LEFT))  moved = board.moveLeft();
        else if(keyboard.isKeyTyped(Keyboard.RIGHT)) moved = board.moveRight();
        else moved = false;
        
        if(keyboard.isKeyTyped(Keyboard.ESCAPE)) 
        { 
            board.clearBoard(); 
            board.resetScore();
            
            board.spawnRandomTile();
            board.spawnRandomTile();
        }
        
        if(moved) board.spawnRandomTile();
    }
    
    public void render() 
    {
        display.render(board);
        
        display.show();
    }
}
