package com.example.where;

//import org.zsl.android.map.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.Userid;
import com.example.where.addperson.AddPerson;
import com.example.where.cache.ImageLoader;
import com.example.where.distance.JuliGanYingActivity;
import com.example.where.functionall.ArcMenu;
import com.example.where.history.HistoryMainActivity;
import com.example.where.mymenu.MyMenu;
import com.example.where.navigation.NavigationActivity;
import com.example.where.photo.Bimp;
import com.example.where.photo.FileUtils;
import com.example.where.photo.Photograph;
import com.example.where.picture.ImgFileListActivity;
import com.example.where.util.SetInternetUtil;
import com.example.where.widget.FileHelper;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.HttpService_;

//import com.example.where.login.LoginActivity;

/**
 * @author chenzhenhongde
 *
 */
/**
 * @author chenzhenhongde
 *
 */
public class MainMapActivity extends Activity {
	public MapView mapView = null;
	public BaiduMap baiduMap = null;
	
//	String userid="2";

	// 定位相关声明
	public LocationClient locationClient = null;
	// 自定义图标
	public Marker marker = null;
	BitmapDescriptor mCurrentMarker = null;
	boolean isFirstLoc = true;// 是否首次定位

	private LocationMode mCurrentMode;
	static BDLocation BD_location = null;

	private LocationBDLocationListener locationListener = new LocationBDLocationListener();
    //最小值
	
	private ImageLoader imageLoader;
	private Marker mMarkerA;
	private Marker mMarkerB;
	// 初始化全局 bitmap 信息，不用时及时 recycle
	private BitmapDescriptor bdA;
	private BitmapDescriptor bdB;
	private InfoWindow mInfoWindow;
	private TextView mTextView;
	// private TextView mAddPerson;
	// private TextView mNavigation;
	// private TextView mHistory;
	// private TextView mDistance;

	private ImageView mHeadView;
	//
	private double latitude1;
	private double longitude1;
	private double meLatitude;
	private double meLongitude;
	String Addid;
//	public String  userid;
//	private DemoApplication app;

//	Intent intent2=getIntent();
//	String StringE=intent2.getStringExtra("fuck");
//	String userid = "9";
//			intent.getStringExtra("extra");
	//功能选择图片
	private static final int[] ITEM_DRAWABLES = {  R.drawable.function_nav_green,
		R.drawable.function_distance_green, R.drawable.function_history_green,
		R.drawable.function_all_green };
	
//	private String latitude1;
//	private String longitude1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Userid userid3= new Userid(); 
	    Addid = userid3.getAddid(); 
//		gethaoyou();
 
		initView();
		
	}
	//ArcMenu 弧形展示菜单 180°
	 private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
	        final int itemCount = itemDrawables.length;
	        for (int i = 0; i < itemCount; i++) {
	            ImageView item = new ImageView(this);
	            item.setImageResource(itemDrawables[i]);

	            final int position = i;
	            menu.addItem(item, new OnClickListener() {

	                @Override
	                public void onClick(View v) {
//	                    Toast.makeText(MainMapActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
	                    if(position==0){
	                    	startActivity(new Intent(MainMapActivity.this,NavigationActivity.class));
	                    }
	                    else if(position==1){
	                    	startActivity(new Intent(MainMapActivity.this,JuliGanYingActivity.class));
	                    }
                    	else if(position==2){
                    		startActivity(new Intent(MainMapActivity.this,HistoryMainActivity.class));
	                    }
                    	else if(position==3){
                    		startActivity(new Intent(MainMapActivity.this,PanoramaActivity.class));
                    	}
	                    
	                }
	            });
	        }
	    }
	
	// 跳转到AddPerson
	public void getMyClick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainMapActivity.this, MyMenu.class);
		startActivity(intent);
	}
	public String getUserId(){
		Intent intent2=getIntent();
		String StringE=intent2.getStringExtra("fuck");
		return StringE;
	}

	public void getAddPerson(View v) {
		Intent intent = new Intent(); 
		intent.setClass(MainMapActivity.this, AddPerson.class);
		startActivity(intent);
	}
	
	public void getChaneImageClick(View v){
		Intent intent = new Intent();
		intent.setClass(MainMapActivity.this, ImgFileListActivity.class);
		startActivity(intent);
	}
	
	

	private void initView() {
		// TODO Auto-generated method stub
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main_map);
		final ArcMenu arcMenu2 = (ArcMenu) findViewById(R.id.arc_menu_111);
