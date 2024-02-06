package br.com.basic.microservice2.exception;


public class NegocioException extends RuntimeException {


	/**
	 *
	 */
	private static final long serialVersionUID = 579704350879353120L;

	/**
	 * Default constructor
	 * @param message The detail message.
	 */
	public NegocioException(String message) {
		super(message);
	}

	/**
	 * Default constructor
	 * @param message The detail message
	 * @param cause The root cause.
	 */
	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor
	 * @param cause The root cause.
	 */
	public NegocioException(Throwable cause) {
		super(cause);
	}

	

}
