package com.icarus.yuehui414.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icarus.yuehui414.R;
import com.icarus.yuehui414.adapter.EventsAdapter;
import com.icarus.yuehui414.application.Appointment;
import com.icarus.yuehui414.entity.EventsList;
import com.icarus.yuehui414.webservice.GetWebService;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class EventsFragment extends Fragment {
	
	private ProgressBar pbWait;
	private ListView lvEvents;
	private Appointment appointment;
	private List<Map<String, Object>> list;
	private Map<String, Object> map;
	private GetWebService getWebService;
	private List<EventsList> list_events;
	private EventsAdapter eventsAdapter;
	private String asy;
	private AsyncTaskHelper asyncTaskHelper;
	//-2表示访问webservice有异常，-1表示返回数据有异常，0数据验证错误，1表示正常，2其他错误
	private int exceptionNo = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_1, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		list = new ArrayList<Map<String,Object>>();
		getWebService = new GetWebService();
		list_events = new ArrayList<EventsList>();
		
		appointment = (Appointment) getActivity().getApplication();
		
		if (savedInstanceState != null) {
			
		}
		
		pbWait = (ProgressBar) getActivity().findViewById(R.id.pbWait);
		lvEvents = (ListView) getActivity().findViewById(R.id.lvEvents);
		
		asy = "0";
		asyncTaskHelper = new AsyncTaskHelper();
		asyncTaskHelper.execute(asy);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
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
			pbWait.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			asy = params[0];
			if (asy.equals("0")) {
				try {
					getEvents();
					exceptionNo = 1;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exceptionNo = -2;
				}
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
				switch (exceptionNo) {
				case -2:
					Toast.makeText(getActivity(), "亲，没有访问到数据", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					lvEvents.setSelector(new ColorDrawable(Color.TRANSPARENT));
					eventsAdapter = new EventsAdapter(getActivity(), list);
					lvEvents.setAdapter(eventsAdapter);
					break;
				}
			}
			pbWait.setVisibility(View.GONE);
		}
		
	}
	
	public void getEvents() throws Exception {
		list_events = getWebService.getEventsList();
		for (int i = 0; i < list_events.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("LU_pic", list_events.get(i).getLU_pic());
			map.put("LA_name", list_events.get(i).getLA_name());
			map.put("LA_province", list_events.get(i).getLA_province());
			map.put("LA_city", list_events.get(i).getLA_city());
			map.put("LA_area", list_events.get(i).getLA_area());
			map.put("La_explain", list_events.get(i).getLa_explain());
			list.add(map);
		}
	}

}
