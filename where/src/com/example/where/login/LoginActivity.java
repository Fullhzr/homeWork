package com.example.where.login;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Userid;
import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.HttpService_;

public class LoginActivity extends Activity {
	private Button register_button = null;
	private Button dllregister_button = null;
	private EditText username_edit;
	private EditText password_edit;
	//SP来记录是否第一次打开软件
	private SharedPreferences preferences;
	
	public void getGotoLogin(View v){
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
		String httpUrl = GlobalFinal.path.concat("users_login.action?");
	
		Map<String, String> map = new HashMap<String, String>();
		//获取edittext 的文本内容  
		final String telephone = username_edit.getText().toString().trim();  
        final String password = password_edit.getText().toString().trim(); 
		map.put("telephone", telephone);
		map.put("password", password);
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
					json = new JSONObject(msg.obj.toString().replace("<br>", "\n"));
					int flag = Integer.valueOf(json.getString("flag"));
					String userid = (json.getString("userid"));
					 Userid userid1=new Userid(); 
					 userid1.setUserid(userid);
//					DemoApplication app;
//					app = (DemoApplication) getApplication(); // 获得CustomApplication对象
//                    app.setUserid1 (userid); // 重新设置值
					//实例化SharedPreferences对象（第一步） 
//					SharedPreferences mySharedPreferences= getSharedPreferences("test", 
//					Activity.MODE_PRIVATE); 
//					//实例化SharedPreferences.Editor对象（第二步） 
//					SharedPreferences.Editor editor = mySharedPreferences.edit(); 
//					//用putString的方法保存数据 
//					editor.putString  ("userid", userid); 
//					//提交当前数据 
//					editor.commit(); 
					if(flag==1){
 
//						Toast.makeText(LoginActivity.this, userid,
//								Toast.LENGTH_SHORT).show();
						Intent intent2=new Intent();
//						intent2.putExtra("fuck", userid);
 //						Toast.makeText(LoginActivity.this, userid,
//								Toast.LENGTH_SHORT).show();
//						Intent intent2=new Intent(); 
 
						intent2.setClass(LoginActivity.this,MainMapActivity.class);
						startActivity(intent2);
						
					}else{
						Toast.makeText(LoginActivity.this, "用户名密码错误",
								Toast.LENGTH_SHORT).show();
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} 
			else {
				Toast.makeText(LoginActivity.this, "网络繁忙",
						Toast.LENGTH_SHORT).show();
			}
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_main);
        
        preferences = getSharedPreferences("count",MODE_WORLD_READABLE);
        int count = preferences.getInt("count", 0);
        //判断程序与第几次运行，如果是第一次运行则跳转到引导页面
        if (count == 0 ) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),FirstPageActivity.class);
            startActivity(intent);
            finish();

        }
        Editor editor = preferences.edit();
        //存入数据
        editor.putInt("count", ++count);
        //提交修改
        editor.commit();
        
        this.dllregister_button=(Button)findViewById(R.id.dllregister_button);
        this.username_edit=(EditText)findViewById(R.id.phone_number_edit);
        this.password_edit=(EditText)findViewById(R.id.password_edit);
        //
        this.dllregister_button.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View arg0) {
				String yhm = "1234567";
				String mm="123";
				username_edit.setText(yhm);
				password_edit.setText(mm);
			}
		});
       
//        LoginCircularImage cover_user_photo = (LoginCircularImage) findViewById(R.id.cover_user_photo);
  		LoginCircularImage cover_user_photo=(LoginCircularImage) findViewById(R.id.ccz);
        
          cover_user_photo.setImageResource(R.drawable.login_face);
        this.register_button=(Button)findViewById(R.id.register_button);//
        this.register_button.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View arg0) {
				 Intent intent = new Intent(LoginActivity.this,LoginRegister.class);
                 startActivity(intent);
			}
		});
	 }
}