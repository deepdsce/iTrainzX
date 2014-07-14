package com.trupt.itrainz.operation;

import com.trupt.itrainz.async.IRHttpRequestAsyncTask;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.Request;
import com.trupt.itrainz.model.result.Result;

public class HttpOperation extends Operation {
	
	public HttpOperation(Request request) {
		super(request);
	}
	
	@Override
	public void startOperation() {
		startRequest();
	}

	@Override
	public void cancelOperation() {
		if(asyncTask != null) {
			asyncTask.setAsyncTaskCompletionListener(null);
			asyncTask.cancel(true);
		}
	}
	
	@Override
	public void onSuccess(Result result) {
		asyncTask = null;
		if(operationStatusListener != null) {
			operationStatusListener.onSuccess(this, result);
		}
	}
	
	@Override
	public void onFailure(Error error) {
		asyncTask = null;
		if(operationStatusListener != null) {
			operationStatusListener.onFailure(this, error);
		}
	}
	
	private void startRequest() {
		switch (this.request.getRequestType()) {
		case PNR_STATUS_REQUEST:
			asyncTask = new IRHttpRequestAsyncTask<>();
			break;
		}
		if(asyncTask != null) {
			asyncTask.setAsyncTaskCompletionListener(this);
			asyncTask.execute(this.request);
		}
	}

}
