package br.com.basic.microservice2.exception;

public class ProcessingException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3845969738940817562L;

	/**
	 * Default constructor
	 * @param message The detail message.
	 */
	public ProcessingException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor
	 * @param message The detail message
	 * @param cause The root cause.
	 */
	public ProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor
	 * @param cause The root cause.
	 */
	public ProcessingException(Throwable cause) {
		super(cause);
	}


}