//		arcMenu2.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
				initArcMenu(arcMenu2, ITEM_DRAWABLES);
//			}
//		});
		

		imageLoader = new ImageLoader(this);
//		Button locationBt = (Button) findViewById(R.id.myposition);
		// locationBt.setOnClickListener(new LocationOnClickListener('L'));
		mCurrentMode = LocationMode.NORMAL;

		mapView = (MapView) this.findViewById(R.id.wheremap); // 获取地图控件引用
		mapView.showZoomControls(false);//取消地图放大缩小图标
		baiduMap = mapView.getMap();
		// 开启定位图层
		baiduMap.setMyLocationEnabled(true);
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
		baiduMap.setMapStatus(msu);
		baiduMap.setOnMarkerClickListener(new LocationOnMarkerClickListener());
		bdA = BitmapDescriptorFactory.fromResource(R.drawable.belocation2);
		bdB = BitmapDescriptorFactory.fromResource(R.drawable.belocation2);
//		initOverlay2();
		// 测试文本框

		locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
		locationClient.registerLocationListener(locationListener); // 注册监听函数
		this.setLocationOption(); // 设置定位参数
		locationClient.start(); // 开始定位
		mCurrentMarker = BitmapDescriptorFactory
				.fromResource(R.drawable.my_point);
//		marker.setIcon(mCurrentMarker);
//		marker.setRotate(90);
		baiduMap
				.setMyLocationConfigeration(new MyLocationConfiguration(
						mCurrentMode, true, mCurrentMarker));
		mHeadView = (ImageView) findViewById(R.id.setup);
		mHeadView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent selectPicIntent = new Intent();
				selectPicIntent.setClass(MainMapActivity.this,
						MyMenu.class);
				selectPicIntent.putExtra("single", true);
				startActivityForResult(selectPicIntent, 1);
			}
		});
//		app = (DemoApplication) getApplication(); // 获取应用程序
//	    userid=app.getUserid1();
		// saveXY();
		// baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图

		// baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //设置为卫星地图
		// baiduMap.setTrafficEnabled(true); //开启交通图

		// longitude1 = 118.074080;
		// latitude1 =a;
		// longitude1=b;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (resultCode == 2) {
				List<String> pathList = data
						.getStringArrayListExtra("pictureList");
				if (pathList != null && pathList.size() > 0) {
					Bimp.bimpClear();
					Photograph.bitmapFromPathToBimpTest(MainMapActivity.this,
							pathList.get(0), "", false);
					 submitportrait();
				}
			}
			break;
		}
	}

	private void record() {
		FileHelper fs = new FileHelper(getApplicationContext());
		if (fs.hasSD()) {
			String txt = "";
			String str1 = String.valueOf(BD_location.getLatitude());
			String str2 = String.valueOf(BD_location.getLongitude());
			txt = str1 + ";" + str2 + ";true";
			if (fs.initDownPath("mBD_location.txt")) {
				fs.deleteSDFile("mBD_location.txt");
				fs.createSDFile("mBD_location.txt");
				fs.writeSDFile(txt, "mBD_location.txt");
			} else {
				fs.createSDFile("mBD_location.txt");
				fs.writeSDFile(txt, "mBD_location.txt");
			}
		} else {
			Toast.makeText(MainMapActivity.this, "温馨提示:该手机当前没有SD卡,无法存储登陆信息",
					3000).show();
		}
	}

