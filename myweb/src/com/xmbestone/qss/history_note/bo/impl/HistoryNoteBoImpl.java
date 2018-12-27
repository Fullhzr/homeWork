/**
 *名称：
 *
 *作者：
 * 
 *下午7:49:45
 */
package com.xmbestone.qss.history_note.bo.impl;

/**
 * @author Administrator
 *
 *
 *下午7:49:45
 */
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xmbestone.qss.history_note.bo.HistoryNoteBo;
import com.xmbestone.qss.history_note.dao.HistoryNoteDao;



public class HistoryNoteBoImpl extends HibernateDaoSupport implements HistoryNoteBo {

	private HistoryNoteDao historyNoteDao;
	
	/* 
	 * 
	 */
	public HistoryNoteDao getHistoryNoteDao() {
		return historyNoteDao;
	}

	public void setHistoryNoteDao(HistoryNoteDao historyNoteDao) {
		this.historyNoteDao = historyNoteDao;
	}

	@Override
	public List<com.xmbestone.qss.history_note.po.HistoryNote> HistoryNote()
			throws Exception {
		// TODO Auto-generated method stub
//		String hql ="from HistoryNote group by history_info_id";
//		getHistoryNoteDao().find(hql);
		return historyNoteDao.getAll();
	}
 
}

