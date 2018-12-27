package com.xmbestone.qss.historyview.bo;
import java.util.List;

import com.xmbestone.qss.util.Page;
import com.xmbestone.qss.historyview.po.Historyview;

public interface HistoryviewBo {
	public abstract void saveHistoryview(Historyview historyview) throws Exception;
	public abstract boolean deleteHistoryview(int userid) throws Exception;
	public abstract Historyview findByUserid(int userid)throws Exception;
	/**
	 * @return
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年10月18日  下午8:43:51
	 * @Description 描述：
	 */
	public abstract List<Historyview> listGroupbyHistroyInfoId()throws Exception;
	
	public abstract List<Historyview> listHistoryview()throws Exception;
	public abstract Page<Historyview> findUsers(Page<Historyview> page)throws Exception;
}
