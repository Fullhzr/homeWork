/**
 *名称：
 *
 *作者：
 * 
 *下午2:34:05
 */
package com.example.where.mymenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.where.MainMapActivity;
import com.example.where.R;



/**
 * @author Administrator
 *
 *
 *下午2:34:05
 */
public class MySetting extends Activity{
	
	public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(MySetting.this, MyMenu.class);
		startActivity(intent);  
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_setting);
	}
	
	/* 创建菜单 */
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "分享");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			// intent.setType("text/plain"); //纯文本
			/*
			 * 图片分享 it.setType("image/png"); 　//添加图片 File f = new
			 * File(Environment.getExternalStorageDirectory()+"/name.png");
			 * 
			 * Uri uri = Uri.fromFile(f); intent.putExtra(Intent.EXTRA_STREAM,
			 * uri);
			 */
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("image/*");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
			intent.putExtra(Intent.EXTRA_TEXT,
					"I have successfully share my message through my app");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, getTitle()));
			return true;
		}
		return false;
	}
	
	
}


