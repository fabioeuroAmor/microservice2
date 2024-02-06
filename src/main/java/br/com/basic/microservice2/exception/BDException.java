package br.com.basic.microservice2.exception;

public class BDException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 579704350879353120L;

	/**
	 * Default constructor
	 * @param message The detail message.
	 */
	public BDException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor
	 * @param message The detail message
	 * @param cause The root cause.
	 */
	public BDException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor
	 * @param cause The root cause.
	 */
	public BDException(Throwable cause) {
		super(cause);
	}

}
