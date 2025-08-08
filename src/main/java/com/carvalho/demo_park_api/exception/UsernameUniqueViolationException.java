package com.carvalho.demo_park_api.exception;

public class UsernameUniqueViolationException extends RuntimeException {
  public UsernameUniqueViolationException(String message) {
    super(message);
  }
}
