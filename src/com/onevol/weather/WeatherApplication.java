package com.onevol.weather;

import android.app.Application;

import com.thinkland.juheapi.common.CommonFun;


public class WeatherApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		CommonFun.initialize(getApplicationContext());
		
	}


}
