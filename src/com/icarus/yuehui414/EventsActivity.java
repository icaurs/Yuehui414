package com.icarus.yuehui414;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icarus.yuehui414.adapter.EventsAdapter;
import com.icarus.yuehui414.adapter.EventsCommentAdapter;
import com.icarus.yuehui414.application.Appointment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class EventsActivity extends Activity {
	
	private ScrollView svEvents;
	private ListView lvEvents;
	private Appointment appointment;
	private EventsCommentAdapter eventsCommentAdapter;
	private String LA_Id = "0";
	private List<Map<String, Object>> list;
	private Map<String, Object> map;
	private String asy;
	private AsyncTaskHelper asyncTaskHelper;
	//-2表示访问webservice有异常，-1表示返回数据有异常，0数据验证错误，1表示正常，2其他错误
	private int exceptionNo = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_events);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		
		appointment = (Appointment) getApplication();
		list = new ArrayList<Map<String,Object>>();
		
		LA_Id = appointment.getLA_Id();
		
		svEvents = (ScrollView) findViewById(R.id.svEvents);
		lvEvents = (ListView) findViewById(R.id.lvEvents);
		
		svEvents.smoothScrollTo(0, 0);
		
		asy = "0";
		asyncTaskHelper = new AsyncTaskHelper();
		asyncTaskHelper.execute(asy);
	}
	
	class AsyncTaskHelper extends AsyncTask<String, String, String>{
		
		public String asy = "";

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			asy = params[0];
			if (asy.equals("0")) {
				getEventComments();
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (asy.equals("0")) {
				try {
					asy0();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(EventsActivity.this, "亲，出错啦", Toast.LENGTH_SHORT).show();
				}
			}
		}
		
	}
	
	public void asy0() throws Exception {
		switch (exceptionNo) {
		case -2:
			Toast.makeText(EventsActivity.this, "亲，没有访问到数据", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			lvEvents.setSelector(new ColorDrawable(Color.TRANSPARENT));
			eventsCommentAdapter = new EventsCommentAdapter(EventsActivity.this, list);
			lvEvents.setAdapter(eventsCommentAdapter);
			break;
		}
	}
	
	public void getEventComments() {
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("LU_pic", R.drawable.ic_launcher);
			map.put("LU_NickName", "大力神杯");
			map.put("Time", "2013-9-23");
			map.put("Comment", "去哪玩啊");
			list.add(map);
		}
	}

}
