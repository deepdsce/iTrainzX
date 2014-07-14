package com.trupt.itrainz.model.result;

import java.io.Serializable;

public abstract class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String errorMessage;
	protected ResultTypeEnum resultType;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public abstract ResultTypeEnum getResultType();

}
