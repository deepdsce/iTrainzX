package com.trupt.itrainz.ui.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trupt.itrainz.R;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.PnrRequest;
import com.trupt.itrainz.model.result.Result;
import com.trupt.itrainz.operation.HttpOperation;
import com.trupt.itrainz.operation.Operation;
import com.trupt.itrainz.operation.Operation.OperationStatusListener;


public class PnrStatusRequestFragment extends BaseFragment implements OnClickListener, OperationStatusListener {
	
	private EditText editTextPnr;
	private Button buttonGetPnrStatus;
	private Operation operation;
	private ProgressDialog progressDialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pnrstatus_request, null);
		init(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setup();
	}
	
	@Override
	public void onActivityBackPressed() {
		if(operation != null) {
			operation.cancelOperation();
		}
	}
	
	private void init(View view) {
		editTextPnr = (EditText) view.findViewById(R.id.editTextPnr);
		editTextPnr.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.toString().length() == 10) {
					buttonGetPnrStatus.setEnabled(true);
				} else {
					buttonGetPnrStatus.setEnabled(false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		buttonGetPnrStatus = (Button) view.findViewById(R.id.buttonGetPnr); 
		buttonGetPnrStatus.setOnClickListener(this);
	}
	
	private void setup() {
		
	}

	@Override
	public void onClick(View v) {
		if(v instanceof Button) {
			String pnrNo = editTextPnr.getText().toString();
			PnrRequest pnrRequest = new PnrRequest(pnrNo);
			this.operation = new HttpOperation(pnrRequest, this);
			this.operation.startOperation();
			this.showLoading();
		}
	}

	@Override
	public void onSuccess(Result result) {
		this.operation = null;
		this.hideLoading();
		if(result.getErrorMessage() != null) {
			Toast.makeText(this.getActivity(), result.getErrorMessage(), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this.getActivity(), "success", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onFailure(Error error) {
		this.operation = null;
		this.hideLoading();
		Toast.makeText(this.getActivity(), error.getErrorMessage(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCancel() {
		this.operation = null;
		this.hideLoading();
		Toast.makeText(this.getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
	}
	
	private void showLoading() {
		this.progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("getting pnr status..");
		progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				operation.cancelOperation();
			}
		});
		progressDialog.show();
	}
	
	private void hideLoading() {
		if(progressDialog != null) {
			progressDialog.dismiss();
		}
		progressDialog = null;
	}

}
