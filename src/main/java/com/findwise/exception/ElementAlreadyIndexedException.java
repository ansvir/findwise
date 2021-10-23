package com.findwise.exception;

public class ElementAlreadyIndexedException extends Throwable {
  public ElementAlreadyIndexedException(String id) {
    super("Element with the ID [" + id + "] is already indexed");
  }
}
