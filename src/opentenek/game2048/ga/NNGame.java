package opentenek.game2048.ga;

import opentenek.ann.net.NeuralNetwork;
import opentenek.game2048.Board;
import opentenek.game2048.Display;
import opentenek.game2048.GameLoop;
import opentenek.game2048.Tile;

public class NNGame extends GameLoop
{
    public Board board;
    public Display display;
    public NeuralNetwork ai;
    public AIPopulation pop;
    public GA2048 ga;
    
    private int ticks = 0;
    
    public NNGame()
    {
        super(60, 5000);
        
        init();
    }

    private void init() 
    {
        board = new Board(4, 4);
        board.spawnRandomTile();
        board.spawnRandomTile();
        display = new Display("2048: Neural Network", 400, 500);
        
        pop = new AIPopulation(
                100,
                2,
                new int[]{board.getWidth()*board.getHeight(), board.getWidth()*board.getHeight(), 4}
            );
        
        ga = new GA2048(pop);
        
        AIData data = pop.get(curIndex);
        ai = new NeuralNetwork(data.getData());
    }
    
    private int curIndex = 0;
    private int bestVal = 0;
    private int bTicks = 0;
    
    public void update()
    {
        ticks++;
        bTicks++;
        
        if(bTicks >= 5000/60) 
        {
            bTicks = 0;
            board.update();
        }
        
        
        if(ticks >= (curIndex == 0 ? 400 : 0)) 
        {
            ticks = 0;
            board.update(); 
            int width = board.getWidth();
            double input[] = new double[board.getWidth()*board.getHeight()];
            double hval = Math.log(board.highestValue()) / Math.log(2);
            for(int x = 0; x < board.getWidth(); x++)
                for(int y = 0; y < board.getHeight(); y++) 
                {
                    Tile t = board.getTile(x, y);
                    double val = t == null ? 1 : t.getValue();
                    val = Math.log(val) / Math.log(2);
                    input[x + y*width] = val / hval;
                }
            
//            printArray(input);
//            System.exit(1);
            boolean moved = false;
            boolean allowed[] = new boolean[]{true, true, true, true};
            double out[] = ai.fire(input);
            
            
            while(!moved && board.canMove()) 
            {
                double max = -1;
                int index = -1;
                for(int i = 0; i < 4; i++) 
                    if(out[i] > max && allowed[i]) 
                    {
                        max = out[i];
                        index = i;
                    }
                
                switch(index) 
                {
                case 0:
                    moved = board.moveUp();
                    if(!moved) allowed[0] = false;
                    break;
                case 1: 
                    moved = board.moveDown();
                    if(!moved) allowed[1] = false;
                    break;
                case 2: 
                    moved = board.moveLeft();
                    if(!moved) allowed[2] = false;
                    break;
                case 3:
                    moved = board.moveRight();
                    if(!moved) allowed[3] = false;
                    break;
                default:
                    break;
                }
                
                boolean m = false;
                for(boolean b : allowed) 
                    if(b) m = true;
                if(!m) break;
            }
            
            
            
            if(moved) board.spawnRandomTile();
            else 
            {
                int high = board.highestValue();
                if(high > bestVal) bestVal = high;
                
                pop.get(curIndex).setScore(board.getScore());
                
                reset();
                
                curIndex++;
//                System.out.println("Index: " + curIndex);
                if(curIndex >= pop.size()) 
                {
                    pop.sortBestScore();
                    System.out.println(); 
                    System.out.println("Generation: " + ga.getGeneration());
                    System.out.println("Highest Value: " + bestVal);
                    pop.print();
                    
                    curIndex = 0;
                    ga.evolve();
                    pop = ga.getPopulation();
                    
                    bestVal = 0;
                }
                ai = new NeuralNetwork(pop.get(curIndex).getData());
            }
        }
    }

    private void printArray(double[] input)
    {
        for(int i = 0; i < input.length; i++) 
        {
            System.out.print(input[i] + ", \t");
            if((i+1) % 4 == 0) System.out.println(); 
        }
    }

    public void render()
    {
        display.render(board);
        
        display.show();
    }
    
    // probably should put this in board...
    private void reset() 
    {
        board.clearBoard(); 
        board.resetScore();
        
        board.spawnRandomTile();
        board.spawnRandomTile();
    }
}
