package com.icarus.yuehui414.application;

import java.util.List;
import java.util.Map;

import com.icarus.yuehui414.R;
import com.icarus.yuehui414.entity.EventsList;

import android.app.Application;

public class Appointment extends Application {
	
	public static String[] main_title = {
		"导航菜单1", "导航菜单2", "导航菜单3"
	};
	
	public static String[] sex = {
		"美女", "帅哥", "全部"
	};
	
	public static int[] main_icon = {
		R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher
	};
	
	public static String url = "http://www.yuehui414.com/";
	
	private String LA_Id;
	
	private EventsList eventsList;
	
	public String getLA_Id() {
		return LA_Id;
	}

	public void setLA_Id(String lA_Id) {
		LA_Id = lA_Id;
	}

	public EventsList getEventsList() {
		return eventsList;
	}

	public void setEventsList(EventsList eventsList) {
		this.eventsList = eventsList;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

}
