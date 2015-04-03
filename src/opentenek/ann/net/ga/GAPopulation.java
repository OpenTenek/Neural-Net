package opentenek.ann.net.ga;

public class GAPopulation
{
    private GAData pop[];
    private int layers;
    private int neurons[];
    
    public GAPopulation(int amt, int layers, int neurons[]) 
    {
        this.layers = layers;
        this.neurons = neurons;
        pop = new GAData[amt];
        for(int i = 0; i < amt; i++) 
            pop[i] = new GAData(layers, neurons);
    }
    
    public GAData get(int index) 
    {
        return pop[index];
    }
    
    public void set(int index, GAData data) 
    {
        pop[index] = data;
    }
    
    public GAPopulation subset(int amt) 
    {
        GAPopulation subset = new GAPopulation(amt, layers, neurons);
        
        for(int i = 0; i < amt; i++) 
        {
            int index = (int)(Math.random()*pop.length);
            subset.set(i, pop[index]);
        }
        
        return subset;
    }
    
    public GAData getFittest() 
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
        GAData tmp;
        for(int i = 0; i < size() - 1; i++) 
        {
            int best = i;
            for(int j = i + 1; j < size(); j++) 
                if(pop[j].getFitness() > pop[best].getFitness()) 
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
        for(GAData data : pop) 
        {
            System.out.print("Index " + i + ": " + data.getFitness() + "\t");
            i++;
            if(i % 10 == 0) System.out.println(); 
        }
    }
}
