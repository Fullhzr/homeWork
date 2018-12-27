package com.example.where.addperson;

import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.zxing.activity.CaptureActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPerson extends Activity{
	//添加新的好友 及 添加历史好友按钮
	private Button mAddNewFriend;
	private Button mAddHistoryFriend;
	
	@SuppressLint("CutPasteId")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_person);
		
		this.mAddNewFriend = (Button)findViewById(R.id.btn_add_newfriend);
		this.mAddNewFriend.setOnClickListener(new OnClickListenerImpl());
		
		this.mAddHistoryFriend = (Button)findViewById(R.id.btn_add_historyfrined);
		this.mAddHistoryFriend.setOnClickListener(new OnClickListenerImpl2());
		
		
		 Button btn_scan_barcode = (Button) this.findViewById(R.id.btn_scan_barcode);
		 btn_scan_barcode.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
						
					Intent openCameraIntent = new Intent(AddPerson.this,CaptureActivity.class);
					startActivityForResult(openCameraIntent, 0);
				}
			});
	        
	        
	   	 Button btn_your_identical = (Button) this.findViewById(R.id.btn_your_identical);
	   	btn_your_identical.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent openCameraIntent = new Intent(AddPerson.this,DimensionalQrself.class);
					startActivityForResult(openCameraIntent, 0);
					
				}
			});
	        
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			Intent intent=new Intent();  
	        intent.setClass(AddPerson.this, DimensionalResult.class);//从一个activity跳转到另一个activity  
	        intent.putExtra("str", scanResult);//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
	        startActivity(intent);  
			
			
			
//			resultTextView.setText(scanResult);
		}
	}
	private class OnClickListenerImpl implements OnClickListener{
			
			public void onClick(View view) {
				Intent intent=new Intent();
				intent.setClass(AddPerson.this, AddNewPerson.class);
				startActivity(intent);
				AddPerson.this.finish();
			}
		}
	private class OnClickListenerImpl2 implements OnClickListener{
		
		public void onClick(View view) {
			Intent intent=new Intent();
			intent.setClass(AddPerson.this, AddHistoryPerson.class);
			startActivity(intent);
			AddPerson.this.finish();
		}
	}
	public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(AddPerson.this, MainMapActivity.class);
		startActivity(intent);
	}
}
