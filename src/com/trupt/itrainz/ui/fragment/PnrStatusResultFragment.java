package com.trupt.itrainz.ui.fragment;

import android.view.View;

import com.trupt.itrainz.R;
import com.trupt.itrainz.core.Constants;
import com.trupt.itrainz.model.result.PnrStatusResult;


public class PnrStatusResultFragment extends BaseFragment {
	
	private PnrStatusResult pnrStatus;
	
	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_pnrstatus_result;
	}
	
	@Override
	protected void init(View view) {
	}
	
	@Override
	protected void setup() {
		Object result = getArguments().getSerializable(Constants.FragmentArgumentExtra.INPUT_DATA);
		if(result instanceof PnrStatusResult) {
			pnrStatus = (PnrStatusResult) result;
		}
	}
}
