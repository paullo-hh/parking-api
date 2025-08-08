package com.carvalho.demo_park_api.exception;

public class PasswordInvalidException extends RuntimeException {
  public PasswordInvalidException(String message) {
    super(message);
  }
}
