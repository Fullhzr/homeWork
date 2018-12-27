package com.example.where.util;

import android.content.Context;
import android.widget.Toast;

public class SetInternetUtil {
	// 声明网络服务类对象
	private static InternetService internetService;

	public static boolean isNetworkConnected(final Context context) {
		if (context == null) {
			return false;
		}
		internetService = new InternetService(context); // 初始化网络服务实例
		if (internetService.isAirplaneModeOn()) {
			Toast.makeText(context, "温馨提示:您正处于飞行模式！", Toast.LENGTH_LONG)
					.show();
			return false;
		} else {
			if (!internetService.isNetworkConnected()) {
				Toast.makeText(context, "温馨提示:您当前的网络不可用！", Toast.LENGTH_LONG)
						.show();
				return false;
			} else {
				if (!internetService.isWifiEnabled()) {
//					Toast.makeText(context, "温馨提示:您正在使用移动数据！",
//							Toast.LENGTH_LONG).show();
				}
			}// 判断网络是否连接可用结束
		}
		return true;
	}

	public static void getAllWifiList(Context context) {
		internetService = new InternetService(context); // 初始化网络服务实例
		if (internetService.getAllWifiList() != null
				&& !internetService.isWifiEnabled()) {
			Toast.makeText(context, "温馨提示:附近有wifi", Toast.LENGTH_LONG).show();
		}
	}

}
