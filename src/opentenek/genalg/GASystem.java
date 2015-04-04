package opentenek.genalg;

public class GASystem
{
    private static String goal = "";
    
    public static String getGoalData() { return goal; }
    public static String getGoal() { return new String(goal).replace('0', '.'); }
    public static void setGoal(String newGoal) { goal = newGoal; }
    
    public static double determineFitness(BinaryString str) 
    {
        int length = goal.length() < str.getData().length() ? goal.length() : str.getData().length();
        int correct = 0;
        char g[] = goal.toCharArray(), s[] = str.getData().toCharArray();
        for(int i = 0; i < length; i++) 
        {
            if(g[i] == s[i]) correct++;
        }
        double percent = (double)correct / length;
        percent = Math.round(percent*10000);
        
        return percent / 100.0;
    }
    
    public static void sortBest(BinaryString pop[]) 
    {
        BinaryString tmp;
        double fitTmp, fitNew;
        int b;
        for(int i = 0; i < pop.length - 1; i++) 
        {
            b = i;
            fitTmp = GASystem.determineFitness(pop[i]);
            for(int j = i+1; j < pop.length; j++) 
            {
                fitNew = GASystem.determineFitness(pop[j]);
                if(fitNew > fitTmp) 
                {
                    b = j;
                    fitTmp = fitNew;
                }
            }
            if(b == i) continue;
            tmp = pop[i];
            pop[i] = pop[b];
            pop[b] = tmp;
        }
        
//        return pop;
    }
    
    
}
