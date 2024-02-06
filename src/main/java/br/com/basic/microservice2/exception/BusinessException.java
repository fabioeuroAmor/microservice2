package br.com.basic.microservice2.exception;




public class BusinessException extends RuntimeException {
	

  
  /**
	 * 
	 */
	private static final long serialVersionUID = -3212409463874095838L;

/**
	 * Default constructor
	 * @param message The detail message.
	 */
	public BusinessException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor
	 * @param message The detail message
	 * @param cause The root cause.
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor
	 * @param cause The root cause.
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

}
