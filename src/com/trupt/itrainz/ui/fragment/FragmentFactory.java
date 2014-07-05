package com.trupt.itrainz.ui.fragment;

public class FragmentFactory {

	public static BaseFragment getFragment(FragmentEnum fragmentEnum) {
		BaseFragment baseFragment = null;
		switch (fragmentEnum) {
		case PNR_STATUS_REQUEST:
			baseFragment = new PnrStatusRequestFragment();
			break;
		case PNR_STATUS_RESULT:
			
			break;
		default:
			baseFragment = new HomeFragment();
			break;
		}
		return baseFragment;
	}
	
}
