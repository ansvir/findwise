package com.findwise.exception;

public class ElementNotIndexedException extends Exception {
  public ElementNotIndexedException() {
    super("Element has not been indexed.");
  }
}
