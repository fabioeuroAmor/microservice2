package br.com.basic.microservice2.exception;


public class FileException extends RuntimeException {

	private static final long serialVersionUID = 7932906037871518427L;

	/**
	 * Default constructor
	 * @param message The detail message.
	 */
	public FileException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor
	 * @param message The detail message
	 * @param cause The root cause.
	 */
	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor
	 * @param cause The root cause.
	 */
	public FileException(Throwable cause) {
		super(cause);
	}
	
}
