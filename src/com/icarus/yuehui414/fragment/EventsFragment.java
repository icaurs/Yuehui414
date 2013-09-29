package com.icarus.yuehui414.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.icarus.yuehui414.EventsActivity;
import com.icarus.yuehui414.R;
import com.icarus.yuehui414.adapter.EventsAdapter;
import com.icarus.yuehui414.application.Appointment;
import com.icarus.yuehui414.entity.EventsList;
import com.icarus.yuehui414.webservice.GetWebService;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EventsFragment extends Fragment {
	
//	private FrameLayout layWait;
//	private ImageView ivWait;
//	private ProgressBar pbWait;
	private ListView lvEvents;
	private View footView;
	private TextView loading_msg;
//	private Spinner spnSex;
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
	private List<String> list_sex;
	/*判断listview中当前第几个item*/
	private int lastItem = 0, spnCount = 0, index = 1, spn_index = 0;
	/*判断是否可更新*/
	private boolean isRefreshFoot = false;
	/*判断是否正在更新*/
	private boolean isRefreshFootIng = false;
	/* 是否是最后一个Item*/
	private boolean isLastItem = false;
	private int v = 0;
	//判断是否更新服务器数据
	private boolean isUpdate = true;
	private Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_events, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		list = new ArrayList<Map<String,Object>>();
		getWebService = new GetWebService();
		list_events = new ArrayList<EventsList>();
		list_sex = new ArrayList<String>();
		
		appointment = (Appointment) getActivity().getApplication();
		
		if (savedInstanceState != null) {
			
		}
		
//		spnSex = (Spinner) getActivity().findViewById(R.id.spnSex);
//		layWait = (FrameLayout) getActivity().findViewById(R.id.layWait);
//		ivWait = (ImageView) getActivity().findViewById(R.id.ivWait);
//		pbWait = (ProgressBar) getActivity().findViewById(R.id.pbWait);
		lvEvents = (ListView) getActivity().findViewById(R.id.lvEvents);
		footView = LayoutInflater.from(getActivity()).inflate(R.layout.list_foot, null);
		loading_msg = (TextView) footView.findViewById(R.id.loading_msg);
		lvEvents.addFooterView(footView);
		
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
//			ivWait.setVisibility(View.GONE);
//			pbWait.setVisibility(View.VISIBLE);
			footView.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			asy = params[0];
			if (asy.equals("0")) {
//				getSexList();
				try {
					getEvents();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exceptionNo = -2;
				}
			}
			if (asy.equals("1")) {
				try {
					getEvents();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exceptionNo = -2;
				}
			}
			if (asy.equals("2")) {
				try {
					getEvents();
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
				try {
					asy0();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getActivity(), "亲，出错啦", Toast.LENGTH_SHORT).show();
					Log.e("EventsFragment", "listview error: " + e);
				}
			}
			if (asy.equals("1")) {
				try {
					asy1();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getActivity(), "亲，出错啦", Toast.LENGTH_SHORT).show();
					Log.e("EventsFragment", "listview error: " + e);
				}
			}
			if (asy.equals("2")) {
				try {
					asy2();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getActivity(), "亲，出错啦", Toast.LENGTH_SHORT).show();
					Log.e("EventsFragment", "listview error: " + e);
				}
			}
			appointment.item_main_update.setVisible(true);
			getActivity().setProgressBarIndeterminateVisibility(false);
//			pbWait.setVisibility(View.GONE);
			footView.setVisibility(View.GONE);
