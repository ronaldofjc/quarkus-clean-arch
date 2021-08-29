package com.quarkus.clean.arch.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorData {
  private String message;
  private ErrorCode code;
  private String field;
  private Object identifier;

  public ErrorData(final String message, final ErrorCode code) {
    this.message = message;
    this.code = code;
  }

  public ErrorData(final String message, final String field, final ErrorCode code) {
    this.message = message;
    this.code = code;
    this.field = field;
  }
}
