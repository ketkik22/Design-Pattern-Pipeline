package Pipes;

import java.util.Observable;

public class Consumer<T> extends Filter 
{
	public java.util.function.Consumer<T> consume;
	
	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		if(Pipe.isDataDriven)
		{
			Message<T> msg = in.read();
			if(!msg.fail)
			{
				//System.out.println("Call consumer");
				consume.accept(msg.content);
			}
		}
	}
	
	public void start()
	{
		if(!Pipe.isDataDriven)
		{
			Message<T> msg = in.read();
			while(!msg.quit)
			{
				consume.accept(msg.content);
				msg = in.read();
			}
		}
	}
	
	
	
}
