package com.demos.demo.comments.exception;

public class ApiException extends RuntimeException{

  private static final long serialVersionUID = 1L;
  
  private String code;
  private String description;
  
  public ApiException(String code, String description) {
	super();
	this.code = code;
	this.description = description;
  }
  
  ApiException(final String code, final String description, final Throwable cause){
    super(description, cause);
    this.code = code;
	this.description = description;
  }
  
  public static ApiException builderException(Throwable cause) {
	  return new ApiException("E001", "Error al consumir el servicio RETO", cause);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
	this.code = code;
  }

  public String getDescription() {
	return description;
  }

  public void setDescription(String description) {
	this.description = description;
  }
  
}
