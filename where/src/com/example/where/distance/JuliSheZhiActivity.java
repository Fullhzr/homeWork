package com.example.where.distance;


import com.example.where.R;


import android.app.Activity;
import android.os.Bundle;


import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.SeekBar;
import android.widget.TextView;

public class JuliSheZhiActivity extends Activity {

	private  SeekBar seek = null ;
	private TextView bangjin = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.juliganying_shezhi);
		this.seek = (SeekBar) super.findViewById(R.id.seekBarjuli);
		this.bangjin = (TextView) super.findViewById(R.id.bangjin);		// ȡ��TextView
		this.bangjin.setMovementMethod(ScrollingMovementMethod.getInstance()); // �����ı�
		this.seek.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl());
	}
	public class OnSeekBarChangeListenerImpl implements SeekBar.OnSeekBarChangeListener {			// ���ò�������
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) 
		{
			JuliSheZhiActivity.this.bangjin.append(""+ seekBar.getProgress()*20 +" m"+ "\n");
		}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar)
		{
			bangjin.setText("");
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) 
		{
			
		}
	}

}