//	/**
//	 * 添加客户点标注覆盖物
//	 */
//	public void addCustMarkers(LatLng point) {
//		// 构建Marker图标
//		BitmapDescriptor bitmap = BitmapDescriptorFactory
//				.fromResource(R.drawable.navigation_point);
//		// 构建MarkerOption，用于在地图上添加Marker
//		OverlayOptions option = new MarkerOptions().position(point)
//				.icon(bitmap).title("厦门");
//		// 在地图上添加Marker，并显示
//		baiduMap.addOverlay(option);
//	}
	/**
	 * 设置定位参数
	 */
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开GPS
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(10000); // 设置发起定位请求的间隔时间为10000ms
		option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(false); // 返回的定位结果包含手机机头的方向

		locationClient.setLocOption(option);
	}

	class LocationOnClickListener implements OnClickListener {

		char type;

		public LocationOnClickListener(char type) {
			this.type = type;
		}

		@Override
		public void onClick(View v) {
			switch (type) {
			case 'L':
				String addrStr = BD_location.getAddrStr();
				double latitude = BD_location.getLatitude();
				double longitude = BD_location.getLongitude();
				double getShortDistance = ShortDistance.GetShortDistance(
						latitude, longitude, latitude1, longitude1);
				Toast.makeText(
						MainMapActivity.this,
						"我当前位置:" + addrStr + "\n" + "与他距离：" + getShortDistance
								+ "米", Toast.LENGTH_LONG).show();
				// mTextView = (TextView)findViewById(R.id.textview);
				// mTextView.setText(getIntent().getStringExtra("我当前位置:" +
				// addrStr +"\n"+ "与他距离：" + getShortDistance + "米"));
				break;

			default:
				break;
			}
		}

	}

	class LocationBDLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			// map view 销毁后不在处理新接收的位置
			if (location == null || mapView == null)
				return;

			float radius = location.getRadius();
			double latitude = location.getLatitude();// 纬度
			double longitude = location.getLongitude();// 经度
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(radius)
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(latitude).longitude(longitude)
					.build();
			baiduMap.setMyLocationData(locData); // 设置定位数据
			if (isFirstLoc) {
				isFirstLoc = false;

				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory
						.newLatLngZoom(ll, 16); // 设置地图中心点以及缩放级别
				// MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				baiduMap.animateMapStatus(u);
			}

			BD_location = location;
			meLatitude = BD_location.getLatitude();
			meLongitude = BD_location.getLongitude();
//			handler.postDelayed(runnable, 5000);// 每两秒执行一次runnable.
//			handler2.postDelayed(runnable2, 10000);// 每10秒执行一次runnable.
//			Thread thread2 = new Thread(runnable2);  
//            thread2.start();  
            new Thread(new Runnable() {
    			
    			@Override
    			public void run() {
    				// TODO Auto-generated method stub
    				
    				try {
//    					Looper.prepare(); 
    					Thread.sleep(1000);
    					handler2.postDelayed(runnable2, 1000);// 每10秒执行一次runnable
//    					Looper.loop(); 
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
    			}
    		}).start();
			LatLng myPoint = new LatLng(latitude, longitude);
//			addCustMarkers(myPoint);
		}

	}
	

	private void initOverlay() {
		if (bdA != null) {
		LatLng llA = new LatLng(latitude1, longitude1);
		OverlayOptions ooA = new MarkerOptions().position(llA).icon(bdA)
				.zIndex(9);
		mMarkerA = (Marker) (baiduMap.addOverlay(ooA));}
	}
	// latitude1 =24.645949 + 0.010002;
			// longitude1 = 118.074080;
	private void initOverlay2() {
		if (bdB != null) {
			LatLng llA = new LatLng(26.086641,119.243506);
			OverlayOptions ooA = new MarkerOptions().position(llA).icon(bdB)
					.zIndex(9);
			mMarkerB = (Marker) (baiduMap.addOverlay(ooA));
			MapStatusUpdate u = MapStatusUpdateFactory
					.newLatLngZoom(llA, 16); // 设置地图中心点以及缩放级别
			baiduMap.animateMapStatus(u);
		
		}
	}

	// class LocationOnMarkerClickListener implements OnMarkerClickListener {
	//
	// @Override
	// public boolean onMarkerClick(final Marker latlng) {
	// Button button = new Button(getApplicationContext());
	// button.setBackgroundResource(R.drawable.popup);
	// OnInfoWindowClickListener listener = null;
	// LatLng position = latlng.getPosition();
	// if (latlng == mMarkerA) {
	// Log.i("TAG", "点击事件" + latlng);
	// button.setText("当前位置：" + "东经" + position.longitude + " 北纬" +
	// position.latitude);
	// listener = new OnInfoWindowClickListener() {
	// public void onInfoWindowClick() {
	// baiduMap.hideInfoWindow();
	// }
	// };
	// }
	// LatLng ll = latlng.getPosition();
	// mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button),
	// ll, listener);
	// baiduMap.showInfoWindow(mInfoWindow);
	//
	// return true;
	// }
	//
	// }

	class LocationOnMarkerClickListener implements OnMarkerClickListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener#onMarkerClick
		 * (com.baidu.mapapi.map.Marker)
		 */
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener#onMarkerClick
		 * (com.baidu.mapapi.map.Marker)
		 */
		@Override
		public boolean onMarkerClick(final Marker latlng) {

			OnInfoWindowClickListener listener = null;
			LatLng position = latlng.getPosition();
			if (latlng == mMarkerA||latlng == mMarkerB) {
				Log.i("TAG", "点击事件" + latlng);
				// Toast.makeText(MainMapActivity.this,"当前位置：" + "东经" +
				// position.longitude + " 北纬" +
				// position.latitude,Toast.LENGTH_LONG).show();
				//
				// 跳转到对监控者的操作 导航 历史 距离感应
				startActivity(new Intent(MainMapActivity.this,
						SelectPicPopupWindow.class));
				// Toast.makeText(LocationActivity.this, "当前位置:" + addrStr +
				// "  两点之间的距离：" + getShortDistance + "米",
				// Toast.LENGTH_LONG).show();
				listener = new OnInfoWindowClickListener() {
					public void onInfoWindowClick() {
						baiduMap.hideInfoWindow();
					}
				};
			}
			// LatLng ll = latlng.getPosition();
			// mInfoWindow = new
			// InfoWindow(BitmapDescriptorFactory.fromView(Toast), ll,
			// listener);
			// baiduMap.showInfoWindow(mInfoWindow);

			return true;
		}

	}

	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 要做的事情
