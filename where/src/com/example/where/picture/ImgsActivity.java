package com.example.where.picture;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.where.R;
import com.example.where.picture.ImgsAdapter.OnItemClickClass;

public class ImgsActivity extends Activity {

	private Bundle bundle;
	private FileTraversal fileTraversal;
	private GridView imgGridView;
	private ImgsAdapter imgsAdapter;
	private LinearLayout select_layout;
	private Util util;
	private RelativeLayout relativeLayout2;
	private HashMap<Integer, ImageView> hashImage;
	private ArrayList<String> filelist;
	private TextView mTitletv;

	// private ImageView imgBack;
	private TextView txtSure;
	private boolean single;

	// private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.photogrally);
		single = getIntent().getBooleanExtra("single", false);
		imgGridView = (GridView) findViewById(R.id.gridView1);
		bundle = getIntent().getExtras();
		select_layout = (LinearLayout) findViewById(R.id.selected_image_layout);
		fileTraversal = bundle.getParcelable("data");
		imgsAdapter = new ImgsAdapter(this, fileTraversal.filecontent,
				onItemClickClass, single);
		imgGridView.setAdapter(imgsAdapter);
		relativeLayout2 = (RelativeLayout) findViewById(R.id.ll_above);
		initTitle();
		txtSure = (TextView) findViewById(R.id.title_right_text);
		txtSure.setVisibility(View.VISIBLE);
		txtSure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// intent.putStringArrayListExtra("files", filelist);
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// intent.putExtras(bundle);
				// startActivity(intent);
				returnResult();
			}
		});

		hashImage = new HashMap<Integer, ImageView>();
		filelist = new ArrayList<String>();
		// imgGridView.setOnItemClickListener(this);
		util = new Util(this);
	}

	/**
	 * 初始化title
	 */
	private void initTitle() {
		mTitletv = (TextView) findViewById(R.id.title_text);
		mTitletv.setText("图片选择");
		ImageView mImgLeft = (ImageView) findViewById(R.id.title_left);
		mImgLeft.setVisibility(View.VISIBLE);
		mImgLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		mImgLeft.setBackgroundResource(R.drawable.back);
	}

	class BottomImgIcon implements OnItemClickListener {

		int index;

		public BottomImgIcon(int index) {
			this.index = index;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

		}
	}

	@SuppressLint("NewApi")
	public ImageView iconImage(String filepath, int index, CheckBox checkBox)
			throws FileNotFoundException {
		LinearLayout.LayoutParams params = new LayoutParams(
				relativeLayout2.getMeasuredHeight() - 10,
				relativeLayout2.getMeasuredHeight() - 10);
		ImageView imageView = new ImageView(this);
		imageView.setLayoutParams(params);
		imageView.setBackgroundResource(R.drawable.imgbg);
		float alpha = 100;
		imageView.setAlpha(alpha);
		util.imgExcute(imageView, imgCallBack, filepath);
		imageView.setOnClickListener(new ImgOnclick(filepath, checkBox));
		return imageView;
	}

	ImgCallBack imgCallBack = new ImgCallBack() {
		@Override
		public void resultImgCall(ImageView imageView, Bitmap bitmap) {
			imageView.setImageBitmap(bitmap);
		}
	};

	class ImgOnclick implements OnClickListener {
		String filepath;
		CheckBox checkBox;

		public ImgOnclick(String filepath, CheckBox checkBox) {
			this.filepath = filepath;
			this.checkBox = checkBox;
		}

		@Override
		public void onClick(View arg0) {
			checkBox.setChecked(false);
			select_layout.removeView(arg0);
			txtSure.setText("已选择(" + select_layout.getChildCount() + "/9)张");
			filelist.remove(filepath);
		}
	}

	ImgsAdapter.OnItemClickClass onItemClickClass = new OnItemClickClass() {
		@Override
		public void OnItemClick(View v, int Position, CheckBox checkBox) {
			String filapath = fileTraversal.filecontent.get(Position);

			if (checkBox.isChecked()) {
				checkBox.setChecked(false);
				select_layout.removeView(hashImage.get(Position));
				filelist.remove(filapath);
				txtSure.setText("已选择(" + select_layout.getChildCount() + "/9)张");
			} else {
				try {
					if (filelist.size() >= 9) {
						Toast.makeText(ImgsActivity.this, "最能只能选择9张",
								Toast.LENGTH_LONG).show();
						return;
					}
					checkBox.setChecked(true);
					Log.i("img", "img choise position->" + Position);
					ImageView imageView = iconImage(filapath, Position,
							checkBox);

					if (imageView != null) {
						hashImage.put(Position, imageView);
						filelist.add(filapath);

						if (single) {
							returnResult();
							return;
						}

						select_layout.addView(imageView);
						txtSure.setText("已选择(" + select_layout.getChildCount()
								+ "/9)张");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public void tobreak(View view) {
		finish();
	}

	private void returnResult() {
		Intent intent = getIntent();
		intent.putStringArrayListExtra("pictureList", filelist);
		ImgFileListActivity.photoAlbumActivity.setResult(1, intent);
		ImgsActivity.this.finish();
		ImgFileListActivity.photoAlbumActivity.finish();
	}

}
