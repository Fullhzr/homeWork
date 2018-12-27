/**
 *鍚嶇О锛?
 *
 *浣滆?锛?
 * 
 *涓嬪崍2:34:05
 */
package com.example.where.mymenu;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.example.where.MainMapActivity;
import com.example.where.R;
import com.example.where.SelectPicPopupWindow;
import com.example.where.addperson.AddNewPerson;
import com.example.where.cache.ImageLoader;
import com.example.where.distance.JuliMainActivity;
import com.example.where.history.HistoryMainActivity;
import com.example.where.navigation.NavigationActivity;
import com.example.where.photo.Bimp;
import com.example.where.photo.BitmapCache;
import com.example.where.photo.FileUtils;
import com.example.where.photo.Photograph;
import com.example.where.picture.ImgFileListActivity;
import com.example.where.picture.ImgsActivity;
import com.example.where.util.SetInternetUtil;
import com.example.where.widget.GlobalFinal;
import com.example.where.widget.GlobalStatic;
import com.example.where.widget.HttpService_;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Administrator
 *
 *
 *         涓嬪崍2:34:05
 */
public class MyMenu extends Activity {

	private static final Mode PorterDuff = null;
	private TextView text_userid;
	private TextView refresh;
	private Button accept;
	private Button rejection;
	private String uid = "1";

	private ImageLoader imageLoader;
	private ImageView profile_image;

	private TextView text_mymessage, text_myhsitory, MyAdvice, MyShare,
			text_mysetting,text_myinformation;

	float x = 0, y = 0;
	int xx = 0, yy = 0;

	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.my_menu);
	// // 鏍规嵁Id鑾峰緱鏂囨湰妗?
	// }
	public void getBackClick(View v) {
//		Intent intent = new Intent();
//		intent.setClass(MyMenu.this, MainMapActivity.class);
		
        finish();
	}

	public void onMessageClick(View v) {
		Intent intent = new Intent();
		intent.setClass(MyMenu.this, MyMessage.class);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_menu);
		// bottom_navigation = (Button)
		// this.findViewById(R.id.bottom_navigation);
		// bottom_distance = (Button) this.findViewById(R.id.bottom_distance);
		// bottom_history = (Button) this.findViewById(R.id.bottom_history);
		imageLoader = new ImageLoader(MyMenu.this);
		profile_image = (ImageView) findViewById(R.id.profile_image);
		profile_image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent selectPicIntent = new Intent();
				selectPicIntent.setType("image/*");
				selectPicIntent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(selectPicIntent, 1);
			    // selectPicIntent.setClass(MyMenu.this,
				// ImgFileListActivity.class);
				// selectPicIntent.putExtra("single", true);
				// startActivityForResult(selectPicIntent, 1);
			}
		});
		String imgUrl = GlobalStatic.getImgUrl();
		if(imgUrl!=null&&!imgUrl.equals("")){
			imageLoader.DisplayImage(imgUrl ,profile_image,R.drawable.head_default);
		}
		text_mymessage = (TextView) this.findViewById(R.id.text_mymessage);
		text_myhsitory = (TextView) this.findViewById(R.id.text_myhsitory);
		MyAdvice = (TextView) this.findViewById(R.id.text_myadvice);
		MyShare = (TextView) this.findViewById(R.id.text_myshare);
		text_mysetting = (TextView) this.findViewById(R.id.text_mysetting);
		text_myinformation = (TextView) this.findViewById(R.id.text_myinformation);
		getWindow().setLayout(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		// layout=(LinearLayout)findViewById(R.id.pop_layout);

		// 娣诲姞閫夋嫨绐楀彛鑼冨洿鐩戝惉鍙互浼樺厛鑾峰彇瑙︾偣锛屽嵆涓嶅啀鎵цonTouchEvent()鍑芥暟锛岀偣鍑诲叾浠栧湴鏂规椂鎵цonTouchEvent()鍑芥暟閿?瘉Activity
		// layout.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Toast.makeText(getApplicationContext(), "鎻愮ず锛氱偣鍑荤獥鍙ｅ閮ㄥ叧闂獥鍙ｏ紒",
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		// 娣诲姞鎸夐挳鐩戝惉
		// bottom_navigation.setOnClickListener(this);
		// bottom_distance.setOnClickListener(this);
		// bottom_history.setOnClickListener(this);
		// text_mysetting.setOnTouchListener(this);
		text_myinformation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, MyInformation.class));

			}
		});
		text_mymessage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, MyMessage.class));

			}
		});
		text_myhsitory.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, MyHistory.class));

			}
		});
		MyAdvice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, MyAdvice.class));
				finish();
			}
		});
		MyShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, MyShare.class));

			}
		});
		text_mysetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, MySetting.class));

			}
		});
	}

	// 瀹炵幇onTouchEvent瑙﹀睆鍑芥暟浣嗙偣鍑诲睆骞曟椂閿?瘉鏈珹ctivity
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_myinformation:
			startActivity(new Intent(MyMenu.this, MyMessage.class));
			break;
		case R.id.text_mymessage:
			startActivity(new Intent(MyMenu.this, MyMessage.class));
			break;
		case R.id.text_myhsitory:
			startActivity(new Intent(MyMenu.this, MyHistory.class));
			break;
		case R.id.text_myadvice:
			startActivity(new Intent(MyMenu.this, MyAdvice.class));
			break;
		case R.id.text_myshare:
			startActivity(new Intent(MyMenu.this, MyShare.class));
			break;
		case R.id.text_mysetting:
			startActivity(new Intent(MyMenu.this, MySetting.class));
			break;
		default:
			break;
		}
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == 1) {

//			List<String> pathList = data.getStringArrayListExtra("pictureList");
			Uri uri = data.getData();
			ContentResolver cr = this.getContentResolver();
//			if (pathList != null && pathList.size() > 0) {
				Bimp.bimpClear();
				Photograph.bitmapFromPathToBimpTest(MyMenu.this,
						GlobalStatic.getRealFilePath(MyMenu.this, uri), "", false);
				submitportrait();
//			}


			try {
				int mColor = 0;
				Bitmap bitmap = BitmapFactory.decodeStream(cr
						.openInputStream(uri));
				Bitmap mBitmap = null;

				int width = bitmap.getWidth();

				int height = bitmap.getHeight();
				float roundPx;
				float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
				if (width <= height) {
					roundPx = width / 2;
					top = 0;
					bottom = width;
					left = 0;
					right = width;
					height = width;
					dst_left = 0;
					dst_top = 0;
					dst_right = width;
					dst_bottom = width;
				} else {
					roundPx = height / 2;
					float clip = (width - height) / 2;
					left = clip;
					right = width - clip;
					top = 0;
					bottom = height;
					width = height;
					dst_left = 0;
					dst_top = 0;
					dst_right = height;
					dst_bottom = height;
				}
				Bitmap output = Bitmap.createBitmap(width, height,
						Config.ARGB_8888);
				Canvas canvas = new Canvas(output);
				final int color = 0xff424242;
				final Paint paint = new Paint();
				final Rect src = new Rect((int) left, (int) top, (int) right,
						(int) bottom);
				final Rect dst = new Rect((int) dst_left, (int) dst_top,
						(int) dst_right, (int) dst_bottom);
				final RectF rectF = new RectF(dst);
				paint.setAntiAlias(true);
				canvas.drawARGB(0, 0, 0, 0);
				paint.setColor(color);
				canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

				paint.setXfermode(new PorterDuffXfermode(
						android.graphics.PorterDuff.Mode.SRC_IN));
				canvas.drawBitmap(bitmap, src, dst, paint);

				profile_image.setImageBitmap(output);

			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
				// mTextView.setText("no");
			}

		}

		super.onActivityResult(requestCode, resultCode, data);

	}


