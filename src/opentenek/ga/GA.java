package opentenek.ga;

public class GA
{
    private int generation = 0;
    private double crossoverRate = 0.8;
    private double mutationRate = 0.02;
    private int subsetSize = 5;
    
    private BinaryPopulation pop;
    
    public GA(BinaryPopulation pop) 
    {
        this.pop = pop;
    }
    
    public double bestFitness() 
    {
        return GASystem.determineFitness(pop.getFittest());
    }
    
    public void print() 
    {
        System.out.println("Generation " + generation);
        pop.printFitness(); 
    }

    public void evolve()
    {
        BinaryPopulation newPop = new BinaryPopulation(pop.size());
        
        newPop.set(0, pop.getFittest());
        
        for(int i = 1; i < pop.size(); i++) 
        {
            BinaryString bs0 = bestInSubset();
            BinaryString bs1 = bestInSubset();
            
            newPop.set(i, mutate(crossover(bs0, bs1)));
        }
        
        pop = newPop;
        
        generation++;
    }
    
    private BinaryString crossover(BinaryString bs0, BinaryString bs1) 
    {
        BinaryString newString = new BinaryString(bs0.size());
        
        for(int i = 0; i < bs0.size(); i++) 
        {
            if(Math.random() < crossoverRate) 
            {
                newString.setBit(i, bs0.getBit(i));
            } else 
            {
                newString.setBit(i, bs1.getBit(i));
            }
        }
        
        return newString;
    }
    
    private BinaryString mutate(BinaryString bs) 
    {
        for(int i = 0; i < bs.size(); i++) 
        {
            if(Math.random() < mutationRate) 
            {
                int c = Math.random() < 0.5 ? 0 : 1;
                bs.setBit(i, c); 
            }
        }
        
        return bs;
    }
    
    private BinaryString bestInSubset() 
    {
        return pop.getSubset(subsetSize).getFittest();
    }
}
