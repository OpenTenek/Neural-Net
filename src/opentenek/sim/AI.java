package opentenek.sim;

import opentenek.ann.net.NeuralNetwork;

public class AI implements Controller
{
    private static final int ROTATE_LEFT    = 0;
    private static final int ROTATE_RIGHT   = 1;
    private static final int MOVE           = 2;
    
    private Rotation rotate = Rotation.None;
    private Movement move = Movement.None;
    private NeuralNetwork nn = null;
    
    public AI(NeuralNetwork nn) 
    {
        if(nn.getNumInputs() == SensorData.NUM_SENSORS &&
           nn.getNumOutputs() == 3)
            this.nn = nn;
    }
    
    public Rotation getRotation()
    {
        return rotate;
    }

    public Movement getMovement()
    {
        return move;
    }

    public void update(SensorData data)
    {
        if(nn == null) return;
        
        double input[] = data.asArray();
        
        double out[] = nn.fire(input);
        
        int rot = 0;
        if(out[ROTATE_LEFT]  > 0.5) rot -= 1;
        if(out[ROTATE_RIGHT] > 0.5) rot += 1;
        if(out[MOVE]         > 0.5) move = Movement.Forward;
        else move = Movement.None;
        
        switch(rot) 
        {
        case -1:
            rotate = Rotation.Left;
            break;
        case 1:
            rotate = Rotation.Right;
            break;
        case 0:
            rotate = Rotation.None;
            break;
        }
    }
}
