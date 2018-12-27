package com.example.where.distance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.where.R;

public class JuliMainActivity extends Activity {
	private static final String FILENAME = "shezhibaochun";
	private static final Context JuliMainActivity = null;
	private static int ganyingfanshi = 1;
	private ImageView Iamgesubmit;
	private SeekBar seek = null;
	private TextView bangjin = null;
	private Spinner ganspinner;
	// dandan 添加的代码
	private ImageView Iamgeback = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juliganying_shezhi);
		this.seek = (SeekBar) super.findViewById(R.id.seekBarjuli);
		this.bangjin = (TextView) super.findViewById(R.id.bangjin); // 取锟斤拷TextView
		this.Iamgesubmit = (ImageView) this.findViewById(R.id.Iamgesubmit);

		// ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		// this,R.array.ganyingfanshispinner,
		// android.R.layout.simple_spinner_item);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ganspinner.setAdapter(adapter);
		this.Iamgesubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(JuliMainActivity.this,
						JuliGanYingActivity.class);
				startActivity(intent);

			}
		});
		// dandan 添加的代码（顶）
		// this.Iamgeback = (ImageView) this.findViewById(R.id.Iamgeback);
		//
		// this.Iamgeback.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent();
		// intent.setClass(JuliMainActivity.this,JuliGanYingActivity.class);
		// startActivity(intent);
		// }
		// });
		// dandan 添加的代码（底）
		ganspinner = (Spinner) findViewById(R.id.ganspinner);

		ganspinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					private Context JuliMainActivity;

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
						String naolinfanshi = JuliMainActivity.this.getResources().getStringArray(R.array.ganyingfanshispinner)[arg2];
						arg0.setVisibility(View.VISIBLE);
						Toast.makeText(JuliMainActivity.this,"您选择的是" + naolinfanshi, Toast.LENGTH_SHORT).show();
						SharedPreferences mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
						SharedPreferences.Editor editor = mySharedPreferences.edit();
					    editor.putString("name", naolinfanshi);
						editor.commit();
						
				        ganspinner.setSelection(arg2);
                     }

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		this.seek.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl());

	}



	
	public class OnSeekBarChangeListenerImpl implements
			SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			bangjin.setText(seekBar.getProgress() * 20 + "m");

			int a = seekBar.getProgress() * 20;
			String julisz = String.valueOf(a);
			SharedPreferences mySharedP = getSharedPreferences("test",
					Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = mySharedP.edit();
			editor.putString("juli", julisz);
			editor.commit();

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			bangjin.setText("");
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

		}
	}

}
