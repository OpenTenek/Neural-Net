package opentenek.game2048.ga;

public class GA2048
{
    private AIPopulation pop;
    private int generation = 0;
    
    public GA2048(AIPopulation pop) 
    {
        this.pop = pop;
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
            AIData parent1 = pop.subset(50).getFittest();
            AIData parent2 = pop.subset(50).getFittest();
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
            if(Math.random() < 0.5) 
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
            if(Math.random() < 0.02) 
            {
                double w = data.getData().getWeight(i);
                w += Math.random() * 10;
                data.getData().setWeight(i, w);
            }
        }
        
        return data;
    }
}
