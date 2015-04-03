package opentenek.gapractice;
import java.util.Arrays;

public class Population
{
	private Individual[] pop;
	
	//ctor
	public Population (int size)
	{
		pop = new Individual[size];
		
		for (int i = 0; i < size; i ++)
		{
		Individual indie = new Individual();
		pop[i] = indie;
		}
	}
	
	public String toString ()
	{
		return Arrays.toString(pop);
	}
}