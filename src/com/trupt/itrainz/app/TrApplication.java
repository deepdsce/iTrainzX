package com.trupt.itrainz.app;

import android.app.Application;
import android.content.Context;

public class TrApplication extends Application {

	private static Context appContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		appContext = getApplicationContext();
	}
	
	public static Context getAppContext() {
		return appContext;
	}
	
}
