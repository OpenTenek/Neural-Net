package opentenek.sim;

public interface Controller
{
    public enum Rotation 
    {
        Left, None, Right;
    }
    
    public enum Movement 
    {
        Forward, None;
    }
    
    public Rotation getRotation();
    
    public Movement getMovement();
    
    public void update(SensorData data);
}
