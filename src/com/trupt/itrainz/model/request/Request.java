package com.trupt.itrainz.model.request;


public abstract class Request {
	
	protected RequestTypeEnum requestType;

	public RequestTypeEnum getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestTypeEnum requestType) {
		this.requestType = requestType;
	}
	
}
