package com.icarus.yuehui414.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icarus.yuehui414.R;
import com.icarus.yuehui414.adapter.EventsAdapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;

public class EventsFragment extends Fragment {
	
	private ListView lvEvents;
	private List<Map<String, Object>> list;
	private Map<String, Object> map;
	private EventsAdapter eventsAdapter;
	private String asy;
	private AsyncTaskHelper asyncTaskHelper;

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
		
		lvEvents = (ListView) getActivity().findViewById(R.id.lvEvents);
		
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
				getEvents();
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
				lvEvents.setSelector(new ColorDrawable(Color.TRANSPARENT));
				eventsAdapter = new EventsAdapter(getActivity(), list);
				lvEvents.setAdapter(eventsAdapter);
			}
			
		}
		
	}
	
	public void getEvents() {
		for (int i = 0; i < 20; i++) {
			map = new HashMap<String, Object>();
			map.put("LU_pic", "");
			map.put("LU_NickName", "昵称");
			map.put("LA_province", "地点");
			map.put("LA_city", "地点");
			map.put("LA_area", "地点");
			list.add(map);
		}
	}

}
