package com.learning.management.common.exceptions;

public class NotFoundException extends RuntimeException {

  public static NotFoundException taskNotFound() {
    return new NotFoundException("The task was not found by the provided ID.");
  }

  public static NotFoundException bodyNotFound() {
    return new NotFoundException("Body not found.");
  }

  public NotFoundException(String message) {
    super(message);
  }
}
