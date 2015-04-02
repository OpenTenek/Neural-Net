package opentenek.ann.ga;

public class BinaryPopulation
{
    private BinaryString pop[];
    
    private int size;
    
    public BinaryPopulation(int num) 
    {
        size = num;
        pop = new BinaryString[size];
        
        generate();
    }
    
    public BinaryString[] getPopulation() 
    {
        return pop;
    }
    
    public BinaryString get(int index) 
    {
        return pop[index];
    }
    
    public void set(int index, BinaryString bs) 
    {
        pop[index] = bs;
    }
    
    public void generate() 
    {
        int length = GASystem.getGoal().length();
        
        for(int i = 0; i < size; i++) 
        {
            pop[i] = new BinaryString(length);
        }
    }
    
    public BinaryString getFittest() 
    {
        GASystem.sortBest(pop);
        return pop[0];
    }
    
    public BinaryPopulation getSubset(int amt) 
    {
        BinaryPopulation newPop = new BinaryPopulation(amt);
        
        for(int i = 0; i < amt; i++) 
        {
            int n = (int)(Math.random() * size);
            newPop.set(i, pop[n]);
        }
        
        return newPop;
    }
    
    public void printFitness() 
    {
        GASystem.sortBest(pop);
        
        for(int i = 0; i < size; i++)
        {
            System.out.print(pop[i]);
            System.out.print(", fitness: ");
            System.out.print(GASystem.determineFitness(pop[i]));
            System.out.println("%");
        }
    }
    
    public int size() 
    {
        return pop.length;
    }
}
