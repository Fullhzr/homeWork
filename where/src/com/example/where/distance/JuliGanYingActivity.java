package com.example.where.distance;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
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
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapLongClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.where.MainMapActivity;
import com.example.where.R;

public class JuliGanYingActivity extends Activity {
	private static int firstone = 1;
	protected static final String SHARE_APP_TAG = null;
	private MapView mMyMap;
	private BaiduMap mBaiduMap;
	private TextView mMyPosition;
	private LocationClient mLocationClient;
	private ImageView Iamgeback;
	private ImageView JuliSetting;
	private TextView xiangqin;
	private TextView shezhi;
	Vibrator vibrator; // 振动
	MediaPlayer alarmMusic; // 响铃

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		super.setContentView(R.layout.juliganying_moshi); // 调用布局管理器
		mMyMap = (MapView) findViewById(R.id.m_map_view);// 获取地图控件引用
		xiangqin = (TextView) findViewById(R.id.xiangqin);
		vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
		shezhi = (TextView) findViewById(R.id.shezhi);
		alarmMusic = MediaPlayer.create(this, R.raw.naoling);
		alarmMusic.setLooping(true);

		
		this.JuliSetting = (ImageView) this.findViewById(R.id.JuliSetting);
		this.JuliSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (firstone == 1) {
					Intent intent = new Intent();
					intent.setClass(JuliGanYingActivity.this, JuliMainActivity.class);
					startActivity(intent);
					firstone = 2;
				} else if (firstone == 2) {
					finish();
					firstone = 1;
				}
 
				else {
					Intent intent = new Intent();
					intent.setClass(JuliGanYingActivity.this, JuliMainActivity.class);
					startActivity(intent);
				}

			}
		});

		this.Iamgeback = (ImageView) this.findViewById(R.id.Iamgeback);
		this.Iamgeback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(JuliGanYingActivity.this,MainMapActivity.class);
				startActivity(intent);

			}
		});

		mBaiduMap = mMyMap.getMap();// 获取地图管理控件引用
		mBaiduMap.setMapStatus(MapStatusUpdateFactory
				.newMapStatus(new MapStatus.Builder().zoom(16).build()));
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0) {

			}
		});

		mBaiduMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng point) {
				Toast.makeText(JuliGanYingActivity.this, "您触发了地图的长按事件",
						Toast.LENGTH_SHORT).show();
				alarmMusic.stop();
				vibrator.cancel();
			}
		});

		mLocationClient = new LocationClient(getApplicationContext());
		mLocationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {

				if (location != null) {
					mLocationClient.stop();
					final LatLng center = new LatLng(location.getLatitude(),
							location.getLongitude());
					mBaiduMap.setMapStatus(MapStatusUpdateFactory
							.newLatLng(center));
					// 添加图标
					// 逆地址解析
					GeoCoder mSearch = GeoCoder.newInstance();
					mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
						@Override
						public void onGetReverseGeoCodeResult(
								ReverseGeoCodeResult arg0) {
							if (arg0 != null) {
								View toastRoot = getLayoutInflater().inflate(R.layout.my_toast, null);
								 Toast toast=new Toast(getApplicationContext());
//								toast.setView(toastRoot);
//							    TextView tv=(TextView)toastRoot.findViewById(R.id.TextViewInfo);
//								tv.setText("您的当前位置是：(" + arg0.getAddress() + ")");
//								toast.show();
//								Toast.makeText(JuliGanYingActivity.this,
//										"您的当前位置是：(" + arg0.getAddress() + ")",
//										Toast.LENGTH_SHORT).show();
								addCustMarkers(center, arg0.getAddress());

								double mLat1 =26.086641;
								double mLon1 =119.243506;
								LatLng pt_start = new LatLng(mLat1, mLon1);

								double distance = DistanceUtil.getDistance(
										pt_start, center);

								distance = Math.floor(distance * 10) / 10;
								String dista = String.valueOf(distance);

								SharedPreferences myShared = getSharedPreferences(
										"distance", Activity.MODE_PRIVATE);
								SharedPreferences.Editor editor = myShared
										.edit();
								editor.putString("dist", dista);
								editor.commit();

								//
								addCustMarkerss(pt_start, arg0.getAddress());
								JuliGanYingActivity.this.xiangqin.setText(""
										+ "您与好友当前距离为" + distance + " m" + "\n"
										+ "" + "好友当前位置：" + arg0.getAddress());

							}
						}

						@Override
						public void onGetGeoCodeResult(GeoCodeResult arg0) {

						}
					});

					mSearch.reverseGeoCode(new ReverseGeoCodeOption()
							.location(center));
