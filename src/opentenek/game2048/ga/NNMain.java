package opentenek.game2048.ga;

import opentenek.game2048.GameLoop;

public class NNMain
{
    public static void main(String args[]) 
    {
        GameLoop game = new NNGame();
        
        game.start();
    }
}
