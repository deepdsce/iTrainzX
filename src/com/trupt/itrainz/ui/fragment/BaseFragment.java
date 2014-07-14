package com.trupt.itrainz.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trupt.itrainz.ui.activity.BaseActivityListener;

public abstract class BaseFragment extends Fragment implements BaseActivityListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(this.getLayoutResource(), null);
		this.init(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.setup();
	}
	
	@Override
	public void onActivityBackPressed() {		
	}
	
	protected void init(View view) {
	}
	
	protected void setup() {
	}
	
	protected abstract int getLayoutResource();
	
}
