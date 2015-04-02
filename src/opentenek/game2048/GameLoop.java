package opentenek.game2048;

public abstract class GameLoop implements Runnable
{
    private int tpsGoal;
    private int fpsGoal;
    private int skipFrames;
    private long frameTime;
    private int skipTicks;
    private long tickTime;
    private boolean running;
    
    private Thread gameThread;
    
    public GameLoop(int fps, int tps) 
    {
        tpsGoal = tps;
        fpsGoal = fps;
        skipTicks = 1000000000 / tpsGoal;
        skipFrames = 1000000000 / fpsGoal;
        frameTime = 0;
        tickTime = 0;
        
        running = false;
    }
    
    public synchronized void start() 
    {
        if(running)
            return;
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }
    
    public synchronized void stop() 
    {
        if(running) 
        {
            try
            {
                gameThread.join();
            } catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            running = false;
        }
    }
    
    public void run() 
    {
        frameTime = tickTime = System.nanoTime();
        
        int tps = 0;
        int fps = 0;
        int ticks = 0;
        int frames = 0;
        
        long timer = System.currentTimeMillis();
        
        while(running) 
        {
            int loops = 0;
            while(tickTime < System.nanoTime() && loops++ < 10) 
            {
                update();
                ticks++;
                
                tickTime += skipTicks;
            }
            
            if(frameTime < System.nanoTime()) 
            {
                render();
                frames++;
                
                frameTime += skipFrames;
            }
            
            if(System.currentTimeMillis() - timer >= 1000) 
            {
                timer = System.currentTimeMillis();
                tps = ticks;
                fps = frames;
                frames = ticks = 0;
                System.out.println(fps + " fps, " + tps + " ticks");
            }
        }
    }
    
    public abstract void update();
    public abstract void render();
}

