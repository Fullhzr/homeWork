package com.xmbestone.qss.historyview.bo.impl;

import com.xmbestone.qss.historyview.bo.HistoryGroupByBo;
import com.xmbestone.qss.historyview.dao.HistoryGroupByDao;
import com.xmbestone.qss.historyview.po.HistoryGroupBy;
import com.xmbestone.qss.util.Page;


public class HistoryGroupbyBoImpl implements HistoryGroupByBo {

	private HistoryGroupByDao historyGroupByDao;

	public HistoryGroupByDao getHistoryGroupByDao() {
		return historyGroupByDao;
	}

	public void setHistoryGroupByDao(HistoryGroupByDao historyGroupByDao) {
		this.historyGroupByDao = historyGroupByDao;
	}

	@Override
	public Page<HistoryGroupBy> findUsers(Page<HistoryGroupBy> page)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from HistoryGroupBy s";
		return historyGroupByDao.findPage(page, hql);
	}
}
