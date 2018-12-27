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
import android.view.View;

import com.example.where.R;

/**
 * @author Administrator
 *
 *
 *下午2:34:05
 */
public class MyInformation extends Activity{
	public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(MyInformation.this, MyMenu.class);
		startActivity(intent);  
	}
	   @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
   }  
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.my_information);
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
