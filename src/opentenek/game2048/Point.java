package opentenek.game2048;

public class Point
{
    public double x, y;
    
    public Point(double x, double y) 
    {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(Point p) 
    {
        return p.x == x && p.y == y;
    }
}
