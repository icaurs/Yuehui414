package com.icarus.yuehui414;

import com.icarus.yuehui414.R;
import com.icarus.yuehui414.application.Appointment;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends BaseActivity {
	
	private Appointment appointment;
	private Handler handler;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		setContentView(R.layout.activity_welcome);
		
		appointment = (Appointment) getApplication();
		
		handler = new Handler();
		handler.postDelayed(runnable, 2000);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.welcome, menu);
//		return true;
//	}
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			intent = new Intent(WelcomeActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			animEnter();
		}
	};

}
