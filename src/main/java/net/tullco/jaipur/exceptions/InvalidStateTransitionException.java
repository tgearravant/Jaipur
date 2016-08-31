package net.tullco.jaipur.exceptions;

public class InvalidStateTransitionException extends Exception {

	private static final long serialVersionUID = -5901498476568883569L;

	public InvalidStateTransitionException(){};
	public InvalidStateTransitionException(String message){
		super(message);
	}
	public InvalidStateTransitionException(Throwable cause){
		super(cause);
	}
	public InvalidStateTransitionException(String message,Throwable cause){
		super(message,cause);
	}
}
