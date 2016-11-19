package Pipes;

import java.util.Observable;
import java.util.function.Supplier;
import Pipes.Producer;

public class Producer<T> extends Filter 
{
	public Supplier<T> produce;

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		if (!Pipe.isDataDriven)
		{
			Message<T> msg = makeContent();
			out.write(msg);
		}
	}
	
	private Message<T> makeContent()
	{
		Message<T> msg = new Message<T>();
		try 
		{
			//System.out.println("Producer make content method");
			T content = produce.get();
			
			if(content == null)
				throw new Exception("Quit");
			msg.content = content;
		} 
		catch(Exception e) 
		{
			//System.out.println(e);
			msg.quit = true;
		}
		return msg;
	}


	public void start() 
	{
		if(Pipe.isDataDriven) 
		{
			//System.out.println("prodcer start method");
			Message<T> msg = makeContent();
			while(!msg.quit) 
			{
				out.write(msg);
				msg = makeContent();
			}
		}
	}
	
}
