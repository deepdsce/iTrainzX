package com.trupt.itrainz.async;

import android.os.AsyncTask;

import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.Request;

public abstract class TrAsyncTask<IN extends Request, Result> extends AsyncTask<IN, Void, Result> {

	protected AsyncTaskCompletionListener<Result> listener;
	protected Error error;
	
	public void setListener(AsyncTaskCompletionListener<Result> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Result doInBackground(IN... params) {
		return null;
	}
	
	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		if(listener != null) {
			if(result != null) {
				listener.onSuccess(result);
			} else {
				//TODO: set default error if no error set 
				listener.onFailure(error);
			}
		}
	}
	
	public interface AsyncTaskCompletionListener<Result> {
		void onSuccess(Result result);
		void onFailure(Error error);
	}
}
