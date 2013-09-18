package com.icarus.yuehui414.webservice;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icarus.yuehui414.application.Appointment;
import com.icarus.yuehui414.entity.EventsList;

public class GetWebService {
	
	private Gson gson;
	private Type type;
	private List<EventsList> list_events;
	private EventsList eventsList;
	
	public RestTemplate getTemplate() {
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().add(new StringHttpMessageConverter());
		return template;
	}
	
//	public SellerDetail getSellerDetail(int id) throws Exception {
//		String url = Constants.url_data + "seller/detail/" + id;
//		System.out.println(url);
//		String s = Tool.geTemplate().getForObject(url, String.class);
////		System.out.println(s);
//		gson = new Gson();
//		sellerDetail = new SellerDetail();
//		type = new TypeToken<SellerDetail>(){}.getType();
//		sellerDetail = gson.fromJson(s, type);
//		return sellerDetail;
//	}
	
	public List<EventsList> getEventsList(int index, int sex) throws Exception {
		String url = Appointment.url + "webservice/actions/default.ashx?code=jintao65535_123&pageindex=" + index + "&sex=" + sex;
		String s = getTemplate().getForObject(url, String.class);
		gson = new Gson();
		list_events = new ArrayList<EventsList>();
		type = new TypeToken<List<EventsList>>(){}.getType();
		list_events = gson.fromJson(s, type);
		return list_events;
	}

}
