package opentenek.game2048.ga;

public class AIPopulation
{
    private AIData pop[];
    private int layers;
    private int neurons[];
    
    public AIPopulation(int amt, int layers, int neurons[]) 
    {
        this.layers = layers;
        this.neurons = neurons;
        pop = new AIData[amt];
        for(int i = 0; i < amt; i++) 
            pop[i] = new AIData(layers, neurons);
    }
    
    public AIData get(int index) 
    {
        return pop[index];
    }
    
    public void set(int index, AIData data) 
    {
        pop[index] = data;
    }
    
    public AIPopulation subset(int amt) 
    {
        AIPopulation subset = new AIPopulation(amt, layers, neurons);
        
        for(int i = 0; i < amt; i++) 
        {
            int index = (int)(Math.random()*pop.length);
            subset.set(i, pop[index]);
        }
        
        return subset;
    }
    
    public AIData getFittest() 
    {
        sortBestScore();
        return pop[0];
    }
    
    public int size() 
    {
        return pop.length;
    }
    
    public int layers() 
    {
        return layers;
    }
    
    public int[] neurons() 
    {
        return neurons;
    }
    
    public void sortBestScore() 
    {
        AIData tmp;
        for(int i = 0; i < size() - 1; i++) 
        {
            int best = i;
            for(int j = i + 1; j < size(); j++) 
                if(pop[j].getScore() > pop[best].getScore()) 
                    best = j;
            if(best == i) continue;
            tmp = pop[i];
            pop[i] = pop[best];
            pop[best] = tmp;
        }
    }

    public void print()
    {
        int i = 0;
        for(AIData data : pop) 
        {
            System.out.print("Index " + i + ": " + data.getScore() + "\t");
            i++;
            if(i % 10 == 0) System.out.println(); 
        }
    }
}
