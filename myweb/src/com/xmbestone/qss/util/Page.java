/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Page.java 1183 2010-08-28 08:05:49Z calvinxiu $
 */
package com.xmbestone.qss.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * 涓庡叿浣揙RM瀹炵幇鏃犲叧鐨勫垎椤靛弬鏁板強鏌ヨ缁撴灉灏佽.
 * 
 * 娉ㄦ剰鎵�鏈夊簭鍙蜂粠1寮�濮�.
 * 
 * @param <T> Page涓褰曠殑绫诲瀷.
 * 
 * @author calvin
 */
public class Page<T> {
	//-- 鍏叡鍙橀噺 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	//-- 鍒嗛〉鍙傛暟 --//
	protected int pageNo = 1;
	protected int pageSize = -1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	//-- 杩斿洖缁撴灉 --//
	protected List<T> result = Lists.newArrayList();
	protected long totalCount = -1;

	//-- 鏋勯�犲嚱鏁� --//
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	//-- 鍒嗛〉鍙傛暟璁块棶鍑芥暟 --//
	/**
	 * 鑾峰緱褰撳墠椤电殑椤靛彿,搴忓彿浠�1寮�濮�,榛樿涓�1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 璁剧疆褰撳墠椤电殑椤靛彿,搴忓彿浠�1寮�濮�,浣庝簬1鏃惰嚜鍔ㄨ皟鏁翠负1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 杩斿洖Page瀵硅薄鑷韩鐨剆etPageNo鍑芥暟,鍙敤浜庤繛缁缃��
	 */
	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 鑾峰緱姣忛〉鐨勮褰曟暟閲�, 榛樿涓�-1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 璁剧疆姣忛〉鐨勮褰曟暟閲�.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 杩斿洖Page瀵硅薄鑷韩鐨剆etPageSize鍑芥暟,鍙敤浜庤繛缁缃��
	 */
	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * 鏍规嵁pageNo鍜宲ageSize璁＄畻褰撳墠椤电涓�鏉¤褰曞湪鎬荤粨鏋滈泦涓殑浣嶇疆,搴忓彿浠�1寮�濮�.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * 鑾峰緱鎺掑簭瀛楁,鏃犻粯璁ゅ��. 澶氫釜鎺掑簭瀛楁鏃剁敤','鍒嗛殧.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 璁剧疆鎺掑簭瀛楁,澶氫釜鎺掑簭瀛楁鏃剁敤','鍒嗛殧.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 杩斿洖Page瀵硅薄鑷韩鐨剆etOrderBy鍑芥暟,鍙敤浜庤繛缁缃��
	 */
	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * 鑾峰緱鎺掑簭鏂瑰悜, 鏃犻粯璁ゅ��.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 璁剧疆鎺掑簭鏂瑰紡鍚�.
	 * 
	 * @param order 鍙�夊�间负desc鎴朼sc,澶氫釜鎺掑簭瀛楁鏃剁敤','鍒嗛殧.
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		//妫�鏌rder瀛楃涓茬殑鍚堟硶鍊�
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("鎺掑簭鏂瑰悜" + orderStr + "涓嶆槸鍚堟硶鍊�");
			}
		}

		this.order = lowcaseOrder;
	}

	/**
	 * 杩斿洖Page瀵硅薄鑷韩鐨剆etOrder鍑芥暟,鍙敤浜庤繛缁缃��
	 */
	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 鏄惁宸茶缃帓搴忓瓧娈�,鏃犻粯璁ゅ��.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	/**
	 * 鑾峰緱鏌ヨ瀵硅薄鏃舵槸鍚﹀厛鑷姩鎵цcount鏌ヨ鑾峰彇鎬昏褰曟暟, 榛樿涓篺alse.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 璁剧疆鏌ヨ瀵硅薄鏃舵槸鍚﹁嚜鍔ㄥ厛鎵цcount鏌ヨ鑾峰彇鎬昏褰曟暟.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * 杩斿洖Page瀵硅薄鑷韩鐨剆etAutoCount鍑芥暟,鍙敤浜庤繛缁缃��
	 */
	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	//-- 璁块棶鏌ヨ缁撴灉鍑芥暟 --//

	/**
	 * 鑾峰緱椤靛唴鐨勮褰曞垪琛�.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 璁剧疆椤靛唴鐨勮褰曞垪琛�.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 鑾峰緱鎬昏褰曟暟, 榛樿鍊间负-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 璁剧疆鎬昏褰曟暟.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 鏍规嵁pageSize涓巘otalCount璁＄畻鎬婚〉鏁�, 榛樿鍊间负-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 鏄惁杩樻湁涓嬩竴椤�.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 鍙栧緱涓嬮〉鐨勯〉鍙�, 搴忓彿浠�1寮�濮�.
	 * 褰撳墠椤典负灏鹃〉鏃朵粛杩斿洖灏鹃〉搴忓彿.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 鏄惁杩樻湁涓婁竴椤�.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 鍙栧緱涓婇〉鐨勯〉鍙�, 搴忓彿浠�1寮�濮�.
	 * 褰撳墠椤典负棣栭〉鏃惰繑鍥為椤靛簭鍙�.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}
}
