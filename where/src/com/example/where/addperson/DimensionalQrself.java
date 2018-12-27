package com.example.where.addperson;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Userid;
import com.example.where.R;
import com.example.where.zxing.encoding.EncodingHandler;
import com.google.zxing.WriterException;

public class DimensionalQrself extends Activity {
	
	private EditText qrStrEditText;
	private ImageView qrImgImageView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dimensional_qrself);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        dimensionaqrse();
     
//        Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
//        generateQRCodeButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				try {
//					String contentString = qrStrEditText.getText().toString();
//					if (!contentString.equals("")) {
//						
//						Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
//						qrImgImageView.setImageBitmap(qrCodeBitmap);
//					}else {
//						Toast.makeText(DimensionalQrself.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
//					}
//					
//				} catch (WriterException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
        
      
    }
    public void getBackClick(View v){
		Intent intent=new Intent();
		intent.setClass(DimensionalQrself.this, AddPerson.class);
		startActivity(intent);
	}
    public void dimensionaqrse(){    
//      qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
		Userid userid1= new Userid(); 
		String userid = userid1.getUserid();
    	String contentString = userid;
		try {	
			Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
			qrImgImageView.setImageBitmap(qrCodeBitmap);
		}catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
      
	
}