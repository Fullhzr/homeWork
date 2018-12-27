package com.example.where.widget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;

public class HttpService_ {
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public String loginPostTimeData(String path, Map<String, String> map,int conTime) {
		String res = "error";
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());

			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
					.build());
		}
		OutputStream outputStream = null;			
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(20000);
//			臻鸿 setConnectTimeout 修改反应时间为20000s 修改时间 2015-9-12
			connection.setReadTimeout(conTime);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			StringBuffer buffer = new StringBuffer();
			if (map != null && !map.isEmpty()) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if(entry.getValue()!=null){
						buffer.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue(), "utf-8"))
						.append("&");
					}else{
						buffer.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode("error", "utf-8"))
						.append("&");
					}
				}
				buffer.deleteCharAt(buffer.length() - 1);
			}
			byte[] data = buffer.toString().getBytes();
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",String.valueOf(data.length));
			outputStream = connection.getOutputStream();
			outputStream.write(data);
			if (connection.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String s = null;
				StringBuffer sb = new StringBuffer();
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				res = sb.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(outputStream!=null){
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	return res;
}
 
}
