package com.example.where.addperson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.where.R;
import com.example.where.widget.AutoListView;
import com.example.where.widget.AutoListView.OnLoadListener;
import com.example.where.widget.AutoListView.OnRefreshListener;

public class AddHistoryPerson extends Activity {
	// private List<Map<String, String>> list1 = new ArrayList<Map<String,
	// String>>();
	// private AutoListView friendlist; // 定义ListView组件
	// private int pageSize = 10;
	// private int pageNo = 1;
	// private int pageTotal = 0;// 总数初始化0
	// private SimpleAdapter simpleAdapter = null;
	private ImageView htui;
	private String data[][] = new String[][] { { "01", "8:20:30~9:30:20" },
			{ "02", "9:31:12~9:33:21" }, { "03", "10:30:20~10:36:22" },
			{ "04", "10:40:11~10:45:11" }, { "05", "11:00:00~11:15:11" },
			{ "06", "12:00:00~12:22:22" }, { "07", "14:14:14~14:30:30" },
			{ "08", "14:40:44~15:00:00" }, { "09", "17:00:00~17:50:00" },
			{ "10", "18:30:30~19:00:00" } }; // 定义显示的数据
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private AutoListView datalis; // 定义ListView组件
	private SimpleAdapter simpleAdapter = null;

	// private ListView et_mobile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_historyperson);
		htui = (ImageView) this.findViewById(R.id.htui);
		datalis = (AutoListView) this.findViewById(R.id.datalis);

		
		
