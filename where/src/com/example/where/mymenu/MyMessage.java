/**
 *名称：
 *
 *作者：
 * 
 *下午2:34:05
 */
package com.example.where.mymenu;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.HttpService_;

/**
 * @author Administrator
 *
 *
 *下午2:34:05
 */
public class MyMessage extends Activity{
	
	private TextView text_userid;
//	private ImageView refresh;
	private Button accept;
	private Button rejection;
	private Button refresh;
	private String uid="9";
	private String request_id;
	private String status = "0";
	
	public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(MyMessage.this, MyMenu.class);
		startActivity(intent);  
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_message);
		// 根据Id获得文本框
		text_userid = (TextView) this.findViewById(R.id.text_userid);
//		refresh = (ImageView) this.findViewById(R.id.refresh2);
		
		accept = (Button) this.findViewById(R.id.accept);
		rejection = (Button) this.findViewById(R.id.rejection);
		refresh = (Button) this.findViewById(R.id.refresh111);
		
		accept.setOnClickListener(new OnClickListener(){  
            public void onClick(View v){  
            	Toast.makeText(MyMessage.this,"你点击的是接受",
    					Toast.LENGTH_SHORT).show();
            	status = "1";
            	execute2();
            }  
        });  
		rejection.setOnClickListener(new OnClickListener(){  
            public void onClick(View v){  
            	Toast.makeText(MyMessage.this,"你点击的是拒绝",
    					Toast.LENGTH_SHORT).show();
            	status = "2";
            	execute2();
            }  
        });  
		refresh.setOnClickListener(new OnClickListener(){  
            public void onClick(View v){  
            	execute();
            }  
        });  
	}
	
	
//	public void getRefreshClick(View v){
//		execute();
//	}
	
	private void execute() {
//		 TODO Auto-generated method stub
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
		String httpUrl = GlobalFinal.path.concat("request_control_receiveRequest.action?");
	
		Map<String, String> map = new HashMap<String, String>();
		//获取edittext 的文本内容  
		map.put("bemonitorUser", uid);
		map.put("status", "0");
		HttpService_ http = new HttpService_();
		//连接地址   写入值    
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
				JSONObject json;
				try {
					json = new JSONObject(msg.obj.toString().replace("<br>",
							"\n"));
					int flag = Integer.valueOf(json.getString("flag"));
//					int userid = Integer.valueOf(json.getString("monitorUser"));
					String userid = json.getString("monitorUser");
					request_id = json.getString("request_id");
					if (flag == 1) {
						Toast.makeText(MyMessage.this,"有新的请求userid"+userid,
								Toast.LENGTH_SHORT).show();
						text_userid.setText("用户"+userid+"申请添加你为好友");
//						execute2();
					} else {
						Toast.makeText(MyMessage.this, "无新的请求",
								Toast.LENGTH_SHORT).show();
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(MyMessage.this, "网络繁忙", Toast.LENGTH_SHORT)
						.show();
			}
		}
	};
	private void execute2() {
//		 TODO Auto-generated method stub
		try {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 请求网络数据加载实现代码
					String res = initData2();
					
					Message msg_netData = new Message();
					msg_netData.obj = res;
					msg_netData.what = 1;
					operate2.sendMessage(msg_netData);
				}
			}).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected String initData2() {
		String httpUrl = GlobalFinal.path.concat("request_control_upStatus.action?");
	
		Map<String, String> map = new HashMap<String, String>();
		//获取edittext 的文本内容  
		map.put("request_id", request_id);
		map.put("status", status);
		HttpService_ http = new HttpService_();
		//连接地址   写入值    
		String res = http.loginPostTimeData(httpUrl, map, 10000);
		
		if (res.equals("error")) {
			res = "";
		} else {
			res = res.replace("\\", "");
		}
		return res;
	}
	private Handler operate2 = new Handler() {
	
		@Override
		public void handleMessage(Message msg) {
	
			if (msg.obj.toString() != null && (!msg.obj.toString().equals(""))) {
				JSONObject json;
				try {
					json = new JSONObject(msg.obj.toString().replace("<br>",
							"\n"));
					int flag = Integer.valueOf(json.getString("flag"));
					if (flag == 1) {
						Toast.makeText(MyMessage.this,"修改status成功",
								Toast.LENGTH_SHORT).show();
						
					} else {
						Toast.makeText(MyMessage.this, "修改status失败",
								Toast.LENGTH_SHORT).show();
					}
	
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(MyMessage.this, "网络繁忙", Toast.LENGTH_SHORT)
						.show();
			}
		}
	};
}
