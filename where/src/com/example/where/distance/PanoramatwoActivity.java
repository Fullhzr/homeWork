package com.example.where.distance;

//import org.zsl.android.map.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.example.where.DemoApplication;
import com.example.where.R;

/**
 * @author chenzhenhongde
 *
 */
/**
 * @author chenzhenhongde
 *
 */
public class PanoramatwoActivity extends Activity {
	 private PanoramaView mPanoView;
	


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		 
		 
		 DemoApplication app = (DemoApplication) this.getApplication();
	        if (app.mBMapManager == null) {
	            app.mBMapManager = new BMapManager(app);
	 
	            app.mBMapManager.init(new DemoApplication.MyGeneralListener());
	        }
	
	
        setContentView(R.layout.panorama);
        mPanoView = (PanoramaView) findViewById(R.id.panorama);
      
        
        SharedPreferences sharedPreferences = getSharedPreferences(
				"geo", Activity.MODE_PRIVATE);
		String latitu= sharedPreferences.getString("latitu","");
		String longit= sharedPreferences.getString("longit","");
   
		double lat=Double.valueOf(latitu);
		double lon=Double.valueOf(longit);
		double mLat1 = lat;
//		052
		double mLon1 = lon;
//		066
//         mPanoView.setPanorama(mLat1, mLon1);
//         mPanoView.setPanorama(lon,lat );
         mPanoView.setPanorama(119.243506,26.086641);     
//		 mPanoView.setPanorama("0100220000130817164838355J5");
    
	}
	  
	
	@Override  
	protected void onDestroy() {  
	    mPanoView.destroy();  
	    super.onDestroy();  
	}
}
