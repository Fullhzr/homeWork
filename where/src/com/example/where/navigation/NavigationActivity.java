package com.example.where.navigation;
 

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapLongClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.where.R;

public class NavigationActivity extends Activity implements
		BaiduMap.OnMapClickListener, OnGetRoutePlanResultListener {
	private ImageView mLuxiantuichu;
	private MapView mMyMap;
	private BaiduMap mBaiduMap;
	private ImageView mMyPosition;
//	private TextView mMyText;
	private ImageView mDriver;
	private ImageView mBus;
	private ImageView mPerson;
	private LocationClient mLocationClient; // 定位类
	private RoutePlanSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	private RouteLine route = null;
	private int nodeIndex = -1;// 节点索引,供浏览节点时使用
	private boolean useDefaultIcon = false;
	private OverlayManager routeOverlay = null;
	private LatLng geom = null;
	private LatLng geomEnd = null;
 

 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.navigation);  // 调用布局管理器
		mMyMap = (MapView) findViewById(R.id.m_map_view);// 获取地图控件引用

		mBaiduMap = mMyMap.getMap();// 获取地图管理控件引用
		mBaiduMap.setOnMapClickListener(this);
		mBaiduMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng arg0) {
				Toast.makeText(NavigationActivity.this, "您触发了地图的长按事件",
						Toast.LENGTH_SHORT).show();
			}
		});
		View toastRoot = getLayoutInflater().inflate(R.layout.my_toast, null);
		 Toast toast=new Toast(getApplicationContext());
		toast.setView(toastRoot);
	    TextView tv=(TextView)toastRoot.findViewById(R.id.TextViewInfo);
		tv.setText("请选择前往方式");
		toast.show();
//		Toast.makeText(NavigationActivity.this, "请选择前往方式",
//				Toast.LENGTH_SHORT).show();
		// 鍒濆鍖栨悳绱㈡ā鍧楋紝娉ㄥ唽浜嬩欢鐩戝惉
		mSearch = RoutePlanSearch.newInstance();
		mSearch.setOnGetRoutePlanResultListener(this);

