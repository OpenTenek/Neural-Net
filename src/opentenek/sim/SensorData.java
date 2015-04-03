package opentenek.sim;

public class SensorData
{
    public final static int NUM_SENSORS = 2;
    
    public boolean plantInFront = false;
    public boolean entityInFront = false;
    
    public double[] asArray() 
    {
        return new double[] 
                {
                    boolToDouble(plantInFront),
                    boolToDouble(entityInFront),
                };
    }
    
    private double boolToDouble(boolean b) 
    {
        return b ? 1.0 : 0.0;
    }
}
