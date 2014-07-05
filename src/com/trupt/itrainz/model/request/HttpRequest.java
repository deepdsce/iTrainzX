package com.trupt.itrainz.model.request;

import java.net.URL;
import java.util.Map;

public abstract class HttpRequest extends Request {
	protected URL url;
	protected Map<String, String> mapParameters;
	protected Map<String, String> mapCookies;
	protected HttpMethodEnum methodEnum;
	
	public HttpRequest() { 
	
	}
	
	public HttpRequest(URL url, Map<String, String> mapParameters,
			Map<String, String> mapCookies, HttpMethodEnum methodEnum) {
		super();
		this.url = url;
		this.mapParameters = mapParameters;
		this.mapCookies = mapCookies;
		this.methodEnum = methodEnum;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Map<String, String> getMapParameters() {
		return mapParameters;
	}

	public void setMapParameters(Map<String, String> mapParameters) {
		this.mapParameters = mapParameters;
	}

	public Map<String, String> getMapCookies() {
		return mapCookies;
	}

	public void setMapCookies(Map<String, String> mapCookies) {
		this.mapCookies = mapCookies;
	}

	public HttpMethodEnum getMethodEnum() {
		return methodEnum;
	}

	public void setMethodEnum(HttpMethodEnum methodEnum) {
		this.methodEnum = methodEnum;
	}


	public enum HttpMethodEnum {
		POST("POST"), GET("GET");
		
		String methodType;
		
		private HttpMethodEnum(String methodType) {
			this.methodType = methodType;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return methodType.toString();
		}
	}
}
