package com.example.where;

//import org.zsl.android.map.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.panoramaview.PanoramaView;

/**
 * @author chenzhenhongde
 *
 */
/**
 * @author chenzhenhongde
 *
 */
public class PanoramaActivity extends Activity {
	 private PanoramaView mPanoView;
	


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		 DemoApplication app = (DemoApplication) this.getApplication();
	        if (app.mBMapManager == null) 
	        {
	            app.mBMapManager = new BMapManager(app);
	 
	            app.mBMapManager.init(new DemoApplication.MyGeneralListener());
	        }
	
        setContentView(R.layout.panorama);
        mPanoView = (PanoramaView) findViewById(R.id.panorama);
      
		SharedPreferences sharedPreferences = getSharedPreferences("test", 
				Activity.MODE_PRIVATE); 
//				String latitude111 = sharedPreferences.getString("latitude111", ""); 
//				String longitude111 = sharedPreferences.getString("longitude111", ""); 
//				 double lat=Double.parseDouble(latitude111);
//				 double lon=Double.parseDouble(longitude111);
//		  double lat =24.645949; 
//		  double lon= 118.074080; 
//          mPanoView.setPanorama(26.088164,119.247242); 
          mPanoView.setPanorama(119.243506,26.086641); 
//		 mPanoView.setPanorama("0100220000130817164838355J5");
    
	}
	  
	
	@Override  
	protected void onDestroy() {  
	    mPanoView.destroy();  
	    super.onDestroy();  
	}
}
