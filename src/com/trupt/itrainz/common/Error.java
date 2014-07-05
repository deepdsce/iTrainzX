package com.trupt.itrainz.common;

public class Error {

	private ErrorCodeEnum errorCode;
	
	public Error(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}
	
	public Error(ErrorCodeEnum errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorCode.setErrorMessage(errorMessage);
	}
	
	public String getErrorMessage() {
		return this.errorCode.toString();
	}
	
	@Override
	public String toString() {
		return this.errorCode.toString();
	}
	
	
	public static enum ErrorCodeEnum {
		ERROR("Error"),
		NETWORK_FAILURE("Unable to connect to server."), 
		FACILITY_NOT_AVAILABLE("Facility does not available due to network connection failure."),
		CUSTOM_ERR("");
		
		private String errorMessage;
		
		private ErrorCodeEnum(String errMsg) {
			errorMessage = errMsg;
		}
		
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		@Override
		public String toString() {
			return errorMessage;
		}
	}
	
}
