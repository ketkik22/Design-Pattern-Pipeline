package Pipes;

import java.util.Observer;

public abstract class Filter implements Observer
{
	protected Pipe in;
	protected Pipe out;
	
	public void setIn(Pipe p) 
	{
		in = p;
		if (Pipe.isDataDriven) 
		{
			in.addObserver(this);
		}
	}
	
	public void setOut(Pipe p)
	{
		out = p;
		if(!Pipe.isDataDriven)
		{
			out.addObserver(this);
		}
	}
}
