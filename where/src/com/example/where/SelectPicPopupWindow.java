/**
 *名称：
 *
 *作者：
 * 
 *上午11:31:26
 */
package com.example.where;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.where.R;
import com.example.where.distance.JuliGanYingActivity;
import com.example.where.distance.JuliMainActivity;
import com.example.where.history.HistoryMainActivity;
import com.example.where.navigation.NavigationActivity;
/**
 * 
 * @author 石头
 *2015年8月25日下午4:57:22
 */
public class SelectPicPopupWindow extends Activity implements OnClickListener {
	 private Button bottom_navigation, bottom_distance, bottom_history,bottom_qujingtu;  
	    private LinearLayout layout;  
	      
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.bottom);  
	        bottom_navigation = (Button) this.findViewById(R.id.bottom_navigation);  
	        bottom_distance = (Button) this.findViewById(R.id.bottom_distance);  
	        bottom_history = (Button) this.findViewById(R.id.bottom_history);  
	        bottom_qujingtu = (Button) this.findViewById(R.id.bottom_qujingtu);
	        getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	        layout=(LinearLayout)findViewById(R.id.pop_layout);  
	          
	        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity  
	        layout.setOnClickListener(new OnClickListener() {  
	              
	            public void onClick(View v) {  
	                // TODO Auto-generated method stub  
	                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",   
	                        Toast.LENGTH_SHORT).show();   
	            }  
	        });  
	        //添加按钮监听  
	        bottom_navigation.setOnClickListener(this);  
	        bottom_distance.setOnClickListener(this);  
	        bottom_history.setOnClickListener(this);  
	        bottom_qujingtu.setOnClickListener(this); 
	    }  
	      
	    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity  
	    @Override  
	    public boolean onTouchEvent(MotionEvent event){  
	        finish();  
	        return true;  
	    }  
	  
	    public void onClick(View v) {  
	        switch (v.getId()) {  
	        case R.id.bottom_navigation: 
	        	startActivity(new Intent(SelectPicPopupWindow.this,NavigationActivity.class));
	            break;  
	        case R.id.bottom_distance:    
	        	startActivity(new Intent(SelectPicPopupWindow.this,JuliGanYingActivity.class));
	            break;  
	        case R.id.bottom_history:  
	        	startActivity(new Intent(SelectPicPopupWindow.this,HistoryMainActivity.class));
	            break;  
	        case R.id.bottom_qujingtu:  
	        	startActivity(new Intent(SelectPicPopupWindow.this,PanoramaActivity.class));
	            break;  
	        default:  
	            break;  
	        }  
	        finish();  
	    }  
}
