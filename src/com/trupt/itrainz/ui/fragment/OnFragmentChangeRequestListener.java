package com.trupt.itrainz.ui.fragment;

public interface OnFragmentChangeRequestListener {

	void onFragmentAddRequest(FragmentEnum fragmentEnum, FragmentTransitionType fragmentTransitionType, boolean addToBackStack);
	void onFragmentRemoveRequest(FragmentEnum fragmentEnum);
		
}
