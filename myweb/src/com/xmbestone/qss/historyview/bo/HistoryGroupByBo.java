package com.xmbestone.qss.historyview.bo;

import com.xmbestone.qss.historyview.po.HistoryGroupBy;
import com.xmbestone.qss.util.Page;


public interface HistoryGroupByBo {
	public Page<HistoryGroupBy> findUsers(Page<HistoryGroupBy> page)throws Exception;
}
