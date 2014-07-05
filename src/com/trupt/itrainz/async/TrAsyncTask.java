package com.trupt.itrainz.async;

import android.os.AsyncTask;

import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.Request;

public abstract class TrAsyncTask<IN extends Request, Result> extends AsyncTask<IN, Void, Result> {

	protected AsyncTaskCompletionListener<Result> asyncTaskCompletionListener;
	protected Error error;
	
	public void setAsyncTaskCompletionListener(AsyncTaskCompletionListener<Result> listener) {
		this.asyncTaskCompletionListener = listener;
	}
	
	@Override
	protected Result doInBackground(IN... params) {
		return null;
	}
	
	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		if(asyncTaskCompletionListener != null) {
			if(result != null) {
				asyncTaskCompletionListener.onSuccess(result);
			} else {
				//TODO: set default error if no error set 
				asyncTaskCompletionListener.onFailure(error);
			}
		}
	}
	
	public interface AsyncTaskCompletionListener<Result> {
		void onSuccess(Result result);
		void onFailure(Error error);
	}
}
