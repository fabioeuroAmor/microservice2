package br.com.basic.microservice2.exception;

public class XMLException extends RuntimeException{
	

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1349638405367969996L;

	/**
	 * Default constructor
	 * @param message The detail message.
	 */
	public XMLException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor
	 * @param message The detail message
	 * @param cause The root cause.
	 */
	public XMLException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor
	 * @param cause The root cause.
	 */
	public XMLException(Throwable cause) {
		super(cause);
	}

	
}
