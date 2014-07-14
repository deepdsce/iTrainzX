package com.trupt.itrainz.operation;

import com.trupt.itrainz.async.BaseAsyncTask;
import com.trupt.itrainz.async.BaseAsyncTask.AsyncTaskCompletionListener;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.Request;
import com.trupt.itrainz.model.result.Result;

public abstract class Operation implements AsyncTaskCompletionListener<Result> {

	protected BaseAsyncTask<Request, Result> asyncTask;
	protected OperationStatusListener operationStatusListener;
	protected Request request;
	
	public abstract void startOperation();
	public abstract void cancelOperation();
	
	public Operation(Request request) {
		this.request = request;
	}
	
	public OperationTypeEnum getOperationEnum() {
		return OperationTypeEnum.OP;
	}
	
	public void setOperationStatusListener(
			OperationStatusListener operationStatusListener) {
		this.operationStatusListener = operationStatusListener;
	}
	
	public interface OperationStatusListener {
		void onSuccess(Operation operation, Result result);
		void onFailure(Operation operation, Error error);
		void onCancel();
	}
	
	protected enum OperationTypeEnum {
		OP, PNR_OP
	}
}

