package com.trupt.itrainz.operation;

import com.trupt.itrainz.async.TrAsyncTask;
import com.trupt.itrainz.async.TrAsyncTask.AsyncTaskCompletionListener;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.Request;
import com.trupt.itrainz.model.result.Result;

public abstract class Operation implements AsyncTaskCompletionListener<Result> {

	protected TrAsyncTask<Request, Result> asyncTask;
	protected OperationStatusListener operationStatusListener;
	protected Request request;
	
	public abstract void startOperation();
	public abstract void cancelOperation();
	
	public Operation(Request request, OperationStatusListener opStatusListener) {
		this.operationStatusListener = opStatusListener;
		this.request = request;
	}
	
	public OperationTypeEnum getOperationEnum() {
		return OperationTypeEnum.OP;
	}
	

	
	public interface OperationStatusListener {
		void onSuccess(Result result);
		void onFailure(Error error);
		void onCancel();
	}
	
	protected enum OperationTypeEnum {
		OP, PNR_OP
	}
}