//		mMyText = (TextView) findViewById(R.id.get_str);
//		mMyText.setText(getIntent().getStringExtra("myLoad"));
		mLuxiantuichu = (ImageView) findViewById(R.id.luxiantuichu);
		mDriver = (ImageView) findViewById(R.id.driver);
		mBus = (ImageView) findViewById(R.id.bus);
		mPerson = (ImageView) findViewById(R.id.person);
		PlanNode stNode = PlanNode.withLocation(geom);
		PlanNode enNode = PlanNode.withLocation(geomEnd);
		mSearch.drivingSearch((new DrivingRoutePlanOption()).from(
				stNode).to(enNode));
		
		
		mLuxiantuichu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(); 
			}
		});
		mDriver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// 初始化搜索模块，注册事件监听
				route = null;
				// mBtnPre.setVisibility(View.INVISIBLE);
				// mBtnNext.setVisibility(View.INVISIBLE);
				mBaiduMap.clear();
				// 重置浏览节点的路线数据
				// EditText editSt = (EditText) findViewById(R.id.start);
				// EditText editEn = (EditText) findViewById(R.id.end);
				// 处理搜索按钮响应
				if (geom == null) {
					Toast.makeText(NavigationActivity.this, "没有起始坐标",
							Toast.LENGTH_SHORT).show();
					return;
				}
				PlanNode stNode = PlanNode.withLocation(geom);
				PlanNode enNode = PlanNode.withLocation(geomEnd);

				// 实际使用中请对起点终点城市进行正确的设定
				if (v.getId() == R.id.driver) {
					mSearch.drivingSearch((new DrivingRoutePlanOption()).from(
							stNode).to(enNode));
				} else if (v.getId() == R.id.bus) {
					mSearch.transitSearch((new TransitRoutePlanOption())
							.from(stNode).city("北京").to(enNode));
				} else if (v.getId() == R.id.person) {
					mSearch.walkingSearch((new WalkingRoutePlanOption()).from(
							stNode).to(enNode));
				}
			}
		});
		mBus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// 初始化搜索模块，注册事件监听
				route = null;
				// mBtnPre.setVisibility(View.INVISIBLE);
				// mBtnNext.setVisibility(View.INVISIBLE);
				mBaiduMap.clear();
				// 重置浏览节点的路线数据
				// EditText editSt = (EditText) findViewById(R.id.start);
				// EditText editEn = (EditText) findViewById(R.id.end);
				// 处理搜索按钮响应
				if (geom == null) {
					Toast.makeText(NavigationActivity.this, "没有起始坐标",
							Toast.LENGTH_SHORT).show();
					return;
				}
				PlanNode stNode = PlanNode.withLocation(geom);
				PlanNode enNode = PlanNode.withLocation(geomEnd);

				// 实际使用中请对起点终点城市进行正确的设定
				if (v.getId() == R.id.driver) {
					mSearch.drivingSearch((new DrivingRoutePlanOption()).from(
							stNode).to(enNode));
				} else if (v.getId() == R.id.bus) {
					mSearch.transitSearch((new TransitRoutePlanOption())
							.from(stNode).city("北京").to(enNode));
				} else if (v.getId() == R.id.person) {
					mSearch.walkingSearch((new WalkingRoutePlanOption()).from(
							stNode).to(enNode));
				}
			}
		});

		mPerson.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 重置浏览节点的路线数据
				route = null;
				// mBtnPre.setVisibility(View.INVISIBLE);
				// mBtnNext.setVisibility(View.INVISIBLE);
				mBaiduMap.clear();
				// 处理搜索按钮响应
				// EditText editSt = (EditText) findViewById(R.id.start);
				// EditText editEn = (EditText) findViewById(R.id.end);
				// 设置起终点信息，对于tranist search 来说，城市名无意义
				// PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京",
				// "龙泽");
				// PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京",
				// "西单");
				if (geom == null) {
					Toast.makeText(NavigationActivity.this, "没有起始坐标",
							Toast.LENGTH_SHORT).show();
					return;
				}
				PlanNode stNode = PlanNode.withLocation(geom);
				PlanNode enNode = PlanNode.withLocation(geomEnd);

				// 实际使用中请对起点终点城市进行正确的设定
				if (v.getId() == R.id.driver) {
					mSearch.drivingSearch((new DrivingRoutePlanOption()).from(
							stNode).to(enNode));
				} else if (v.getId() == R.id.bus) {
					mSearch.transitSearch((new TransitRoutePlanOption())
							.from(stNode).city("北京").to(enNode));
				} else if (v.getId() == R.id.person) {
					mSearch.walkingSearch((new WalkingRoutePlanOption()).from(
							stNode).to(enNode));
				}
			}
		});

		mLocationClient = new LocationClient(getApplicationContext()); // 婢圭増妲慙ocationClient缁拷
		mLocationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				if (location != null) {
					SharedPreferences sharedPreferences = getSharedPreferences("test", 
							Activity.MODE_PRIVATE); 
//							String latitude111 = sharedPreferences.getString("latitude111", ""); 
//							String longitude111 = sharedPreferences.getString("longitude111", ""); 
//							 double latitude112=Double.parseDouble(latitude111);
//							 double longitude112=Double.parseDouble(longitude111);
//							 double latitude112=24.931052;
//							 double longitude112=118.092066;							 
					mLocationClient.stop();
					LatLng center = new LatLng(location.getLatitude(), location
							.getLongitude());
					geom = center;
//					geomEnd = new LatLng(latitude112, longitude112);
					geomEnd = new LatLng(26.086641,119.243506);
//					longitude111 latitude111
//					geomEnd = new LatLng(latitude112,longitude112);
 
					mBaiduMap.setMapStatus(MapStatusUpdateFactory
							.newLatLng(center));
					mBaiduMap.setMapStatus(MapStatusUpdateFactory
							.newLatLng(geomEnd));
					// 添加图标
					addCustMarkers(center);
					addCustMarkers1(geomEnd);
					// 逆地址解析
					GeoCoder mSearch = GeoCoder.newInstance();
					mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
						@Override
						public void onGetReverseGeoCodeResult(
								ReverseGeoCodeResult geomEnd) {
							if (geomEnd != null) {
//								Toast.makeText(NavigationActivity.this,
//										"好友当前位置是(" + geomEnd.getAddress() + ")",
//										Toast.LENGTH_SHORT).show();
							}
						}

						@Override
						public void onGetGeoCodeResult(GeoCodeResult arg0) {

						}
					});
					mSearch.reverseGeoCode(new ReverseGeoCodeOption()
							.location(geomEnd));
//					Toast.makeText(
//							NavigationActivity.this,
//							"您的当前位置是：(" + geomEnd.latitude + ","
//									+ geomEnd.longitude + ")",
//							Toast.LENGTH_SHORT).show();
				}
			}
		});
		initLocation();

