package Baetube_backEnd.exception;

public class DuplicateUserException extends RuntimeException 
{
	
	public DuplicateUserException(String message) 
	{
		super(message);
	}
}
