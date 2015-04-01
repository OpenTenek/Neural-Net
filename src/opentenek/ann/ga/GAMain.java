package opentenek.ann.ga;

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
        BinaryPopulation bp = new BinaryPopulation(10);
        
        System.out.println("Goal: ");
        System.out.println(GASystem.getGoal());
        bp.sortBest();
        bp.printFitness();
    }
}
