package com.kanyuxia.mockdata.exception;

public class MockException extends RuntimeException {

  public MockException(String message, Throwable cause) {
    super(message, cause);
  }

  public MockException() {
    super();
  }

  public MockException(String message) {
    super(message);
  }

}
