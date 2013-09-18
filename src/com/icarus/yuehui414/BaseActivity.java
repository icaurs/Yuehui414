package com.icarus.yuehui414;

import com.icarus.yuehui414.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	public void animEnter() {
		overridePendingTransition(R.anim.push_left_in, R.anim.zoom_exit_2);
	}
	
	public void animBack() {
		overridePendingTransition(R.anim.zoom_enter_2, R.anim.push_right_out);
	}

}
