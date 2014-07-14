package com.trupt.itrainz.model.request;

public class LoginIRCTCRequest extends Request {

	@Override
	public RequestTypeEnum getRequestType() {
		return RequestTypeEnum.LOGIN_IRCTC_REQUEST;
	}
	
}