		try {
			
			SharedPreferences sp = getSharedPreferences("history_strs", 0);
			String save_history = sp.getString("history", "");
			String[] hisArrays = save_history.split(",");
			
			
			for (int i = 0; i < hisArrays.length; i++)
			{
				//
				
			
				Map<String, String> map = new HashMap<String, String>();
				map.put("numberphone", hisArrays[i]); // 设置_id组件显示数据
				//map.put("haoma","号码:"); // 设置_id组件显示数据
				this.list.add(map); // 增加数据
//				if(simpleAdapter==null){
//					this.simpleAdapter = new SimpleAdapter(this, // 实例化SimpleAdapter0
//							this.list, R.layout.add_historypersondata, // 要使用的显示模板
//							new String[] { "haoma","numberphone" }, // 定义要显示的Map的Key
//							new int[] { R.id.haoma, R.id.numberphone });// 与模板中的组件匹配
//					datalis.setAdapter(this.simpleAdapter); // 设置显示数据
//				}else{
//					simpleAdapter.notifyDataSetChanged();
//				}
			}
			if(simpleAdapter==null){
				this.simpleAdapter = new SimpleAdapter(this, // 实例化SimpleAdapter0
						this.list, R.layout.add_historypersondata, // 要使用的显示模板
						new String[] { "numberphone" }, // 定义要显示的Map的Key
						new int[] { R.id.numberphone});// 与模板中的组件匹配
				datalis.setAdapter(this.simpleAdapter); // 设置显示数据
			}else{
				simpleAdapter.notifyDataSetChanged();
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
	}
//		for (int x = 0; x < this.data.length; x++) { // 循环设置数据
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("numberphone", data[x][0]); // 设置_id组件显示数据
//			this.list.add(map); // 增加数据
//		}
		
//		this.simpleAdapter = new SimpleAdapter(this, // 实例化SimpleAdapter
//				this.list, R.layout.add_historypersondata, // 要使用的显示模板
//				new String[] { "haoma","numberphone" }, // 定义要显示的Map的Key
//				new int[] { R.id.haoma, R.id.numberphone });// 与模板中的组件匹配
//		this.datalis.setAdapter(this.simpleAdapter); // 设置显示数据

		// et_mobile = (TextView) this.findViewById(R.id.hma);

		// et_mobile.setText(save_history);
		// String lhc=save_history;
		// Toast.makeText(AddHistoryPerson.this, lhc,
		// Toast.LENGTH_SHORT).show();
		// ListView et_mobile = (ListView) findViewById(R.id.listView);
		// SimpleAdapter adapter = new SimpleAdapter(this, getData(),
		// R.layout.add_historyperson,
		// new String[]{"save_history"}, new int[]{R.id.haoma});
		// et_mobile.setAdapter(adapter);
//	}

	// this.htui.setOnClickListener(new OnClickListener() {
	// @Override
	// // public void onClick(View v) {
	// // Intent intent = new Intent();
	// // intent.setClass(AddHistoryPerson.this, AddPerson.class);
	// // startActivity(intent);
	// // }
	// });

//	 datalis = (AutoListView) super.findViewById(R.id.datalis);// 取得组件
	
//          datalis.setAdapter(adapter);
	
//	 datalis.setOnRefreshListener(new OnRefreshListener() {
//	 @Override
//	 public void onRefresh() {
//	
//	  new Handler().postDelayed(new Runnable() {
//	 public void run() {
//	 Toast.makeText(AddHistoryPerson.this, "刷新了",
//	 Toast.LENGTH_SHORT).show();
//	 list.clear();
//	 // execute();
//	 refreshData();
//	 datalis.onRefreshComplete();
//	 int pageTotal = 0;
//	 datalis.setResultSize(pageTotal);
//	 simpleAdapter.notifyDataSetChanged();
//	
//	 }
//	
//	 }, 0);
//	
//	 }
//	 });
//	 datalis.setOnLoadListener(new OnLoadListener() {
//	 @Override
//	 public void onLoad() {
//	 new Handler().postDelayed(new Runnable() {
//	 public void run() {
//	   Toast.makeText(AddHistoryPerson.this, "加载了",
//	   Toast.LENGTH_SHORT).show();
//	 // execute();
//	 refreshData();
//	 datalis.onLoadComplete();
//	 datalis.setResultSize(list.size());
//	 simpleAdapter.notifyDataSetChanged();
//	 }
//	 }, 1000);
//	 }
//	 });
//	// // execute();
//	//
//	 }

//	 private void refreshData() {
//	 SharedPreferences sp = getSharedPreferences("history_strs", 0);
//	 String save_Str = sp.getString("history", "");
//	 String[] hisArrays = save_Str.split(",");
//	 Map<String, String> map;
//	
//	 map = new HashMap<String, String>();
//	 map.put("numberphone", hisArrays[0]);
//	 list.add(map);
//	 }
	 
	 
	 
	 

	// protected void initListView(String res) {
	// for (int i = 0; i < arry.length(); i++) {
	//
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("txiang", sp.getString("headImg")); // 设置_id组件显示数据
	// map.put("haoma", sp.getString("phoneNo"));
	// this.list1.add(map);
	//
	//
	// if(simpleAdapter==null){
	// this.simpleAdapter = new SimpleAdapter(this, // 实例化SimpleAdapter0
	// list1, R.layout.add_historypersondata, // 要使用的显示模板
	// new String[] { "headImg", "phoneNo" }, // 定义要显示的Map的Key
	// new int[] { R.id.txiang, R.id.haoma, });// 与模板中的组件匹配
	// friendlist.setAdapter(this.simpleAdapter); // 设置显示数据
	// }else{
	// simpleAdapter.notifyDataSetChanged();
	// }
	// }
	// protected void initListView(String res) {
	// SharedPreferences sp = getSharedPreferences("history_strs", 0);
	// String save_Str = sp.getString("history", "");
	// String[] res1 = save_Str.split(",");
	// Map<String, String> map;
	//
	// map = new HashMap<String, String>();
	// map.put("headImg", res1[0]);
	// map.put("phoneNo", res1[1]);
	// list1.add(map);
	//
	// if(simpleAdapter==null){
	// this.simpleAdapter = new SimpleAdapter(this, // 实例化SimpleAdapter0
	// list1, R.layout.add_historypersondata, // 要使用的显示模板
	// new String[] { "headImg", "phoneNo" }, // 定义要显示的Map的Key
	// new int[] { R.id.txiang, R.id.haoma, });// 与模板中的组件匹配
	// friendlist.setAdapter(this.simpleAdapter); // 设置显示数据
	// }else{
	// simpleAdapter.notifyDataSetChanged();
	// }
	// }
	// private ArrayList<HashMap<String, Object>> getData(){
	//
	//
	//
	// ArrayList<HashMap<String, Object>> arrayList = new
	// ArrayList<HashMap<String,Object>>();
	// for(int i=0;i<10;i++){
	// HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
	// tempHashMap.put("haoma", tempHashMap );
	// arrayList.add(tempHashMap);
	//
	// }
	//
	//
	// return arrayList;
	//
	// }
	public void getBackClick(View v) {
		Intent intent = new Intent();
		intent.setClass(AddHistoryPerson.this, AddPerson.class);
		startActivity(intent);
	}

}
