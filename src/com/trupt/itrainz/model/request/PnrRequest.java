package com.trupt.itrainz.model.request;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class PnrRequest extends HttpRequest {

	private String pnrNumber;
	
	public PnrRequest(String pnString) {
		this.pnrNumber = pnString;
		this.createPnrRequest();
	}
	
	public String getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	private void createPnrRequest() {
		//TODO: getValueFromSharedPreference and create request accordingly
		try {
			URL url = new URL("http://www.indianrail.gov.in/cgi_bin/inet_pnstat_cgi_10521.cgi");
			Map<String, String> params = new LinkedHashMap<>();
			params.put("lccp_pnrno1", this.pnrNumber);
			Random random = new Random();
			int capchaValue = random.nextInt(100000);
			params.put("lccp_cap_val", "" + capchaValue);
			params.put("lccp_capinp_val", "" + capchaValue);
			this.setUrl(url);
			this.setMethodEnum(HttpMethodEnum.POST);
			this.setMapParameters(params);
			this.setMapCookies(null);
			this.setRequestType(RequestTypeEnum.PNR_STATUS);
		} catch (MalformedURLException e) {
		}
	}
 	
}