//			ivWait.setVisibility(View.VISIBLE);
		}
		
	}
	
	public void asy0() throws Exception {
		switch (exceptionNo) {
		case -2:
			Toast.makeText(getActivity(), "亲，没有访问到数据", Toast.LENGTH_SHORT).show();
			break;
		case 1:
//			spnSex.setVisibility(View.VISIBLE);
//			ArrayAdapter<String> disAdapter = new ArrayAdapter<String>(getActivity(), R.layout.myspinner, list_sex);
//			disAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//			spnSex.setAdapter(disAdapter);
//			spnSex.setSelection(spn_index);
//			spnSex.setOnItemSelectedListener(new SexItemSelect());
			
			lvEvents.setSelector(new ColorDrawable(Color.TRANSPARENT));
			eventsAdapter = new EventsAdapter(getActivity(), list);
			lvEvents.setAdapter(eventsAdapter);
			lvEvents.setOnScrollListener(new EventsScroll());
			lvEvents.setOnItemClickListener(new EventsItemClick());
			
//			layWait.setOnClickListener(new UpdateEvents());
			break;
		case 2:
			Toast.makeText(getActivity(), "亲，内容已经到底", Toast.LENGTH_SHORT).show();
			break;
		}
	}
	
	public void asy1() throws Exception {
		switch (exceptionNo) {
		case -2:
			Toast.makeText(getActivity(), "亲，没有访问到数据", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			eventsAdapter.notifyDataSetChanged();
			break;
		case 2:
			Toast.makeText(getActivity(), "亲，内容已经到底", Toast.LENGTH_SHORT).show();
			break;
		}
		isRefreshFootIng = false;
	}

	public void asy2() throws Exception {
		switch (exceptionNo) {
		case -2:
			Toast.makeText(getActivity(), "亲，没有访问到数据", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			lvEvents.setSelector(new ColorDrawable(Color.TRANSPARENT));
			eventsAdapter = new EventsAdapter(getActivity(), list);
			lvEvents.setAdapter(eventsAdapter);
			lvEvents.setOnScrollListener(new EventsScroll());
			break;
		case 2:
			Toast.makeText(getActivity(), "亲，内容已经到底", Toast.LENGTH_SHORT).show();
			break;
		}
		isRefreshFootIng = false;
	}
	
	class SexItemSelect implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			if (spnCount == 1) {
				try {
					if (isRefreshFootIng == false) {
						switch (arg2) {
						case 0:
							appointment.setSex_index(0);
							break;
						case 1:
							appointment.setSex_index(1);
							break;
						case 2:
							appointment.setSex_index(3);
							break;
						}
						spn_index = arg2;
						index = 1;
						isRefreshFootIng = false;
						isUpdate = true;
						list.removeAll(list);
//						eventsAdapter.notifyDataSetChanged();
						asy = "2";
						asyncTaskHelper = new AsyncTaskHelper();
						asyncTaskHelper.execute(asy);
					}
					isRefreshFootIng = true;
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getActivity(), "亲，没有访问到数据", Toast.LENGTH_SHORT).show();
				}
			}
			spnCount = 1;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class EventsScroll implements OnScrollListener{

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			try {
				lastItem = firstVisibleItem + visibleItemCount;
				//判断 最后一条开始显示，那么加载新数据 
				if (lastItem == totalItemCount) {
					isRefreshFoot = true;
				}else {
					isRefreshFoot = false;
				}
				v = visibleItemCount;
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getActivity(), "亲，出错啦", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			try {
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && isRefreshFoot) {
					if (isRefreshFootIng == false) {
						asy = "1";
						asyncTaskHelper = new AsyncTaskHelper();
						asyncTaskHelper.execute(asy);
					}else {
//						Toast.makeText(HotelActivity.this, "内容正在加载", Toast.LENGTH_SHORT).show();
					}
					isRefreshFootIng = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getActivity(), "亲，出错啦", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	class UpdateEvents implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (isRefreshFootIng == false) {
				index = 1;
				isRefreshFootIng = false;
				isUpdate = true;
				list.removeAll(list);
//				eventsAdapter.notifyDataSetChanged();
				asy = "2";
				asyncTaskHelper = new AsyncTaskHelper();
				asyncTaskHelper.execute(asy);
			}
			isRefreshFootIng = true;
		}
		
	}
	
	class EventsItemClick implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			intent = new Intent(getActivity(), EventsActivity.class);
			appointment.setLA_Id((String) list.get(arg2).get("LA_Id"));
			startActivity(intent);
		}
		
	}
	
	public void getEvents() throws Exception {
		if (isUpdate == true) {
			getEventsList();
		}else {
			exceptionNo = 2;
		}
	}
	
	public void getEventsList() throws Exception {
		list_events = getWebService.getEventsList(index, appointment.getSex_index());
		if (list_events.size() < 10) {
			isUpdate = false;
		}else {
			isUpdate = true;
		}
		for (int i = 0; i < list_events.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("LA_Id", list_events.get(i).getLA_Id());
			map.put("LU_pic", list_events.get(i).getLU_pic());
			map.put("LA_name", list_events.get(i).getLA_name());
			map.put("LA_province", list_events.get(i).getLA_province());
			map.put("LA_city", list_events.get(i).getLA_city());
			map.put("LA_area", list_events.get(i).getLA_area());
			map.put("La_explain", list_events.get(i).getLa_explain());
			list.add(map);
		}
		index++;
		exceptionNo = 1;
	}
	
	public void getSexList() {
		for (int i = 0; i < appointment.sex.length; i++) {
			list_sex.add(appointment.sex[i]);
		}
	}

}
