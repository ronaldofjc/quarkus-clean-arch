package com.quarkus.clean.arch.exception;


public class GatewayException extends RuntimeException {
  
    public GatewayException(final String message, final Throwable cause) {
      super(message, cause);
    }

    public GatewayException(String message) {
    }
}