//		mMyPosition = (ImageView) findViewById(R.id.my_position);
//		mMyPosition.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				// 启动定位服务
//				mLocationClient.start();
//			}
//		});
		
		 new Handler().postDelayed(new Runnable() {
			 public void run() {
			
			
			 initLocation();
			 mLocationClient.start();
			 
			 }
			 }, 0);
	}

	/**
	 * 添加客户点标注覆盖物
	 */
	public void addCustMarkers(LatLng point) {
		// 构建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.navigation_icon_st);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap).title("厦门");
		// 在地图上添加Marker，并显示
		mBaiduMap.addOverlay(option);
	}
	public void addCustMarkers1(LatLng point) {
		// 构建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.navigation_icon_en);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap).title("厦门");
		// 在地图上添加Marker，并显示
		mBaiduMap.addOverlay(option);
	}


	/**
	 * 
	 * 
	 * @date 日期: 2015年8月9日 下午1:16:49
	 * @author 作者： ZhengChenHui
	 * @description 描述:初始化百度定位参数
	 */
	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
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

	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(NavigationActivity.this,"抱歉,未找到结果", Toast.LENGTH_SHORT)
					.show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {

			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			// mBtnPre.setVisibility(View.VISIBLE);
			// mBtnNext.setVisibility(View.VISIBLE);
			route = result.getRouteLines().get(0);
			WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
			mBaiduMap.setOnMarkerClickListener(overlay);
			routeOverlay = overlay;
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}

	}

	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(NavigationActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			// mBtnPre.setVisibility(View.VISIBLE);
			// mBtnNext.setVisibility(View.VISIBLE);
			route = result.getRouteLines().get(0);
			TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaiduMap);
			mBaiduMap.setOnMarkerClickListener(overlay);
			routeOverlay = overlay;
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}
	}

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(NavigationActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			// mBtnPre.setVisibility(View.VISIBLE);
			// mBtnNext.setVisibility(View.VISIBLE);
			route = result.getRouteLines().get(0);
			DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
			routeOverlay = overlay;
			mBaiduMap.setOnMarkerClickListener(overlay);
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}
	}

	// 定制RouteOverly
	private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

		public MyDrivingRouteOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public BitmapDescriptor getStartMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory.fromResource(R.drawable.navigation_icon_st);
			}
			return null;
		}

		@Override
		public BitmapDescriptor getTerminalMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory.fromResource(R.drawable.navigation_icon_en);
			}
			return null;
		}
	}

	private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

		public MyWalkingRouteOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public BitmapDescriptor getStartMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory.fromResource(R.drawable.navigation_icon_st);
			}
			return null;
		}

		@Override
		public BitmapDescriptor getTerminalMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory.fromResource(R.drawable.navigation_icon_en);
			}
			return null;
		}
	}

	private class MyTransitRouteOverlay extends TransitRouteOverlay {

		public MyTransitRouteOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public BitmapDescriptor getStartMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory.fromResource(R.drawable.navigation_icon_st);
			}
			return null;
		}

		@Override
		public BitmapDescriptor getTerminalMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory.fromResource(R.drawable.navigation_icon_en);
			}
			return null;
		}
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public void onMapClick(LatLng arg0) {
//		// TODO Auto-generated method stub
//		geomEnd = arg0;
		// 重置浏览节点的路线数据
//		route = null;
		// mBtnPre.setVisibility(View.INVISIBLE);
		// mBtnNext.setVisibility(View.INVISIBLE);
//		mBaiduMap.clear();
		// 处理搜索按钮响应
		// EditText editSt = (EditText) findViewById(R.id.start);
		// EditText editEn = (EditText) findViewById(R.id.end);
		// 设置起终点信息，对于tranist search 来说，城市名无意义
		// PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "龙泽");
		// PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "西单");
//		if (geom == null) {
//			Toast.makeText(NavigationActivity.this, "没有起始坐标", Toast.LENGTH_SHORT)
//					.show();
//			return;
//		}
//		PlanNode stNode = PlanNode.withLocation(geom);
//		PlanNode enNode = PlanNode.withLocation(geomEnd);
//
//		// 实际使用中请对起点终点城市进行正确的设定
//		mSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode).to(
//				enNode));
//	}

//	@Override
//	public boolean onMapPoiClick(MapPoi arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
