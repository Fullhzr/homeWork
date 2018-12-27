package com.example.where.photo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class FileUtils {

	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/where/";
	public static String path = Environment.getExternalStorageDirectory()
			.getPath() + "photograph/where/";
	/*
	 * public static String SDPATH = Environment.getExternalStorageDirectory() +
	 * File.separator + Environment.DIRECTORY_DCIM + "/Photo_LJ/";
	 * 
	 * public static String path = "/sdcard/photograph/where/";
	 */

	private static final String TAG = "uploadFile"; // 为了上传图片而设定的变量
	private static final int TIME_OUT = 30 * 1000; // 超时时间 ，为了上传图片而设定的变量
	private static final String CHARSET = "utf-8"; // 设置编码 ，为了上传图片而设定的变量

	/**
	 * 将拍下来的照片存放在SD卡中
	 * 
	 * @param data
	 * @throws IOException
	 */
	public static void saveToSDCard(byte[] data, String fileName)
			throws IOException {
		if (!isFileExist("")) {
			createSDDir("");
		}
		File f = new File(SDPATH, fileName + ".jpg");
		if (f.exists()) {
			f.delete();
		}
		FileOutputStream out = new FileOutputStream(f);
		out.write(data); // 写入sd卡中
		out.close(); // 关闭输出流
	}

	public static void saveBitmap(Bitmap bm, String picName) {
		try {
			if (!isFileExist("")) {
				createSDDir("");
			}
			File f = new File(SDPATH, picName + ".jpg");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static void delFile(String fileName) {
		File file = new File(SDPATH + fileName);
		if (file.isFile()) {
			file.delete();
		}
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete();
			else if (file.isDirectory())
				deleteDir();
		}
		dir.delete();
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	/* <-------------------------以上是拍照使用的代码--------------------------------> */
	/* <-------------------------以下是上传图片使用的代码--------------------------------> */
	/**
	 * 遍历图片缩略图缓存列表
	 * 
	 * @param mContext
	 *            父亲Activity
	 * @param RequestURL
	 *            请求的url
	 * @return 返回响应的内容
	 */
	public static String uploadFile(Context mContext, String RequestURL,
			Map<String, String> map) {
		String str = "";
		if (Bimp.tempSelectBitmap.size() > 0) {
			int datasize = Bimp.tempSelectBitmap.size();
			for (int i = 0; i < datasize; i++) {
				File f = new File(Bimp.tempSelectBitmap.get(i).getImagePath());
				str = uploadFileReal(f, RequestURL, map);
				int res = Integer.valueOf(str.split(",")[0]);
				if (res != 200) {
					str = "";
				}
			}
		} else {
		}
		return str;
	}

	/**
	 * 上传音频文件件
	 * 
	 * @param mContext
	 *            父亲Activity
	 * @param RequestURL
	 *            请求的url
	 * @return 返回响应的内容
	 */
	public static void uploadFileYP(Context mContext, String RequestURL,
			Map<String, String> map, String ypurl) {
		String[] arry = ypurl.split(";");
		if (arry.length > 0) {
			for (int i = 0; i < arry.length; i++) {
				File f = new File(arry[i]);
				String str = uploadFileReal(f, RequestURL, map);
				int res = Integer.valueOf(str.split(",")[0]);
				if (res != 200) {
					Toast.makeText(mContext, "图片上传错误", Toast.LENGTH_SHORT)
							.show();
					return;
				}
			}
		} else {
			Toast.makeText(mContext, "没有选择图片", Toast.LENGTH_SHORT).show();
			return;
		}
	}

	/**
	 * 上传文件到服务器
	 * 
	 * @param file
	 *            需要上传的文件
	 * @param RequestURL
	 *            请求的url
	 * @return 返回响应的内容
	 */
	public static String uploadFileReal(File file, String RequestURL,
			Map<String, String> map) {

		int res = 0;
		String imageUrl = "";
		String result = null;
		String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data"; // 内容类型
		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(30*1000);
			conn.setConnectTimeout(15*1000);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
//			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
					+ BOUNDARY);

			StringBuilder sbu = new StringBuilder();
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			if (map != null) {
				// 上传的表单参数部分，格式请参考文章
				for (Map.Entry<String, String> entry : map.entrySet()) {
					sbu.append("--");
					sbu.append(BOUNDARY);
					sbu.append("\r\n");
					sbu.append("Content-Disposition: form-data; name=\""
							+ entry.getKey() + "\"\r\n\r\n");
					sbu.append(entry.getValue());
					sbu.append("\r\n");
				}

				sbu.append("--");
				sbu.append(BOUNDARY);
				sbu.append("\r\n");
				dos.write(sbu.toString().getBytes());
			}

			if (file != null) { // 当文件不为空时执行上传
				// DataOutputStream dos = new
				// DataOutputStream(conn.getOutputStream());
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				/**
				 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后缀名
				 */

				sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ file.getName() + "\"" + LINE_END);
				sb.append("Content-Type: application/octet-stream; charset="
						+ CHARSET + LINE_END);
				sb.append(LINE_END);
				dos.write(sb.toString().getBytes());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
						.getBytes();
				dos.write(end_data);
				dos.flush();
				/**
				 * 获取响应码 200=成功 当响应成功，获取响应的流
				 */
				res = conn.getResponseCode();

				BufferedReader br = null;
				br = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				imageUrl = br.readLine();// 0成功 1失败
				Log.e(TAG, "imageUrl:" + imageUrl);
				Log.e(TAG, "response code:" + res);
				if (res == 200) {
					Log.e(TAG, "request success");
					InputStream input = conn.getInputStream();
					StringBuffer sb1 = new StringBuffer();
					int ss;
					while ((ss = input.read()) != -1) {
						sb1.append((char) ss);
					}
					result = sb1.toString();
					Log.e(TAG, "result : " + result);
				} else {
					Log.e(TAG, "request error");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res + "," + imageUrl;

	}

}
