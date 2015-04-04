package opentenek.ga;

import opentenek.ann.graphics.GraphDisplay;

/* 
 * File Name : GAMain.java
 * Author(s) : Thinic
 * Created   : 2015 Apr 1
 * Updated   : 2015 Apr 1
 * Version   : 0.0.1
 * 
 * Main method for genetic algorithm practice.
 */

public class GAMain
{
    public static void main(String args[]) 
    {
        GASystem.setGoal("11111111000000000000000011111111");
        BinaryPopulation bp = new BinaryPopulation(20);
        GA ga = new GA(bp);
        
        System.out.println("Goal: ");
        System.out.println(GASystem.getGoal());
        ga.print(); 
        
        GraphDisplay display = new GraphDisplay(640, 480);
        
        while(ga.bestFitness() < 100) 
        {
            ga.evolve();
            ga.print(); 
            double best = ga.bestFitness();
            display.addValue(best);
            
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
