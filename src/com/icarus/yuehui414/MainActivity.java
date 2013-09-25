package com.icarus.yuehui414;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icarus.yuehui414.R;
import com.icarus.yuehui414.R.color;
import com.icarus.yuehui414.adapter.MainAdapter;
import com.icarus.yuehui414.application.Appointment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends FragmentActivity {
	
	private List<Map<String, Object>> list;
	private Map<String, Object> map;
	private DrawerLayout drawer;
	private ListView navList;
	private ImageView ivLeft, ivUser, ivIcon;
	private TextView tvTitle, tvUser, tvMenu;
	private MainAdapter mainAdapter;
	private String[] classes;
	private int dw_state;
	private Appointment appointment;
	private View headView;
	private RelativeLayout layUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		
		list = new ArrayList<Map<String,Object>>();
		
		appointment = (Appointment) getApplication();
		
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		ivLeft = (ImageView) findViewById(R.id.ivLeft);
		
		drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		navList = (ListView) findViewById(R.id.drawer);
		headView = LayoutInflater.from(this).inflate(R.layout.listview_head_main, null);
		ivUser = (ImageView) headView.findViewById(R.id.ivUser);
		tvUser = (TextView) headView.findViewById(R.id.tvUser);
		navList.addHeaderView(headView);
		headView.setOnClickListener(new HeadClick());
		
		//加载页面标题
//		tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
		//点击左侧菜单
		ivLeft.setOnClickListener(new LeftClick());
		
		navList.setSelector(new ColorDrawable(getResources().getColor(R.color.menu_user_bg)));
		//加载菜单列表
		list = getTitleList();
		mainAdapter = new MainAdapter(MainActivity.this, list);
		navList.setAdapter(mainAdapter);
//		navList.setBackgroundResource(android.R.drawable.dialog_holo_dark_frame);
		
		//加载页面模块
		classes = getResources().getStringArray(R.array.nav_classes);
		navList.setOnItemClickListener(new OnItemClickListener(){
			@SuppressLint("ResourceAsColor")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int pos, long id){
				if (pos > 0) {
					drawer.setDrawerListener(new DwListener(0, pos - 1));
					drawer.closeDrawer(navList);
				}
			}
		});
		navList.setOnItemSelectedListener(new MenuItemSelected());
		
		//加载初始页面模块
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, classes[0]));
		tx.commit();
		
		//监听DrawerLayout滑动事件
		drawer.setDrawerListener(new DwListener(1, 1));
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
//			overridePendingTransition(R.anim.zoom_enter_2, R.anim.push_right_out);
			return false;
		}
		return false;
	}
	
	class MenuItemSelected implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			tvMenu = (TextView) arg1.findViewById(R.id.tvMenu);
			tvMenu.setTextColor(getResources().getColor(R.color.white));
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			tvMenu.setTextColor(getResources().getColor(R.color.menu_title));
		}
		
	}
	
	class DwListener implements DrawerListener{
		
		private int mode;
		private int pos;
		
		public DwListener(int mode, int pos) {
			this.mode = mode;
			this.pos = pos;
		}

		@Override
		public void onDrawerClosed(View arg0) {
			// TODO Auto-generated method stub
			dw_state = 0;  //0表示关闭左侧菜单
			switch (mode) {
			case 0:
				FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
				tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, classes[pos]));
				tx.commit();
//				tvTitle.setText(Appointment.main_title[pos]);
				break;
			case 1:
				
				break;
			}
		}

		@Override
		public void onDrawerOpened(View arg0) {
			// TODO Auto-generated method stub
			dw_state = 1;  //1表示打开左侧菜单
		}

		@Override
		public void onDrawerSlide(View arg0, float arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDrawerStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class HeadClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			drawer.closeDrawer(navList);
		}
		
	}
	
	class LeftClick implements OnClickListener{

		@SuppressLint("NewApi")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (dw_state) {
			case 0:
				drawer.openDrawer(navList);
				break;
			case 1:
				drawer.closeDrawer(navList);
				break;
			}
		}
		
	}
	
	public List<Map<String, Object>> getTitleList() {
//		map = new HashMap<String, Object>();
//		map.put("icon", R.drawable.ic_launcher);
//		map.put("title", "大力神杯");
//		map.put("type", 1);
//		list.add(map);
		for (int i = 0; i < Appointment.main_title.length; i++) {
			map = new HashMap<String, Object>();
			map.put("icon", Appointment.main_icon[i]);
			map.put("title", Appointment.main_title[i]);
//			map.put("type", 2);
			list.add(map);
		}
		return list;
	}

}
