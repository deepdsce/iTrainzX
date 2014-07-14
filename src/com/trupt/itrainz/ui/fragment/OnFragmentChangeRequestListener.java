package com.trupt.itrainz.ui.fragment;

import com.trupt.itrainz.model.result.Result;

public interface OnFragmentChangeRequestListener {

	void onFragmentAddRequest(FragmentEnum fragmentEnum, FragmentTransitionType fragmentTransitionType, boolean addToBackStack, Result result);
	void onFragmentRemoveRequest(FragmentEnum fragmentEnum);
		
}
