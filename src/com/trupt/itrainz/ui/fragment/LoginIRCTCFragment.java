package com.trupt.itrainz.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.trupt.itrainz.R;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.LoginIRCTCRequest;
import com.trupt.itrainz.model.result.CaptchaIRCTCResult;
import com.trupt.itrainz.model.result.Result;
import com.trupt.itrainz.operation.HttpOperation;
import com.trupt.itrainz.operation.Operation;
import com.trupt.itrainz.operation.Operation.OperationStatusListener;


public class LoginIRCTCFragment extends BaseFragment implements OperationStatusListener {
	
	private EditText editTextUsername;
	private EditText editTextPassword;
	private ImageView imageViewCaptcha;
	private Button buttonSubmitLogin;
	private HttpOperation operation;
	
	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_login_irctc;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	protected void init(View view) {
		editTextUsername = (EditText) view.findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
		imageViewCaptcha = (ImageView) view.findViewById(R.id.imageViewCaptcha);
		buttonSubmitLogin = (Button) view.findViewById(R.id.buttonSubmitLogin);
		buttonSubmitLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginIRCTCRequest request= new LoginIRCTCRequest();
				operation = new HttpOperation(request);
				operation.setOperationStatusListener(LoginIRCTCFragment.this);
				operation.startOperation();
			}
		});
	}
	
	@Override
	protected void setup() {
		//TODO start request to get captcha image in background		
	}

	@Override
	public void onSuccess(Operation operation, Result result) {
		switch (result.getResultType()) {
		case CAPTCHA_RESULT:
			if(result instanceof CaptchaIRCTCResult) {
				byte[] imageData = ((CaptchaIRCTCResult)result).getImageData();
				Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
				imageViewCaptcha.setImageBitmap(bitmap);
			}
			break;

		case LOGIN_IRCTC_RESULT:
				
		default:
			break;
		}
	}

	@Override
	public void onFailure(Operation operation, Error error) {
	}

	@Override
	public void onCancel() {
	}
}
