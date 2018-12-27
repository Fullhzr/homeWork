/**
 *名称：
 *
 *作者：
 * 
 *上午9:51:46
 */
package com.example.where.util;

import com.example.where.widget.CrashHandler;

import android.app.Application;

/**
 * @author Administrator
 *
 *
 *上午9:51:46
 */
public class TLBApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}
}