// @Override
// protected void onActivityResult(int requestCode, int resultCode, Intent data)
// {
// super.onActivityResult(requestCode, resultCode, data);
// switch (requestCode) {
// case 1:
// if (resultCode == 1) {
//
// List<String> pathList = data
// .getStringArrayListExtra("pictureList");
// if (pathList != null && pathList.size() > 0) {
// Bimp.bimpClear();
// Photograph.bitmapFromPathToBimpTest( MyMenu.this,
// pathList.get(0), "", false);
// submitportrait();
//
// }
// }
// break;
// }
// }
//

// 鍥剧墖鎻愪氦绾跨▼
 private Thread thead;
 private ProgressDialog progressDialog;
 protected void submitportrait() {
 try {
 if (SetInternetUtil.isNetworkConnected(MyMenu.this)) {// 鍒ゆ柇缃戠粶鏄惁鍙敤锛屽苟璁剧疆缃戠粶
 progressDialog = new ProgressDialog(MyMenu.this);
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
 Toast.makeText(MyMenu.this, "淇濆瓨澶辫触", Toast.LENGTH_SHORT).show();
 break;
 case 2:

		GlobalStatic.setImgUrl(msg.obj.toString());
//	 imageLoader.DisplayImage(msg.obj.toString(),profile_image,R.drawable.head_default);
 break;
 }
 }
 };

 private String submitData() {
 Map<String, String> map = new HashMap<String, String>();
 map.put("flag", "changePortrait");
 map.put("personId", "1");
 String str = FileUtils.uploadFile(MyMenu.this,
 GlobalFinal.path.concat("ImageUpServlet.do"), map);
 String res = "";
 if (str != null && !str.equals(""))
 res = str.split(",")[1];
 return res;
 }


}

