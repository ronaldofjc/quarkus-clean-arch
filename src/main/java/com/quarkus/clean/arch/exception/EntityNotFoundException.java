package com.quarkus.clean.arch.exception;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(final String message) {
    super(message);
  }

}

