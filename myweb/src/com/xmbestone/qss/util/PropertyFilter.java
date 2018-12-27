
package com.xmbestone.qss.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * 涓庡叿浣揙RM瀹炵幇鏃犲叧鐨勫睘鎬ц繃婊ゆ潯浠跺皝瑁呯被, 涓昏璁板綍椤甸潰涓畝鍗曠殑鎼滅储杩囨护鏉′欢.
 * 
 * @author calvin
 */
public class PropertyFilter {

	/** 澶氫釜灞炴�ч棿OR鍏崇郴鐨勫垎闅旂. */
	public static final String OR_SEPARATOR = "_OR_";

	/** 灞炴�ф瘮杈冪被鍨�. */
	public enum MatchType {
		EQ, LIKE, LT, GT, LE, GE;
	}

	/** 灞炴�ф暟鎹被鍨�. */
	public enum PropertyType {
		S(String.class), C(Character.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class);

		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	private MatchType matchType = null;
	private Object matchValue = null;

	private Class<?> propertyClass = null;
	private String[] propertyNames = null;

	public PropertyFilter() {
	}

	/**
	 * @param filterName 姣旇緝灞炴�у瓧绗︿覆,鍚緟姣旇緝鐨勬瘮杈冪被鍨嬨�佸睘鎬у�肩被鍨嬪強灞炴�у垪琛�. 
	 *                   eg. LIKES_NAME_OR_LOGIN_NAME
	 * @param value 寰呮瘮杈冪殑鍊�.
	 */
	public PropertyFilter(final String filterName, final String value) {

		String firstPart = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
		String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());

		try {
			matchType = Enum.valueOf(MatchType.class, matchTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter鍚嶇О" + filterName + "娌℃湁鎸夎鍒欑紪鍐�,鏃犳硶寰楀埌灞炴�ф瘮杈冪被鍨�.", e);
		}

		try {
			propertyClass = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter鍚嶇О" + filterName + "娌℃湁鎸夎鍒欑紪鍐�,鏃犳硶寰楀埌灞炴�у�肩被鍨�.", e);
		}

		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter鍚嶇О" + filterName + "娌℃湁鎸夎鍒欑紪鍐�,鏃犳硶寰楀埌灞炴�у悕绉�.");
		propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, PropertyFilter.OR_SEPARATOR);

		this.matchValue = ConvertUtils.convertStringToObject(value, propertyClass);
	}

	/**
	 * 浠嶩ttpRequest涓垱寤篜ropertyFilter鍒楄〃, 榛樿Filter灞炴�у悕鍓嶇紑涓篺ilter.
	 * 
	 * @see #buildFromHttpRequest(HttpServletRequest, String)
	 */
	public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request) {
		return buildFromHttpRequest(request, "filter");
	}

	/**
	 * 浠嶩ttpRequest涓垱寤篜ropertyFilter鍒楄〃
	 * PropertyFilter鍛藉悕瑙勫垯涓篎ilter灞炴�у墠缂�_姣旇緝绫诲瀷灞炴�х被鍨媉灞炴�у悕.
	 * 
	 * eg.
	 * filter_EQS_name
	 * filter_LIKES_name_OR_email
	 */
	public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request, final String filterPrefix) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

		//浠巖equest涓幏鍙栧惈灞炴�у墠缂�鍚嶇殑鍙傛暟,鏋勯�犲幓闄ゅ墠缂�鍚嶅悗鐨勫弬鏁癕ap.
		Map<String, Object> filterParamMap = ServletUtils.getParametersStartingWith(request, filterPrefix + "_");

		//鍒嗘瀽鍙傛暟Map,鏋勯�燩ropertyFilter鍒楄〃
		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = (String) entry.getValue();
			//濡傛灉value鍊间负绌�,鍒欏拷鐣ユfilter.
			if (StringUtils.isNotBlank(value)) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}

		return filterList;
	}

	/**
	 * 鑾峰彇姣旇緝鍊肩殑绫诲瀷.
	 */
	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	/**
	 * 鑾峰彇姣旇緝鏂瑰紡.
	 */
	public MatchType getMatchType() {
		return matchType;
	}

	/**
	 * 鑾峰彇姣旇緝鍊�.
	 */
	public Object getMatchValue() {
		return matchValue;
	}

	/**
	 * 鑾峰彇姣旇緝灞炴�у悕绉板垪琛�.
	 */
	public String[] getPropertyNames() {
		return propertyNames;
	}

	/**
	 * 鑾峰彇鍞竴鐨勬瘮杈冨睘鎬у悕绉�.
	 */
	public String getPropertyName() {
		Assert.isTrue(propertyNames.length == 1, "There are not only one property in this filter.");
		return propertyNames[0];
	}

	/**
	 * 鏄惁姣旇緝澶氫釜灞炴��.
	 */
	public boolean hasMultiProperties() {
		return (propertyNames.length > 1);
	}
}
