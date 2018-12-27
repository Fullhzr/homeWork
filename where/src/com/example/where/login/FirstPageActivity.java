package com.example.where.login;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.where.R;

public class FirstPageActivity extends Activity {
    private static final Date DELAY = null;
    private TextView mTextView; 

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_page);
        
        this.mTextView = (TextView) findViewById(R.id.jump);
        this.mTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(FirstPageActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
        
        final Intent localIntent=new Intent(this,LoginActivity.class);
        Timer timer=new Timer();
        TimerTask tast=new TimerTask()
        {
         @Override
         public void run(){
          startActivity(localIntent);
         }
        };
//        timer.schedule(tast,DELAY);
        timer.schedule(tast, 6000);
    }
}