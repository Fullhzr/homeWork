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

import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.SelectPicPopupWindow;
import com.example.where.addperson.AddNewPerson;
import com.example.where.distance.JuliMainActivity;
import com.example.where.history.HistoryMainActivity;
import com.example.where.navigation.NavigationActivity;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.HttpService_;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Administrator
 *
 *
 *下午2:34:05
 */
public class MyHistory extends Activity{
	
	private TextView text_userid;
	private TextView refresh;
	private Button accept;
	private Button rejection;
	private String uid="1";
	
	public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(MyHistory.this, MyMenu.class);
		startActivity(intent);  
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_history);
		// 根据Id获得文本框
	}
	
	public void onClick(View v) {  
        switch (v.getId()) {  
        case R.id.refresh2: 
            break;  
        case R.id.accept:    
        	
            break;  
        case R.id.rejection:  
        	
            break;  
        default:  
            break;  
        }  
        finish();  
    }  
	
	
}
