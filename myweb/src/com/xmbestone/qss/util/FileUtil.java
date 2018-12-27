package com.xmbestone.qss.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;

/**
 * 
 * @description: 文件处理工具类
 * @date: 2012-11-5 下午05:16:53
 * @author: wangwei
 */
public class FileUtil {

	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

	/**
	 * @param path 文件夹完整绝对路径
	 * @return true or false
	 * @description: 删除指定文件夹下所有文件
	 * @date: 2013-1-15 下午02:33:03
	 * @author： wangwei
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);//再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @param folderPath 删除文件夹路径
	 * @description: 删除文件夹及其下面所有内容
	 * @date: 2013-1-15 下午02:54:03
	 * @author： wangwei
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); //删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); //删除空文件夹
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
	}
	/**
	 * 
	 * @param destDirName 文件绝对路径
	 * @return boolean
	 * @description: 创建文件
	 * @date: 2012-11-5 下午05:17:03
	 * @author：wangwei
	 */
	public static void createDir(String destDirName) {
		File dir = new File(destDirName);
		if (!dir.exists())
		dir.mkdirs();
	}
}
