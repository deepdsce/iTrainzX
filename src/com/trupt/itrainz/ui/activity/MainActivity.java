package com.trupt.itrainz.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.trupt.itrainz.R;
import com.trupt.itrainz.ui.fragment.BaseFragment;
import com.trupt.itrainz.ui.fragment.FragmentEnum;
import com.trupt.itrainz.ui.fragment.FragmentFactory;
import com.trupt.itrainz.ui.fragment.FragmentTransitionType;
import com.trupt.itrainz.ui.fragment.OnFragmentChangeRequestListener;

public class MainActivity extends BaseActivity implements OnFragmentChangeRequestListener {

	BaseFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.onFragmentAddRequest(FragmentEnum.HOME, FragmentTransitionType.REPLACE, false);
	}

	@Override
	public void onFragmentAddRequest(FragmentEnum fragmentEnum, FragmentTransitionType fragmentTransitionType, boolean addToBackStack) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		this.fragment = FragmentFactory.getFragment(fragmentEnum);
		if(fragmentTransitionType == FragmentTransitionType.REPLACE) {
			fragmentTransaction.replace(R.id.layoutMain, this.fragment);
		} else {
			fragmentTransaction.add(R.id.layoutMain, this.fragment);
		}
		if(addToBackStack) {
			fragmentTransaction.addToBackStack(fragment.getClass().getName());
		}
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		fragmentTransaction.commit();
	}

	@Override
	public void onFragmentRemoveRequest(FragmentEnum fragmentEnum) {
		
	}
	
}
