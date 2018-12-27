package com.xmbestone.qss.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * 
 * @description: 异步交互时返回前台的数据格式工具类
 * @date: 2012-11-13 上午11:40:35
 * @author: wangwei
 */
public class ResponseUtil {

	private static final Logger LOGGER = Logger.getLogger(ResponseUtil.class);

	/**
	 * 构造函数
	 */
	public ResponseUtil() {

	}

	/**
	 * 
	 * @param message 要返回的json字符串
	 * @return String
	 * @throws IOException
	 * @description: 返回json格式的字符串
	 * @date: 2012-11-8 下午06:20:11
	 * @author：wangwei
	 */
	public static String returnJson(String message){
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/json; charset=utf-8");
		res.setCharacterEncoding("utf-8");
		try {
			res.getWriter().print(message);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param message 要返回的字符串
	 * @return String
	 * @description: 返回html格式的字符串
	 * @date: 2012-11-13 上午11:38:10
	 * @author：wangwei
	 */
	public static String returnHtml(String message){
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html; charset=utf-8");
		res.setCharacterEncoding("utf-8");
		try {
			res.getWriter().print(message);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
}
