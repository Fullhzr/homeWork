package com.example.where.history;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.map.ArcOptions;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.DotOptions;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.where.HistoryNote;
import com.example.where.R;

public class HistoryMap extends Activity  {
	public BaiduMap baiduMap = null;
	private BitmapDescriptor bdB;
	private Marker mMarkerA;
	private Marker mMarkerB;
	private ImageView mhistorymsptuichu;
	
	private List<Marker> listM  =new ArrayList<Marker>();
	// 地图相关
	MapView mMapView;
	BaiduMap mBaiduMap;
	// UI相关
	Button resetBtn;
	Button clearBtn;
	private LatLng geom1 = null;
	private LatLng geomEnd1 = null;
	private List<HistoryNote> list = new ArrayList<HistoryNote>();;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_main_map);
		mMapView = (MapView) findViewById(R.id.wheremap);
		mhistorymsptuichu = (ImageView) findViewById(R.id.ic_lishiguji_map_back);
		mBaiduMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
		mBaiduMap.setMapStatus(msu);
		initOverlay();
		mhistorymsptuichu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(); 
			}
		});
//		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
//			public boolean onMarkerClick(final Marker marker) {
//				Button button = new Button(getApplicationContext());
//				button.setBackgroundResource(R.drawable.popup);
//				OnInfoWindowClickListener listener = null;
//				if (marker == mMarkerA || marker == mMarkerD) {
//					button.setText("更改位置");
//					listener = new OnInfoWindowClickListener() {
//						public void onInfoWindowClick() {
//							LatLng ll = marker.getPosition();
//							LatLng llNew = new LatLng(ll.latitude + 0.005,
//									ll.longitude + 0.005);
//							marker.setPosition(llNew);
//							mBaiduMap.hideInfoWindow();
//						}
//					};
//					LatLng ll = marker.getPosition();
//					mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, listener);
//					mBaiduMap.showInfoWindow(mInfoWindow);
//				} else if (marker == mMarkerB) {
//					button.setText("更改图标");
//					button.setOnClickListener(new OnClickListener() {
//						public void onClick(View v) {
//							marker.setIcon(bd);
//							mBaiduMap.hideInfoWindow();
//						}
//					});
//					LatLng ll = marker.getPosition();
//					mInfoWindow = new InfoWindow(button, ll, -47);
//					mBaiduMap.showInfoWindow(mInfoWindow);
//				} else if (marker == mMarkerC) {
//					button.setText("删除");
//					button.setOnClickListener(new OnClickListener() {
//						public void onClick(View v) {
//							marker.remove();
//							mBaiduMap.hideInfoWindow();
//						}
//					});
//					LatLng ll = marker.getPosition();
//					mInfoWindow = new InfoWindow(button, ll, -47);
//					mBaiduMap.showInfoWindow(mInfoWindow);
//				}
//				return true;
//			}
//		});
	}
	/**
	 * 添加点、线
	 */
	public void initOverlay() {
		Intent intent=getIntent();
		String title=intent.getStringExtra("title"); 
		int i= Integer.parseInt(title);
		switch (i){ 
		case 1 : 		// add marker overlay
			// 添加折线
			LatLng p1 = new LatLng(26.087631,119.243499);
			LatLng p2 = new LatLng(26.087619,119.243269);
			LatLng p3 = new LatLng(26.086978,119.24344);
			LatLng p4 = new LatLng(26.086905,119.242951);
			LatLng p5 = new LatLng(26.086601,119.241356);
			LatLng p6 = new LatLng(26.08525,119.241639);
			LatLng p7 = new LatLng(26.085165,119.241473);
			LatLng p8 = new LatLng(26.084743,119.241572);
			LatLng p9 = new LatLng(26.084719,119.24141);
			List<LatLng> points = new ArrayList<LatLng>();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			points.add(p4);
			points.add(p5);
			points.add(p6);
			points.add(p7);
			points.add(p8);
			points.add(p9);
			OverlayOptions ooPolyline = new PolylineOptions().width(10)
					.color(0xAAFF0000).points(points);
			mBaiduMap.addOverlay(ooPolyline);
			// 添加图标
			addCustMarkers(p1);
			addCustMarkers1(p9);
			LatLng southwest =p1;
			LatLng northeast =p9;
			LatLngBounds bounds = new LatLngBounds.Builder().include(northeast)
					.include(southwest).build();
			MapStatusUpdate u = MapStatusUpdateFactory
					.newLatLng(bounds.getCenter());
			mBaiduMap.setMapStatus(u);
			break;
		case 2 : 		// add marker overlay
			// 添加折线
			LatLng p11 = new LatLng(26.090601,119.246507);
			LatLng p12 = new LatLng(26.090446,119.245807);
			LatLng p13 = new LatLng(26.090309,119.244908);
			LatLng p14 = new LatLng(26.090004,119.243444);
			LatLng p15 = new LatLng(26.089887,119.242802);
			LatLng p16 = new LatLng(26.090398,119.242681);
			LatLng p17 = new LatLng(26.090917,119.242582);
			LatLng p18 = new LatLng(26.091132,119.242541);
			LatLng p19 = new LatLng(26.091481,119.242474);
			List<LatLng> points1 = new ArrayList<LatLng>();
			points1.add(p11);
			points1.add(p12);
			points1.add(p13);
			points1.add(p14);
			points1.add(p15);
			points1.add(p16);
			points1.add(p17);
			points1.add(p18);
			points1.add(p19);
			OverlayOptions ooPolyline1 = new PolylineOptions().width(10)
					.color(0xAAFF0000).points(points1);
			mBaiduMap.addOverlay(ooPolyline1);
			// 添加图标
			addCustMarkers(p11);
			addCustMarkers1(p19);
			LatLng southwest1 =p11;
			LatLng northeast1 =p19;
			LatLngBounds bounds1 = new LatLngBounds.Builder().include(northeast1)
					.include(southwest1).build();
			MapStatusUpdate u1 = MapStatusUpdateFactory
					.newLatLng(bounds1.getCenter());
			mBaiduMap.setMapStatus(u1);
			break; 
		default : 			// 添加折线
			LatLng p21 = new LatLng(26.087631,119.243499);
			LatLng p22 = new LatLng(26.087619,119.243269);
			LatLng p23 = new LatLng(26.086978,119.24344);
			LatLng p24 = new LatLng(26.086905,119.242951);
			LatLng p25 = new LatLng(26.086601,119.241356);
			LatLng p26 = new LatLng(26.08525,119.241639);
			LatLng p27 = new LatLng(26.085165,119.241473);
			LatLng p28 = new LatLng(26.084743,119.241572);
			LatLng p29 = new LatLng(26.084719,119.24141);
			List<LatLng> points2 = new ArrayList<LatLng>();
			points2.add(p21);
			points2.add(p22);
			points2.add(p23);
			points2.add(p24);
			points2.add(p25);
			points2.add(p26);
			points2.add(p27);
			points2.add(p28);
			points2.add(p29);
			OverlayOptions ooPolyline2 = new PolylineOptions().width(10)
					.color(0xAAFF0000).points(points2);
			mBaiduMap.addOverlay(ooPolyline2);
			// 添加图标
			addCustMarkers(p21);
			addCustMarkers1(p29);
			LatLng southwest2 =p21;
			LatLng northeast2 =p29;
			LatLngBounds bounds2 = new LatLngBounds.Builder().include(northeast2)
					.include(southwest2).build();
			MapStatusUpdate u2 = MapStatusUpdateFactory
					.newLatLng(bounds2.getCenter());
			mBaiduMap.setMapStatus(u2); break; 
		}
		// add marker overlay
		// 添加折线
//		LatLng p1 = new LatLng(26.087631,119.243499);
//		LatLng p2 = new LatLng(26.087619,119.243269);
//		LatLng p3 = new LatLng(26.086978,119.24344);
//		LatLng p4 = new LatLng(26.086905,119.242951);
//		LatLng p5 = new LatLng(26.086601,119.241356);
//		LatLng p6 = new LatLng(26.08525,119.241639);
//		LatLng p7 = new LatLng(26.085165,119.241473);
//		LatLng p8 = new LatLng(26.084743,119.241572);
//		LatLng p9 = new LatLng(26.084719,119.24141);
//		LatLng p6 = new LatLng(24.632977,118.097465);
//		LatLng p7 = new LatLng(24.631975,118.098561);
//		LatLng p8 = new LatLng(24.631047,118.099486);
//		LatLng p9 = new LatLng(24.630514,118.099962);
//		LatLng p10 = new LatLng(24.628231,118.101453);
//		LatLng p11 = new LatLng(24.626009,118.100007);
//		LatLng p12 = new LatLng(24.626872,118.102095);
//		LatLng p13 = new LatLng(24.626744,118.10205);
//		LatLng p14 = new LatLng(24.625973,118.101125);
//		LatLng p15 = new LatLng(24.626794,118.100348);
//		LatLng p16 = new LatLng(24.626814,118.100231); 	
//		List<LatLng> points = new ArrayList<LatLng>();
//		points.add(p1);
//		points.add(p2);
//		points.add(p3);
//		points.add(p4);
//		points.add(p5);
//		points.add(p6);
//		points.add(p7);
//		points.add(p8);
//		points.add(p9);
//		points.add(p10);
//		points.add(p11);
//		points.add(p12);
//		points.add(p13);
//		points.add(p14);
//		points.add(p15);
//		points.add(p16); 	
//		OverlayOptions ooPolyline = new PolylineOptions().width(10)
//				.color(0xAAFF0000).points(points);
//		mBaiduMap.addOverlay(ooPolyline);
//		// 添加图标
//		addCustMarkers(p1);
//		addCustMarkers1(p9);
		
		
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
	 * 清除所有Overlay
	 * 
	 * @param view
	 */
	public void clearOverlay(View view) {
		mBaiduMap.clear();
	}

	/**
	 * 重新添加Overlay
	 * 
	 * @param view
	 */
	public void resetOverlay(View view) {
		clearOverlay(null);
		initOverlay();
	}

	@Override
	protected void onPause() {
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
		mMapView.onDestroy();
		super.onDestroy();
		// 回收 bitmap 资源
//		bdA.recycle();
//		bdB.recycle();
//		bdC.recycle();
//		bdD.recycle();
//		bd.recycle();
//		bdGround.recycle();
	}

}

