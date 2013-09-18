package com.icarus.yuehui414.application;

import com.icarus.yuehui414.R;

import android.app.Application;

public class Appointment extends Application {
	
	public static String[] main_title = {
		"导航菜单1", "导航菜单2", "导航菜单3"
	};
	
	public static int[] main_icon = {
		R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher
	};

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

}
