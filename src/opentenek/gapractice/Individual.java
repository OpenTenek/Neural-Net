package opentenek.gapractice;
import java.util.Arrays;

public class Individual
{
	//it's chromosome; it's solution to the problem
	//represented by a binary bitstring
	//example value: 1000101010110
	private String value = "";
	
	//length of goal... to be put in elsewhere...
	int length = 9;
	
	//ctor
	public Individual ()
	{
		//generates random numbers for value, so it's as long as the goal value
		for (int i = 0; i < length; i++)
		{
		int rand = (int) (Math.random()*2);
		value = value += rand;
		}
	}
	
	//public String getValue () { return value; }
	
	public String toString ()
	{
		return value;
	}
}

