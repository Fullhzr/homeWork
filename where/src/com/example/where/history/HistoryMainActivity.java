package com.example.where.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.PointList;
import com.example.where.HistoryNote;
import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.widget.AutoListView;
import com.example.where.widget.AutoListView.OnLoadListener;
import com.example.where.widget.AutoListView.OnRefreshListener;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.HttpService_;

public class HistoryMainActivity extends Activity {

	private String data[][] = new String[][] { { "01", "8:20:30~9:30:20" },
			{ "02", "9:31:12~9:33:21" }, { "03", "10:30:20~10:36:22" },
			{ "04", "10:40:11~10:45:11" }, { "05", "11:00:00~11:15:11" },
			{ "06", "12:00:00~12:22:22" }, { "07", "14:14:14~14:30:30" },
			{ "08", "14:40:44~15:00:00" }, { "09", "17:00:00~17:50:00" },
			{ "10", "18:30:30~19:00:00" } }; // 定义显示的数据
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	
	private AutoListView datalist; // 定义ListView组件
	
	private SimpleAdapter simpleAdapter = null;
	
	private int pageSize = 10;
	private int pageNo = 1;
	private int pageTotal = 0;// 总数初始化0
	private ImageView houtui = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_main);
		 this.houtui= (ImageView) this.findViewById(R.id.houtui);
			this.houtui.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HistoryMainActivity.this, MainMapActivity.class);
					startActivity(intent);
				}
			});

		

		datalist = (AutoListView) super.findViewById(R.id.datalist);// 取得组件
		datalist.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				
				new Handler().postDelayed(new Runnable() {
					public void run() {
						Toast.makeText(HistoryMainActivity.this, "刷新了",
								Toast.LENGTH_SHORT).show();
						list.clear();
						execute();
						datalist.onRefreshComplete();
						datalist.setResultSize(pageTotal);
						simpleAdapter.notifyDataSetChanged();
					}
				}, 1000);
				
				
			}
		});
		datalist.setOnLoadListener(new OnLoadListener() {
			@Override
			public void onLoad() {
				new Handler().postDelayed(new Runnable() {
					public void run() {
						Toast.makeText(HistoryMainActivity.this, "加载了",
								Toast.LENGTH_SHORT).show();
						execute();
						datalist.onLoadComplete();
						datalist.setResultSize(list.size());
						simpleAdapter.notifyDataSetChanged();
					}
				}, 1000);
			}
		});
		datalist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) parent
						.getItemAtPosition(position);
				String title = map.get("title");

				Intent intent = new Intent(); 
				intent.putExtra("title", title);
				intent.setClass(HistoryMainActivity.this,HistoryMap.class);
				startActivity(intent);
				finish();

			}
		});
		execute();

	}


	private void execute() {
		try {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 请求网络数据加载实现代码
					String res = initData1();
					
					Message msg_netData = new Message();
					msg_netData.obj = res;
					msg_netData.what = 1;
					operate.sendMessage(msg_netData);
				}
			}).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String initData1() {
		
		String httpUrl = GlobalFinal.path.concat("historyview_find.action?");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("page.pageNo", pageNo + "");
		map.put("page.pageSize", pageSize + "");
		HttpService_ http = new HttpService_();
		String res = http.loginPostTimeData(httpUrl, map, 10000);
		if (res.equals("error")) {
			res = "";
		} else {
			res = res.replace("\\", "");
		}
		return res;
	}

	private Handler operate = new Handler() {
		
		
		@Override
		public void handleMessage(Message msg) {
			
			if (msg.obj.toString() != null && (!msg.obj.toString().equals(""))) {
				initListView(msg.obj.toString());
			} else {
				Toast.makeText(HistoryMainActivity.this, "网络繁忙",
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	protected void initListView(String res) {
		try {
			JSONObject json = new JSONObject(res.replace("<br>", "\n"));
			String list = json.getString("list");
			pageTotal = Integer.valueOf(json.getString("pagetotal"));
			pageNo = Integer.valueOf(json.getString("pageNo")) + 1;
			JSONObject obj = new JSONObject(list);
			JSONArray arry = obj.getJSONArray("list");
			for (int i = 0; i < arry.length(); i++) {
				JSONObject obj1 = arry.getJSONObject(i);
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", obj1.getString("userid")); // 设置_id组件显示数据
				map.put("info", obj1.getString("username")); // 设置name组件显示数据
 				map.put("stime", obj1.getString("starttime"));
 				map.put("etime", obj1.getString("endtime"));
 				HistoryNote ll = new HistoryNote();
 				ll.setEndtime(obj1.getString("endtime"));
 				ll.setLongtitude(obj1.getString("longitude"));
 				ll.setStarttime(obj1.getString("starttime"));
 				ll.setLatitude(obj1.getString("latitude"));
 				ll.setTitle(obj1.getString("userid"));
 				PointList.list.add(ll);
// 				map.put("usimg", obj1.getString("user_picture"));
// 				map.put("lotude", obj1.getString("longitude"));
// 				map.put("latude", obj1.getSltring("latitude"));
				this.list.add(map); // 增加数据
			}
			if(simpleAdapter==null){
				this.simpleAdapter = new SimpleAdapter(this, // 实例化SimpleAdapter0
						this.list, R.layout.historydata_list, // 要使用的显示模板
						new String[] { "title", "info", "stime", "etime" }, // 定义要显示的Map的Key
						new int[] { R.id.title, R.id.info, R.id.stime, R.id.etime});// 与模板中的组件匹配
				datalist.setAdapter(this.simpleAdapter); // 设置显示数据
			}else{
				simpleAdapter.notifyDataSetChanged();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	 

}
