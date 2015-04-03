package opentenek.ann.net.ga;

public class GA
{
    private GAPopulation pop;
    private int generation = 0;
    private int subset;
    private double crossoverRate;
    private double mutationRate;
    private double weightChange;
    
    public GA(GAPopulation pop, int sub, double cross, double mutate, double change) 
    {
        this.pop = pop;
        subset = sub;
        crossoverRate = cross;
        mutationRate = mutate;
        weightChange = change;
    }
    
    public GAPopulation getPopulation() 
    {
        return pop;
    }
    
    public int getGeneration() 
    {
        return generation;
    }
    
    public void evolve() 
    {
        pop.sortBestScore();
        
        GAPopulation newPop = new GAPopulation(
                pop.size(),
                pop.layers(),
                pop.neurons());
        
        newPop.set(0, pop.getFittest());
        
        for(int i = 1; i < pop.size(); i++) 
        {
            GAData parent1 = pop.subset(subset).getFittest();
            GAData parent2 = pop.subset(subset).getFittest();
            newPop.set(i, mutate(crossover(parent1, parent2)));
        }
        
        pop = newPop;
        
        generation++;
    }
    
    private GAData crossover(GAData data1, GAData data2) 
    {
        GAData data = new GAData(pop.layers(), pop.neurons());
        
        for(int i = 0; i < data.getData().getDataArray().length; i++) 
        {
            if(Math.random() < crossoverRate) 
            {
                data.getData().setWeight(i, data1.getData().getWeight(i));
            } else 
            {
                data.getData().setWeight(i, data2.getData().getWeight(i));
            }
        }
        
        return data;
    }
    
    private GAData mutate(GAData data) 
    {
        for(int i = 0; i < data.getData().getDataArray().length; i++) 
        {
            if(Math.random() < mutationRate) 
            {
                double w = data.getData().getWeight(i);
                w += Math.random() * weightChange * 2 - weightChange;
                data.getData().setWeight(i, w);
            }
        }
        
        return data;
    }
    
    public void print() 
    {
        System.out.println("Generation: " + generation);
        pop.print();
    }
}
