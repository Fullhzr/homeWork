package com.xmbestone.qss.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;

public class ConvertUtils {

	static {
		registerDateConverter();
	}

	/**
	 * 鎻愬彇闆嗗悎涓殑瀵硅薄鐨勫睘鎬�(閫氳繃getter鍑芥暟), 缁勫悎鎴怢ist.
	 * 
	 * @param collection 鏉ユ簮闆嗗悎.
	 * @param propertyName 瑕佹彁鍙栫殑灞炴�у悕.
	 */
	@SuppressWarnings("unchecked")
	public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 鎻愬彇闆嗗悎涓殑瀵硅薄鐨勫睘鎬�(閫氳繃getter鍑芥暟), 缁勫悎鎴愮敱鍒嗗壊绗﹀垎闅旂殑瀛楃涓�.
	 * 
	 * @param collection 鏉ユ簮闆嗗悎.
	 * @param propertyName 瑕佹彁鍙栫殑灞炴�у悕.
	 * @param separator 鍒嗛殧绗�.
	 */
	@SuppressWarnings("unchecked")
	public static String convertElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 杞崲瀛楃涓插埌鐩稿簲绫诲瀷.
	 * 
	 * @param value 寰呰浆鎹㈢殑瀛楃涓�.
	 * @param toType 杞崲鐩爣绫诲瀷.
	 */
	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return org.apache.commons.beanutils.ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 瀹氫箟鏃ユ湡Converter鐨勬牸寮�: yyyy-MM-dd 鎴� yyyy-MM-dd HH:mm:ss
	 */
	private static void registerDateConverter() {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		org.apache.commons.beanutils.ConvertUtils.register(dc, Date.class);
	}
}
