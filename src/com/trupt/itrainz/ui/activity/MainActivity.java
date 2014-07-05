package com.trupt.itrainz.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.trupt.itrainz.R;
import com.trupt.itrainz.ui.fragment.BaseFragment;
import com.trupt.itrainz.ui.fragment.FragmentEnum;
import com.trupt.itrainz.ui.fragment.FragmentFactory;
import com.trupt.itrainz.ui.fragment.OnFragmentChangeRequestListener;

public class MainActivity extends BaseActivity implements OnFragmentChangeRequestListener {

	BaseFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.onFragmentAddRequest(FragmentEnum.HOME);
	}

	@Override
	public void onFragmentAddRequest(FragmentEnum fragmentEnum) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		this.fragment = FragmentFactory.getFragment(fragmentEnum);
		fragmentTransaction.replace(R.id.layoutMain, this.fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onFragmentRemoveRequest(FragmentEnum fragmentEnum) {
		
	}
	
}
