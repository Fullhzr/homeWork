package com.example.where.addperson;


import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.zxing.activity.CaptureActivity;
import com.example.where.zxing.encoding.EncodingHandler;
import com.google.zxing.WriterException;

public class DimensionalResult extends Activity {
	private TextView resultTextView;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dimensional_result);
        
        
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent  
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据  
        String str=bundle.getString("str");//getString()返回指定key的值  
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);//用TextView显示值  
        resultTextView.setText(str);  
     
    }

    public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(DimensionalResult.this, AddPerson.class);
		startActivity(intent);
	}
}