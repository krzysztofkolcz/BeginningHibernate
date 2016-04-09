package com.kkolcz.exception;

public class SkuExistsException extends Exception{

  public SkuExistsException() { super(); }
  public SkuExistsException(String message) { super(message); }
  public SkuExistsException(String message, Throwable cause) { super(message, cause); }
  public SkuExistsException(Throwable cause) { super(cause); }

}
