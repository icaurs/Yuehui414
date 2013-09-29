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
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {
	
	private ActionBar actionBar;
//	private MenuItem item1, item2;
	private List<Map<String, Object>> list;
	private Map<String, Object> map;
	private DrawerLayout drawer;
	private ListView navList;
	private ImageView ivUser, ivIcon;
	private TextView tvUser, tvMenu;
	private MainAdapter mainAdapter;
	private String[] classes;
	private int dw_state, item = 0, itemIndex, itemposition;
	private Appointment appointment;
	private View headView;
	private RelativeLayout layUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		
		list = new ArrayList<Map<String,Object>>();
		
		appointment = (Appointment) getApplication();
		appointment.setSex_index(0);
		
		setProgressBarIndeterminateVisibility(false);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("");
		//����actionbar�ĵ���ģʽ  
		actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_LIST);
		//����һ��spinneradaper������actionbar�����˵��Ĳ˵���  
		SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_bar_list, R.layout.myspinner);  
		//Ϊactionbar������������������  
		actionBar.setListNavigationCallbacks(spinnerAdapter,new DropDownListener());
		
//		tvTitle = (TextView) findViewById(R.id.tvTitle);
//		ivLeft = (ImageView) findViewById(R.id.ivLeft);
		
		drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		navList = (ListView) findViewById(R.id.drawer);
		headView = LayoutInflater.from(this).inflate(R.layout.listview_head_main, null);
		ivUser = (ImageView) headView.findViewById(R.id.ivUser);
		tvUser = (TextView) headView.findViewById(R.id.tvUser);
		navList.addHeaderView(headView);
		headView.setOnClickListener(new HeadClick());
		
//		//����ҳ�����
////		tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
//		//������˵�
//		ivLeft.setOnClickListener(new LeftClick());
		
		navList.setSelector(new ColorDrawable(getResources().getColor(R.color.menu_user_bg)));
		//���ز˵��б�
		list = getTitleList();
		mainAdapter = new MainAdapter(MainActivity.this, list);
		navList.setAdapter(mainAdapter);
//		navList.setBackgroundResource(android.R.drawable.dialog_holo_dark_frame);
		
		//����ҳ��ģ��
		classes = getResources().getStringArray(R.array.nav_classes);
		navList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int pos, long id){
				item = pos - 1;
				if (pos > 0) {
					drawer.setDrawerListener(new DwListener(1, item));
					drawer.closeDrawer(navList);
				}
			}
		});
		navList.setOnItemSelectedListener(new MenuItemSelected());
		
//		//���س�ʼҳ��ģ��
//		loadFragment(item);
//		
		//����DrawerLayout�����¼�
		drawer.setDrawerListener(new DwListener(0, item));
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		appointment.item_main_update = menu.findItem(R.id.item_main_update);
//		item2 = menu.findItem(R.id.item2);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			switch (dw_state) {
			case 0:
				drawer.openDrawer(navList);
				break;
			case 1:
				drawer.closeDrawer(navList);
				break;
			}
			return true;
		case R.id.item_main_update:
			updateEvents();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	class DropDownListener implements OnNavigationListener {
		
		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			// TODO Auto-generated method stub
			
//			TestFragement testFragement = new TestFragement();
//			FragmentManager manager = getFragmentManager();
//			FragmentTransaction transaction = manager.beginTransaction();             
//	    	// ��Activity�е������滻�ɶ�Ӧѡ���Fragment              
//	    	transaction.replace(android.R.id.content, testFragement, bar[itemPosition]);
//	    	transaction.commit();
			
			itemposition = itemPosition;
			updateEvents();
			
	        return true;
	    }
	    
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
			dw_state = 0;  //0��ʾ�ر����˵�
			switch (mode) {
			case 0:
				
				break;
			case 1:
				if (pos != itemIndex) {
					switch (pos) {
					case 0:
						//����actionbar�ĵ���ģʽ  
						actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_LIST);
						//����һ��spinneradaper������actionbar�����˵��Ĳ˵���  
						SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.action_bar_list, R.layout.myspinner);  
						//Ϊactionbar������������������  
						actionBar.setListNavigationCallbacks(spinnerAdapter,new DropDownListener());
						break;
					case 1:
						actionBar.setNavigationMode(0);
						loadFragment(pos);
						break;
					case 2:
						actionBar.setNavigationMode(0);
						loadFragment(pos);
						break;
					}
				}
				itemIndex = pos;
				break;
			}
		}

		@Override
		public void onDrawerOpened(View arg0) {
			// TODO Auto-generated method stub
			dw_state = 1;  //1��ʾ�����˵�
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
	
	public void loadFragment(int pos) {
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, classes[pos]));
		tx.commit();
	}
	
	public void updateEvents() {
		appointment.item_main_update.setVisible(false);
		setProgressBarIndeterminateVisibility(true);
		
		switch (item) {
		case 0:
			switch (itemposition) {
			case 0:
				appointment.setSex_index(0);
				break;
			case 1:
				appointment.setSex_index(1);
				break;
			case 2:
				appointment.setSex_index(2);
				break;
			}
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		}
		loadFragment(item);
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
//		map.put("title", "������");
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
