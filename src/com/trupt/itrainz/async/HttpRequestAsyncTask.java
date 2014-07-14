package com.trupt.itrainz.async;

import com.google.gson.Gson;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.HttpRequest;
import com.trupt.itrainz.model.request.Request;
import com.trupt.itrainz.model.result.PnrStatusResult;

public class HttpRequestAsyncTask<IN extends Request, Result> extends BaseAsyncTask<IN, Result> {

	protected HttpRequest request;
	
	@Override
	protected Result doInBackground(IN... params) {
		Result result = null;
		try {
			this.request = (HttpRequest) params[0];
			String response = this.getResponse(request);
			String jsonResponse = this.getJsonResponse(response);
			Class<? extends Result> clazz = this.getResultClass();
			Gson gson = new Gson();
			result = gson.fromJson(jsonResponse, clazz);
		} catch (Exception e) {
			error = new Error(Error.ErrorCodeEnum.NETWORK_FAILURE);
			e.printStackTrace();
		}
		return result;
	}
	
	protected String getResponse(Request req) throws Exception {
		return null;
	}
	
	protected String getJsonResponse(String resp) {
		return resp;
	}
	
	protected Class getResultClass() {
		Class clazz = null;
		switch (request.getRequestType()) {
		case PNR_STATUS_REQUEST:
			clazz = PnrStatusResult.class;
			break;
		}
		return clazz;
	}
}
