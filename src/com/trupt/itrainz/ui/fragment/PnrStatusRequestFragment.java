package com.trupt.itrainz.ui.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trupt.itrainz.R;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.PnrStatusRequest;
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
	public void onActivityBackPressed() {
		if(operation != null) {
			operation.cancelOperation();
		}
	}
	
	@Override
	protected void init(View view) {
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
	
	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_pnrstatus_request;
	}

	@Override
	public void onClick(View v) {
		if(v instanceof Button) {
			String pnrNo = editTextPnr.getText().toString();
			PnrStatusRequest pnrRequest = new PnrStatusRequest(pnrNo);
			this.operation = new HttpOperation(pnrRequest);
			this.operation.setOperationStatusListener(this);
			this.operation.startOperation();
			this.showLoading();
		}
	}

	@Override
	public void onSuccess(Operation operation, Result result) {
		this.operation = null;
		this.hideLoading();
		if(result.getErrorMessage() != null) {
			Toast.makeText(this.getActivity(), result.getErrorMessage(), Toast.LENGTH_LONG).show();
		} else {
			if(getActivity() instanceof OnFragmentChangeRequestListener) {
				((OnFragmentChangeRequestListener)getActivity()).onFragmentAddRequest(FragmentEnum.PNR_STATUS_RESULT, FragmentTransitionType.ADD, true, result);
			}
		}
	}

	@Override
	public void onFailure(Operation operation, Error error) {
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
