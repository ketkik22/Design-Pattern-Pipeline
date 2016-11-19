package Pipes;

import java.util.Observable;
import Pipes.Message;

public class Pipe<T> extends Observable 
{
	public static boolean isDataDriven;
	Message msg;
	
	public Message<T> read() 
	{
		if(!isDataDriven) 
		{
			setChanged();
			notifyObservers();
		}
		return msg;
	}
	
	public void write(Message<T> msg)
	{
		this.msg = msg;
		if(isDataDriven) 
		{
			setChanged();
			notifyObservers();
		}
	}
}
