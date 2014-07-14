package com.trupt.itrainz.model.request;

public class PingIRCTCRequest extends Request {

	@Override
	public RequestTypeEnum getRequestType() {
		return RequestTypeEnum.PING_IRCTC_REQUEST;
	}
	
}
