package com.icarus.yuehui414.adapter;

import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalBitmap;

import com.icarus.yuehui414.R;
import com.icarus.yuehui414.application.Appointment;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventsCommentAdapter extends BaseAdapter {
	
	private Context context;
	private List<Map<String, Object>> list;
	private LayoutInflater mInflater;
	private FinalBitmap finalBitmap;
	
	public EventsCommentAdapter(Context context, List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		this.mInflater = LayoutInflater.from(context);
		this.finalBitmap = FinalBitmap.create(context);
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
			holder.tvLU_Detail = holder.getLU_Detail();
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
//		String image = (String) list.get(position).get("LU_pic");
//		if (!image.equals("") || image != null) {
//			finalBitmap.display(holder.ivLU_pic, Appointment.url + image);
//		}
//		String la_explain = (String) list.get(position).get("La_explain");
//		if (la_explain.equals("")) {
//			holder.tvLU_Detail.setVisibility(View.GONE);
//		}else {
//			holder.tvLU_Detail.setVisibility(View.VISIBLE);
//			holder.tvLU_Detail.setText(la_explain);
//		}
		
		return convertView;
	}
	
	public class ViewHolder{
		private View convertView;
		private ImageView ivLU_pic;
		private TextView tvLU_Detail;
		
		public ViewHolder(View convertView){
			this.convertView = convertView;
		}
		
		public ImageView getLU_pic() {
			if (ivLU_pic == null) {
				ivLU_pic = (ImageView) convertView.findViewById(R.id.ivLU_pic);
			}
			return ivLU_pic;
		}
		
		public TextView getLU_Detail() {
			if (tvLU_Detail == null) {
				tvLU_Detail = (TextView) convertView.findViewById(R.id.tvLU_Detail);
			}
			return tvLU_Detail;
		}
		
	}

}
