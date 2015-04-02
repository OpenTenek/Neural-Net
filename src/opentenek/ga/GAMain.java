package opentenek.ga;

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
        
        while(ga.bestFitness() < 100) 
        {
            ga.evolve();
            ga.print(); 
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