//					Toast.makeText(
//							JuliGanYingActivity.this,
//							"您的当前位置是：(" + center.latitude + ","
//									+ center.longitude + ")",
//							Toast.LENGTH_SHORT).show();
					String la = String.valueOf(center.latitude);
					String lo = String.valueOf(center.longitude);

					SharedPreferences mySharedPreferences = getSharedPreferences(
							"geo", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = mySharedPreferences
							.edit();
					editor.putString("latitu", la);
					editor.putString("longit", lo);
					editor.commit();

				}
			}
		});

		
		
		
		
		
		
		
		
		
		
		new Handler().postDelayed(new Runnable() {
			public void run() {

				initLocation();
				mLocationClient.start();

				SharedPreferences myShared = getSharedPreferences("distance",
						Activity.MODE_PRIVATE);

				String dist = myShared.getString("dist", "");

				SharedPreferences mySharedP = getSharedPreferences("test",
						Activity.MODE_PRIVATE);

				String juli = mySharedP.getString("juli", "200.00");
				Double julishezhi = Double.parseDouble(juli);
				// double dis;
				// dis=Double.parseDouble(dist);
				// SharedPreferences mysharedPreferences1 =
				// getSharedPreferences("test", Activity.MODE_PRIVATE);
				// String dis = mysharedPreferences1.getString("dist", "");
				// Double distance = Double.parseDouble(dis);
				// Toast.makeText(JuliGanYingActivity.this,
				// dist,Toast.LENGTH_SHORT).show();
				if (dist != null && !dist.equals("")) 
				{
					Double distance = Double.parseDouble(dist);
					Double banjin = 200.00;
                if (distance > julishezhi) {
                	
                	
                	SharedPreferences mySharedPreferences1 = getSharedPreferences("test", Activity.MODE_PRIVATE);
					String szfs = mySharedPreferences1.getString("name", "手机闹铃+振动");
                    if (szfs.equals("手机振动")) {
                    vibrator.vibrate(new long[] { 1000, 3000, 1000,
								3000 }, -1);
						setmessge();
					} else if (szfs.equals("手机闹铃")) {

						try {
							alarmMusic.prepare();
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						alarmMusic.start();
						setmessge();
					} else {

						vibrator.vibrate(new long[] { 1000, 3000, 1000,
								3000 }, -1);

						try {
							alarmMusic.prepare();
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						alarmMusic.start();
						setmessge(); 	
                	
                	
                	
				}}
            	
        		else {
        			
        		}
                
				}

			}
                
               
		}, 2000);
	}

					
		

	public void addCustMarkers(LatLng point, String string) {
		// 构建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_mark_me);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap).title(string);
		// 在地图上添加Marker，并显示
		mBaiduMap.addOverlay(option);
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker arg0)

			{
//				Toast.makeText(JuliGanYingActivity.this, "您触发了全景图事件!",
//						Toast.LENGTH_SHORT).show();
//
//				Intent intent = new Intent();
//				intent.setClass(JuliGanYingActivity.this,PanoramatwoActivity.class);
//				startActivity(intent);
				// Intent intent = new;
				// intent = new
				// Intent(JuliGanYingActivity.this,PanoramaActivity.class);
				// startActivity(intent);

				// TODO Auto-generated method stub
				return false;
			}

			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}

			public void onMapClick(LatLng arg0) {

			}
		});
	}

	public void addCustMarkerss(LatLng point, String string) {
		// 构建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.belocation2);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap).title(string);
		// 在地图上添加Marker，并显示
		mBaiduMap.addOverlay(option);
	}

	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy);
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		option.setProdName("com.baidu.location.service_v2.2");
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
		int span = 1000 * 20;
		option.setScanSpan(span);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMyMap.onDestroy();
		if (mLocationClient != null) {
			mLocationClient.stop();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMyMap.onResume();
		if (mLocationClient != null) {
			mLocationClient.stop();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMyMap.onPause();
		if (mLocationClient != null) {
			mLocationClient.stop();
		}
	}
	
	
	
	
public void setmessge() {

		
	Dialog dialog = new AlertDialog.Builder(JuliGanYingActivity.this) // 实例化对象
		// 设置显示图片
	.setTitle("提示信息") 	// 设置显示标题
	.setMessage("您与好友的距离已经超出指定范围") 	// 设置显示内容
	.setPositiveButton("停止提醒", 		// 增加一个确定按钮
	new DialogInterface.OnClickListener() {// 设置操作监听
		public void onClick(DialogInterface dialog,int whichButton) { 
			
			alarmMusic.stop();
			vibrator.cancel();
			
			// 单击事件
		}
	}).setNegativeButton("取消", 		// 增加取消按钮
			new DialogInterface.OnClickListener() {// 设置操作监听
			public void onClick(DialogInterface dialog,int whichButton) { 
				
				dialog.cancel();
		}}).create(); 	// 创建Dialog
	dialog.show(); 	
	
	
	
	
	
	
	
	
	
}	
		
		
		
		
		
		



private Builder setNegativeButton(String string,
		android.content.DialogInterface.OnClickListener onClickListener) {
	// TODO Auto-generated method stub
	return null;
}
		
	
}
