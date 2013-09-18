package com.icarus.yuehui414.adapter;

import java.util.List;
import java.util.Map;

import com.icarus.yuehui414.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventsAdapter extends BaseAdapter {
	
	public Context context;
	public List<Map<String, Object>> list;
	public LayoutInflater mInflater;
	
	public EventsAdapter(Context context, List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listview_item_event, null);
			holder = new ViewHolder(convertView);
			holder.ivLU_pic = holder.getLU_pic();
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		
		return convertView;
	}
	
	public class ViewHolder{
		private View convertView;
		private ImageView ivLU_pic;
		
		public ViewHolder(View convertView){
			this.convertView = convertView;
		}
		
		public ImageView getLU_pic() {
			if (ivLU_pic == null) {
				ivLU_pic = (ImageView) convertView.findViewById(R.id.ivLU_pic);
			}
			return ivLU_pic;
		}
		
	}

}
