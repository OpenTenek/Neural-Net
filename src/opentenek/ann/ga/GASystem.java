package opentenek.ann.ga;

public class GASystem
{
    private static String goal = "";
    
    public static String getGoalData() { return goal; }
    public static String getGoal() { return new String(goal).replace('0', '.'); }
    public static void setGoal(String newGoal) { goal = newGoal; }
    
    public static double determineFitness(BinaryString str) 
    {
        int length = goal.length() < str.toString().length() ? goal.length() : str.toString().length();
        int correct = 0;
        char g[] = goal.toCharArray(), s[] = str.toString().toCharArray();
        for(int i = 0; i < length; i++) 
        {
            if(g[i] == s[i]) correct++;
        }
        double percent = (double)correct / length;
        percent = Math.round(percent*10000);
        
        return percent / 100.0;
    }
}
