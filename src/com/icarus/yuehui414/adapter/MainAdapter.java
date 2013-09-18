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

public class MainAdapter extends BaseAdapter {
	
	public Context context;
	public List<Map<String, Object>> list;
	public LayoutInflater mInflater;
	
	public MainAdapter(Context context, List<Map<String, Object>> list) {
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
			convertView = mInflater.inflate(R.layout.listview_item_main, null);
			holder = new ViewHolder(convertView);
			holder.layMenu = holder.getLayMenu();
			holder.ivIcon = holder.getIcon();
			holder.tvMenu = holder.getMenu();
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.ivIcon.setImageResource((Integer) list.get(position).get("icon"));
		holder.tvMenu.setText((String) list.get(position).get("title"));
		
		return convertView;
	}
	
	public class ViewHolder{
		private View convertView;
		private LinearLayout layMenu;
		private ImageView ivIcon;
		private TextView tvMenu;
		
		public ViewHolder(View convertView){
			this.convertView = convertView;
		}
		
		public LinearLayout getLayMenu() {
			if (layMenu == null) {
				layMenu = (LinearLayout) convertView.findViewById(R.id.layMenu);
			}
			return layMenu;
		}
		
		public ImageView getIcon() {
			if (ivIcon == null) {
				ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
			}
			return ivIcon;
		}
		
		public TextView getMenu() {
			if (tvMenu == null) {
				tvMenu = (TextView) convertView.findViewById(R.id.tvMenu);
			}
			return tvMenu;
		}
		
	}

}
