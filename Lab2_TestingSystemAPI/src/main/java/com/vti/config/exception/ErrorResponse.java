package com.vti.config.exception;

public class ErrorResponse {

	private String message;

	private String detailMessage;
	
	private Object error;

	private Integer code;

	private String moreInformation;

	
	public ErrorResponse() {
		super();
	}
	
	

	public ErrorResponse(String message, String detailMessage, Object error, Integer code, String moreInformation) {
		super();
		this.message = message;
		this.detailMessage = detailMessage;
		this.error = error;
		this.code = code;
		this.moreInformation = moreInformation;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMoreInformation() {
		return moreInformation;
	}

	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}
	
	
}