//			repetitionLocation(meLatitude, meLongitude, latitude1, longitude1);
		}
	};
	Handler handler2 = new Handler();
	Runnable runnable2 = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 要做的事情
//			saveXY();
//			getXY();
			gethaoyou();
			handler2.postDelayed(this, 10000);
		}
	};
//	private Handler handler2 = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			saveXY();
//			Toast.makeText(getApplicationContext(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 0).show();
//			
//		}
//		
//	};
	private void gethaoyou(){
		if(Addid!=null){
			initOverlay2();
			
		}
		
	}	

	private void getXY() {

		JSONObject json;
		try {
		
			String httpUrl = GlobalFinal.path
					.concat("real_position_getXY.action?");
			Map<String, String> map = new HashMap<String, String>();
			HttpService_ http = new HttpService_();
			Userid userid1= new Userid(); 
			String userid = userid1.getUserid();
			map.put("userid", userid);
			String res = http.loginPostTimeData(httpUrl, map, 10000);
			json = new JSONObject(res);
			String s = json.getString("realPosition");
			JSONObject obj = new JSONObject(s);
			String arry1 = obj.getString("realPosition.userid");
			String arry2 = obj.getString("realPosition.longitude");
			String arry3 = obj.getString("realPosition.latitude");
			String arry4 = obj.getString("realPosition.positionTime");
			// String arry5 = obj.getString("realPosition.evaluation");
			// if(arry3.equals(null)||arry3.equals("null")||arry3.equals("")){
			// arry3 = "提醒你的是自我的情形就好忽然爱的飞";
			// }
			// tv_username.setText(arry1);
			// tv_motto.setText(arry3);

//			double a = Double.valueOf(arry3.toString());
//			double b = Double.valueOf(arry2.toString());
//			latitude1 = a;
//			longitude1 = b;
			String latitude11 = (arry3.toString());
			String longitude11 = (arry2.toString());

			// double a = Double.valueOf(arry3.toString());
			// double b = Double.valueOf(arry2.toString());
//			 实例化SharedPreferences对象（第一步）
			SharedPreferences mySharedPreferences = getSharedPreferences(
					"test", Activity.MODE_PRIVATE);
			// 实例化SharedPreferences.Editor对象（第二步）
			SharedPreferences.Editor editor = mySharedPreferences.edit();
			// 用putString的方法保存数据
			editor.putString("latitude111", latitude11);
			editor.putString("longitude111", longitude11);
			// 提交当前数据
			editor.commit();
			// 使用toast信息提示框提示成功写入数据
//			Toast.makeText(this, "数据成功写入SharedPreferences！", Toast.LENGTH_LONG)
//					.show();
//			Toast.makeText(this,  userid, Toast.LENGTH_LONG)
//					.show();
			initOverlay();

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void saveXY() {
		String httpUrl = GlobalFinal.path
				.concat("real_position_saveXY.action?");
		Map<String, String> map = new HashMap<String, String>();
		String latitude = String.valueOf(BD_location.getLatitude());
		String longitude = String.valueOf(BD_location.getLongitude());
		Userid userid1= new Userid(); 
		String userid = userid1.getUserid();
		String my_userid = userid;
		// 注意类型转换 double 是否转成string 若代码不成功 请测试此位置
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日   HH:mm:ss     ");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String strTime = formatter.format(curDate);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("userid", my_userid);
		map.put("position_time", strTime);
		// HttpService
		HttpService_ http = new HttpService_();
		String res = http.loginPostTimeData(httpUrl, map, 10000);

		JSONObject json;
		try {
			json = new JSONObject(res);
			String msg = json.getString("msg");
			String personId = json.getString("personId");
			if (msg.equals("success")) {
//				Toast.makeText(MainMapActivity.this, "xy写入成功！ "+"当前用户id 为"+my_userid+"personId"+
//						personId, 2000).show();
				// record();//记录到sd卡
				// intent.putExtra("stuId", stuId);
			} else {
				Toast.makeText(MainMapActivity.this, "xy写入失败！ ", 2000).show();
			}
		} catch (JSONException e) {
//			Toast.makeText(MainMapActivity.this, "save异常", 2000).show();
		}

	}

	/**
	 * 
	 * @param mLatitude
	 *            我的纬度
	 * @param mLongitude
	 *            我的经度
	 * @param fLatitude
	 *            好友纬度
	 * @param fLongitude
	 *            好友经度
	 */
	private void repetitionLocation(double mLatitude, double mLongitude,
			double fLatitude, double fLongitude) {

		double getShortDistance = ShortDistance.GetShortDistance(mLatitude,
				mLongitude, fLatitude, fLongitude);
		if (getShortDistance > 500) {
			Toast.makeText(MainMapActivity.this, "两点距离大于500", Toast.LENGTH_LONG)
					.show();
			handler.removeCallbacks(runnable);
		} else {
			Toast.makeText(MainMapActivity.this,
					"10秒后两点之间的距离：" + getShortDistance + "米", Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();	
		mapView.onPause();
	}

	// 三个状态实现地图生命周期管理
	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		locationClient.stop();
		baiduMap.setMyLocationEnabled(false);
		super.onDestroy();
		mapView.onDestroy();
		mapView = null;
		bdA.recycle();
		bdA=null;
	}

	
	//图片提交线程
	private Thread thead;
	private ProgressDialog progressDialog;
	protected void submitportrait() {
		try {
			if (SetInternetUtil.isNetworkConnected(MainMapActivity.this)) {// 判断网络是否可用，并设置网络
				progressDialog = new ProgressDialog(MainMapActivity.this);
				progressDialog.show();
				thead = new Thread(new Runnable() {
					@Override
					public void run() {
						String res = submitData();
						Message msg_netData = new Message();
						if (res == null || res.equals("") || res.equals("0")) {
							msg_netData.what = 0;
						}else {
							msg_netData.obj = res;
							msg_netData.what = 2;
						}
						operate.sendMessage(msg_netData);
					}
				});
				thead.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Handler operate = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			switch (msg.what) {
			case 0:
				Toast.makeText(MainMapActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				imageLoader.DisplayImage(msg.obj.toString(), mHeadView,
						R.drawable.head_default);
				break;
			}
		}
	};

	private String submitData() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", "changePortrait");
		map.put("personId", "1");
		String str = FileUtils.uploadFile(MainMapActivity.this,
				GlobalFinal.path.concat("ImageUpServlet.do"), map);
		String res = "";
		if (str != null && !str.equals(""))
			res = str.split(",")[1];
		return res;
	}
}
