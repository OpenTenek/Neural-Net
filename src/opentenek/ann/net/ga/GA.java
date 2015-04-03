package opentenek.ann.net.ga;

public class GA
{
    private AIPopulation pop;
    private int generation = 0;
    private int subset;
    private double crossoverRate;
    private double mutationRate;
    private double weightChange;
    
    public GA(AIPopulation pop, int sub, double cross, double mutate, double change) 
    {
        this.pop = pop;
        subset = sub;
        crossoverRate = cross;
        mutationRate = mutate;
        weightChange = change;
    }
    
    public AIPopulation getPopulation() 
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
        
        AIPopulation newPop = new AIPopulation(
                pop.size(),
                pop.layers(),
                pop.neurons());
        
        newPop.set(0, pop.getFittest());
        
        for(int i = 1; i < pop.size(); i++) 
        {
            AIData parent1 = pop.subset(subset).getFittest();
            AIData parent2 = pop.subset(subset).getFittest();
            newPop.set(i, mutate(crossover(parent1, parent2)));
        }
        
        pop = newPop;
        
        generation++;
    }
    
    private AIData crossover(AIData data1, AIData data2) 
    {
        AIData data = new AIData(pop.layers(), pop.neurons());
        
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
    
    private AIData mutate(AIData data) 
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
