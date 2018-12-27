package com.example.where.login;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.where.R;
import com.example.where.widget.FileHelper;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.GlobalStatic;
import com.example.where.widget.HttpService_;
public class LoginRegister extends Activity {	
	private TextView tv_telephone, tv_password, tv_resurepwd;
	private EditText edit_telephone, edit_password, edit_resurepwd;
	private Button btn_register_submit;
	private String telephone, pwd, repwd;
	private Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 填充标题栏
				requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置要使用的布局管理器
		setContentView(R.layout.login_register);
		// 获取各个组件
		tv_telephone = (TextView) findViewById(R.id.login_phone_number_input);
		tv_password = (TextView) findViewById(R.id.login_password_input);
		tv_resurepwd = (TextView) findViewById(R.id.login_confirm_password_input);		
		edit_telephone = (EditText) findViewById(R.id.phone_number_edit);
		edit_password = (EditText) findViewById(R.id.password_edit);
		edit_resurepwd = (EditText) findViewById(R.id.confirm_password_edit);
		btn_register_submit = (Button) findViewById(R.id.register_button);
		//点击事件处理
		btn_register_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 注册的判断
				if (edit_telephone.getText().toString().trim().length()>0 
					&& edit_password.getText().toString().trim().length()>0 
					&& edit_resurepwd.getText().toString().trim().length()>0) 
				{
					if (edit_password.getText().toString().trim().equals(edit_resurepwd.getText().toString().trim())) {
						submitRegistData();
						//数据交互提交后台
					} else {
						Toast.makeText(getApplicationContext(), "两次密码输入不一致",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "注册失败！手机号、密码和确认密码不能为空",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}	
	/**
	 * 用户注册
	 */
	private void submitRegistData() {
		String httpUrl = GlobalFinal.path.concat("users_regist.action?");
		Map<String, String> map = new HashMap<String, String>();
		String telephone = edit_telephone.getText().toString().trim();
		String password = edit_password.getText().toString().trim();
		map.put("telephone", telephone);
		map.put("password", password);
		HttpService_ http = new HttpService_();
		String res = http.loginPostTimeData(httpUrl, map,10000);
		JSONObject json;
		try {
			json = new JSONObject(res);
			String msg = json.getString("msg");
			String userid = json.getString("userid");
			if (msg.equals("success")){
				Toast.makeText(LoginRegister.this, " 用户注册成功！ ", 2000).show();
				record();
				//记录到sd卡
				GlobalStatic.setUserid(userid);
			    intent.putExtra("userid", userid);
	            intent.setClass(LoginRegister.this, LoginActivity.class);
	            startActivity(intent);
	            finish();
			}else{
				Toast.makeText(LoginRegister.this, " 用户注册失败！ ", 2000).show();
			}
		} catch (JSONException e) {
			Toast.makeText(LoginRegister.this, "用户注册", 2000).show();
		}
	}
	private void record() {
		FileHelper fs = new FileHelper(getApplicationContext());
		if (fs.hasSD()) {
			String txt= "";
			String str1 = edit_telephone.getText().toString().trim();
			String str2 = edit_password.getText().toString().trim();
			txt = str1+";"+str2+";true";
			if (fs.initDownPath("kxtlogin.txt")){
				fs.deleteSDFile("kxtlogin.txt");
				fs.createSDFile("kxtlogin.txt");
				fs.writeSDFile(txt, "kxtlogin.txt");
			} else {
				fs.createSDFile("kxtlogin.txt");
				fs.writeSDFile(txt, "kxtlogin.txt");
			}
		} else {
			Toast.makeText(LoginRegister.this, "温馨提示:该手机当前没有SD卡,无法存储登陆信息", 3000).show();
		}
	}
}