package com.icarus.yuehui414.application;

import java.util.List;
import java.util.Map;

import com.icarus.yuehui414.R;
import com.icarus.yuehui414.entity.EventsList;

import android.app.Application;

public class Appointment extends Application {
	
	public static String[] main_title = {
		"�����˵�1", "�����˵�2", "�����˵�3"
	};
	
	public static String[] sex = {
		"��Ů", "˧��", "ȫ��"
	};
	
	public static int[] main_icon = {
		R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher
	};
	
	public static String url = "http://www.yuehui414.com/";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

}
