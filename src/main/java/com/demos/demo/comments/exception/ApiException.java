package com.demos.demo.comments.exception;

public class ApiException extends RuntimeException{

  private static final long serialVersionUID = 1L;
  
  private String code;
  
  public ApiException(String code) {
	super();
	this.code = code;
  }
  
  ApiException(final String code, final String description, final Throwable cause){
    super(description, cause);
    this.code = code;
  }
  
  public static ApiException builderException(Throwable cause) {
	  return new ApiException("E001", "Error al consumir el servicio RETO", cause);
  }

  public String getCode() {
    return code;
  }
  
}
