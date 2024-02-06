package br.com.basic.microservice2.exception;

public class LocaleException extends RuntimeException{

  /**
   *
   */
  private static final long serialVersionUID = -6946811761480344079L;

  public LocaleException(String messageId) {
    super(messageId);
  }

  public LocaleException(Throwable cause) {
    super(cause);
  }

  public LocaleException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

  public LocaleException(String messageId, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(messageId, cause, enableSuppression, writableStackTrace);
  }

  
  
}