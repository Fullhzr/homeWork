package com.example.where.history;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.example.where.HistoryNote;
import com.example.where.R;

public class HistoryMainMapActivity extends Activity  {
	public BaiduMap baiduMap = null;

	private BitmapDescriptor bdB;
	private Marker mMarkerA;
	private Marker mMarkerB;
	
	private List<Marker> listM  =new ArrayList<Marker>();
	// 地图相关
	MapView mMapView;
	BaiduMap mBaiduMap;
	// UI相关
	Button resetBtn;
	Button clearBtn;
	private List<HistoryNote> list = new ArrayList<HistoryNote>();;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_main_map);
		// 初始化地图
		mMapView = (MapView) findViewById(R.id.wheremap);
		mBaiduMap = mMapView.getMap();
		// UI初始化
//		clearBtn = (Button) findViewById(R.id.button1);
//		resetBtn = (Button) findViewById(R.id.button2);

		OnClickListener clearListener = new OnClickListener() {
			public void onClick(View v) {
				clearClick();
			}
		};
		OnClickListener restListener = new OnClickListener() {
			public void onClick(View v) {
				resetClick();
			}
		};

		clearBtn.setOnClickListener(clearListener);
		resetBtn.setOnClickListener(restListener);

		// 界面加载时添加绘制图层
//		addCustomElementsDemo();
	  String title = getIntent().getStringExtra("title");
	  Toast.makeText(HistoryMainMapActivity.this, "position:" + title, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 添加点、线
	 */
//	public void addCustomElementsDemo() { 
//
//		Intent intent=getIntent();
//		String title=intent.getStringExtra("title");
////		String title = getIntent().getStringExtra("title");
//		for(int i=0;i<PointList.list.size();i++){
//			if(title.equals(PointList.list.get(i))){
//				list.add(PointList.list.get(i));
//			}
//		}
//		List<LatLng> points = new ArrayList<LatLng>();
//		for(HistoryNote historyNote:list){
//			if (bdB != null) {
//				LatLng llA = new LatLng(Double.valueOf(historyNote.getLatitude()), Double.valueOf(historyNote.getLongtitude()));
//				OverlayOptions ooA = new MarkerOptions().position(llA).icon(bdB)
//						.zIndex(9);
//				points.add(llA);
//				mMarkerA = (Marker) (baiduMap.addOverlay(ooA));
//				listM.add(mMarkerA);
//				baiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
//					@Override
//					public boolean onMarkerClick(Marker latlng) {
//						for(int i=0;i<listM.size();i++){
//							if(listM.get(i)==latlng){
//								break;
//							}
//							
//						}
//						OnInfoWindowClickListener listener = null;
//						LatLng position = latlng.getPosition();
//						if (latlng == mMarkerA||latlng == mMarkerB) {
//							Log.i("TAG", "点击事件" + latlng);
//							//
//							// 跳转到对监控者的操作 导航 历史 距离感应
//							startActivity(new Intent(HistoryMap.this,
//									SelectPicPopupWindow.class));
//							listener = new OnInfoWindowClickListener() {
//								public void onInfoWindowClick() {
//									baiduMap.hideInfoWindow();
//								}
//							};
//						}
//						return false;
//					}
//				});
//			}
//		}
//		OverlayOptions ooPolyline = new PolylineOptions().width(10)
//				.color(0xAAFF0000).points(points);
//		mBaiduMap.addOverlay(ooPolyline);
//	}

	

	public void resetClick() {
		// 添加绘制元素
//		addCustomElementsDemo();
	}

	public void clearClick() {
		// 清除所有图层
		mMapView.getMap().clear();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		super.onDestroy();
	}

}
