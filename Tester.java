package Pipes;

import java.util.Observable;
import java.util.function.Function;

public class Tester<T,R> extends Filter 
{
	public Function<T,T> test;
	
	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		//System.out.println("Tester");
		Message<T> msg = in.read();
		try
		{
			if(!msg.fail)
				test.apply(msg.content);
			if(msg.content == null)
				throw new Exception();
		}
		catch(Exception e)
		{
			msg.fail = true;
		}
		finally
		{
			out.write(msg);
		}
	}
}
