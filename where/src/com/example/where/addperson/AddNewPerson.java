package com.example.where.addperson;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Userid;
import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.HttpService_;

/**
 * @author Administrator
 *
 *
 *下午8:17:00
 */
/**
 * @author Administrator
 *
 *
 *下午8:17:02
 */
/**
 * @author Administrator
 *
 *
 *下午8:17:03
 */
/**
 * @author Administrator
 *
 *
 *下午8:17:04
 */
public class AddNewPerson extends Activity {
	// 声明文本框
	private EditText et_mobile;
	// 声明姓名，电话
	private String usernumber;
	private Button btn_ok; 
//	private String  userid="2";
//	Intent intent=getIntent();
//	String userid="2";
	private ArrayAdapter<String> adapter_history;  
//	SharedPreferences sharedPreferences = getSharedPreferences("test", 
//			Activity.MODE_PRIVATE); 
//			String userid = sharedPreferences.getString("userid", ""); 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_newperson);
		// 根据Id获得文本框
		et_mobile = (EditText) this.findViewById(R.id.add_newperson);
		btn_ok =  (Button) this.findViewById(R.id.addnew_ok_btn);
        SharedPreferences sp = getSharedPreferences("history_strs", 0); 
        String save_history = sp.getString("history", ""); 
        String[] hisArrays = save_history.split(","); 
        adapter_history = new ArrayAdapter<String>(this, 
                android.R.layout.simple_dropdown_item_1line, hisArrays); 
        if (hisArrays.length > 5) { 
            String[] newArrays = new String[50]; 
            System.arraycopy(hisArrays, 0, newArrays, 0, 50); 
            adapter_history = new ArrayAdapter<String>(this, 
                    android.R.layout.simple_dropdown_item_1line, newArrays); 
        } 

        btn_ok.setOnClickListener(new Button.OnClickListener() { 


        	
            public void onClick(View v) { 
                // TODO Auto-generated method stub 
           	 
    			String Addid1 = "1" ;
    			 Userid userid2=new Userid(); 
    			 userid2.setAddid(Addid1);
 
            	Save(); 
            	Toast.makeText(AddNewPerson.this,"发送请求成功！",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(AddNewPerson.this,
						MainMapActivity.class);
				startActivity(intent);
//            	execute();
//            	showAlertDialog(v); 
            } 
        }); 

	}
	public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(AddNewPerson.this, AddPerson.class);
		startActivity(intent);
	}
	public void getTelClick(View v) {

		startActivityForResult(new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI), 0);
	}
	public void getOKClick(View v){
		execute();
	}
	public void showAlertDialog(View view) {

		CustomDialog.Builder builder = new CustomDialog.Builder(this);
		builder.setMessage("亲，找不到该联系人，请您重新输入手机号或者选择通讯联系人。");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//设置你的操作事项
			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.create().show();

	}
	/**
	 * @author shitou
	 * @time 20150906 20：17
	 * @description 描述：执行 查询该用户
	 */
	private void execute() {
		// TODO Auto-generated method stub
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
		String httpUrl = GlobalFinal.path.concat("users_requestControl.action?");
	
		Map<String, String> map = new HashMap<String, String>();
		//获取edittext 的文本内容  
		final String addnew_tel = et_mobile.getText().toString().trim();
		Userid userid1= new Userid(); 
//		String userid = userid1.getUserid();
		String userid ="9";
		Toast.makeText(this,  userid, Toast.LENGTH_LONG)
		.show(); 	
		map.put("telephone", addnew_tel);
		map.put("userid", userid);
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
					if (flag == 1) {
						Toast.makeText(AddNewPerson.this,"发送请求成功！",
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent();
						intent.setClass(AddNewPerson.this,
								MainMapActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(AddNewPerson.this, "获取新的好友用户坐标失败",
								Toast.LENGTH_SHORT).show();
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(AddNewPerson.this, "网络繁忙", Toast.LENGTH_SHORT)
						.show();
			}
		}
	};
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			// ContentProvider展示数据类似一个单个数据库表
			// ContentResolver实例带的方法可实现找到指定的ContentProvider并获取到ContentProvider的数据
			ContentResolver reContentResolverol = getContentResolver();
			// URI,每个ContentProvider定义一个唯一的公开的URI,用于指定到它的数据集
			Uri contactData = data.getData();
			// 查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
			Cursor cursor = managedQuery(contactData, null, null, null, null);
			cursor.moveToFirst();
			// 获得DATA表中的名字
//			username = cursor.getString(cursor
//					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			// 条件为联系人ID
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			// 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
			Cursor phone = reContentResolverol.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
							+ contactId, null, null);
			while (phone.moveToNext()) {
				usernumber = phone
						.getString(phone
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				et_mobile.setText(usernumber);
			}
		}
	}
    private void Save() { 


        String text = et_mobile.getText().toString(); 
        SharedPreferences sp = getSharedPreferences("history_strs", 0); 
        String save_Str = sp.getString("history", ""); 
        String[] hisArrays = save_Str.split(","); 
        for(int i=0;i<hisArrays.length;i++) 
        { 
            if(hisArrays[i].equals(text)) 
            { 
                return; 
            } 
        } 
        StringBuilder sb = new StringBuilder(save_Str); 
        sb.append(text + ","); 
        sp.edit().putString("history", sb.toString()).commit(); 
        Toast.makeText(AddNewPerson.this, sb.toString(), Toast.LENGTH_LONG).show(); 
    } 
}