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
    
    public void generate() 
    {
        int length = GASystem.getGoal().length();
        
        for(int i = 0; i < size; i++) 
        {
            pop[i] = new BinaryString(length);
        }
    }
    
    public void printFitness() 
    {
        for(int i = 0; i < size; i++)
        {
            System.out.print(pop[i]);
            System.out.print(", fitness: ");
            System.out.print(GASystem.determineFitness(pop[i]));
            System.out.println("%");
        }
    }
    
    public void sortBest() 
    {
        BinaryString tmp;
        double fitTmp, fitNew;
        int b;
        for(int i = 0; i < size; i++) 
        {
            b = i;
            fitTmp = GASystem.determineFitness(pop[b]);
            for(int j = i+1; j < size; j++) 
            {
                fitNew = GASystem.determineFitness(pop[j]);
                if(fitNew > fitTmp) 
                {
                    b = j;
                    fitTmp = fitNew;
                }
            }
            tmp = pop[i];
            pop[i] = pop[b];
            pop[b] = tmp;
        }
    }
}
