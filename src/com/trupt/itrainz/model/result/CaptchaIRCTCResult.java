package com.trupt.itrainz.model.result;

public class CaptchaIRCTCResult extends Result {

	private byte[] imageData;
	
	@Override
	public ResultTypeEnum getResultType() {
		return ResultTypeEnum.CAPTCHA_RESULT;
	}
	
	public byte[] getImageData() {
		return imageData;
	}
	
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
}
