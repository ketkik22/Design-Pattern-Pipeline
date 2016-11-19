package Pipes;

import java.util.Observable;
import java.util.function.Function;

public class Transformer<T,R> extends Filter 
{
	public Function<T,T> transform;
	
	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		Message<T> msg = in.read();
		try
		{
			if(!msg.fail)
				msg.content = transform.apply(msg.content);
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
