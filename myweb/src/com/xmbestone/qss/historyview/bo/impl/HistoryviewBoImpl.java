package com.xmbestone.qss.historyview.bo.impl;
import java.util.List;

import javax.websocket.Session;

import org.hibernate.SessionFactory;

import com.xmbestone.qss.historyview.bo.HistoryviewBo;
import com.xmbestone.qss.historyview.dao.HistoryviewDao;
import com.xmbestone.qss.historyview.po.Historyview;
import com.xmbestone.qss.util.Page;

public class HistoryviewBoImpl implements HistoryviewBo {

	private HistoryviewDao historyviewDao;
	private Session session;
	private SessionFactory sessionFactory;
	public HistoryviewDao getHistoryviewDao() {
		return historyviewDao;
	}

	public void setHistoryviewDao(HistoryviewDao historyviewDao) {
		this.historyviewDao = historyviewDao;
	}

	@Override
	public void saveHistoryview(Historyview historyview) throws Exception {
		int userid = historyview.getUserid();
		if(userid<0){
			historyviewDao.save(historyview);
		}else{
			historyviewDao.update(historyview);
		}
		
	}
	
	
	@Override
	public boolean deleteHistoryview(int userid) throws Exception {
		//studentDao.delete(Integer.valueOf(id));
		getHistoryviewDao().delete(userid);
		return true;
	}

	@Override
	public Historyview findByUserid(int userid) throws Exception {
		
//		String sql = "select * from student";
//		SQLQuery query=this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
//		List<Student> list2 = query.addEntity(Student.class).list();
		String hql ="from Historyview s ";
		getHistoryviewDao().find(hql);
		return historyviewDao.get(Integer.valueOf(userid));
	}
	
	@Override
	public List<Historyview> listHistoryview() throws Exception {
		
		return historyviewDao.getAll();
	}
	
	@Override
	public Page<Historyview> findUsers(Page<Historyview> page)
			throws Exception {
		String hql="from Historyview";
//		String hql="select userid  from Historyview group by history_info_id  ";
//		String hql="select count(s.history_info_id) from Historyview s group by s.history_info_id order by s.history_id desc ";
//		String hql="select count(*) from Historyview s order by s.history_id desc ";
//		String sql="select * from historyview group by history_info_id ";
		
//		session.createQuery(sql);
//		return historyviewDao.findPage(page, hql);
		return historyviewDao.findPage(page, hql);
	}
 

	/* 
	 * 
	 */
	@Override
	public List<Historyview> listGroupbyHistroyInfoId() throws Exception {
		// TODO Auto-generated method stub
		String hql ="from Historyview group by history_info_id ";
		getHistoryviewDao().find(hql);
		return historyviewDao.getAll();
	}
}
